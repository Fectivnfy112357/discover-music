package androidx.test.orchestrator.listeners;

import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import androidx.test.orchestrator.junit.BundleJUnitUtils;
import androidx.test.orchestrator.junit.ParcelableDescription;
import androidx.test.orchestrator.junit.ParcelableFailure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class OrchestrationListenerManager {
    public static final String KEY_TEST_EVENT = "TestEvent";
    private static final String TAG = "ListenerManager";
    private final Instrumentation instrumentation;
    private ParcelableDescription lastDescription;
    private final List<OrchestrationRunListener> listeners = new ArrayList();
    private boolean markTerminationAsFailure = false;

    public enum TestEvent {
        TEST_RUN_STARTED,
        TEST_RUN_FINISHED,
        TEST_STARTED,
        TEST_FINISHED,
        TEST_FAILURE,
        TEST_ASSUMPTION_FAILURE,
        TEST_IGNORED
    }

    public OrchestrationListenerManager(Instrumentation instrumentation) {
        if (instrumentation == null) {
            throw new IllegalArgumentException("Instrumentation must not be null");
        }
        this.instrumentation = instrumentation;
    }

    public void addListener(OrchestrationRunListener listener) {
        listener.setInstrumentation(this.instrumentation);
        this.listeners.add(listener);
    }

    public void orchestrationRunStarted(int testCount) {
        Iterator<OrchestrationRunListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().orchestrationRunStarted(testCount);
        }
    }

    public void testProcessStarted(ParcelableDescription description) {
        this.lastDescription = description;
        this.markTerminationAsFailure = true;
    }

    public void testProcessFinished(String outputFile) {
        if (this.markTerminationAsFailure) {
            for (OrchestrationRunListener orchestrationRunListener : this.listeners) {
                orchestrationRunListener.testFailure(new ParcelableFailure(this.lastDescription, new Throwable(new StringBuilder(String.valueOf(outputFile).length() + 56).append("Test instrumentation process crashed. Check ").append(outputFile).append(" for details").toString())));
                orchestrationRunListener.testFinished(this.lastDescription);
            }
        }
    }

    public void handleNotification(Bundle bundle) {
        bundle.setClassLoader(getClass().getClassLoader());
        cacheStatus(bundle);
        Iterator<OrchestrationRunListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            handleNotificationForListener(it.next(), bundle);
        }
    }

    private void cacheStatus(Bundle bundle) {
        if (BundleJUnitUtils.getDescription(bundle) != null) {
            this.lastDescription = BundleJUnitUtils.getDescription(bundle);
        }
        int i = AnonymousClass1.$SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.valueOf(bundle.getString(KEY_TEST_EVENT)).ordinal()];
        if (i == 1) {
            this.markTerminationAsFailure = true;
        } else if (i == 2) {
            this.markTerminationAsFailure = false;
        } else {
            if (i != 3) {
                return;
            }
            this.markTerminationAsFailure = false;
        }
    }

    /* renamed from: androidx.test.orchestrator.listeners.OrchestrationListenerManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent;

        static {
            int[] iArr = new int[TestEvent.values().length];
            $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent = iArr;
            try {
                iArr[TestEvent.TEST_RUN_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.TEST_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.TEST_RUN_FINISHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.TEST_STARTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.TEST_FINISHED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.TEST_ASSUMPTION_FAILURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.TEST_IGNORED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private void handleNotificationForListener(OrchestrationRunListener listener, Bundle bundle) {
        switch (AnonymousClass1.$SwitchMap$androidx$test$orchestrator$listeners$OrchestrationListenerManager$TestEvent[TestEvent.valueOf(bundle.getString(KEY_TEST_EVENT)).ordinal()]) {
            case 1:
                listener.testRunStarted(BundleJUnitUtils.getDescription(bundle));
                break;
            case 2:
                listener.testFailure(BundleJUnitUtils.getFailure(bundle));
                break;
            case 3:
                listener.testRunFinished(BundleJUnitUtils.getResult(bundle));
                break;
            case 4:
                listener.testStarted(BundleJUnitUtils.getDescription(bundle));
                break;
            case 5:
                listener.testFinished(BundleJUnitUtils.getDescription(bundle));
                break;
            case 6:
                listener.testAssumptionFailure(BundleJUnitUtils.getFailure(bundle));
                break;
            case 7:
                listener.testIgnored(BundleJUnitUtils.getDescription(bundle));
                break;
            default:
                Log.e(TAG, "Unknown notification type");
                break;
        }
    }
}
