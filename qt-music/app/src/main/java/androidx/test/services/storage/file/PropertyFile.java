package androidx.test.services.storage.file;

import android.net.Uri;
import androidx.test.internal.util.Checks;
import androidx.test.services.storage.TestStorageConstants;

/* loaded from: classes.dex */
public final class PropertyFile {

    public enum Column {
        NAME("name", 0),
        VALUE("value", 1);

        private final String columnName;
        private final int position;

        Column(String columnName, int position) {
            this.columnName = (String) Checks.checkNotNull(columnName);
            this.position = position;
        }

        public String getName() {
            return this.columnName;
        }

        public int getPosition() {
            return this.position;
        }

        public static String[] getNames() {
            Column[] columnArrValues = values();
            int length = values().length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = columnArrValues[i].getName();
            }
            return strArr;
        }
    }

    public enum Authority {
        TEST_ARGS(TestStorageConstants.TEST_ARGS_PROVIDER_AUTHORITY);

        private final String authority;

        Authority(String authority) {
            this.authority = (String) Checks.checkNotNull(authority);
        }

        public String getAuthority() {
            return this.authority;
        }
    }

    public static Uri buildUri(Authority host) {
        Checks.checkNotNull(host);
        return new Uri.Builder().scheme("content").authority(host.getAuthority()).build();
    }

    public static Uri buildUri(Authority host, String property) {
        Checks.checkNotNull(host);
        Checks.checkNotNull(property);
        return new Uri.Builder().scheme("content").authority(host.getAuthority()).path(property).build();
    }

    private PropertyFile() {
    }
}
