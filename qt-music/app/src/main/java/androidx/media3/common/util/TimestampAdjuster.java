package androidx.media3.common.util;

import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class TimestampAdjuster {
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    public static final long MODE_NO_OFFSET = Long.MAX_VALUE;
    public static final long MODE_SHARED = 9223372036854775806L;
    private long firstSampleTimestampUs;
    private long lastUnadjustedTimestampUs;
    private final ThreadLocal<Long> nextSampleTimestampUs = new ThreadLocal<>();
    private long timestampOffsetUs;

    public TimestampAdjuster(long j) {
        reset(j);
    }

    public synchronized void sharedInitializeOrWait(boolean z, long j, long j2) throws InterruptedException, TimeoutException {
        Assertions.checkState(this.firstSampleTimestampUs == 9223372036854775806L);
        if (isInitialized()) {
            return;
        }
        if (z) {
            this.nextSampleTimestampUs.set(Long.valueOf(j));
        } else {
            long jElapsedRealtime = 0;
            long j3 = j2;
            while (!isInitialized()) {
                if (j2 == 0) {
                    wait();
                } else {
                    Assertions.checkState(j3 > 0);
                    long jElapsedRealtime2 = android.os.SystemClock.elapsedRealtime();
                    wait(j3);
                    jElapsedRealtime += android.os.SystemClock.elapsedRealtime() - jElapsedRealtime2;
                    if (jElapsedRealtime >= j2 && !isInitialized()) {
                        throw new TimeoutException("TimestampAdjuster failed to initialize in " + j2 + " milliseconds");
                    }
                    j3 = j2 - jElapsedRealtime;
                }
            }
        }
    }

    public synchronized long getFirstSampleTimestampUs() {
        long j;
        j = this.firstSampleTimestampUs;
        if (j == Long.MAX_VALUE || j == 9223372036854775806L) {
            j = -9223372036854775807L;
        }
        return j;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        long firstSampleTimestampUs;
        long j = this.lastUnadjustedTimestampUs;
        if (j != -9223372036854775807L) {
            firstSampleTimestampUs = j + this.timestampOffsetUs;
        } else {
            firstSampleTimestampUs = getFirstSampleTimestampUs();
        }
        return firstSampleTimestampUs;
    }

    public synchronized long getTimestampOffsetUs() {
        return this.timestampOffsetUs;
    }

    public synchronized void reset(long j) {
        this.firstSampleTimestampUs = j;
        this.timestampOffsetUs = j == Long.MAX_VALUE ? 0L : -9223372036854775807L;
        this.lastUnadjustedTimestampUs = -9223372036854775807L;
    }

    public synchronized long adjustTsTimestamp(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        long j2 = this.lastUnadjustedTimestampUs;
        if (j2 != -9223372036854775807L) {
            long jUsToNonWrappedPts = usToNonWrappedPts(j2);
            long j3 = (4294967296L + jUsToNonWrappedPts) / MAX_PTS_PLUS_ONE;
            long j4 = ((j3 - 1) * MAX_PTS_PLUS_ONE) + j;
            j += j3 * MAX_PTS_PLUS_ONE;
            if (Math.abs(j4 - jUsToNonWrappedPts) < Math.abs(j - jUsToNonWrappedPts)) {
                j = j4;
            }
        }
        return adjustSampleTimestamp(ptsToUs(j));
    }

    public synchronized long adjustTsTimestampGreaterThanPreviousTimestamp(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        long j2 = this.lastUnadjustedTimestampUs;
        if (j2 != -9223372036854775807L) {
            long jUsToNonWrappedPts = usToNonWrappedPts(j2);
            long j3 = jUsToNonWrappedPts / MAX_PTS_PLUS_ONE;
            long j4 = (j3 * MAX_PTS_PLUS_ONE) + j;
            j += (j3 + 1) * MAX_PTS_PLUS_ONE;
            if (j4 >= jUsToNonWrappedPts) {
                j = j4;
            }
        }
        return adjustSampleTimestamp(ptsToUs(j));
    }

    public synchronized long adjustSampleTimestamp(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (!isInitialized()) {
            long jLongValue = this.firstSampleTimestampUs;
            if (jLongValue == 9223372036854775806L) {
                jLongValue = ((Long) Assertions.checkNotNull(this.nextSampleTimestampUs.get())).longValue();
            }
            this.timestampOffsetUs = jLongValue - j;
            notifyAll();
        }
        this.lastUnadjustedTimestampUs = j;
        return j + this.timestampOffsetUs;
    }

    public synchronized boolean isInitialized() {
        return this.timestampOffsetUs != -9223372036854775807L;
    }

    public static long ptsToUs(long j) {
        return Util.scaleLargeTimestamp(j, 1000000L, 90000L);
    }

    public static long usToWrappedPts(long j) {
        return usToNonWrappedPts(j) % MAX_PTS_PLUS_ONE;
    }

    public static long usToNonWrappedPts(long j) {
        return Util.scaleLargeTimestamp(j, 90000L, 1000000L);
    }
}
