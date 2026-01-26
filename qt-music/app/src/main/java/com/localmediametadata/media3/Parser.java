package com.localmediametadata.media3;

import android.os.Bundle;
import android.util.Log;
import androidx.media3.common.Metadata;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.id3.ApicFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import androidx.media3.extractor.metadata.vorbis.VorbisComment;
import com.localmediametadata.Utils;

/* loaded from: classes3.dex */
public class Parser {
    private static Bundle handleId3Metadata(Metadata metadata) {
        TextInformationFrame textInformationFrame;
        String str = null;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof TextInformationFrame) {
                textInformationFrame = (TextInformationFrame) entry;
                String upperCase = textInformationFrame.id.toUpperCase();
                Log.d(Utils.LOG, "id3 TextInformationFrame key: " + upperCase + " value: " + textInformationFrame.values.get(0));
                upperCase.hashCode();
                switch (upperCase) {
                    case "TAL":
                    case "TALB":
                    case "TOAL":
                        str3 = textInformationFrame.values.get(0);
                        break;
                    case "TCO":
                    case "TCON":
                        textInformationFrame.values.get(0);
                        break;
                    case "TP1":
                    case "TOPE":
                    case "TPE1":
                        str2 = textInformationFrame.values.get(0);
                        break;
                    case "TOR":
                    case "TDRC":
                        textInformationFrame.values.get(0);
                        break;
                    case "TT2":
                    case "TIT2":
                        str = textInformationFrame.values.get(0);
                        break;
                    case "USLT":
                        textInformationFrame.values.get(0);
                        break;
                }
            } else if (entry instanceof ApicFrame) {
                ApicFrame apicFrame = (ApicFrame) entry;
                Log.d(Utils.LOG, "id3 ApicFrame pictureType: " + apicFrame.pictureType + " pictureData: " + apicFrame.pictureData.length);
            }
        }
        if (str == null && str2 == null && str3 == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        bundle.putString("singer", str2);
        bundle.putString("albumName", str3);
        return bundle;
    }

    private static Bundle handleVorbisCommentMetadata(Metadata metadata) {
        VorbisComment vorbisComment;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof PictureFrame) {
                PictureFrame pictureFrame = (PictureFrame) entry;
                Log.d(Utils.LOG, "vorbis-comment pictureType: " + pictureFrame.pictureType + " pictureData: " + pictureFrame.pictureData.length);
            }
            if (entry instanceof VorbisComment) {
                vorbisComment = (VorbisComment) entry;
                String str5 = vorbisComment.key;
                Log.d(Utils.LOG, "vorbis-comment key: " + str5 + " value: " + vorbisComment.value);
                str5.hashCode();
                switch (str5) {
                    case "LYRICS":
                        str4 = vorbisComment.value;
                        break;
                    case "DATE":
                        String str6 = vorbisComment.value;
                        break;
                    case "ALBUM":
                        str3 = vorbisComment.value;
                        break;
                    case "GENRE":
                        String str7 = vorbisComment.value;
                        break;
                    case "TITLE":
                        str = vorbisComment.value;
                        break;
                    case "ARTIST":
                        str2 = vorbisComment.value;
                        break;
                }
            }
        }
        if (str == null && str2 == null && str3 == null && str4 == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        bundle.putString("singer", str2);
        bundle.putString("albumName", str3);
        return bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.os.Bundle handleQuickTimeMetadata(androidx.media3.common.Metadata r15) {
        /*
            Method dump skipped, instructions count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.localmediametadata.media3.Parser.handleQuickTimeMetadata(androidx.media3.common.Metadata):android.os.Bundle");
    }

    public static Bundle parseMetadata(Metadata metadata) {
        Bundle bundleHandleId3Metadata = handleId3Metadata(metadata);
        if (bundleHandleId3Metadata == null) {
            bundleHandleId3Metadata = handleVorbisCommentMetadata(metadata);
        }
        if (bundleHandleId3Metadata == null) {
            bundleHandleId3Metadata = handleQuickTimeMetadata(metadata);
        }
        return bundleHandleId3Metadata == null ? new Bundle() : bundleHandleId3Metadata;
    }
}
