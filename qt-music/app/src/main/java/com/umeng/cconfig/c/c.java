package com.umeng.cconfig.c;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public final class c {
    private static BlockingQueue<Runnable> d = new ArrayBlockingQueue(64);
    private static int a = 8;
    private static int b = 64;
    private static int c = 5;
    private static ThreadPoolExecutor e = new ThreadPoolExecutor(a, b, c, TimeUnit.SECONDS, d);

    public static void a(Runnable runnable) {
        e.execute(runnable);
    }
}
