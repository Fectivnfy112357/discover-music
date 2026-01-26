package org.jaudiotagger.tag.lyrics3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes3.dex */
public class Lyrics3v1Iterator implements Iterator<String> {
    private int lastIndex = 0;
    private int removeIndex = 0;
    private Lyrics3v1 tag;

    public Lyrics3v1Iterator(Lyrics3v1 lyrics3v1) {
        this.tag = lyrics3v1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.tag.getLyric().indexOf(10, this.lastIndex) >= 0 || this.lastIndex <= this.tag.getLyric().length();
    }

    @Override // java.util.Iterator
    public String next() {
        String strSubstring;
        int iIndexOf = this.tag.getLyric().indexOf(10, this.lastIndex);
        int i = this.lastIndex;
        this.removeIndex = i;
        if (i >= 0) {
            if (iIndexOf >= 0) {
                strSubstring = this.tag.getLyric().substring(this.lastIndex, iIndexOf);
            } else {
                strSubstring = this.tag.getLyric().substring(this.lastIndex);
            }
            this.lastIndex = iIndexOf;
            return strSubstring;
        }
        throw new NoSuchElementException("Iteration has no more elements.");
    }

    @Override // java.util.Iterator
    public void remove() {
        this.tag.setLyric(this.tag.getLyric().substring(0, this.removeIndex) + this.tag.getLyric().substring(this.lastIndex));
    }
}
