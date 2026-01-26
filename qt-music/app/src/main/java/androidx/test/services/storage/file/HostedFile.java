package androidx.test.services.storage.file;

import android.net.Uri;
import androidx.test.services.storage.TestStorageConstants;

/* loaded from: classes.dex */
public final class HostedFile {

    public enum HostedFileColumn {
        NAME("name", String.class, 3, 0),
        TYPE("type", String.class, 3, 1),
        SIZE("size", Long.class, 1, 2),
        DATA("_data", Byte[].class, 4, 3),
        DISPLAY_NAME("_display_name", String.class, 3, 4),
        SIZE_2("_size", Long.class, 2, 5);

        private final int androidType;
        private final String columnName;
        private final Class<?> columnType;
        private final int position;

        HostedFileColumn(String columnName, Class columnType, int androidType, int position) {
            this.columnName = (String) HostedFile.checkNotNull(columnName);
            this.columnType = (Class) HostedFile.checkNotNull(columnType);
            this.androidType = androidType;
            this.position = position;
        }

        public String getColumnName() {
            return this.columnName;
        }

        public Class<?> getColumnType() {
            return this.columnType;
        }

        public int getAndroidType() {
            return this.androidType;
        }

        public int getPosition() {
            return this.position;
        }

        public static String[] getColumnNames() {
            HostedFileColumn[] hostedFileColumnArrValues = values();
            int length = hostedFileColumnArrValues.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = hostedFileColumnArrValues[i].getColumnName();
            }
            return strArr;
        }
    }

    public enum FileType {
        FILE("f"),
        DIRECTORY("d");

        private String type;

        FileType(String type) {
            this.type = (String) HostedFile.checkNotNull(type);
        }

        public String getTypeCode() {
            return this.type;
        }

        public static FileType fromTypeCode(String type) {
            for (FileType fileType : values()) {
                if (fileType.getTypeCode().equals(type)) {
                    return fileType;
                }
            }
            String strValueOf = String.valueOf(type);
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "unknown type: ".concat(strValueOf) : new String("unknown type: "));
        }
    }

    public enum FileHost {
        TEST_FILE(TestStorageConstants.TEST_RUNFILES_PROVIDER_AUTHORITY, false),
        EXPORT_PROPERTIES(TestStorageConstants.OUTPUT_PROPERTIES_PROVIDER_AUTHORITY, true),
        OUTPUT(TestStorageConstants.TEST_OUTPUT_PROVIDER_AUTHORITY, true),
        INTERNAL_USE_ONLY(TestStorageConstants.INTERNAL_USE_PROVIDER_AUTHORITY, true);

        private final String authority;
        private final boolean writeable;

        FileHost(String authority, boolean writeable) {
            this.authority = (String) HostedFile.checkNotNull(authority);
            this.writeable = writeable;
        }

        public String getAuthority() {
            return this.authority;
        }

        public boolean isWritable() {
            return this.writeable;
        }
    }

    public static Uri buildUri(FileHost host, String fileName) {
        return new Uri.Builder().scheme("content").authority(host.getAuthority()).path(fileName).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T checkNotNull(T reference) {
        reference.getClass();
        return reference;
    }

    private HostedFile() {
    }
}
