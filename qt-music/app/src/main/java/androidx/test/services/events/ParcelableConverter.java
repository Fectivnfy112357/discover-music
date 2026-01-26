package androidx.test.services.events;

import android.util.Log;
import androidx.test.services.events.internal.StackTrimmer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

/* loaded from: classes.dex */
public final class ParcelableConverter {
    private static final String TAG = "ParcelableConverter";

    private ParcelableConverter() {
    }

    public static TestCaseInfo getTestCaseFromDescription(Description description) throws TestEventException {
        List<AnnotationInfo> listEmptyList;
        if (!isValidJUnitDescription(description)) {
            String strValueOf = String.valueOf(description);
            throw new TestEventException(new StringBuilder(String.valueOf(strValueOf).length() + 33).append("Unexpected description instance: ").append(strValueOf).toString());
        }
        List<AnnotationInfo> annotationsFromCollection = getAnnotationsFromCollection(description.getAnnotations());
        if (description.getTestClass() != null) {
            listEmptyList = getAnnotationsFromArray(description.getTestClass().getAnnotations());
        } else {
            listEmptyList = Collections.emptyList();
        }
        return new TestCaseInfo(description.getClassName(), description.getMethodName() != null ? description.getMethodName() : "", annotationsFromCollection, listEmptyList);
    }

    public static boolean isValidJUnitDescription(Description description) {
        return (description.equals(Description.EMPTY) || description.equals(Description.TEST_MECHANISM)) ? false : true;
    }

    public static List<AnnotationInfo> getAnnotationsFromArray(Annotation[] annotations) {
        ArrayList arrayList = new ArrayList(annotations.length);
        for (Annotation annotation : annotations) {
            arrayList.add(getAnnotation(annotation));
        }
        return arrayList;
    }

    public static List<AnnotationInfo> getAnnotationsFromCollection(Collection<Annotation> annotations) {
        ArrayList arrayList = new ArrayList(annotations.size());
        Iterator<Annotation> it = annotations.iterator();
        while (it.hasNext()) {
            arrayList.add(getAnnotation(it.next()));
        }
        return arrayList;
    }

    public static FailureInfo getFailure(Failure junitFailure) throws TestEventException {
        return new FailureInfo(junitFailure.getMessage(), junitFailure.getTestHeader(), StackTrimmer.getTrimmedStackTrace(junitFailure), getTestCaseFromDescription(junitFailure.getDescription()));
    }

    public static List<FailureInfo> getFailuresFromList(List<Failure> failures) throws TestEventException {
        ArrayList arrayList = new ArrayList();
        Iterator<Failure> it = failures.iterator();
        while (it.hasNext()) {
            arrayList.add(getFailure(it.next()));
        }
        return arrayList;
    }

    public static AnnotationInfo getAnnotation(Annotation javaAnnotation) throws SecurityException {
        ArrayList arrayList = new ArrayList();
        for (Method method : javaAnnotation.annotationType().getDeclaredMethods()) {
            arrayList.add(getAnnotationValue(javaAnnotation, method));
        }
        return new AnnotationInfo(javaAnnotation.annotationType().getName(), arrayList);
    }

    private static AnnotationValue getAnnotationValue(Annotation javaAnnotation, Method annotationField) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List arrayList;
        String name = annotationField.getName();
        String fieldValuesType = "NULL";
        try {
            Object objInvoke = annotationField.invoke(javaAnnotation, null);
            fieldValuesType = getFieldValuesType(objInvoke);
            arrayList = getArrayElements(objInvoke);
        } catch (Exception e) {
            String strValueOf = String.valueOf(javaAnnotation);
            Log.e(TAG, new StringBuilder(String.valueOf(name).length() + 48 + String.valueOf(strValueOf).length()).append("Unable to get annotation values for field '").append(name).append("': [").append(strValueOf).append("]").toString(), e);
            arrayList = new ArrayList();
        }
        return new AnnotationValue(name, arrayList, fieldValuesType);
    }

    private static String getFieldValuesType(Object fieldValues) {
        return fieldValues.getClass().getSimpleName().replace("[", "").replace("]", "");
    }

    static List<String> getArrayElements(Object obj) {
        ArrayList arrayList = new ArrayList();
        if (obj == null) {
            arrayList.add("<null>");
        } else if (obj.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(obj); i++) {
                arrayList.add(Array.get(obj, i).toString());
            }
        } else if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toString());
            }
        } else {
            arrayList.add(obj.toString());
        }
        return arrayList;
    }
}
