package org.jaudiotagger.tag;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.tag.images.Artwork;

/* loaded from: classes3.dex */
public interface Tag {
    void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException;

    void addField(TagField tagField) throws FieldDataInvalidException;

    void addField(Artwork artwork) throws FieldDataInvalidException;

    TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException;

    TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException;

    TagField createField(Artwork artwork) throws FieldDataInvalidException;

    void deleteArtworkField() throws KeyNotFoundException;

    void deleteField(String str) throws KeyNotFoundException;

    void deleteField(FieldKey fieldKey) throws KeyNotFoundException;

    List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException;

    List<Artwork> getArtworkList();

    int getFieldCount();

    int getFieldCountIncludingSubValues();

    Iterator<TagField> getFields();

    List<TagField> getFields(String str);

    List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException;

    String getFirst(String str);

    String getFirst(FieldKey fieldKey) throws KeyNotFoundException;

    Artwork getFirstArtwork();

    TagField getFirstField(String str);

    TagField getFirstField(FieldKey fieldKey);

    String getValue(FieldKey fieldKey, int i);

    boolean hasCommonFields();

    boolean hasField(String str);

    boolean hasField(FieldKey fieldKey);

    boolean isEmpty();

    boolean setEncoding(Charset charset) throws FieldDataInvalidException;

    void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException;

    void setField(TagField tagField) throws FieldDataInvalidException;

    void setField(Artwork artwork) throws FieldDataInvalidException;

    String toString();
}
