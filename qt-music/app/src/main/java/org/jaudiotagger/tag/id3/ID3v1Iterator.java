package org.jaudiotagger.tag.id3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes3.dex */
public class ID3v1Iterator implements Iterator<Object> {
    private static final int ALBUM = 3;
    private static final int ARTIST = 2;
    private static final int COMMENT = 4;
    private static final int GENRE = 6;
    private static final int TITLE = 1;
    private static final int TRACK = 7;
    private static final int YEAR = 5;
    private ID3v1Tag id3v1tag;
    private int lastIndex = 0;

    public ID3v1Iterator(ID3v1Tag iD3v1Tag) {
        this.id3v1tag = iD3v1Tag;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return hasNext(this.lastIndex);
    }

    @Override // java.util.Iterator
    public Object next() {
        return next(this.lastIndex);
    }

    @Override // java.util.Iterator
    public void remove() {
        switch (this.lastIndex) {
            case 1:
                this.id3v1tag.title = "";
            case 2:
                this.id3v1tag.artist = "";
            case 3:
                this.id3v1tag.album = "";
            case 4:
                this.id3v1tag.comment = "";
            case 5:
                this.id3v1tag.year = "";
            case 6:
                this.id3v1tag.genre = (byte) -1;
            case 7:
                ID3v1Tag iD3v1Tag = this.id3v1tag;
                if (iD3v1Tag instanceof ID3v11Tag) {
                    ((ID3v11Tag) iD3v1Tag).track = (byte) -1;
                    break;
                }
                break;
        }
    }

    private boolean hasNext(int i) {
        switch (i) {
            case 1:
                if (this.id3v1tag.title.length() > 0 || hasNext(i + 1)) {
                    break;
                }
                break;
            case 2:
                if (this.id3v1tag.artist.length() > 0 || hasNext(i + 1)) {
                    break;
                }
                break;
            case 3:
                if (this.id3v1tag.album.length() > 0 || hasNext(i + 1)) {
                    break;
                }
                break;
            case 4:
                if (this.id3v1tag.comment.length() > 0 || hasNext(i + 1)) {
                    break;
                }
                break;
            case 5:
                if (this.id3v1tag.year.length() > 0 || hasNext(i + 1)) {
                    break;
                }
                break;
            case 6:
                if (this.id3v1tag.genre >= 0 || hasNext(i + 1)) {
                    break;
                }
                break;
            case 7:
                ID3v1Tag iD3v1Tag = this.id3v1tag;
                if (iD3v1Tag instanceof ID3v11Tag) {
                    if (((ID3v11Tag) iD3v1Tag).track >= 0 || hasNext(i + 1)) {
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private Object next(int i) {
        switch (this.lastIndex) {
            case 0:
                return this.id3v1tag.title.length() > 0 ? this.id3v1tag.title : next(i + 1);
            case 1:
                return this.id3v1tag.artist.length() > 0 ? this.id3v1tag.artist : next(i + 1);
            case 2:
                return this.id3v1tag.album.length() > 0 ? this.id3v1tag.album : next(i + 1);
            case 3:
                return this.id3v1tag.comment.length() > 0 ? this.id3v1tag.comment : next(i + 1);
            case 4:
                return this.id3v1tag.year.length() > 0 ? this.id3v1tag.year : next(i + 1);
            case 5:
                return this.id3v1tag.genre >= 0 ? Byte.valueOf(this.id3v1tag.genre) : next(i + 1);
            case 6:
                ID3v1Tag iD3v1Tag = this.id3v1tag;
                if (!(iD3v1Tag instanceof ID3v11Tag) || ((ID3v11Tag) iD3v1Tag).track < 0) {
                    return null;
                }
                return Byte.valueOf(((ID3v11Tag) this.id3v1tag).track);
            default:
                throw new NoSuchElementException("Iteration has no more elements.");
        }
    }
}
