package com.umeng.analytics.pro;

import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.cc;
import java.io.Serializable;

/* compiled from: TBase.java */
/* loaded from: classes3.dex */
public interface bv<T extends bv<?, ?>, F extends cc> extends Serializable {
    void clear();

    bv<T, F> deepCopy();

    F fieldForId(int i);

    void read(cu cuVar) throws cb;

    void write(cu cuVar) throws cb;
}
