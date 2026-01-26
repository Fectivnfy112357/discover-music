package com.facebook.imagepipeline.common;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: BytesRange.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0006\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0000H\u0086\u0002J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/imagepipeline/common/BytesRange;", "", "from", "", "to", "(II)V", "component1", "component2", "contains", "", "compare", "copy", "equals", "other", "hashCode", "toHttpRangeHeaderValue", "", "toString", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class BytesRange {
    public static final int TO_END_OF_CONTENT = Integer.MAX_VALUE;
    public final int from;
    public final int to;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<Pattern> headerParsingRegEx$delegate = LazyKt.lazy(new Function0<Pattern>() { // from class: com.facebook.imagepipeline.common.BytesRange$Companion$headerParsingRegEx$2
        @Override // kotlin.jvm.functions.Function0
        public final Pattern invoke() {
            return Pattern.compile("[-/ ]");
        }
    });

    public static /* synthetic */ BytesRange copy$default(BytesRange bytesRange, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bytesRange.from;
        }
        if ((i3 & 2) != 0) {
            i2 = bytesRange.to;
        }
        return bytesRange.copy(i, i2);
    }

    @JvmStatic
    public static final BytesRange from(int i) {
        return INSTANCE.from(i);
    }

    @JvmStatic
    public static final BytesRange fromContentRangeHeader(String str) throws IllegalArgumentException {
        return INSTANCE.fromContentRangeHeader(str);
    }

    @JvmStatic
    public static final BytesRange toMax(int i) {
        return INSTANCE.toMax(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getFrom() {
        return this.from;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTo() {
        return this.to;
    }

    public final BytesRange copy(int from, int to) {
        return new BytesRange(from, to);
    }

    public BytesRange(int i, int i2) {
        this.from = i;
        this.to = i2;
    }

    public final String toHttpRangeHeaderValue() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Companion companion = INSTANCE;
        String str = String.format(null, "bytes=%s-%s", Arrays.copyOf(new Object[]{companion.valueOrEmpty(this.from), companion.valueOrEmpty(this.to)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    public final boolean contains(BytesRange compare) {
        return compare != null && this.from <= compare.from && compare.to <= this.to;
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Companion companion = INSTANCE;
        String str = String.format(null, "%s-%s", Arrays.copyOf(new Object[]{companion.valueOrEmpty(this.from), companion.valueOrEmpty(this.to)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.facebook.imagepipeline.common.BytesRange");
        BytesRange bytesRange = (BytesRange) other;
        return this.from == bytesRange.from && this.to == bytesRange.to;
    }

    public int hashCode() {
        return (this.from * 31) + this.to;
    }

    /* compiled from: BytesRange.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0007J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/facebook/imagepipeline/common/BytesRange$Companion;", "", "()V", "TO_END_OF_CONTENT", "", "headerParsingRegEx", "Ljava/util/regex/Pattern;", "getHeaderParsingRegEx", "()Ljava/util/regex/Pattern;", "headerParsingRegEx$delegate", "Lkotlin/Lazy;", "from", "Lcom/facebook/imagepipeline/common/BytesRange;", "fromContentRangeHeader", "header", "", "toMax", "to", "valueOrEmpty", "n", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Pattern getHeaderParsingRegEx() {
            Object value = BytesRange.headerParsingRegEx$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-headerParsingRegEx>(...)");
            return (Pattern) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String valueOrEmpty(int n) {
            return n == Integer.MAX_VALUE ? "" : String.valueOf(n);
        }

        @JvmStatic
        public final BytesRange from(int from) {
            Preconditions.checkArgument(Boolean.valueOf(from >= 0));
            return new BytesRange(from, Integer.MAX_VALUE);
        }

        @JvmStatic
        public final BytesRange toMax(int to) {
            Preconditions.checkArgument(Boolean.valueOf(to > 0));
            return new BytesRange(0, to);
        }

        @JvmStatic
        public final BytesRange fromContentRangeHeader(String header) throws IllegalArgumentException {
            if (header == null) {
                return null;
            }
            try {
                String[] strArrSplit = getHeaderParsingRegEx().split(header);
                Preconditions.checkArgument(Boolean.valueOf(strArrSplit.length == 4));
                Preconditions.checkArgument(Boolean.valueOf(Intrinsics.areEqual(strArrSplit[0], "bytes")));
                String str = strArrSplit[1];
                Intrinsics.checkNotNullExpressionValue(str, "headerParts[1]");
                int i = Integer.parseInt(str);
                String str2 = strArrSplit[2];
                Intrinsics.checkNotNullExpressionValue(str2, "headerParts[2]");
                int i2 = Integer.parseInt(str2);
                String str3 = strArrSplit[3];
                Intrinsics.checkNotNullExpressionValue(str3, "headerParts[3]");
                int i3 = Integer.parseInt(str3);
                Preconditions.checkArgument(Boolean.valueOf(i2 > i));
                Preconditions.checkArgument(Boolean.valueOf(i3 > i2));
                if (i2 < i3 - 1) {
                    return new BytesRange(i, i2);
                }
                return new BytesRange(i, Integer.MAX_VALUE);
            } catch (IllegalArgumentException e) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str4 = String.format(null, "Invalid Content-Range header value: \"%s\"", Arrays.copyOf(new Object[]{header}, 1));
                Intrinsics.checkNotNullExpressionValue(str4, "format(locale, format, *args)");
                throw new IllegalArgumentException(str4, e);
            }
        }
    }
}
