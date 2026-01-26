package androidx.test.internal.util;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ReflectionUtil {
    private static final String TAG = "ReflectionUtil";

    public static void reflectivelyInvokeRemoteMethod(final String className, final String methodName) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Checks.checkNotNull(className);
        Checks.checkNotNull(methodName);
        String strValueOf = String.valueOf(methodName);
        Log.i(TAG, strValueOf.length() != 0 ? "Attempting to reflectively call: ".concat(strValueOf) : new String("Attempting to reflectively call: "));
        try {
            Method declaredMethod = Class.forName(className).getDeclaredMethod(methodName, new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(TAG, "Reflective call failed: ", e);
        }
    }
}
