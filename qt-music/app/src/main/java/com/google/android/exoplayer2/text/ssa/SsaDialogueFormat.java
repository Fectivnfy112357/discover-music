package com.google.android.exoplayer2.text.ssa;

@Deprecated
/* loaded from: classes2.dex */
final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int i, int i2, int i3, int i4, int i5) {
        this.startTimeIndex = i;
        this.endTimeIndex = i2;
        this.styleIndex = i3;
        this.textIndex = i4;
        this.length = i5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.text.ssa.SsaDialogueFormat fromFormatLine(java.lang.String r9) {
        /*
            java.lang.String r0 = "Format:"
            boolean r1 = r9.startsWith(r0)
            com.google.android.exoplayer2.util.Assertions.checkArgument(r1)
            int r0 = r0.length()
            java.lang.String r9 = r9.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r9 = android.text.TextUtils.split(r9, r0)
            r0 = -1
            r1 = 0
            r4 = r0
            r5 = r4
            r6 = r5
            r7 = r6
            r2 = r1
        L1e:
            int r3 = r9.length
            if (r2 >= r3) goto L70
            r3 = r9[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.common.base.Ascii.toLowerCase(r3)
            r3.hashCode()
            int r8 = r3.hashCode()
            switch(r8) {
                case 100571: goto L58;
                case 3556653: goto L4d;
                case 109757538: goto L42;
                case 109780401: goto L37;
                default: goto L35;
            }
        L35:
            r3 = r0
            goto L62
        L37:
            java.lang.String r8 = "style"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L40
            goto L35
        L40:
            r3 = 3
            goto L62
        L42:
            java.lang.String r8 = "start"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L4b
            goto L35
        L4b:
            r3 = 2
            goto L62
        L4d:
            java.lang.String r8 = "text"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L56
            goto L35
        L56:
            r3 = 1
            goto L62
        L58:
            java.lang.String r8 = "end"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L61
            goto L35
        L61:
            r3 = r1
        L62:
            switch(r3) {
                case 0: goto L6c;
                case 1: goto L6a;
                case 2: goto L68;
                case 3: goto L66;
                default: goto L65;
            }
        L65:
            goto L6d
        L66:
            r6 = r2
            goto L6d
        L68:
            r4 = r2
            goto L6d
        L6a:
            r7 = r2
            goto L6d
        L6c:
            r5 = r2
        L6d:
            int r2 = r2 + 1
            goto L1e
        L70:
            if (r4 == r0) goto L7e
            if (r5 == r0) goto L7e
            if (r7 == r0) goto L7e
            com.google.android.exoplayer2.text.ssa.SsaDialogueFormat r0 = new com.google.android.exoplayer2.text.ssa.SsaDialogueFormat
            int r8 = r9.length
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8)
            goto L7f
        L7e:
            r0 = 0
        L7f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDialogueFormat.fromFormatLine(java.lang.String):com.google.android.exoplayer2.text.ssa.SsaDialogueFormat");
    }
}
