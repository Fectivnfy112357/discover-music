package okio.internal;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Path;
import okio.ZipFileSystem;

/* compiled from: ZipFiles.kt */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\"\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H\u0002\u001a\u001f\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u001c\u001a\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0000\u001a.\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0014\b\u0002\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020&0%H\u0000\u001a\f\u0010'\u001a\u00020\u0016*\u00020(H\u0000\u001a\f\u0010)\u001a\u00020**\u00020(H\u0002\u001a.\u0010+\u001a\u00020,*\u00020(2\u0006\u0010-\u001a\u00020\u00012\u0018\u0010.\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020,0/H\u0002\u001a\u0014\u00100\u001a\u00020\u0016*\u00020(2\u0006\u00101\u001a\u00020\u0016H\u0000\u001a\u0018\u00102\u001a\u0004\u0018\u00010\u0016*\u00020(2\b\u00101\u001a\u0004\u0018\u00010\u0016H\u0002\u001a\u0014\u00103\u001a\u00020**\u00020(2\u0006\u00104\u001a\u00020*H\u0002\u001a\f\u00105\u001a\u00020,*\u00020(H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u000f\u001a\u00020\u0010*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u00066"}, d2 = {"BIT_FLAG_ENCRYPTED", "", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_NTFS_EXTRA", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "hex", "", "getHex", "(I)Ljava/lang/String;", "buildIndex", "", "Lokio/Path;", "Lokio/internal/ZipEntry;", "entries", "", "dosDateTimeToEpochMillis", "date", "time", "(II)Ljava/lang/Long;", "filetimeToEpochMillis", "filetime", "openZip", "Lokio/ZipFileSystem;", "zipPath", "fileSystem", "Lokio/FileSystem;", "predicate", "Lkotlin/Function1;", "", "readCentralDirectoryZipEntry", "Lokio/BufferedSource;", "readEocdRecord", "Lokio/internal/EocdRecord;", "readExtra", "", "extraSize", "block", "Lkotlin/Function2;", "readLocalHeader", "centralDirectoryZipEntry", "readOrSkipLocalHeader", "readZip64EocdRecord", "regularRecord", "skipLocalHeader", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ZipFilesKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_NTFS_EXTRA = 10;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = new Function1<ZipEntry, Boolean>() { // from class: okio.internal.ZipFilesKt.openZip.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(ZipEntry it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }
            };
        }
        return openZip(path, fileSystem, function1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0055, code lost:
    
        r12.close();
        r6 = r6 - 20;
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005f, code lost:
    
        if (r6 <= r8) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0061, code lost:
    
        r6 = okio.Okio.buffer(r5.source(r6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x006c, code lost:
    
        r0 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0076, code lost:
    
        if (r0.readIntLe() != okio.internal.ZipFilesKt.ZIP64_LOCATOR_SIGNATURE) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0078, code lost:
    
        r7 = r0.readIntLe();
        r13 = r0.readLongLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0085, code lost:
    
        if (r0.readIntLe() != 1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0087, code lost:
    
        if (r7 != 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0089, code lost:
    
        r7 = okio.Okio.buffer(r5.source(r13));
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0094, code lost:
    
        r0 = r7;
        r13 = r0.readIntLe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x009e, code lost:
    
        if (r13 != okio.internal.ZipFilesKt.ZIP64_EOCD_RECORD_SIGNATURE) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a0, code lost:
    
        r10 = readZip64EocdRecord(r0, r10);
        r13 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a6, code lost:
    
        if (r7 == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a8, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ac, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ae, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00da, code lost:
    
        throw new java.io.IOException("bad zip: expected " + getHex(okio.internal.ZipFilesKt.ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(r13));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00db, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00dd, code lost:
    
        if (r7 != null) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00df, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00e3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e5, code lost:
    
        kotlin.ExceptionsKt.addSuppressed(r0, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e8, code lost:
    
        r0 = r0;
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ea, code lost:
    
        if (r0 == null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ec, code lost:
    
        r13 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ef, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f7, code lost:
    
        throw new java.io.IOException("unsupported zip: spanned");
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f8, code lost:
    
        r7 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00fa, code lost:
    
        if (r6 != null) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00fc, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0100, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0102, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0104, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0106, code lost:
    
        if (r6 != null) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0108, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x010c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x010e, code lost:
    
        kotlin.ExceptionsKt.addSuppressed(r0, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0111, code lost:
    
        r0 = r0;
        r7 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0113, code lost:
    
        if (r0 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0115, code lost:
    
        r7 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0118, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0119, code lost:
    
        r6 = new java.util.ArrayList();
        r5 = okio.Okio.buffer(r5.source(r10.getCentralDirectoryOffset()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0130, code lost:
    
        r0 = r5;
        r7 = r10.getEntryCount();
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x013d, code lost:
    
        r9 = readCentralDirectoryZipEntry(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x014b, code lost:
    
        if (r9.getOffset() < r10.getCentralDirectoryOffset()) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0157, code lost:
    
        if (r23.invoke(r9).booleanValue() != false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0159, code lost:
    
        r6.add(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x015f, code lost:
    
        r16 = r16 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x016b, code lost:
    
        throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x016c, code lost:
    
        r3 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x016e, code lost:
    
        if (r5 != null) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0170, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0174, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0175, code lost:
    
        r12 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0176, code lost:
    
        r20 = r12;
        r12 = r3;
        r3 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x017c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x017d, code lost:
    
        r3 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x017e, code lost:
    
        if (r5 != null) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0180, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0184, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0186, code lost:
    
        kotlin.ExceptionsKt.addSuppressed(r3, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0189, code lost:
    
        if (r3 != null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x018b, code lost:
    
        r12 = r12;
        r3 = new okio.ZipFileSystem(r21, r22, buildIndex(r6), r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0196, code lost:
    
        if (r4 != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0198, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x019b, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x019c, code lost:
    
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0048, code lost:
    
        r10 = readEocdRecord(r12);
        r11 = r12.readUtf8(r10.getCommentByteCount());
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0170 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0115 A[Catch: all -> 0x01cf, TryCatch #12 {all -> 0x01cf, blocks: (B:3:0x001d, B:5:0x002e, B:6:0x0037, B:10:0x0055, B:12:0x0061, B:61:0x0115, B:62:0x0118, B:58:0x010e, B:63:0x0119, B:91:0x018b, B:95:0x019c, B:89:0x0186, B:96:0x019d, B:99:0x01ab, B:100:0x01b2, B:102:0x01b4, B:103:0x01b7, B:104:0x01b8, B:105:0x01ce, B:7:0x003f, B:9:0x0048, B:85:0x0180, B:13:0x006c, B:15:0x0078, B:18:0x0089, B:40:0x00ec, B:41:0x00ef, B:37:0x00e5, B:42:0x00f0, B:43:0x00f7, B:44:0x00f8, B:33:0x00df, B:19:0x0094, B:21:0x00a0, B:28:0x00b0, B:29:0x00da, B:64:0x0130, B:67:0x013d, B:69:0x014d, B:71:0x0159, B:72:0x015f, B:73:0x0164, B:74:0x016b, B:75:0x016c, B:54:0x0108), top: B:136:0x001d, inners: #2, #3, #5, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0118 A[Catch: all -> 0x01cf, TryCatch #12 {all -> 0x01cf, blocks: (B:3:0x001d, B:5:0x002e, B:6:0x0037, B:10:0x0055, B:12:0x0061, B:61:0x0115, B:62:0x0118, B:58:0x010e, B:63:0x0119, B:91:0x018b, B:95:0x019c, B:89:0x0186, B:96:0x019d, B:99:0x01ab, B:100:0x01b2, B:102:0x01b4, B:103:0x01b7, B:104:0x01b8, B:105:0x01ce, B:7:0x003f, B:9:0x0048, B:85:0x0180, B:13:0x006c, B:15:0x0078, B:18:0x0089, B:40:0x00ec, B:41:0x00ef, B:37:0x00e5, B:42:0x00f0, B:43:0x00f7, B:44:0x00f8, B:33:0x00df, B:19:0x0094, B:21:0x00a0, B:28:0x00b0, B:29:0x00da, B:64:0x0130, B:67:0x013d, B:69:0x014d, B:71:0x0159, B:72:0x015f, B:73:0x0164, B:74:0x016b, B:75:0x016c, B:54:0x0108), top: B:136:0x001d, inners: #2, #3, #5, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x013d A[Catch: all -> 0x017c, TryCatch #9 {all -> 0x017c, blocks: (B:64:0x0130, B:67:0x013d, B:69:0x014d, B:71:0x0159, B:72:0x015f, B:73:0x0164, B:74:0x016b, B:75:0x016c), top: B:131:0x0130, outer: #12 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x018b A[Catch: all -> 0x01cf, TRY_LEAVE, TryCatch #12 {all -> 0x01cf, blocks: (B:3:0x001d, B:5:0x002e, B:6:0x0037, B:10:0x0055, B:12:0x0061, B:61:0x0115, B:62:0x0118, B:58:0x010e, B:63:0x0119, B:91:0x018b, B:95:0x019c, B:89:0x0186, B:96:0x019d, B:99:0x01ab, B:100:0x01b2, B:102:0x01b4, B:103:0x01b7, B:104:0x01b8, B:105:0x01ce, B:7:0x003f, B:9:0x0048, B:85:0x0180, B:13:0x006c, B:15:0x0078, B:18:0x0089, B:40:0x00ec, B:41:0x00ef, B:37:0x00e5, B:42:0x00f0, B:43:0x00f7, B:44:0x00f8, B:33:0x00df, B:19:0x0094, B:21:0x00a0, B:28:0x00b0, B:29:0x00da, B:64:0x0130, B:67:0x013d, B:69:0x014d, B:71:0x0159, B:72:0x015f, B:73:0x0164, B:74:0x016b, B:75:0x016c, B:54:0x0108), top: B:136:0x001d, inners: #2, #3, #5, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x019c A[Catch: all -> 0x01cf, TRY_ENTER, TryCatch #12 {all -> 0x01cf, blocks: (B:3:0x001d, B:5:0x002e, B:6:0x0037, B:10:0x0055, B:12:0x0061, B:61:0x0115, B:62:0x0118, B:58:0x010e, B:63:0x0119, B:91:0x018b, B:95:0x019c, B:89:0x0186, B:96:0x019d, B:99:0x01ab, B:100:0x01b2, B:102:0x01b4, B:103:0x01b7, B:104:0x01b8, B:105:0x01ce, B:7:0x003f, B:9:0x0048, B:85:0x0180, B:13:0x006c, B:15:0x0078, B:18:0x0089, B:40:0x00ec, B:41:0x00ef, B:37:0x00e5, B:42:0x00f0, B:43:0x00f7, B:44:0x00f8, B:33:0x00df, B:19:0x0094, B:21:0x00a0, B:28:0x00b0, B:29:0x00da, B:64:0x0130, B:67:0x013d, B:69:0x014d, B:71:0x0159, B:72:0x015f, B:73:0x0164, B:74:0x016b, B:75:0x016c, B:54:0x0108), top: B:136:0x001d, inners: #2, #3, #5, #9, #10 }] */
    /* JADX WARN: Type inference failed for: r3v4, types: [kotlin.Unit] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final okio.ZipFileSystem openZip(okio.Path r21, okio.FileSystem r22, kotlin.jvm.functions.Function1<? super okio.internal.ZipEntry, java.lang.Boolean> r23) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 477
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ZipFilesKt.openZip(okio.Path, okio.FileSystem, kotlin.jvm.functions.Function1):okio.ZipFileSystem");
    }

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        Path path = Path.Companion.get$default(Path.INSTANCE, "/", false, 1, (Object) null);
        Map<Path, ZipEntry> mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(path, new ZipEntry(path, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, null, null, null, 65532, null)));
        Iterator it = CollectionsKt.sortedWith(list, new Comparator() { // from class: okio.internal.ZipFilesKt$buildIndex$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((ZipEntry) t).getCanonicalPath(), ((ZipEntry) t2).getCanonicalPath());
            }
        }).iterator();
        while (it.hasNext()) {
            ZipEntry zipEntry = (ZipEntry) it.next();
            if (mapMutableMapOf.put(zipEntry.getCanonicalPath(), zipEntry) == null) {
                while (true) {
                    Path pathParent = zipEntry.getCanonicalPath().parent();
                    if (pathParent != null) {
                        ZipEntry zipEntry2 = mapMutableMapOf.get(pathParent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(pathParent, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, null, null, null, 65532, null);
                        mapMutableMapOf.put(pathParent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                        it = it;
                    }
                }
            }
        }
        return mapMutableMapOf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ZipEntry readCentralDirectoryZipEntry(final BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        int intLe = bufferedSource.readIntLe();
        if (intLe != CENTRAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(4L);
        short shortLe = bufferedSource.readShortLe();
        int i = shortLe & UShort.MAX_VALUE;
        if ((shortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i));
        }
        int shortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int shortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int shortLe4 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        long intLe2 = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref.LongRef longRef2 = new Ref.LongRef();
        longRef2.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        int shortLe5 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int shortLe6 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int shortLe7 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        bufferedSource.skip(8L);
        final Ref.LongRef longRef3 = new Ref.LongRef();
        longRef3.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        String utf8 = bufferedSource.readUtf8(shortLe5);
        if (StringsKt.contains$default((CharSequence) utf8, (char) 0, false, 2, (Object) null)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        long j = longRef2.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? 8 : 0L;
        if (longRef.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        if (longRef3.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        final long j2 = j;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        readExtra(bufferedSource, shortLe6, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipFilesKt.readCentralDirectoryZipEntry.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) throws IOException {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2, long j3) throws IOException {
                if (i2 != 1) {
                    if (i2 != 10) {
                        return;
                    }
                    if (j3 < 4) {
                        throw new IOException("bad zip: NTFS extra too short");
                    }
                    bufferedSource.skip(4L);
                    final Ref.ObjectRef<Long> objectRef4 = objectRef;
                    final BufferedSource bufferedSource2 = bufferedSource;
                    final Ref.ObjectRef<Long> objectRef5 = objectRef2;
                    final Ref.ObjectRef<Long> objectRef6 = objectRef3;
                    ZipFilesKt.readExtra(bufferedSource, (int) (j3 - 4), new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipFilesKt.readCentralDirectoryZipEntry.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) throws IOException {
                            invoke(num.intValue(), l.longValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Type inference failed for: r4v11, types: [T, java.lang.Long] */
                        /* JADX WARN: Type inference failed for: r4v5, types: [T, java.lang.Long] */
                        /* JADX WARN: Type inference failed for: r4v8, types: [T, java.lang.Long] */
                        public final void invoke(int i3, long j4) throws IOException {
                            if (i3 == 1) {
                                if (objectRef4.element != null) {
                                    throw new IOException("bad zip: NTFS extra attribute tag 0x0001 repeated");
                                }
                                if (j4 != 24) {
                                    throw new IOException("bad zip: NTFS extra attribute tag 0x0001 size != 24");
                                }
                                objectRef4.element = Long.valueOf(bufferedSource2.readLongLe());
                                objectRef5.element = Long.valueOf(bufferedSource2.readLongLe());
                                objectRef6.element = Long.valueOf(bufferedSource2.readLongLe());
                            }
                        }
                    });
                    return;
                }
                if (booleanRef.element) {
                    throw new IOException("bad zip: zip64 extra repeated");
                }
                booleanRef.element = true;
                if (j3 < j2) {
                    throw new IOException("bad zip: zip64 extra too short");
                }
                Ref.LongRef longRef4 = longRef2;
                longRef4.element = longRef4.element == ZipFilesKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : longRef2.element;
                Ref.LongRef longRef5 = longRef;
                longRef5.element = longRef5.element == ZipFilesKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : 0L;
                Ref.LongRef longRef6 = longRef3;
                longRef6.element = longRef6.element == ZipFilesKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : 0L;
            }
        });
        if (j2 > 0 && !booleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        return new ZipEntry(Path.Companion.get$default(Path.INSTANCE, "/", false, 1, (Object) null).resolve(utf8), StringsKt.endsWith$default(utf8, "/", false, 2, (Object) null), bufferedSource.readUtf8(shortLe7), intLe2, longRef.element, longRef2.element, shortLe2, longRef3.element, shortLe4, shortLe3, (Long) objectRef.element, (Long) objectRef2.element, (Long) objectRef3.element, null, null, null, 57344, null);
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) throws IOException {
        int shortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        int shortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        long shortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        if (shortLe3 != (bufferedSource.readShortLe() & UShort.MAX_VALUE) || shortLe != 0 || shortLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(4L);
        return new EocdRecord(shortLe3, MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE & bufferedSource.readIntLe(), bufferedSource.readShortLe() & UShort.MAX_VALUE);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) throws IOException {
        bufferedSource.skip(12L);
        int intLe = bufferedSource.readIntLe();
        int intLe2 = bufferedSource.readIntLe();
        long longLe = bufferedSource.readLongLe();
        if (longLe != bufferedSource.readLongLe() || intLe != 0 || intLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(8L);
        return new EocdRecord(longLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void readExtra(BufferedSource bufferedSource, int i, Function2<? super Integer, ? super Long, Unit> function2) throws IOException {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int shortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            long shortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            long j2 = j - 4;
            if (j2 < shortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(shortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(shortLe), Long.valueOf(shortLe2));
            long size2 = (bufferedSource.getBuffer().size() + shortLe2) - size;
            if (size2 < 0) {
                throw new IOException("unsupported zip: too many bytes processed for " + shortLe);
            }
            if (size2 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j = j2 - shortLe2;
        }
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, null);
    }

    public static final ZipEntry readLocalHeader(BufferedSource bufferedSource, ZipEntry centralDirectoryZipEntry) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(centralDirectoryZipEntry, "centralDirectoryZipEntry");
        ZipEntry orSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, centralDirectoryZipEntry);
        Intrinsics.checkNotNull(orSkipLocalHeader);
        return orSkipLocalHeader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ZipEntry readOrSkipLocalHeader(final BufferedSource bufferedSource, ZipEntry zipEntry) throws IOException {
        int intLe = bufferedSource.readIntLe();
        if (intLe != LOCAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(2L);
        short shortLe = bufferedSource.readShortLe();
        int i = shortLe & UShort.MAX_VALUE;
        if ((shortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i));
        }
        bufferedSource.skip(18L);
        long shortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        int shortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        bufferedSource.skip(shortLe2);
        if (zipEntry == null) {
            bufferedSource.skip(shortLe3);
            return null;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        readExtra(bufferedSource, shortLe3, new Function2<Integer, Long, Unit>() { // from class: okio.internal.ZipFilesKt.readOrSkipLocalHeader.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) throws IOException {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r10v11, types: [T, java.lang.Integer] */
            /* JADX WARN: Type inference failed for: r11v4, types: [T, java.lang.Integer] */
            /* JADX WARN: Type inference failed for: r11v7, types: [T, java.lang.Integer] */
            public final void invoke(int i2, long j) throws IOException {
                if (i2 == ZipFilesKt.HEADER_ID_EXTENDED_TIMESTAMP) {
                    if (j < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    byte b = bufferedSource.readByte();
                    boolean z = (b & 1) == 1;
                    boolean z2 = (b & 2) == 2;
                    boolean z3 = (b & 4) == 4;
                    BufferedSource bufferedSource2 = bufferedSource;
                    long j2 = z ? 5L : 1L;
                    if (z2) {
                        j2 += 4;
                    }
                    if (z3) {
                        j2 += 4;
                    }
                    if (j < j2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        objectRef.element = Integer.valueOf(bufferedSource2.readIntLe());
                    }
                    if (z2) {
                        objectRef2.element = Integer.valueOf(bufferedSource.readIntLe());
                    }
                    if (z3) {
                        objectRef3.element = Integer.valueOf(bufferedSource.readIntLe());
                    }
                }
            }
        });
        return zipEntry.copy$okio((Integer) objectRef.element, (Integer) objectRef2.element, (Integer) objectRef3.element);
    }

    public static final long filetimeToEpochMillis(long j) {
        return (j / 10000) - 11644473600000L;
    }

    public static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        return Long.valueOf(_ZlibJvmKt.datePartsToEpochMillis(((i >> 9) & 127) + 1980, (i >> 5) & 15, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1));
    }

    private static final String getHex(int i) {
        StringBuilder sb = new StringBuilder("0x");
        String string = Integer.toString(i, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return sb.append(string).toString();
    }
}
