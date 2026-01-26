package androidx.test.rule;

import android.util.Log;
import androidx.test.internal.util.Checks;
import java.util.Properties;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class PortForwardingRule implements TestRule {
    static final String DEFAULT_PROXY_HOST = "127.0.0.1";
    static final int DEFAULT_PROXY_PORT = 8080;
    static final String HTTPS_HOST_PROPERTY = "https.proxyHost";
    static final String HTTPS_PORT_PROPERTY = "https.proxyPort";
    static final String HTTP_HOST_PROPERTY = "http.proxyHost";
    static final String HTTP_PORT_PROPERTY = "http.proxyPort";
    public static final int MAX_PORT = 65535;
    public static final int MIN_PORT = 1024;
    private static final String TAG = "PortForwardingRule";
    private Properties backUpProp;
    Properties prop;
    final String proxyHost;
    final int proxyPort;

    protected static int getDefaultPort() {
        return DEFAULT_PROXY_PORT;
    }

    protected void afterPortForwarding() {
    }

    protected void afterRestoreForwarding() {
    }

    protected void beforePortForwarding() {
    }

    protected void beforeRestoreForwarding() {
    }

    public static class Builder {
        private String proxyHost = PortForwardingRule.DEFAULT_PROXY_HOST;
        private int proxyPort = PortForwardingRule.DEFAULT_PROXY_PORT;
        private Properties prop = System.getProperties();

        public Builder withProxyHost(String proxyHost) {
            this.proxyHost = (String) Checks.checkNotNull(proxyHost);
            return this;
        }

        public Builder withProxyPort(int proxyPort) {
            Checks.checkArgument(proxyPort >= 1024 && proxyPort <= 65535, "%d is used as a proxy port, must in range [%d, %d]", Integer.valueOf(proxyPort), 1024, 65535);
            this.proxyPort = proxyPort;
            return this;
        }

        public Builder withProperties(Properties properties) {
            this.prop = (Properties) Checks.checkNotNull(properties);
            return this;
        }

        public PortForwardingRule build() {
            return new PortForwardingRule(this);
        }
    }

    private PortForwardingRule(Builder builder) {
        this(builder.proxyHost, builder.proxyPort, builder.prop);
    }

    protected PortForwardingRule(int proxyPort) {
        this(DEFAULT_PROXY_HOST, proxyPort, System.getProperties());
    }

    PortForwardingRule(String proxyHost, int proxyPort, Properties properties) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.prop = (Properties) Checks.checkNotNull(properties);
        this.backUpProp = new Properties();
        backUpProperties();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPortForwarding() {
        beforePortForwarding();
        this.prop.setProperty(HTTP_HOST_PROPERTY, this.proxyHost);
        this.prop.setProperty(HTTPS_HOST_PROPERTY, this.proxyHost);
        this.prop.setProperty(HTTP_PORT_PROPERTY, String.valueOf(this.proxyPort));
        this.prop.setProperty(HTTPS_PORT_PROPERTY, String.valueOf(this.proxyPort));
        afterPortForwarding();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restorePortForwarding() {
        try {
            beforeRestoreForwarding();
        } finally {
            restoreOneProperty(this.prop, this.backUpProp, HTTP_HOST_PROPERTY);
            restoreOneProperty(this.prop, this.backUpProp, HTTPS_HOST_PROPERTY);
            restoreOneProperty(this.prop, this.backUpProp, HTTP_PORT_PROPERTY);
            restoreOneProperty(this.prop, this.backUpProp, HTTPS_PORT_PROPERTY);
            afterRestoreForwarding();
        }
    }

    private void backUpProperties() {
        if (this.prop.getProperty(HTTP_HOST_PROPERTY) != null) {
            this.backUpProp.setProperty(HTTP_HOST_PROPERTY, this.prop.getProperty(HTTP_HOST_PROPERTY));
        }
        if (this.prop.getProperty(HTTPS_HOST_PROPERTY) != null) {
            this.backUpProp.setProperty(HTTPS_HOST_PROPERTY, this.prop.getProperty(HTTPS_HOST_PROPERTY));
        }
        if (this.prop.getProperty(HTTP_PORT_PROPERTY) != null) {
            this.backUpProp.setProperty(HTTP_PORT_PROPERTY, this.prop.getProperty(HTTP_PORT_PROPERTY));
        }
        if (this.prop.getProperty(HTTPS_PORT_PROPERTY) != null) {
            this.backUpProp.setProperty(HTTPS_PORT_PROPERTY, this.prop.getProperty(HTTPS_PORT_PROPERTY));
        }
    }

    private void restoreOneProperty(Properties prop, Properties backUpProp, String key) {
        if (backUpProp.getProperty(key) != null) {
            prop.setProperty(key, backUpProp.getProperty(key));
        } else {
            prop.remove(key);
        }
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement base, Description description) {
        return new PortForwardingStatement(base);
    }

    private class PortForwardingStatement extends Statement {
        private final Statement base;

        public PortForwardingStatement(Statement base) {
            this.base = base;
        }

        @Override // org.junit.runners.model.Statement
        public void evaluate() throws Throwable {
            try {
                PortForwardingRule.this.setPortForwarding();
                Log.i(PortForwardingRule.TAG, String.format("The current process traffic is forwarded to %s:%d", PortForwardingRule.this.proxyHost, Integer.valueOf(PortForwardingRule.this.proxyPort)));
                this.base.evaluate();
            } finally {
                PortForwardingRule.this.restorePortForwarding();
                Log.i(PortForwardingRule.TAG, "Current process traffic forwarding is cancelled");
            }
        }
    }
}
