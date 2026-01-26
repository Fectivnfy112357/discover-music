package org.jaudiotagger.audio.generic;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.images.Artwork;

/* loaded from: classes3.dex */
public abstract class AbstractTag implements Tag {
    protected int commonNumber = 0;
    protected Map<String, List<TagField>> fields = new LinkedHashMap();

    @Override // org.jaudiotagger.tag.Tag
    public abstract TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException;

    @Override // org.jaudiotagger.tag.Tag
    public abstract void deleteField(FieldKey fieldKey) throws KeyNotFoundException;

    @Override // org.jaudiotagger.tag.Tag
    public abstract TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException;

    protected abstract boolean isAllowedEncoding(Charset charset);

    @Override // org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) {
        if (tagField == null) {
            return;
        }
        List<TagField> list = this.fields.get(tagField.getId());
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(tagField);
            this.fields.put(tagField.getId(), arrayList);
            if (tagField.isCommon()) {
                this.commonNumber++;
                return;
            }
            return;
        }
        list.add(tagField);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(String str) {
        List<TagField> list = this.fields.get(str);
        return list == null ? new ArrayList() : list;
    }

    public List<String> getAll(String str) throws KeyNotFoundException {
        ArrayList arrayList = new ArrayList();
        Iterator<TagField> it = getFields(str).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toString());
        }
        return arrayList;
    }

    public String getItem(String str, int i) {
        List<TagField> fields = getFields(str);
        return fields.size() > i ? fields.get(i).toString() : "";
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(FieldKey fieldKey) throws KeyNotFoundException {
        return getValue(fieldKey, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(String str) {
        List<TagField> fields = getFields(str);
        return fields.size() != 0 ? fields.get(0).toString() : "";
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(String str) {
        List<TagField> fields = getFields(str);
        if (fields.size() != 0) {
            return fields.get(0);
        }
        return null;
    }

    public List<TagField> getAll() {
        ArrayList arrayList = new ArrayList();
        Iterator<List<TagField>> it = this.fields.values().iterator();
        while (it.hasNext()) {
            Iterator<TagField> it2 = it.next().iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next());
            }
        }
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.Tag
    public Iterator<TagField> getFields() {
        final Iterator<Map.Entry<String, List<TagField>>> it = this.fields.entrySet().iterator();
        return new Iterator<TagField>() { // from class: org.jaudiotagger.audio.generic.AbstractTag.1
            private Iterator<TagField> fieldsIt;

            private void changeIt() {
                if (it.hasNext()) {
                    this.fieldsIt = ((List) ((Map.Entry) it.next()).getValue()).iterator();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                Iterator<TagField> it2;
                if (this.fieldsIt == null) {
                    changeIt();
                }
                return it.hasNext() || ((it2 = this.fieldsIt) != null && it2.hasNext());
            }

            @Override // java.util.Iterator
            public TagField next() {
                if (!this.fieldsIt.hasNext()) {
                    changeIt();
                }
                return this.fieldsIt.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.fieldsIt.remove();
            }
        };
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCount() {
        Iterator<TagField> fields = getFields();
        int i = 0;
        while (fields.hasNext()) {
            i++;
            fields.next();
        }
        return i;
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCountIncludingSubValues() {
        return getFieldCount();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasCommonFields() {
        return this.commonNumber != 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(String str) {
        return getFields(str).size() != 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        return hasField(fieldKey.name());
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean isEmpty() {
        return this.fields.size() == 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(fieldKey, strArr));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        addField(createField(fieldKey, strArr));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(TagField tagField) {
        if (tagField == null) {
            return;
        }
        List<TagField> list = this.fields.get(tagField.getId());
        if (list != null) {
            list.set(0, tagField);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(tagField);
        this.fields.put(tagField.getId(), arrayList);
        if (tagField.isCommon()) {
            this.commonNumber++;
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean setEncoding(Charset charset) {
        if (!isAllowedEncoding(charset)) {
            return false;
        }
        Iterator<TagField> fields = getFields();
        while (fields.hasNext()) {
            TagField next = fields.next();
            if (next instanceof TagTextField) {
                ((TagTextField) next).setEncoding(charset);
            }
        }
        return true;
    }

    @Override // org.jaudiotagger.tag.Tag
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Tag content:\n");
        Iterator<TagField> fields = getFields();
        while (fields.hasNext()) {
            TagField next = fields.next();
            stringBuffer.append("\t");
            stringBuffer.append(next.getId());
            stringBuffer.append(":");
            stringBuffer.append(next.toString());
            stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(String str) {
        this.fields.remove(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public Artwork getFirstArtwork() {
        List<Artwork> artworkList = getArtworkList();
        if (artworkList.size() > 0) {
            return artworkList.get(0);
        }
        return null;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(Artwork artwork) throws FieldDataInvalidException {
        setField(createField(artwork));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(Artwork artwork) throws FieldDataInvalidException {
        addField(createField(artwork));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteArtworkField() throws KeyNotFoundException {
        deleteField(FieldKey.COVER_ART);
    }
}
