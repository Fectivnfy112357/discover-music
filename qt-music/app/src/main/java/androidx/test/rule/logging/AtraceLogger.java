package androidx.test.rule.logging;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class AtraceLogger {
    private static final String ATRACEHELPER_TAG = "AtraceLogger";
    private static final String ATRACE_DUMP = "atrace --async_dump -b %d -z %s";
    private static final String ATRACE_START = "atrace --async_start -b %d -c %s";
    private static final String ATRACE_STOP = "atrace --async_stop -b %d -z %s";
    private static final int BUFFER_SIZE = 8192;
    private static final String CATEGORY_SEPARATOR = " ";
    private static volatile AtraceLogger loggerInstance;
    private List<ByteArrayOutputStream> atraceDataList;
    private boolean atraceRunning = false;
    private File destAtraceDirectory;
    private IOException dumpIOException;
    private Thread dumpThread;
    private String traceFileName;
    private UiAutomation uiAutomation;

    private AtraceLogger(Instrumentation instrumentation) {
        this.uiAutomation = instrumentation.getUiAutomation();
    }

    public static AtraceLogger getAtraceLoggerInstance(Instrumentation instrumentation) {
        if (loggerInstance == null) {
            synchronized (AtraceLogger.class) {
                if (loggerInstance == null) {
                    loggerInstance = new AtraceLogger(instrumentation);
                }
            }
        }
        return loggerInstance;
    }

    public void atraceStart(Set<String> traceCategoriesSet, int atraceBufferSize, int dumpIntervalSecs, File destDirectory, String traceFileName) throws IOException {
        if (this.atraceRunning) {
            throw new IllegalStateException("Attempted multiple atrace start");
        }
        if (traceCategoriesSet.isEmpty()) {
            throw new IllegalArgumentException("Empty categories. Should contain atleast one category");
        }
        if (destDirectory == null) {
            throw new IllegalArgumentException("Destination directory cannot be null");
        }
        if (!destDirectory.exists() && !destDirectory.mkdirs()) {
            throw new IOException("Unable to create the destination directory");
        }
        this.destAtraceDirectory = destDirectory;
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = traceCategoriesSet.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next()).append(CATEGORY_SEPARATOR);
        }
        if (traceFileName != null && !traceFileName.isEmpty()) {
            this.traceFileName = traceFileName;
        }
        String str = String.format(ATRACE_START, Integer.valueOf(atraceBufferSize), stringBuffer.toString());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeDataToByteStream(this.uiAutomation.executeShellCommand(str), byteArrayOutputStream);
            byteArrayOutputStream.close();
            this.atraceRunning = true;
            this.dumpIOException = null;
            this.atraceDataList = new ArrayList();
            Thread thread = new Thread(new DumpTraceRunnable(stringBuffer.toString(), atraceBufferSize, dumpIntervalSecs));
            this.dumpThread = thread;
            thread.start();
        } catch (Throwable th) {
            byteArrayOutputStream.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeDataToByteStream(ParcelFileDescriptor pfDescriptor, ByteArrayOutputStream outputStream) throws IOException {
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(pfDescriptor);
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = autoCloseInputStream.read(bArr);
                if (i < 0) {
                    return;
                } else {
                    outputStream.write(bArr, 0, i);
                }
            }
        } finally {
            autoCloseInputStream.close();
        }
    }

    public void atraceStop() throws InterruptedException, IOException {
        if (!this.atraceRunning) {
            throw new IllegalStateException("ATrace is not running currently. Start atrace beforestopping.");
        }
        try {
            this.dumpThread.interrupt();
            this.dumpThread.join();
            IOException iOException = this.dumpIOException;
            if (iOException != null) {
                throw iOException;
            }
            atraceWrite();
        } finally {
            Iterator<ByteArrayOutputStream> it = this.atraceDataList.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.atraceRunning = false;
            this.traceFileName = null;
        }
    }

    private void atraceWrite() throws IOException {
        File file;
        int i = 0;
        for (ByteArrayOutputStream byteArrayOutputStream : this.atraceDataList) {
            if (this.traceFileName != null) {
                file = new File(this.destAtraceDirectory, String.format("%s-atrace-%d.txt", this.traceFileName, Integer.valueOf(i)));
            } else {
                file = new File(this.destAtraceDirectory, String.format("atrace-%d.txt", Integer.valueOf(i)));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
                fileOutputStream.close();
                i++;
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        }
    }

    private class DumpTraceRunnable implements Runnable {
        private int bufferSize;
        private int dumpIntervalInSecs;
        private String traceCategories;

        DumpTraceRunnable(String traceCategories, int bufferSize, int dumpIntervalInSecs) {
            this.traceCategories = traceCategories;
            this.bufferSize = bufferSize;
            this.dumpIntervalInSecs = dumpIntervalInSecs;
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    try {
                        Thread.sleep(this.dumpIntervalInSecs * 1000);
                        String str = String.format(AtraceLogger.ATRACE_DUMP, Integer.valueOf(this.bufferSize), this.traceCategories);
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        AtraceLogger atraceLogger = AtraceLogger.this;
                        atraceLogger.writeDataToByteStream(atraceLogger.uiAutomation.executeShellCommand(str), byteArrayOutputStream);
                        AtraceLogger.this.atraceDataList.add(byteArrayOutputStream);
                        Log.i(AtraceLogger.ATRACEHELPER_TAG, new StringBuilder(54).append("Time taken by - DumpTraceRunnable ").append(System.currentTimeMillis() - jCurrentTimeMillis).toString());
                    } catch (InterruptedException unused) {
                    }
                } catch (IOException e) {
                    AtraceLogger.this.dumpIOException = e;
                    return;
                }
            }
            String str2 = String.format(AtraceLogger.ATRACE_STOP, Integer.valueOf(this.bufferSize), this.traceCategories);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            AtraceLogger atraceLogger2 = AtraceLogger.this;
            atraceLogger2.writeDataToByteStream(atraceLogger2.uiAutomation.executeShellCommand(str2), byteArrayOutputStream2);
            AtraceLogger.this.atraceDataList.add(byteArrayOutputStream2);
        }
    }
}
