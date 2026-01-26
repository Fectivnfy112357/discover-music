package org.jaudiotagger.tag.id3.valuepair;

import java.util.Collections;
import java.util.List;
import org.jaudiotagger.tag.reference.GenreTypes;

/* loaded from: classes3.dex */
public class V2GenreTypes {
    private static V2GenreTypes v2GenresTypes;

    private V2GenreTypes() {
    }

    public static V2GenreTypes getInstanceOf() {
        if (v2GenresTypes == null) {
            v2GenresTypes = new V2GenreTypes();
        }
        return v2GenresTypes;
    }

    public List<String> getAlphabeticalValueList() {
        List<String> alphabeticalValueList = GenreTypes.getInstanceOf().getAlphabeticalValueList();
        alphabeticalValueList.add(ID3V2ExtendedGenreTypes.CR.getDescription());
        alphabeticalValueList.add(ID3V2ExtendedGenreTypes.RX.getDescription());
        Collections.sort(alphabeticalValueList);
        return alphabeticalValueList;
    }
}
