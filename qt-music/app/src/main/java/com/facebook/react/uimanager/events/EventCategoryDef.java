package com.facebook.react.uimanager.events;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes2.dex */
public @interface EventCategoryDef {
    public static final int CONTINUOUS = 4;
    public static final int CONTINUOUS_END = 1;
    public static final int CONTINUOUS_START = 0;
    public static final int DISCRETE = 3;
    public static final int UNSPECIFIED = 2;
}
