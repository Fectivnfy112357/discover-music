package com.facebook.systrace;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystraceMessage.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\f\r\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/systrace/SystraceMessage;", "", "()V", "INCLUDE_ARGS", "", "beginSection", "Lcom/facebook/systrace/SystraceMessage$Builder;", "tag", "", "sectionName", "", "endSection", "Builder", "EndSectionBuilder", "StartSectionBuilder", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SystraceMessage {
    public static boolean INCLUDE_ARGS;
    public static final SystraceMessage INSTANCE = new SystraceMessage();

    /* compiled from: SystraceMessage.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H&J\u0018\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH&J\u0018\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/facebook/systrace/SystraceMessage$Builder;", "", "()V", "arg", "key", "", "value", "", "", "", "flush", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Builder {
        public abstract Builder arg(String key, double value);

        public abstract Builder arg(String key, int value);

        public abstract Builder arg(String key, long value);

        public abstract Builder arg(String key, Object value);

        public abstract void flush();
    }

    private SystraceMessage() {
    }

    @JvmStatic
    public static final Builder beginSection(long tag, String sectionName) {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        return new StartSectionBuilder(tag, sectionName);
    }

    @JvmStatic
    public static final Builder endSection(long tag) {
        return new EndSectionBuilder(tag);
    }

    /* compiled from: SystraceMessage.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0002J\u0018\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000eH\u0016J\u0018\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000fH\u0016J\u0018\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0010H\u0016J\u0018\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/systrace/SystraceMessage$StartSectionBuilder;", "Lcom/facebook/systrace/SystraceMessage$Builder;", "tag", "", "sectionName", "", "(JLjava/lang/String;)V", "args", "", "addArg", "", "key", "value", "arg", "", "", "", "flush", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class StartSectionBuilder extends Builder {
        private final List<String> args;
        private final String sectionName;
        private final long tag;

        public StartSectionBuilder(long j, String sectionName) {
            Intrinsics.checkNotNullParameter(sectionName, "sectionName");
            this.tag = j;
            this.sectionName = sectionName;
            this.args = new ArrayList();
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
            String str;
            long j = this.tag;
            String str2 = this.sectionName;
            if (SystraceMessage.INCLUDE_ARGS && !this.args.isEmpty()) {
                str = " (" + UByte$$ExternalSyntheticBackport0.m(", ", this.args) + ")";
            } else {
                str = "";
            }
            Systrace.beginSection(j, str2 + str);
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, Object value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            addArg(key, value.toString());
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, int value) {
            Intrinsics.checkNotNullParameter(key, "key");
            addArg(key, String.valueOf(value));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, long value) {
            Intrinsics.checkNotNullParameter(key, "key");
            addArg(key, String.valueOf(value));
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, double value) {
            Intrinsics.checkNotNullParameter(key, "key");
            addArg(key, String.valueOf(value));
            return this;
        }

        private final void addArg(String key, String value) {
            this.args.add(key + ": " + value);
        }
    }

    /* compiled from: SystraceMessage.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH\u0016J\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0016J\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/systrace/SystraceMessage$EndSectionBuilder;", "Lcom/facebook/systrace/SystraceMessage$Builder;", "tag", "", "(J)V", "arg", "key", "", "value", "", "", "", "flush", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class EndSectionBuilder extends Builder {
        private final long tag;

        public EndSectionBuilder(long j) {
            this.tag = j;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
            Systrace.endSection(this.tag);
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, Object value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, int value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, long value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String key, double value) {
            Intrinsics.checkNotNullParameter(key, "key");
            return this;
        }
    }
}
