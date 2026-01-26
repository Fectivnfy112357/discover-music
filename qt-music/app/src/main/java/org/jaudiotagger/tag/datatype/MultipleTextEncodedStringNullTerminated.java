package org.jaudiotagger.tag.datatype;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class MultipleTextEncodedStringNullTerminated extends AbstractDataType {
    public MultipleTextEncodedStringNullTerminated(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        this.value = new Values();
    }

    public MultipleTextEncodedStringNullTerminated(TextEncodedStringSizeTerminated textEncodedStringSizeTerminated) {
        super(textEncodedStringSizeTerminated);
        this.value = new Values();
    }

    public MultipleTextEncodedStringNullTerminated(MultipleTextEncodedStringNullTerminated multipleTextEncodedStringNullTerminated) {
        super(multipleTextEncodedStringNullTerminated);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof MultipleTextEncodedStringNullTerminated) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return this.size;
    }

    public boolean canBeEncoded() {
        ListIterator<String> listIterator = ((Values) this.value).getList().listIterator();
        while (listIterator.hasNext()) {
            if (!new TextEncodedStringNullTerminated(this.identifier, this.frameBody, listIterator.next()).canBeEncoded()) {
                return false;
            }
        }
        return true;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        TextEncodedStringNullTerminated textEncodedStringNullTerminated;
        logger.finer("Reading MultipleTextEncodedStringNullTerminated from array from offset:" + i);
        do {
            try {
                textEncodedStringNullTerminated = new TextEncodedStringNullTerminated(this.identifier, this.frameBody, true);
                textEncodedStringNullTerminated.readByteArray(bArr, i);
            } catch (InvalidDataTypeException unused) {
            }
            if (textEncodedStringNullTerminated.getSize() != 0) {
                ((Values) this.value).add((String) textEncodedStringNullTerminated.getValue());
                this.size += textEncodedStringNullTerminated.getSize();
                i += textEncodedStringNullTerminated.getSize();
            } else {
                logger.finer("Read  MultipleTextEncodedStringNullTerminated:" + this.value + " size:" + this.size);
                return;
            }
        } while (this.size != 0);
        logger.warning("No null terminated Strings found");
        throw new InvalidDataTypeException("No null terminated Strings found");
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        logger.finer("Writing MultipleTextEncodedStringNullTerminated");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ListIterator<String> listIterator = ((Values) this.value).getList().listIterator();
            int size = 0;
            while (listIterator.hasNext()) {
                TextEncodedStringNullTerminated textEncodedStringNullTerminated = new TextEncodedStringNullTerminated(this.identifier, this.frameBody, listIterator.next());
                byteArrayOutputStream.write(textEncodedStringNullTerminated.writeByteArray());
                size += textEncodedStringNullTerminated.getSize();
            }
            this.size = size;
            logger.finer("Written MultipleTextEncodedStringNullTerminated");
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException in MultipleTextEncodedStringNullTerminated when writing byte array", (Throwable) e);
            throw new RuntimeException(e);
        }
    }

    public static class Values {
        private List<String> valueList = new ArrayList();

        public void add(String str) {
            this.valueList.add(str);
        }

        public List<String> getList() {
            return this.valueList;
        }

        public int getNumberOfValues() {
            return this.valueList.size();
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            ListIterator<String> listIterator = this.valueList.listIterator();
            while (listIterator.hasNext()) {
                stringBuffer.append(listIterator.next());
                if (listIterator.hasNext()) {
                    stringBuffer.append(",");
                }
            }
            return stringBuffer.toString();
        }
    }
}
