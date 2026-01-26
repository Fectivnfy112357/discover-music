package org.jaudiotagger.audio.aiff;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.generic.GenericAudioHeader;

/* loaded from: classes3.dex */
public class AiffAudioHeader extends GenericAudioHeader {
    private String author;
    private String copyright;
    private AiffType fileType;
    private String name;
    private Date timestamp;
    private List<String> applicationIdentifiers = new ArrayList();
    private List<String> comments = new ArrayList();
    private List<String> annotations = new ArrayList();
    private Endian endian = Endian.BIG_ENDIAN;

    public enum Endian {
        BIG_ENDIAN,
        LITTLE_ENDIAN
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public AiffType getFileType() {
        return this.fileType;
    }

    public void setFileType(AiffType aiffType) {
        this.fileType = aiffType;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String str) {
        this.copyright = str;
    }

    public Endian getEndian() {
        return this.endian;
    }

    public void setEndian(Endian endian) {
        this.endian = endian;
    }

    public List<String> getApplicationIdentifiers() {
        return this.applicationIdentifiers;
    }

    public void addApplicationIdentifier(String str) {
        this.applicationIdentifiers.add(str);
    }

    public List<String> getAnnotations() {
        return this.annotations;
    }

    public void addAnnotation(String str) {
        this.annotations.add(str);
    }

    public List<String> getComments() {
        return this.comments;
    }

    public void addComment(String str) {
        this.comments.add(str);
    }

    @Override // org.jaudiotagger.audio.generic.GenericAudioHeader
    public String toString() {
        StringBuilder sb = new StringBuilder(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        String str = this.name;
        if (str != null && !str.isEmpty()) {
            sb.append("\tName:" + this.name + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        String str2 = this.author;
        if (str2 != null && !str2.isEmpty()) {
            sb.append("\tAuthor:" + this.author + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        String str3 = this.copyright;
        if (str3 != null && !str3.isEmpty()) {
            sb.append("\tCopyright:" + this.copyright + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.comments.size() > 0) {
            sb.append("Comments:\n");
            Iterator<String> it = this.comments.iterator();
            while (it.hasNext()) {
                sb.append("\t" + it.next() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        if (this.applicationIdentifiers.size() > 0) {
            sb.append("ApplicationIds:\n");
            Iterator<String> it2 = this.applicationIdentifiers.iterator();
            while (it2.hasNext()) {
                sb.append("\t" + it2.next() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        if (this.annotations.size() > 0) {
            sb.append("Annotations:\n");
            Iterator<String> it3 = this.annotations.iterator();
            while (it3.hasNext()) {
                sb.append("\t" + it3.next() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        return super.toString() + sb.toString();
    }
}
