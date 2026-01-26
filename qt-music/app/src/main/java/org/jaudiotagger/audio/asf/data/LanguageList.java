package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.jaudiotagger.audio.asf.util.Utils;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public class LanguageList extends Chunk {
    private final List<String> languages;

    public LanguageList() {
        super(GUID.GUID_LANGUAGE_LIST, 0L, BigInteger.ZERO);
        this.languages = new ArrayList();
    }

    public LanguageList(long j, BigInteger bigInteger) {
        super(GUID.GUID_LANGUAGE_LIST, j, bigInteger);
        this.languages = new ArrayList();
    }

    public void addLanguage(String str) {
        if (str.length() < 127) {
            if (this.languages.contains(str)) {
                return;
            }
            this.languages.add(str);
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.WMA_LENGTH_OF_LANGUAGE_IS_TOO_LARGE.getMsg(Integer.valueOf((str.length() * 2) + 2)));
    }

    public String getLanguage(int i) {
        return this.languages.get(i);
    }

    public int getLanguageCount() {
        return this.languages.size();
    }

    public List<String> getLanguages() {
        return new ArrayList(this.languages);
    }

    @Override // org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        for (int i = 0; i < getLanguageCount(); i++) {
            sb.append(str);
            sb.append("  |-> ");
            sb.append(i);
            sb.append(" : ");
            sb.append(getLanguage(i));
            sb.append(Utils.LINE_SEPARATOR);
        }
        return sb.toString();
    }

    public void removeLanguage(int i) {
        this.languages.remove(i);
    }
}
