package com.localmediametadata.media3;

import android.net.Uri;
import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.exoplayer.MetadataRetriever;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.id3.ApicFrame;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.localmediametadata.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import org.jaudiotagger.tag.id3.valuepair.ImageFormats;

/* loaded from: classes3.dex */
public class MetadataMedia3 {

    private static class ThreadPerTaskExecutor implements Executor {
        private ThreadPerTaskExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String writePic(ReactApplicationContext reactApplicationContext, String str, String str2, String str3, byte[] bArr) throws IOException {
        File file = new File(str2);
        if (!file.exists() && !file.mkdirs()) {
            return "";
        }
        DocumentFile documentFileFromSingleUri = DocumentFile.fromSingleUri(reactApplicationContext, Uri.parse(str));
        String formatForMimeType = ImageFormats.getFormatForMimeType(str3);
        File file2 = new File(str2, Utils.getName(documentFileFromSingleUri.getName()) + "." + (formatForMimeType == null ? "jpg" : formatForMimeType.toLowerCase()));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                return file2.getPath();
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void readPic(final ReactApplicationContext reactApplicationContext, final String str, final String str2, final Promise promise) {
        Futures.addCallback(MetadataRetriever.retrieveMetadata(reactApplicationContext, MediaItem.fromUri(str)), new FutureCallback<TrackGroupArray>() { // from class: com.localmediametadata.media3.MetadataMedia3.1
            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(TrackGroupArray trackGroupArray) {
                for (int i = 0; i < trackGroupArray.length; i++) {
                    Metadata metadata = trackGroupArray.get(i).getFormat(0).metadata;
                    if (metadata != null) {
                        for (int i2 = 0; i2 < metadata.length(); i2++) {
                            Metadata.Entry entry = metadata.get(i2);
                            if (entry instanceof ApicFrame) {
                                ApicFrame apicFrame = (ApicFrame) entry;
                                if ("APIC".equals(apicFrame.id) && apicFrame.pictureType == 3) {
                                    promise.resolve(MetadataMedia3.writePic(reactApplicationContext, str, str2, apicFrame.mimeType, apicFrame.pictureData));
                                    return;
                                }
                            } else if (entry instanceof PictureFrame) {
                                PictureFrame pictureFrame = (PictureFrame) entry;
                                if (pictureFrame.pictureType == 3) {
                                    promise.resolve(MetadataMedia3.writePic(reactApplicationContext, str, str2, pictureFrame.mimeType, pictureFrame.pictureData));
                                    return;
                                }
                            } else {
                                continue;
                            }
                        }
                        promise.resolve("");
                    }
                }
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                Log.d(Utils.LOG, "error: " + th);
                promise.reject("-1", "read failed: " + th.getMessage());
            }
        }, new ThreadPerTaskExecutor());
    }
}
