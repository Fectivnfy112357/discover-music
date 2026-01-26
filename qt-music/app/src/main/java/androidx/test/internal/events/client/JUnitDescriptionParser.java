package androidx.test.internal.events.client;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Description;

/* loaded from: classes.dex */
final class JUnitDescriptionParser {
    private JUnitDescriptionParser() {
    }

    public static List<Description> getAllTestCaseDescriptions(Description origin) {
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(origin);
        while (!arrayDeque.isEmpty()) {
            Description description = (Description) arrayDeque.pop();
            arrayDeque.addAll(description.getChildren());
            if (description.isTest()) {
                arrayList.add(description);
            }
        }
        return arrayList;
    }
}
