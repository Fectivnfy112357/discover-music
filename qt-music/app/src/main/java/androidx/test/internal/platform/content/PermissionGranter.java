package androidx.test.internal.platform.content;

/* loaded from: classes.dex */
public interface PermissionGranter {
    void addPermissions(String... permissions);

    void requestPermissions();
}
