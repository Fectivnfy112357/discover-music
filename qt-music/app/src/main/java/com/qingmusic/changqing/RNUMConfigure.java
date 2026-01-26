package com.qingmusic.changqing;

import android.content.Context;
import com.umeng.commonsdk.UMConfigure;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class RNUMConfigure {
    public static void init(Context context, String str, String str2, int i, String str3) throws IllegalAccessException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        initRN("react-native", "2.0");
        UMConfigure.init(context, str, str2, i, str3);
    }

    private static void initRN(String str, String str2) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Method declaredMethod = Class.forName("com.umeng.commonsdk.UMConfigure").getDeclaredMethod("setWraperType", String.class, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, str, str2);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
