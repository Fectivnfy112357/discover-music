package com.localmediametadata;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class AsyncTask {

    /* JADX INFO: Access modifiers changed from: private */
    static class TaskRunner {
        private final ExecutorService executor;
        private final Handler handler;

        public interface Callback<Object> {
            void onComplete(Object object);
        }

        private TaskRunner() {
            this.executor = Executors.newSingleThreadExecutor();
            this.handler = new Handler(Looper.getMainLooper());
        }

        public <Object> void executeAsync(final Callable<Object> callable, final Callback<Object> callback) {
            this.executor.execute(new Runnable() { // from class: com.localmediametadata.AsyncTask$TaskRunner$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws Exception {
                    this.f$0.lambda$executeAsync$2(callable, callback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$executeAsync$2(Callable callable, final Callback callback) throws Exception {
            try {
                final Object objCall = callable.call();
                this.handler.post(new Runnable() { // from class: com.localmediametadata.AsyncTask$TaskRunner$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback.onComplete(objCall);
                    }
                });
            } catch (Exception e) {
                this.handler.post(new Runnable() { // from class: com.localmediametadata.AsyncTask$TaskRunner$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        callback.onComplete(e);
                    }
                });
                Log.e("TaskRunner", "execute error:");
                e.printStackTrace();
            }
        }

        public void shutdown() {
            this.executor.shutdown();
        }
    }

    public static void runTask(Callable<Object> callable, final Promise promise) {
        final TaskRunner taskRunner = new TaskRunner();
        try {
            taskRunner.executeAsync(callable, new TaskRunner.Callback() { // from class: com.localmediametadata.AsyncTask$$ExternalSyntheticLambda0
                @Override // com.localmediametadata.AsyncTask.TaskRunner.Callback
                public final void onComplete(Object obj) {
                    AsyncTask.lambda$runTask$0(taskRunner, promise, obj);
                }
            });
        } catch (Exception e) {
            promise.reject("-1", e.getMessage());
        }
    }

    static /* synthetic */ void lambda$runTask$0(TaskRunner taskRunner, Promise promise, Object obj) {
        taskRunner.shutdown();
        if (obj instanceof Exception) {
            promise.reject("-1", ((Exception) obj).getMessage());
        } else {
            promise.resolve(obj);
        }
    }
}
