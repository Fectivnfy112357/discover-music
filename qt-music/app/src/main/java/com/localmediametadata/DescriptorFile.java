package com.localmediametadata;

import java.io.File;

/* loaded from: classes3.dex */
class DescriptorFile extends File {
    private final String rawName;

    DescriptorFile(String str, String str2) {
        super(str);
        this.rawName = str2;
    }

    @Override // java.io.File
    public String getName() {
        return this.rawName;
    }
}
