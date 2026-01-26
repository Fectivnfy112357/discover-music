package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class EncryptionChunk extends Chunk {
    private String keyID;
    private String licenseURL;
    private String protectionType;
    private String secretData;
    private final ArrayList<String> strings;

    public EncryptionChunk(BigInteger bigInteger) {
        super(GUID.GUID_CONTENT_ENCRYPTION, bigInteger);
        this.strings = new ArrayList<>();
        this.secretData = "";
        this.protectionType = "";
        this.keyID = "";
        this.licenseURL = "";
    }

    public void addString(String str) {
        this.strings.add(str);
    }

    public String getKeyID() {
        return this.keyID;
    }

    public String getLicenseURL() {
        return this.licenseURL;
    }

    public String getProtectionType() {
        return this.protectionType;
    }

    public String getSecretData() {
        return this.secretData;
    }

    public Collection<String> getStrings() {
        return new ArrayList(this.strings);
    }

    @Override // org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        sb.insert(0, Utils.LINE_SEPARATOR + str + " Encryption:" + Utils.LINE_SEPARATOR);
        sb.append(str).append("\t|->keyID ").append(this.keyID).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("\t|->secretData ").append(this.secretData).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("\t|->protectionType ").append(this.protectionType).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("\t|->licenseURL ").append(this.licenseURL).append(Utils.LINE_SEPARATOR);
        this.strings.iterator();
        Iterator<String> it = this.strings.iterator();
        while (it.hasNext()) {
            sb.append(str).append("   |->").append(it.next()).append(Utils.LINE_SEPARATOR);
        }
        return sb.toString();
    }

    public void setKeyID(String str) {
        this.keyID = str;
    }

    public void setLicenseURL(String str) {
        this.licenseURL = str;
    }

    public void setProtectionType(String str) {
        this.protectionType = str;
    }

    public void setSecretData(String str) {
        this.secretData = str;
    }
}
