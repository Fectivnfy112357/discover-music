package com.google.android.exoplayer2.upstream.experimental;

@Deprecated
/* loaded from: classes2.dex */
public interface BandwidthStatistic {
    void addSample(long j, long j2);

    long getBandwidthEstimate();

    void reset();
}
