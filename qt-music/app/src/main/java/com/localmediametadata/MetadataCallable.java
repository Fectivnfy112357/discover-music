package com.localmediametadata;

import android.os.Bundle;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.concurrent.Callable;

/* loaded from: classes3.dex */
public class MetadataCallable {

    public static class ReadMetadata implements Callable<Object> {
        private final ReactApplicationContext context;
        private final String filePath;

        public ReadMetadata(ReactApplicationContext reactApplicationContext, String str) {
            this.context = reactApplicationContext;
            this.filePath = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call, reason: merged with bridge method [inline-methods] */
        public Object call2() {
            try {
                return Metadata.readMetadata(this.context, this.filePath);
            } catch (Exception e) {
                Log.e("ReadMetadata", "Read Metadata Error:");
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class WriteMetadata implements Callable<Object> {
        private final ReactApplicationContext context;
        private final String filePath;
        private final boolean isOverwrite;
        private final Bundle metadata;

        public WriteMetadata(ReactApplicationContext reactApplicationContext, String str, Bundle bundle, boolean z) {
            this.context = reactApplicationContext;
            this.filePath = str;
            this.metadata = bundle;
            this.isOverwrite = z;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            Metadata.writeMetadata(this.context, this.filePath, this.metadata, this.isOverwrite);
            return null;
        }
    }

    public static class ReadPic implements Callable<Object> {
        private final ReactApplicationContext context;
        private final String filePath;
        private final String picDir;

        public ReadPic(ReactApplicationContext reactApplicationContext, String str, String str2) {
            this.context = reactApplicationContext;
            this.filePath = str;
            this.picDir = str2;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            try {
                return Metadata.readPic(this.context, this.filePath, this.picDir);
            } catch (Exception e) {
                Log.e("ReadMetadata", "Read Pic Error:");
                e.printStackTrace();
                return "";
            }
        }
    }

    public static class WritePic implements Callable<Object> {
        private final ReactApplicationContext context;
        private final String filePath;
        private final String picPath;

        public WritePic(ReactApplicationContext reactApplicationContext, String str, String str2) {
            this.context = reactApplicationContext;
            this.filePath = str;
            this.picPath = str2;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            Metadata.writePic(this.context, this.filePath, this.picPath);
            return null;
        }
    }

    public static class ReadLyric implements Callable<Object> {
        private final ReactApplicationContext context;
        private final String filePath;
        private final boolean isReadLrcFile;

        public ReadLyric(ReactApplicationContext reactApplicationContext, String str, boolean z) {
            this.context = reactApplicationContext;
            this.filePath = str;
            this.isReadLrcFile = z;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            try {
                return Metadata.readLyric(this.context, this.filePath, this.isReadLrcFile);
            } catch (Exception e) {
                Log.e("ReadMetadata", "Read Lyric Error: ");
                e.printStackTrace();
                return "";
            }
        }
    }

    public static class WriteLyric implements Callable<Object> {
        private final ReactApplicationContext context;
        private final String filePath;
        private final String lyric;

        public WriteLyric(ReactApplicationContext reactApplicationContext, String str, String str2) {
            this.context = reactApplicationContext;
            this.filePath = str;
            this.lyric = str2;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            Metadata.writeLyric(this.context, this.filePath, this.lyric);
            return null;
        }
    }
}
