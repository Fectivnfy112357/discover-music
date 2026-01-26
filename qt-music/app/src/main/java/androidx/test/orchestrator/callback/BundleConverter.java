package androidx.test.orchestrator.callback;

import android.os.Bundle;
import androidx.test.internal.events.client.TestEventClientException;
import androidx.test.orchestrator.junit.ParcelableDescription;
import androidx.test.orchestrator.junit.ParcelableFailure;
import androidx.test.orchestrator.junit.ParcelableResult;
import androidx.test.orchestrator.listeners.OrchestrationListenerManager;
import androidx.test.services.events.FailureInfo;
import androidx.test.services.events.run.TestAssumptionFailureEvent;
import androidx.test.services.events.run.TestFailureEvent;
import androidx.test.services.events.run.TestFinishedEvent;
import androidx.test.services.events.run.TestIgnoredEvent;
import androidx.test.services.events.run.TestRunEvent;
import androidx.test.services.events.run.TestRunEventWithTestCase;
import androidx.test.services.events.run.TestRunFinishedEvent;
import androidx.test.services.events.run.TestRunStartedEvent;
import androidx.test.services.events.run.TestStartedEvent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class BundleConverter {
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_FAILURE = "failure";
    private static final String KEY_RESULT = "result";

    private BundleConverter() {
    }

    public static Bundle getBundleFromTestRunEvent(TestRunEvent event) throws TestEventClientException {
        if (event instanceof TestAssumptionFailureEvent) {
            return getBundleFromFailureEvent((TestAssumptionFailureEvent) event, OrchestrationListenerManager.TestEvent.TEST_ASSUMPTION_FAILURE);
        }
        if (event instanceof TestFailureEvent) {
            return getBundleFromFailureEvent((TestFailureEvent) event, OrchestrationListenerManager.TestEvent.TEST_FAILURE);
        }
        if (event instanceof TestFinishedEvent) {
            return getBundleFromTestCaseEvent((TestFinishedEvent) event, OrchestrationListenerManager.TestEvent.TEST_FINISHED);
        }
        if (event instanceof TestIgnoredEvent) {
            return getBundleFromTestCaseEvent((TestIgnoredEvent) event, OrchestrationListenerManager.TestEvent.TEST_IGNORED);
        }
        if (event instanceof TestRunFinishedEvent) {
            return getBundleFromTestRunFinishedEvent((TestRunFinishedEvent) event);
        }
        if (event instanceof TestRunStartedEvent) {
            return getBundleFromTestCaseEvent((TestRunStartedEvent) event, OrchestrationListenerManager.TestEvent.TEST_RUN_STARTED);
        }
        if (event instanceof TestStartedEvent) {
            return getBundleFromTestCaseEvent((TestStartedEvent) event, OrchestrationListenerManager.TestEvent.TEST_STARTED);
        }
        String strValueOf = String.valueOf(event);
        throw new TestEventClientException(new StringBuilder(String.valueOf(strValueOf).length() + 35).append("Unrecognized test run event type [").append(strValueOf).append("]").toString());
    }

    private static Bundle getBundleFromFailureEvent(TestFailureEvent event, OrchestrationListenerManager.TestEvent testFailureEventType) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_FAILURE, new ParcelableFailure(new ParcelableDescription(event.testCase.getClassAndMethodName()), event.failure.stackTrace));
        bundle.putString(OrchestrationListenerManager.KEY_TEST_EVENT, testFailureEventType.name());
        return bundle;
    }

    private static Bundle getBundleFromTestCaseEvent(TestRunEventWithTestCase event, OrchestrationListenerManager.TestEvent testEventType) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DESCRIPTION, new ParcelableDescription(event.testCase.getClassAndMethodName()));
        bundle.putString(OrchestrationListenerManager.KEY_TEST_EVENT, testEventType.name());
        return bundle;
    }

    private static Bundle getBundleFromTestRunFinishedEvent(TestRunFinishedEvent event) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_RESULT, new ParcelableResult(getParcelableFailureFromList(event.failures)));
        bundle.putString(OrchestrationListenerManager.KEY_TEST_EVENT, OrchestrationListenerManager.TestEvent.TEST_RUN_FINISHED.name());
        return bundle;
    }

    private static List<ParcelableFailure> getParcelableFailureFromList(List<FailureInfo> failures) {
        ArrayList arrayList = new ArrayList();
        for (FailureInfo failureInfo : failures) {
            arrayList.add(new ParcelableFailure(new ParcelableDescription(failureInfo.testCase.getClassAndMethodName()), failureInfo.stackTrace));
        }
        return arrayList;
    }
}
