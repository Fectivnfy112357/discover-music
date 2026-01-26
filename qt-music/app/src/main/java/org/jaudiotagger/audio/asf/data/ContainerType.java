package org.jaudiotagger.audio.asf.data;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.jaudiotagger.audio.asf.util.Utils;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public enum ContainerType {
    CONTENT_BRANDING(GUID.GUID_CONTENT_BRANDING, 32, false, false, false, false),
    CONTENT_DESCRIPTION(GUID.GUID_CONTENTDESCRIPTION, 16, false, false, false, false),
    EXTENDED_CONTENT(GUID.GUID_EXTENDED_CONTENT_DESCRIPTION, 16, false, false, false, false),
    METADATA_LIBRARY_OBJECT(GUID.GUID_METADATA_LIBRARY, 32, true, true, true, true),
    METADATA_OBJECT(GUID.GUID_METADATA, 16, false, true, false, true);

    private final GUID containerGUID;
    private final boolean guidEnabled;
    private final boolean languageEnabled;
    private final BigInteger maximumDataLength;
    private final boolean multiValued;
    private final long perfMaxDataLen;
    private final boolean streamEnabled;

    public static boolean areInCorrectOrder(ContainerType containerType, ContainerType containerType2) {
        List listAsList = Arrays.asList(getOrdered());
        return listAsList.indexOf(containerType) <= listAsList.indexOf(containerType2);
    }

    public static ContainerType[] getOrdered() {
        return new ContainerType[]{CONTENT_DESCRIPTION, CONTENT_BRANDING, EXTENDED_CONTENT, METADATA_OBJECT, METADATA_LIBRARY_OBJECT};
    }

    ContainerType(GUID guid, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this.containerGUID = guid;
        BigInteger bigIntegerSubtract = BigInteger.valueOf(2L).pow(i).subtract(BigInteger.ONE);
        this.maximumDataLength = bigIntegerSubtract;
        if (bigIntegerSubtract.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0) {
            this.perfMaxDataLen = bigIntegerSubtract.longValue();
        } else {
            this.perfMaxDataLen = -1L;
        }
        this.guidEnabled = z;
        this.streamEnabled = z2;
        this.languageEnabled = z3;
        this.multiValued = z4;
    }

    public void assertConstraints(String str, byte[] bArr, int i, int i2, int i3) {
        RuntimeException runtimeExceptionCheckConstraints = checkConstraints(str, bArr, i, i2, i3);
        if (runtimeExceptionCheckConstraints != null) {
            throw runtimeExceptionCheckConstraints;
        }
    }

    public RuntimeException checkConstraints(String str, byte[] bArr, int i, int i2, int i3) {
        IllegalArgumentException illegalArgumentException;
        if (str == null || bArr == null) {
            illegalArgumentException = new IllegalArgumentException("Arguments must not be null.");
        } else {
            illegalArgumentException = !Utils.isStringLengthValidNullSafe(str) ? new IllegalArgumentException(ErrorMessage.WMA_LENGTH_OF_STRING_IS_TOO_LARGE.getMsg(Integer.valueOf(str.length()))) : null;
        }
        if (illegalArgumentException == null && !isWithinValueRange(bArr.length)) {
            illegalArgumentException = new IllegalArgumentException(ErrorMessage.WMA_LENGTH_OF_DATA_IS_TOO_LARGE.getMsg(Integer.valueOf(bArr.length), getMaximumDataLength(), getContainerGUID().getDescription()));
        }
        String str2 = SessionDescription.SUPPORTED_SDP_VERSION;
        if (illegalArgumentException == null && (i2 < 0 || i2 > 127 || (!isStreamNumberEnabled() && i2 != 0))) {
            illegalArgumentException = new IllegalArgumentException(ErrorMessage.WMA_INVALID_STREAM_REFERNCE.getMsg(Integer.valueOf(i2), isStreamNumberEnabled() ? "0 to 127" : SessionDescription.SUPPORTED_SDP_VERSION, getContainerGUID().getDescription()));
        }
        if (illegalArgumentException == null && i == 6 && !isGuidEnabled()) {
            illegalArgumentException = new IllegalArgumentException(ErrorMessage.WMA_INVALID_GUID_USE.getMsg(getContainerGUID().getDescription()));
        }
        if (illegalArgumentException == null && ((i3 != 0 && !isLanguageEnabled()) || i3 < 0 || i3 >= 127)) {
            if (isStreamNumberEnabled()) {
                str2 = "0 to 126";
            }
            illegalArgumentException = new IllegalArgumentException(ErrorMessage.WMA_INVALID_LANGUAGE_USE.getMsg(Integer.valueOf(i3), getContainerGUID().getDescription(), str2));
        }
        return (illegalArgumentException == null && this == CONTENT_DESCRIPTION && i != 0) ? new IllegalArgumentException(ErrorMessage.WMA_ONLY_STRING_IN_CD.getMsg()) : illegalArgumentException;
    }

    public GUID getContainerGUID() {
        return this.containerGUID;
    }

    public BigInteger getMaximumDataLength() {
        return this.maximumDataLength;
    }

    public boolean isGuidEnabled() {
        return this.guidEnabled;
    }

    public boolean isLanguageEnabled() {
        return this.languageEnabled;
    }

    public boolean isWithinValueRange(long j) {
        long j2 = this.perfMaxDataLen;
        return (j2 == -1 || j2 >= j) && j >= 0;
    }

    public boolean isMultiValued() {
        return this.multiValued;
    }

    public boolean isStreamNumberEnabled() {
        return this.streamEnabled;
    }
}
