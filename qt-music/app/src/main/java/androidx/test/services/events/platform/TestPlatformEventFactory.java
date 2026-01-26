package androidx.test.services.events.platform;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes.dex */
public final class TestPlatformEventFactory implements Parcelable.Creator<TestPlatformEvent> {

    /* renamed from: androidx.test.services.events.platform.TestPlatformEventFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType;

        static {
            int[] iArr = new int[TestPlatformEvent.EventType.values().length];
            $SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType = iArr;
            try {
                iArr[TestPlatformEvent.EventType.TEST_RUN_STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType[TestPlatformEvent.EventType.TEST_RUN_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType[TestPlatformEvent.EventType.TEST_CASE_STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType[TestPlatformEvent.EventType.TEST_CASE_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType[TestPlatformEvent.EventType.TEST_CASE_FINISHED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType[TestPlatformEvent.EventType.TEST_RUN_FINISHED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public TestPlatformEvent createFromParcel(Parcel source) {
        TestPlatformEvent.EventType eventTypeValueOf = TestPlatformEvent.EventType.valueOf(source.readString());
        switch (AnonymousClass1.$SwitchMap$androidx$test$services$events$platform$TestPlatformEvent$EventType[eventTypeValueOf.ordinal()]) {
            case 1:
                return new TestRunStartedEvent(source);
            case 2:
                return new TestRunErrorEvent(source);
            case 3:
                return new TestCaseStartedEvent(source);
            case 4:
                return new TestCaseErrorEvent(source);
            case 5:
                return new TestCaseFinishedEvent(source);
            case 6:
                return new TestRunFinishedEvent(source);
            default:
                String strValueOf = String.valueOf(eventTypeValueOf);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(strValueOf).length() + 22).append("Unhandled event type: ").append(strValueOf).toString());
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public TestPlatformEvent[] newArray(int size) {
        return new TestPlatformEvent[size];
    }
}
