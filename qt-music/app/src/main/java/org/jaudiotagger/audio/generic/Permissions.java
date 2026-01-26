package org.jaudiotagger.audio.generic;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Iterator;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
public class Permissions {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.generic");

    public static String displayPermissions(Path path) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("File " + path + " permissions\n");
        try {
            AclFileAttributeView aclFileAttributeView = (AclFileAttributeView) Files.getFileAttributeView(path, AclFileAttributeView.class, new LinkOption[0]);
            if (aclFileAttributeView != null) {
                sb.append("owner:" + aclFileAttributeView.getOwner().getName() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                Iterator<AclEntry> it = aclFileAttributeView.getAcl().iterator();
                while (it.hasNext()) {
                    sb.append(it.next() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
            }
            PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, new LinkOption[0]);
            if (posixFileAttributeView != null) {
                PosixFileAttributes attributes = posixFileAttributeView.readAttributes();
                sb.append(":owner:" + attributes.owner().getName() + ":group:" + attributes.group().getName() + ":" + PosixFilePermissions.toString(attributes.permissions()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        } catch (IOException unused) {
            logger.severe("Unable to read permissions for:" + path.toString());
        }
        return sb.toString();
    }
}
