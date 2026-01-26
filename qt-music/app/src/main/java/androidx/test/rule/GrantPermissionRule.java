package androidx.test.rule;

import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.content.PermissionGranter;
import androidx.test.internal.util.Checks;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class GrantPermissionRule implements TestRule {
    private PermissionGranter permissionGranter;

    private GrantPermissionRule() {
        this((PermissionGranter) ServiceLoaderWrapper.loadSingleService(PermissionGranter.class, GrantPermissionRule$$Lambda$0.$instance));
    }

    GrantPermissionRule(PermissionGranter permissionGranter) {
        setPermissionGranter(permissionGranter);
    }

    public static GrantPermissionRule grant(String... permissions) {
        GrantPermissionRule grantPermissionRule = new GrantPermissionRule();
        grantPermissionRule.grantPermissions(permissions);
        return grantPermissionRule;
    }

    private void grantPermissions(String... permissions) {
        Set<String> setSatisfyPermissionDependencies = satisfyPermissionDependencies(permissions);
        this.permissionGranter.addPermissions((String[]) setSatisfyPermissionDependencies.toArray(new String[setSatisfyPermissionDependencies.size()]));
    }

    Set<String> satisfyPermissionDependencies(String... permissions) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(permissions));
        if (linkedHashSet.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
            linkedHashSet.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        return linkedHashSet;
    }

    @Override // org.junit.rules.TestRule
    public final Statement apply(final Statement base, Description description) {
        return new RequestPermissionStatement(base);
    }

    void setPermissionGranter(PermissionGranter permissionGranter) {
        this.permissionGranter = (PermissionGranter) Checks.checkNotNull(permissionGranter, "permissionRequester cannot be null!");
    }

    private class RequestPermissionStatement extends Statement {
        private final Statement base;

        public RequestPermissionStatement(Statement base) {
            this.base = base;
        }

        @Override // org.junit.runners.model.Statement
        public void evaluate() throws Throwable {
            GrantPermissionRule.this.permissionGranter.requestPermissions();
            this.base.evaluate();
        }
    }
}
