package com.qingmusic.changqing;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.doublesymmetry.trackplayer.service.MusicService;
import com.facebook.common.util.UriUtil;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.net.HttpHeaders;
import com.umeng.analytics.pro.bm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.random.Random;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SecureKwApiModule.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b-\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\fH\u0002J$\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00132\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fH\u0002J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\bH\u0002J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\fH\u0002J\u0010\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\"H\u0002J\u0010\u0010'\u001a\u00020\f2\u0006\u0010&\u001a\u00020\"H\u0002J\u0010\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u001fH\u0002J\u0012\u0010*\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010\u001cH\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0007J\u0010\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\bH\u0002J\u0010\u00102\u001a\u00020\f2\u0006\u00103\u001a\u00020\bH\u0002J \u00104\u001a\u00020-2\u0006\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0007J\u0012\u00107\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010\u001cH\u0002J\u0010\u00108\u001a\u00020\f2\u0006\u00103\u001a\u00020\bH\u0002J\u0010\u00109\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\bH\u0002J \u0010:\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0007J\b\u0010;\u001a\u00020\fH\u0016J\u0012\u0010<\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010\u001cH\u0002J\u0018\u0010=\u001a\u00020\f2\u0006\u00103\u001a\u00020\b2\u0006\u0010>\u001a\u00020\fH\u0002J\u0012\u0010?\u001a\u00020\f2\b\u0010@\u001a\u0004\u0018\u00010\u001cH\u0002J\u0018\u0010A\u001a\u00020-2\u0006\u00105\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010B\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010C\u001a\u00020-2\u0006\u0010D\u001a\u00020\f2\u0006\u00106\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010E\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010F\u001a\u00020-2\u0006\u0010D\u001a\u00020\f2\u0006\u00106\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0007J\u0010\u0010G\u001a\u00020\u001c2\u0006\u0010H\u001a\u00020\fH\u0002J\u0012\u0010I\u001a\u00020\u001c2\b\u0010J\u001a\u0004\u0018\u00010\u001cH\u0002J\u0010\u0010K\u001a\u00020\u001c2\u0006\u0010L\u001a\u00020\bH\u0002J\u0012\u0010M\u001a\u0004\u0018\u00010\b2\u0006\u00101\u001a\u00020\bH\u0002J\u0012\u0010N\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\bH\u0002J\u0012\u0010O\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\bH\u0002J\u0012\u0010P\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\bH\u0002J\u0012\u0010Q\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\bH\u0002J\u0012\u0010R\u001a\u0004\u0018\u00010\b2\u0006\u0010S\u001a\u00020\bH\u0002J\u0012\u0010T\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\bH\u0002J \u0010U\u001a\u00020-2\u0006\u0010D\u001a\u00020\f2\u0006\u00106\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0007J\u0018\u0010V\u001a\u00020-2\u0006\u0010W\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010X\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010Y\u001a\u00020-2\u0006\u0010D\u001a\u00020\f2\u0006\u00106\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010Z\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010.\u001a\u00020/H\u0007J \u0010[\u001a\u00020-2\u0006\u0010D\u001a\u00020\f2\u0006\u00106\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/qingmusic/changqing/SecureKwApiModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "configLock", "", "detailConfig", "Lorg/json/JSONObject;", "ioScope", "Lkotlinx/coroutines/CoroutineScope;", "buildDetailUrl", "", "platform", "rid", "level", "buildHttpClient", "Lokhttp3/OkHttpClient;", "createMgSignature", "", "time", "text", "decodeHtmlEntities", "str", "eapiEncrypt", "url", "data", "extractQqSongs", "Lorg/json/JSONArray;", "formatDuration", "seconds", "", "formatFileSize", "bytes", "", "formatKwFileSize", "sizeStr", "formatWyPublishTime", "timestamp", "formatWyUpdateTime", "generateRandomString", SessionDescription.ATTR_LENGTH, "getArtistName", "singers", "getDetailConfig", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getKgMusicCover", "rawData", "getKwCover", "song", "getKwRankingData", "rankType", "page", "getMgArtistName", "getMgCover", "getMgDuration", "getMusicDetail", "getName", "getQqArtistName", "getQqCover", "albumId", "getWyArtistName", "artists", "getWyRankingData", "kgMusicDetail", "kgSearchMusic", "searchValue", "mgMusicDetail", "mgSearchMusic", "parseKwQualityInfo", "nMinfo", "parseMgQualityInfo", "audioFormats", "parseQqQualityInfo", "file", "processKgSongDataFromApi", "processKwRankingSong", "processKwSongData", "processMgSongData", "processQqSongData", "processWyRankingSong", MusicService.TRACK_KEY, "processWySongData", "searchMusic", "setDetailConfig", "configJson", "txMusicDetail", "txSearchMusic", "wyMusicDetail", "wySearchMusic", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SecureKwApiModule extends ReactContextBaseJavaModule {
    private final Object configLock;
    private JSONObject detailConfig;
    private final CoroutineScope ioScope;
    private final ReactApplicationContext reactContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecureKwApiModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.ioScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.configLock = new Object();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "SecureKwApi";
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$searchMusic$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$searchMusic$1, reason: invalid class name and case insensitive filesystem */
    static final class C01661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $searchValue;
        int label;
        final /* synthetic */ SecureKwApiModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01661(String str, int i, SecureKwApiModule secureKwApiModule, Promise promise, Continuation<? super C01661> continuation) {
            super(2, continuation);
            this.$searchValue = str;
            this.$page = i;
            this.this$0 = secureKwApiModule;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01661(this.$searchValue, this.$page, this.this$0, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            Response responseExecute;
            String strString;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("KW_LOCAL", "开始本地搜索酷我音乐: " + this.$searchValue + ", 页码: " + this.$page);
                String str2 = "http://search.kuwo.cn/r.s?client=kt&all=" + URLEncoder.encode(this.$searchValue, "UTF-8") + "&pn=" + this.$page + "&rn=30&uid=794762570&ver=kwplayer_ar_9.2.2.1&vipver=1&show_copyright_off=1&newver=1&ft=music&cluster=0&strategy=2012&encoding=utf8&rformat=json&vermerge=1&mobi=1&issubtitle=1";
                Log.d("KW_LOCAL", "搜索URL: " + str2);
                responseExecute = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(str2).addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36").build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                strString = responseBodyBody != null ? responseBodyBody.string() : null;
            } catch (Exception e) {
                Log.e("KW_LOCAL", "酷我搜索异常: " + e.getMessage());
                this.$promise.reject("KW_SEARCH_ERROR", "KW search failed: " + e.getMessage(), e);
            }
            if (!responseExecute.isSuccessful() || (str = strString) == null || str.length() == 0) {
                throw new IOException("HTTP请求失败: " + responseExecute.code());
            }
            Log.d("KW_LOCAL", "收到API响应，长度: " + strString.length());
            JSONObject jSONObject = new JSONObject(strString);
            if (Intrinsics.areEqual(jSONObject.optString("SHOW", ""), SessionDescription.SUPPORTED_SDP_VERSION) && !Intrinsics.areEqual(jSONObject.optString("TOTAL", ""), SessionDescription.SUPPORTED_SDP_VERSION)) {
                throw new IOException("酷我API搜索失败");
            }
            if (!jSONObject.has("abslist")) {
                throw new IOException("响应数据结构错误");
            }
            JSONArray jSONArray = jSONObject.getJSONArray("abslist");
            JSONArray jSONArray2 = new JSONArray();
            Log.d("KW_LOCAL", "开始处理 " + jSONArray.length() + " 条原始数据");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2.has("MUSICRID") && jSONObject2.has("N_MINFO")) {
                    SecureKwApiModule secureKwApiModule = this.this$0;
                    Intrinsics.checkNotNull(jSONObject2);
                    JSONObject jSONObjectProcessKwSongData = secureKwApiModule.processKwSongData(jSONObject2);
                    if (jSONObjectProcessKwSongData != null) {
                        jSONArray2.put(jSONObjectProcessKwSongData);
                    }
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("code", 200);
            jSONObject3.put(NotificationCompat.CATEGORY_MESSAGE, "搜索成功");
            jSONObject3.put("data", jSONArray2);
            jSONObject3.put("_method", "酷我音乐:本地官方API");
            Log.d("KW_LOCAL", "酷我搜索完成，返回" + jSONArray2.length() + "条数据");
            this.$promise.resolve(jSONObject3.toString());
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void searchMusic(String searchValue, int page, Promise promise) {
        Intrinsics.checkNotNullParameter(searchValue, "searchValue");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01661(searchValue, page, this, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$getMusicDetail$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$getMusicDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C01601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $level;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $rid;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01601(String str, String str2, Promise promise, Continuation<? super C01601> continuation) {
            super(2, continuation);
            this.$rid = str;
            this.$level = str2;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureKwApiModule.this.new C01601(this.$rid, this.$level, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Response responseExecute;
            Promise promise;
            Response response;
            String strString;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("DETAIL_DEBUG", "开始请求音乐详情");
                String strBuildDetailUrl = SecureKwApiModule.this.buildDetailUrl("kw", this.$rid, this.$level);
                OkHttpClient okHttpClientBuildHttpClient = SecureKwApiModule.this.buildHttpClient("kw");
                Log.d("DETAIL_URL", "解析URL: " + strBuildDetailUrl);
                responseExecute = okHttpClientBuildHttpClient.newCall(new Request.Builder().url(strBuildDetailUrl).build()).execute();
                promise = this.$promise;
                try {
                    response = responseExecute;
                } finally {
                }
            } catch (Exception e) {
                Log.e("DETAIL_DEBUG", "解析异常: " + e.getMessage());
                this.$promise.reject("DETAIL_ERROR", "Get music detail failed: " + e.getMessage(), e);
            }
            if (!response.isSuccessful()) {
                throw new IOException("HTTP error! Status: " + response.code());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                throw new IOException("Empty response");
            }
            Log.d("DETAIL_DEBUG", "解析成功");
            promise.resolve(strString);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(responseExecute, null);
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void getMusicDetail(String rid, String level, Promise promise) {
        Intrinsics.checkNotNullParameter(rid, "rid");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01601(rid, level, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$txSearchMusic$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$txSearchMusic$1, reason: invalid class name and case insensitive filesystem */
    static final class C01691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $searchValue;
        int label;
        final /* synthetic */ SecureKwApiModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01691(String str, int i, SecureKwApiModule secureKwApiModule, Promise promise, Continuation<? super C01691> continuation) {
            super(2, continuation);
            this.$searchValue = str;
            this.$page = i;
            this.this$0 = secureKwApiModule;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01691(this.$searchValue, this.$page, this.this$0, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            int i;
            Response responseExecute;
            String strString;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("TX_LOCAL", "开始本地搜索QQ音乐: " + this.$searchValue + ", 页码: " + this.$page);
                int i2 = this.$page;
                JSONObject jSONObject = new JSONObject();
                String str2 = this.$searchValue;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("ct", "11");
                jSONObject2.put("cv", "14090508");
                jSONObject2.put("v", "14090508");
                jSONObject2.put("tmeAppID", "qqmusic");
                jSONObject2.put("phonetype", "EBG-AN10");
                jSONObject2.put("deviceScore", "553.47");
                jSONObject2.put("devicelevel", "50");
                jSONObject2.put("newdevicelevel", "20");
                jSONObject2.put("rom", "HuaWei/EMOTION/EmotionUI_14.2.0");
                jSONObject2.put("os_ver", "12");
                jSONObject2.put("OpenUDID", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("OpenUDID2", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("QIMEI36", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("udid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("chid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("aid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("oaid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("taid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("tid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("wid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("uid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("sid", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("modeSwitch", "6");
                jSONObject2.put("teenMode", SessionDescription.SUPPORTED_SDP_VERSION);
                jSONObject2.put("ui_mode", ExifInterface.GPS_MEASUREMENT_2D);
                jSONObject2.put("nettype", "1020");
                jSONObject2.put("v4ip", "");
                Unit unit = Unit.INSTANCE;
                jSONObject.put("comm", jSONObject2);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(bm.e, "music.search.SearchCgiService");
                jSONObject3.put("method", "DoSearchForQQMusicMobile");
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("search_type", 0);
                jSONObject4.put("query", str2);
                jSONObject4.put("page_num", i2);
                jSONObject4.put("num_per_page", 30);
                jSONObject4.put("highlight", 0);
                jSONObject4.put("nqc_flag", 0);
                jSONObject4.put("multi_zhida", 0);
                jSONObject4.put("cat", 2);
                jSONObject4.put("grp", 1);
                jSONObject4.put("sin", 0);
                jSONObject4.put("sem", 0);
                Unit unit2 = Unit.INSTANCE;
                jSONObject3.put("param", jSONObject4);
                Unit unit3 = Unit.INSTANCE;
                jSONObject.put("req", jSONObject3);
                Log.d("TX_LOCAL", "构建请求体完成");
                OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build();
                RequestBody.Companion companion = RequestBody.INSTANCE;
                MediaType mediaType = MediaType.INSTANCE.parse("application/json");
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                responseExecute = okHttpClientBuild.newCall(new Request.Builder().url("https://u.y.qq.com/cgi-bin/musicu.fcg").post(companion.create(mediaType, string)).addHeader("User-Agent", "QQMusic 14090508(android 12)").addHeader("Content-Type", "application/json").build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                strString = responseBodyBody != null ? responseBodyBody.string() : null;
            } catch (Exception e) {
                Log.e("TX_LOCAL", "QQ搜索异常: " + e.getMessage());
                this.$promise.reject("TX_SEARCH_ERROR", "TX search failed: " + e.getMessage(), e);
            }
            if (!responseExecute.isSuccessful() || (str = strString) == null || str.length() == 0) {
                throw new IOException("HTTP请求失败: " + responseExecute.code());
            }
            Log.d("TX_LOCAL", "收到API响应，长度: " + strString.length());
            JSONObject jSONObject5 = new JSONObject(strString);
            if (jSONObject5.optInt("code", -1) != 0) {
                throw new IOException("QQ音乐API返回错误: " + jSONObject5.optString(NotificationCompat.CATEGORY_MESSAGE, "搜索失败"));
            }
            if (!jSONObject5.has("req")) {
                throw new IOException("响应数据结构错误：缺少req字段");
            }
            JSONObject jSONObject6 = jSONObject5.getJSONObject("req");
            if (jSONObject6.optInt("code", -1) != 0) {
                throw new IOException("QQ音乐搜索请求失败: " + jSONObject6.optString(NotificationCompat.CATEGORY_MESSAGE, "搜索请求失败"));
            }
            if (!jSONObject6.has("data")) {
                throw new IOException("响应数据结构错误：缺少data字段");
            }
            SecureKwApiModule secureKwApiModule = this.this$0;
            JSONObject jSONObject7 = jSONObject6.getJSONObject("data");
            Intrinsics.checkNotNullExpressionValue(jSONObject7, "getJSONObject(...)");
            JSONArray jSONArrayExtractQqSongs = secureKwApiModule.extractQqSongs(jSONObject7);
            Log.d("TX_LOCAL", "开始处理 " + jSONArrayExtractQqSongs.length() + " 条原始数据");
            JSONArray jSONArray = new JSONArray();
            int length = jSONArrayExtractQqSongs.length();
            for (i = 0; i < length; i++) {
                JSONObject jSONObject8 = jSONArrayExtractQqSongs.getJSONObject(i);
                SecureKwApiModule secureKwApiModule2 = this.this$0;
                Intrinsics.checkNotNull(jSONObject8);
                JSONObject jSONObjectProcessQqSongData = secureKwApiModule2.processQqSongData(jSONObject8);
                if (jSONObjectProcessQqSongData != null) {
                    jSONArray.put(jSONObjectProcessQqSongData);
                }
            }
            JSONObject jSONObject9 = new JSONObject();
            jSONObject9.put("code", 200);
            jSONObject9.put(NotificationCompat.CATEGORY_MESSAGE, "搜索成功");
            jSONObject9.put("data", jSONArray);
            jSONObject9.put("_method", "QQ音乐:本地官方API");
            Log.d("TX_LOCAL", "QQ搜索完成，返回" + jSONArray.length() + "条数据");
            this.$promise.resolve(jSONObject9.toString());
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void txSearchMusic(String searchValue, int page, Promise promise) {
        Intrinsics.checkNotNullParameter(searchValue, "searchValue");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01691(searchValue, page, this, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$txMusicDetail$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$txMusicDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C01681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $level;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $rid;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01681(String str, String str2, Promise promise, Continuation<? super C01681> continuation) {
            super(2, continuation);
            this.$rid = str;
            this.$level = str2;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureKwApiModule.this.new C01681(this.$rid, this.$level, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Response responseExecute;
            Promise promise;
            Response response;
            String strString;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("DETAIL_DEBUG", "开始请求TX音乐详情");
                String strBuildDetailUrl = SecureKwApiModule.this.buildDetailUrl("qq", this.$rid, this.$level);
                OkHttpClient okHttpClientBuildHttpClient = SecureKwApiModule.this.buildHttpClient("qq");
                Log.d("DETAIL_URL", "解析URL: " + strBuildDetailUrl);
                responseExecute = okHttpClientBuildHttpClient.newCall(new Request.Builder().url(strBuildDetailUrl).build()).execute();
                promise = this.$promise;
                try {
                    response = responseExecute;
                } finally {
                }
            } catch (Exception e) {
                Log.e("DETAIL_DEBUG", "TX音乐详情异常: " + e.getMessage());
                this.$promise.reject("TX_DETAIL_ERROR", "TX detail failed: " + e.getMessage(), e);
            }
            if (!response.isSuccessful()) {
                throw new IOException("HTTP error! Status: " + response.code());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                throw new IOException("Empty response");
            }
            Log.d("DETAIL_DEBUG", "TX音乐详情成功");
            try {
                JSONObject jSONObject = new JSONObject(strString);
                jSONObject.put("_method", "TX详情:配置驱动");
                promise.resolve(jSONObject.toString());
            } catch (Exception unused) {
                promise.resolve(strString);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(responseExecute, null);
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void txMusicDetail(String rid, String level, Promise promise) {
        Intrinsics.checkNotNullParameter(rid, "rid");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01681(rid, level, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$kgSearchMusic$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$kgSearchMusic$1, reason: invalid class name and case insensitive filesystem */
    static final class C01631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $searchValue;
        int label;
        final /* synthetic */ SecureKwApiModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01631(String str, int i, SecureKwApiModule secureKwApiModule, Promise promise, Continuation<? super C01631> continuation) {
            super(2, continuation);
            this.$searchValue = str;
            this.$page = i;
            this.this$0 = secureKwApiModule;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01631(this.$searchValue, this.$page, this.this$0, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            Response responseExecute;
            String strString;
            String str;
            JSONArray jSONArray;
            String str2;
            String str3;
            String str4 = "Grp";
            String str5 = "Audioid";
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("KG_LOCAL", "开始本地搜索KG音乐: " + this.$searchValue + ", 页码: " + this.$page);
                String str6 = "https://songsearch.kugou.com/song_search_v2?keyword=" + URLEncoder.encode(this.$searchValue, "UTF-8") + "&page=" + (this.$page + 1) + "&pagesize=30&userid=0&clientver=&platform=WebFilter&filter=2&iscorrection=1&privilege_filter=0&area_code=1";
                Log.d("KG_LOCAL", "搜索URL: " + str6);
                responseExecute = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(str6).addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36").build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                strString = responseBodyBody != null ? responseBodyBody.string() : null;
            } catch (Exception e) {
                Log.e("KG_LOCAL", "KG搜索异常: " + e.getMessage());
                this.$promise.reject("KG_SEARCH_ERROR", "KG search failed: " + e.getMessage(), e);
            }
            if (!responseExecute.isSuccessful() || (str = strString) == null || str.length() == 0) {
                throw new IOException("HTTP请求失败: " + responseExecute.code());
            }
            Log.d("KG_LOCAL", "收到API响应，长度: " + strString.length());
            JSONObject jSONObject = new JSONObject(strString);
            if (jSONObject.optInt("error_code", -1) != 0) {
                throw new IOException("酷狗API返回错误: " + jSONObject.optString("error", "搜索失败"));
            }
            if (!jSONObject.has("data") || !jSONObject.getJSONObject("data").has("lists")) {
                throw new IOException("响应数据结构错误");
            }
            JSONArray jSONArray2 = jSONObject.getJSONObject("data").getJSONArray("lists");
            JSONArray jSONArray3 = new JSONArray();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Log.d("KG_LOCAL", "开始处理 " + jSONArray2.length() + " 条原始数据");
            int length = jSONArray2.length();
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                if (jSONObject2.has(str5) && jSONObject2.has("FileHash")) {
                    String str7 = String.valueOf(jSONObject2.optInt(str5, i)) + jSONObject2.optString("FileHash", "");
                    if (!linkedHashSet.contains(str7)) {
                        linkedHashSet.add(str7);
                        SecureKwApiModule secureKwApiModule = this.this$0;
                        Intrinsics.checkNotNull(jSONObject2);
                        JSONObject jSONObjectProcessKgSongDataFromApi = secureKwApiModule.processKgSongDataFromApi(jSONObject2);
                        if (jSONObjectProcessKgSongDataFromApi != null) {
                            jSONArray3.put(jSONObjectProcessKgSongDataFromApi);
                        }
                    }
                }
                if (jSONObject2.has(str4) && (jSONObject2.get(str4) instanceof JSONArray)) {
                    JSONArray jSONArray4 = jSONObject2.getJSONArray(str4);
                    int length2 = jSONArray4.length();
                    int i3 = 0;
                    while (i3 < length2) {
                        JSONObject jSONObject3 = jSONArray4.getJSONObject(i3);
                        if (jSONObject3.has(str5) && jSONObject3.has("FileHash")) {
                            jSONArray = jSONArray2;
                            str2 = str4;
                            str3 = str5;
                            String str8 = String.valueOf(jSONObject3.optInt(str5, 0)) + jSONObject3.optString("FileHash", "");
                            if (!linkedHashSet.contains(str8)) {
                                linkedHashSet.add(str8);
                                SecureKwApiModule secureKwApiModule2 = this.this$0;
                                Intrinsics.checkNotNull(jSONObject3);
                                JSONObject jSONObjectProcessKgSongDataFromApi2 = secureKwApiModule2.processKgSongDataFromApi(jSONObject3);
                                if (jSONObjectProcessKgSongDataFromApi2 != null) {
                                    jSONArray3.put(jSONObjectProcessKgSongDataFromApi2);
                                }
                            }
                        } else {
                            jSONArray = jSONArray2;
                            str2 = str4;
                            str3 = str5;
                        }
                        i3++;
                        jSONArray2 = jSONArray;
                        str4 = str2;
                        str5 = str3;
                    }
                }
                i2++;
                jSONArray2 = jSONArray2;
                str4 = str4;
                str5 = str5;
                i = 0;
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("code", 200);
            jSONObject4.put(NotificationCompat.CATEGORY_MESSAGE, "搜索成功");
            jSONObject4.put("data", jSONArray3);
            jSONObject4.put("_method", "KG音乐:本地官方API");
            Log.d("KG_LOCAL", "KG搜索完成，返回" + jSONArray3.length() + "条数据");
            this.$promise.resolve(jSONObject4.toString());
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void kgSearchMusic(String searchValue, int page, Promise promise) {
        Intrinsics.checkNotNullParameter(searchValue, "searchValue");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01631(searchValue, page, this, promise, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject processKgSongDataFromApi(JSONObject rawData) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            String strOptString = rawData.optString("FileHash", "");
            Intrinsics.checkNotNull(strOptString);
            if (strOptString.length() == 0) {
                return null;
            }
            Object upperCase = strOptString.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            jSONObject.put("rid", upperCase);
            String strOptString2 = rawData.optString("SongName", "");
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            jSONObject.put("name", decodeHtmlEntities(strOptString2));
            jSONObject.put("artist", getArtistName(rawData.optJSONArray("Singers")));
            String strOptString3 = rawData.optString("AlbumName", "");
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
            jSONObject.put("album", decodeHtmlEntities(strOptString3));
            jSONObject.put("pic", getKgMusicCover(rawData));
            jSONObject.put("duration", formatDuration(rawData.optInt("Duration", 0)));
            JSONArray jSONArray = new JSONArray();
            long jOptLong = rawData.optLong("FileSize", 0L);
            if (jOptLong > 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("size", formatFileSize(jOptLong));
                jSONObject2.put("quality", "普通音质MP3");
                jSONObject2.put("level", Constants.COLLATION_STANDARD);
                jSONArray.put(jSONObject2);
            }
            long jOptLong2 = rawData.optLong("HQFileSize", 0L);
            if (jOptLong2 > 0) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("size", formatFileSize(jOptLong2));
                jSONObject3.put("quality", "高音质MP3");
                jSONObject3.put("level", "exhigh");
                jSONArray.put(jSONObject3);
            }
            long jOptLong3 = rawData.optLong("SQFileSize", 0L);
            if (jOptLong3 > 0) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("size", formatFileSize(jOptLong3));
                jSONObject4.put("quality", "无损音质FLAC");
                jSONObject4.put("level", "lossless");
                jSONArray.put(jSONObject4);
            }
            long jOptLong4 = rawData.optLong("ResFileSize", 0L);
            if (jOptLong4 > 0) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("size", formatFileSize(jOptLong4));
                jSONObject5.put("quality", "超清母带FLAC");
                jSONObject5.put("level", "jymaster");
                jSONArray.put(jSONObject5);
            }
            if (jSONArray.length() == 0) {
                JSONObject jSONObject6 = new JSONObject();
                jSONObject6.put("size", "未知大小");
                jSONObject6.put("quality", "普通音质MP3");
                jSONObject6.put("level", Constants.COLLATION_STANDARD);
                jSONArray.put(jSONObject6);
            }
            jSONObject.put("quality", jSONArray);
            return jSONObject;
        } catch (Exception e) {
            Log.e("KG_PROCESS", "处理API歌曲数据失败: " + e.getMessage());
            return null;
        }
    }

    private final String getKgMusicCover(JSONObject rawData) {
        String[] strArr = {"Img", "img", "Image", "image", "AlbumImg", "AlbumPic", "ImgUrl", "imgurl", "Pic", "pic", "album_image", "img_url"};
        for (int i = 0; i < 12; i++) {
            String strOptString = rawData.optString(strArr[i], "");
            Intrinsics.checkNotNull(strOptString);
            String str = strOptString;
            if (str.length() > 0) {
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "{size}", false, 2, (Object) null)) {
                    return StringsKt.replace$default(strOptString, "{size}", "720", false, 4, (Object) null);
                }
                if (StringsKt.startsWith$default(strOptString, UriUtil.HTTP_SCHEME, false, 2, (Object) null)) {
                    return strOptString;
                }
                Regex regex = new Regex("^[0-9]{8}/.*\\.(jpg|jpeg|png)$", RegexOption.IGNORE_CASE);
                Regex regex2 = new Regex("^[0-9]{12,}\\.\\.(jpg|jpeg|png)$", RegexOption.IGNORE_CASE);
                if (!regex.matches(str) && !regex2.matches(str)) {
                    return "";
                }
                return "http://imge.kugou.com/stdmusic/720/" + strOptString;
            }
        }
        return "";
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$kgMusicDetail$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$kgMusicDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C01621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $level;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $rid;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01621(String str, String str2, Promise promise, Continuation<? super C01621> continuation) {
            super(2, continuation);
            this.$rid = str;
            this.$level = str2;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureKwApiModule.this.new C01621(this.$rid, this.$level, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Response responseExecute;
            Promise promise;
            Response response;
            String strString;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("DETAIL_DEBUG", "开始请求KG音乐详情");
                String strBuildDetailUrl = SecureKwApiModule.this.buildDetailUrl("kg", this.$rid, this.$level);
                OkHttpClient okHttpClientBuildHttpClient = SecureKwApiModule.this.buildHttpClient("kg");
                Log.d("DETAIL_URL", "解析URL: " + strBuildDetailUrl);
                responseExecute = okHttpClientBuildHttpClient.newCall(new Request.Builder().url(strBuildDetailUrl).build()).execute();
                promise = this.$promise;
                try {
                    response = responseExecute;
                } finally {
                }
            } catch (Exception e) {
                Log.e("DETAIL_DEBUG", "KG音乐详情异常: " + e.getMessage());
                this.$promise.reject("KG_DETAIL_ERROR", "KG detail failed: " + e.getMessage(), e);
            }
            if (!response.isSuccessful()) {
                throw new IOException("HTTP error! Status: " + response.code());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                throw new IOException("Empty response");
            }
            Log.d("DETAIL_DEBUG", "KG音乐详情成功");
            try {
                JSONObject jSONObject = new JSONObject(strString);
                jSONObject.put("_method", "KG详情:配置驱动");
                promise.resolve(jSONObject.toString());
            } catch (Exception unused) {
                promise.resolve(strString);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(responseExecute, null);
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void kgMusicDetail(String rid, String level, Promise promise) {
        Intrinsics.checkNotNullParameter(rid, "rid");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01621(rid, level, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$wySearchMusic$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$wySearchMusic$1, reason: invalid class name and case insensitive filesystem */
    static final class C01711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $searchValue;
        int label;
        final /* synthetic */ SecureKwApiModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01711(String str, int i, SecureKwApiModule secureKwApiModule, Promise promise, Continuation<? super C01711> continuation) {
            super(2, continuation);
            this.$searchValue = str;
            this.$page = i;
            this.this$0 = secureKwApiModule;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01711(this.$searchValue, this.$page, this.this$0, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            Response responseExecute;
            String strString;
            String str;
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("WY_LOCAL", "开始本地搜索网易云音乐: " + this.$searchValue + ", 页码: " + this.$page);
                int i2 = this.$page;
                int i3 = i2 * 30;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("s", this.$searchValue);
                jSONObject.put("type", 1);
                jSONObject.put("limit", 30);
                jSONObject.put("total", i2 == 1);
                jSONObject.put("offset", i3);
                SecureKwApiModule secureKwApiModule = this.this$0;
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                responseExecute = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url("http://interface.music.163.com/eapi/batch").post(new FormBody.Builder(null, 1, 0 == true ? 1 : 0).add("params", secureKwApiModule.eapiEncrypt("/api/cloudsearch/pc", string)).build()).addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36").addHeader(HttpHeaders.ORIGIN, "https://music.163.com").addHeader("Content-Type", "application/x-www-form-urlencoded").build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                strString = responseBodyBody != null ? responseBodyBody.string() : null;
            } catch (Exception e) {
                Log.e("WY_LOCAL", "网易云搜索异常: " + e.getMessage());
                this.$promise.reject("WY_SEARCH_ERROR", "WY search failed: " + e.getMessage(), e);
            }
            if (!responseExecute.isSuccessful() || (str = strString) == null || str.length() == 0) {
                throw new IOException("HTTP请求失败: " + responseExecute.code());
            }
            Log.d("WY_LOCAL", "收到API响应，长度: " + strString.length());
            JSONObject jSONObject2 = new JSONObject(strString);
            String strOptString = jSONObject2.optString("code", "");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            if (strOptString.length() == 0) {
                i = 0;
                if (StringsKt.startsWith$default(strString, "\"", false, 2, (Object) null)) {
                    try {
                        jSONObject2 = new JSONObject(StringsKt.trim(strString, Typography.quote));
                    } catch (Exception unused) {
                    }
                }
            } else {
                i = 0;
            }
            if (jSONObject2.has(SessionDescription.SUPPORTED_SDP_VERSION) && jSONObject2.getJSONObject(SessionDescription.SUPPORTED_SDP_VERSION).has("code")) {
                jSONObject2 = jSONObject2.getJSONObject(SessionDescription.SUPPORTED_SDP_VERSION);
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "getJSONObject(...)");
            }
            if (jSONObject2.optInt("code", -1) != 200) {
                throw new IOException("网易云API返回错误: " + jSONObject2.optString(NotificationCompat.CATEGORY_MESSAGE, "搜索失败"));
            }
            if (!jSONObject2.has("result") || !jSONObject2.getJSONObject("result").has("songs")) {
                throw new IOException("响应数据结构错误");
            }
            JSONArray jSONArray = jSONObject2.getJSONObject("result").getJSONArray("songs");
            JSONArray jSONArray2 = new JSONArray();
            Log.d("WY_LOCAL", "开始处理 " + jSONArray.length() + " 条原始数据");
            int length = jSONArray.length();
            for (int i4 = i; i4 < length; i4++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i4);
                SecureKwApiModule secureKwApiModule2 = this.this$0;
                Intrinsics.checkNotNull(jSONObject3);
                JSONObject jSONObjectProcessWySongData = secureKwApiModule2.processWySongData(jSONObject3);
                if (jSONObjectProcessWySongData != null) {
                    jSONArray2.put(jSONObjectProcessWySongData);
                }
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("code", 200);
            jSONObject4.put(NotificationCompat.CATEGORY_MESSAGE, "搜索成功");
            jSONObject4.put("data", jSONArray2);
            jSONObject4.put("_method", "网易云音乐:本地官方API");
            Log.d("WY_LOCAL", "网易云搜索完成，返回" + jSONArray2.length() + "条数据");
            this.$promise.resolve(jSONObject4.toString());
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void wySearchMusic(String searchValue, int page, Promise promise) {
        Intrinsics.checkNotNullParameter(searchValue, "searchValue");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01711(searchValue, page, this, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$wyMusicDetail$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$wyMusicDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C01701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $level;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $rid;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01701(String str, String str2, Promise promise, Continuation<? super C01701> continuation) {
            super(2, continuation);
            this.$rid = str;
            this.$level = str2;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureKwApiModule.this.new C01701(this.$rid, this.$level, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01701) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Response responseExecute;
            Promise promise;
            Response response;
            String strString;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("DETAIL_DEBUG", "开始请求网易云音乐详情");
                String strBuildDetailUrl = SecureKwApiModule.this.buildDetailUrl("wy", this.$rid, this.$level);
                OkHttpClient okHttpClientBuildHttpClient = SecureKwApiModule.this.buildHttpClient("wy");
                Log.d("DETAIL_URL", "解析URL: " + strBuildDetailUrl);
                responseExecute = okHttpClientBuildHttpClient.newCall(new Request.Builder().url(strBuildDetailUrl).build()).execute();
                promise = this.$promise;
                try {
                    response = responseExecute;
                } finally {
                }
            } catch (Exception e) {
                Log.e("DETAIL_DEBUG", "网易云音乐详情异常: " + e.getMessage());
                this.$promise.reject("WY_DETAIL_ERROR", "WY detail failed: " + e.getMessage(), e);
            }
            if (!response.isSuccessful()) {
                throw new IOException("HTTP error! Status: " + response.code());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                throw new IOException("Empty response");
            }
            Log.d("DETAIL_DEBUG", "网易云音乐详情成功");
            try {
                JSONObject jSONObject = new JSONObject(strString);
                jSONObject.put("_method", "网易云详情:配置驱动");
                promise.resolve(jSONObject.toString());
            } catch (Exception unused) {
                promise.resolve(strString);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(responseExecute, null);
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void wyMusicDetail(String rid, String level, Promise promise) {
        Intrinsics.checkNotNullParameter(rid, "rid");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01701(rid, level, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$mgSearchMusic$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$mgSearchMusic$1, reason: invalid class name and case insensitive filesystem */
    static final class C01651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $searchValue;
        int label;
        final /* synthetic */ SecureKwApiModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01651(String str, int i, SecureKwApiModule secureKwApiModule, Promise promise, Continuation<? super C01651> continuation) {
            super(2, continuation);
            this.$searchValue = str;
            this.$page = i;
            this.this$0 = secureKwApiModule;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01651(this.$searchValue, this.$page, this.this$0, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            Response responseExecute;
            String strString;
            String str;
            String str2;
            String str3 = "copyrightId";
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("MG_LOCAL", "开始本地搜索咪咕音乐: " + this.$searchValue + ", 页码: " + this.$page);
                int i = this.$page;
                String strValueOf = String.valueOf(System.currentTimeMillis());
                Map mapCreateMgSignature = this.this$0.createMgSignature(strValueOf, this.$searchValue);
                String str4 = "https://jadeite.migu.cn/music_search/v3/search/searchAll?isCorrect=0&isCopyright=1&searchSwitch=" + URLEncoder.encode("{\"song\":1,\"album\":0,\"singer\":0,\"tagSong\":1,\"mvSong\":0,\"bestShow\":1,\"songlist\":0,\"lyricSong\":0}", "UTF-8") + "&pageSize=30&text=" + URLEncoder.encode(this.$searchValue, "UTF-8") + "&pageNo=" + i + "&sort=0&sid=USS";
                Log.d("MG_LOCAL", "搜索URL: " + str4);
                OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build();
                Request.Builder builderAddHeader = new Request.Builder().url(str4).addHeader("uiVersion", "A_music_3.6.1");
                Object obj2 = mapCreateMgSignature.get("deviceId");
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                Request.Builder builderAddHeader2 = builderAddHeader.addHeader("deviceId", (String) obj2).addHeader("timestamp", strValueOf);
                Object obj3 = mapCreateMgSignature.get("sign");
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.String");
                responseExecute = okHttpClientBuild.newCall(builderAddHeader2.addHeader("sign", (String) obj3).addHeader("channel", "0146921").addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 11.0.0; zh-cn; MI 11 Build/OPR1.170623.032) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30").build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                strString = responseBodyBody != null ? responseBodyBody.string() : null;
            } catch (Exception e) {
                Log.e("MG_LOCAL", "咪咕搜索异常: " + e.getMessage());
                this.$promise.reject("MG_SEARCH_ERROR", "MG search failed: " + e.getMessage(), e);
            }
            if (!responseExecute.isSuccessful() || (str = strString) == null || str.length() == 0) {
                throw new IOException("HTTP请求失败: " + responseExecute.code());
            }
            Log.d("MG_LOCAL", "收到API响应，长度: " + strString.length());
            JSONObject jSONObject = new JSONObject(strString);
            if (!Intrinsics.areEqual(jSONObject.optString("code", ""), "000000")) {
                throw new IOException("咪咕API返回错误: " + jSONObject.optString("info", "搜索失败"));
            }
            if (!jSONObject.has("songResultData") || !jSONObject.getJSONObject("songResultData").has("resultList")) {
                throw new IOException("响应数据结构错误");
            }
            JSONArray jSONArray = jSONObject.getJSONObject("songResultData").getJSONArray("resultList");
            JSONArray jSONArray2 = new JSONArray();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Log.d("MG_LOCAL", "开始处理 " + jSONArray.length() + " 条原始数据");
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONArray jSONArray3 = jSONArray.getJSONArray(i2);
                int length2 = jSONArray3.length();
                int i3 = 0;
                while (i3 < length2) {
                    JSONObject jSONObject2 = jSONArray3.getJSONObject(i3);
                    if (jSONObject2.has("songId")) {
                        str2 = str3;
                        if (jSONObject2.has(str2)) {
                            String strOptString = jSONObject2.optString(str2, "");
                            Intrinsics.checkNotNull(strOptString);
                            if (strOptString.length() != 0 && !linkedHashSet.contains(strOptString)) {
                                linkedHashSet.add(strOptString);
                                SecureKwApiModule secureKwApiModule = this.this$0;
                                Intrinsics.checkNotNull(jSONObject2);
                                JSONObject jSONObjectProcessMgSongData = secureKwApiModule.processMgSongData(jSONObject2);
                                if (jSONObjectProcessMgSongData != null) {
                                    jSONArray2.put(jSONObjectProcessMgSongData);
                                }
                            }
                        }
                    } else {
                        str2 = str3;
                    }
                    i3++;
                    str3 = str2;
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("code", 200);
            jSONObject3.put(NotificationCompat.CATEGORY_MESSAGE, "搜索成功");
            jSONObject3.put("data", jSONArray2);
            jSONObject3.put("_method", "咪咕音乐:本地官方API");
            Log.d("MG_LOCAL", "咪咕搜索完成，返回" + jSONArray2.length() + "条数据");
            this.$promise.resolve(jSONObject3.toString());
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void mgSearchMusic(String searchValue, int page, Promise promise) {
        Intrinsics.checkNotNullParameter(searchValue, "searchValue");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01651(searchValue, page, this, promise, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$mgMusicDetail$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$mgMusicDetail$1, reason: invalid class name and case insensitive filesystem */
    static final class C01641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $level;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $rid;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01641(String str, String str2, Promise promise, Continuation<? super C01641> continuation) {
            super(2, continuation);
            this.$rid = str;
            this.$level = str2;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureKwApiModule.this.new C01641(this.$rid, this.$level, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Response responseExecute;
            Promise promise;
            Response response;
            String strString;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("DETAIL_DEBUG", "开始请求咪咕音乐详情");
                String strBuildDetailUrl = SecureKwApiModule.this.buildDetailUrl("mg", this.$rid, this.$level);
                OkHttpClient okHttpClientBuildHttpClient = SecureKwApiModule.this.buildHttpClient("mg");
                Log.d("DETAIL_URL", "解析URL: " + strBuildDetailUrl);
                responseExecute = okHttpClientBuildHttpClient.newCall(new Request.Builder().url(strBuildDetailUrl).build()).execute();
                promise = this.$promise;
                try {
                    response = responseExecute;
                } finally {
                }
            } catch (Exception e) {
                Log.e("DETAIL_DEBUG", "咪咕音乐详情异常: " + e.getMessage());
                this.$promise.reject("MG_DETAIL_ERROR", "MG detail failed: " + e.getMessage(), e);
            }
            if (!response.isSuccessful()) {
                throw new IOException("HTTP error! Status: " + response.code());
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                throw new IOException("Empty response");
            }
            Log.d("DETAIL_DEBUG", "咪咕音乐详情成功");
            try {
                JSONObject jSONObject = new JSONObject(strString);
                jSONObject.put("_method", "咪咕详情:配置驱动");
                promise.resolve(jSONObject.toString());
            } catch (Exception unused) {
                promise.resolve(strString);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(responseExecute, null);
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void mgMusicDetail(String rid, String level, Promise promise) {
        Intrinsics.checkNotNullParameter(rid, "rid");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01641(rid, level, promise, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String eapiEncrypt(String url, String data) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = ("nobody" + url + "use" + data + "md5forencrypt").getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNull(bArrDigest);
        String str = url + "-36cd479b6b5-" + data + "-36cd479b6b5-" + ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.qingmusic.changqing.SecureKwApiModule$eapiEncrypt$digestHex$1
            public final CharSequence invoke(byte b) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str2 = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                return str2;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }
        }, 30, (Object) null);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte[] bytes2 = "e82ckenh8dichen8".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        cipher.init(1, new SecretKeySpec(bytes2, "AES"));
        byte[] bytes3 = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes3, "getBytes(...)");
        byte[] bArrDoFinal = cipher.doFinal(bytes3);
        Intrinsics.checkNotNull(bArrDoFinal);
        String upperCase = ArraysKt.joinToString$default(bArrDoFinal, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.qingmusic.changqing.SecureKwApiModule.eapiEncrypt.1
            public final CharSequence invoke(byte b) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str2 = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                return str2;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }
        }, 30, (Object) null).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject processWySongData(JSONObject song) throws JSONException {
        JSONObject jSONObject;
        String str = "";
        try {
            JSONObject jSONObject2 = new JSONObject();
            int iOptInt = song.optInt("id", 0);
            if (iOptInt == 0) {
                return null;
            }
            jSONObject2.put("rid", iOptInt);
            jSONObject2.put("name", song.optString("name", ""));
            jSONObject2.put("artist", getWyArtistName(song.optJSONArray("ar")));
            JSONObject jSONObjectOptJSONObject = song.optJSONObject("al");
            if (jSONObjectOptJSONObject != null) {
                jSONObject2.put("album", jSONObjectOptJSONObject.optString("name", ""));
                jSONObject2.put("pic", jSONObjectOptJSONObject.optString("picUrl", ""));
            } else {
                jSONObject2.put("album", "");
                jSONObject2.put("pic", "");
            }
            jSONObject2.put("duration", formatDuration(song.optInt("dt", 0) / 1000));
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObjectOptJSONObject2 = song.optJSONObject("privilege");
            String strOptString = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("maxBrLevel", "") : null;
            if (strOptString != null) {
                str = strOptString;
            }
            JSONObject jSONObjectOptJSONObject3 = song.optJSONObject(CmcdData.Factory.STREAM_TYPE_LIVE);
            if (jSONObjectOptJSONObject3 == null || !jSONObjectOptJSONObject3.has("size")) {
                jSONObject = jSONObject2;
            } else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject = jSONObject2;
                jSONObject3.put("size", formatFileSize(jSONObjectOptJSONObject3.optLong("size", 0L)));
                jSONObject3.put("quality", "普通音质MP3");
                jSONObject3.put("level", Constants.COLLATION_STANDARD);
                jSONArray.put(jSONObject3);
            }
            JSONObject jSONObjectOptJSONObject4 = song.optJSONObject("h");
            if (jSONObjectOptJSONObject4 != null && jSONObjectOptJSONObject4.has("size")) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("size", formatFileSize(jSONObjectOptJSONObject4.optLong("size", 0L)));
                jSONObject4.put("quality", "高音质MP3");
                jSONObject4.put("level", "exhigh");
                jSONArray.put(jSONObject4);
            }
            JSONObject jSONObjectOptJSONObject5 = song.optJSONObject("sq");
            if (jSONObjectOptJSONObject5 != null && jSONObjectOptJSONObject5.has("size")) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("size", formatFileSize(jSONObjectOptJSONObject5.optLong("size", 0L)));
                jSONObject5.put("quality", "无损音质FLAC");
                jSONObject5.put("level", "lossless");
                jSONArray.put(jSONObject5);
            }
            if (Intrinsics.areEqual(str, "hires")) {
                JSONObject jSONObjectOptJSONObject6 = song.optJSONObject("hr");
                JSONObject jSONObject6 = new JSONObject();
                if (jSONObjectOptJSONObject6 != null && jSONObjectOptJSONObject6.has("size")) {
                    jSONObject6.put("size", formatFileSize(jSONObjectOptJSONObject6.optLong("size", 0L)));
                } else {
                    jSONObject6.put("size", "未知大小");
                }
                jSONObject6.put("quality", "超清母带FLAC");
                jSONObject6.put("level", "jymaster");
                jSONArray.put(jSONObject6);
            }
            if (jSONArray.length() == 0) {
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("size", "未知大小");
                jSONObject7.put("quality", "普通音质MP3");
                jSONObject7.put("level", Constants.COLLATION_STANDARD);
                jSONArray.put(jSONObject7);
            }
            JSONObject jSONObject8 = jSONObject;
            jSONObject8.put("quality", jSONArray);
            return jSONObject8;
        } catch (Exception e) {
            Log.e("WY_PROCESS", "处理网易云歌曲数据失败: " + e.getMessage());
            return null;
        }
    }

    private final String getWyArtistName(JSONArray artists) {
        if (artists == null || artists.length() == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        int length = artists.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = artists.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("name", "");
                Intrinsics.checkNotNull(strOptString);
                if (strOptString.length() > 0) {
                    arrayList.add(strOptString);
                }
            }
        }
        return CollectionsKt.joinToString$default(arrayList, "、", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject processKwSongData(JSONObject song) throws JSONException {
        Integer intOrNull;
        try {
            JSONObject jSONObject = new JSONObject();
            String strOptString = song.optString("MUSICRID", "");
            String strOptString2 = song.optString("N_MINFO", "");
            Intrinsics.checkNotNull(strOptString);
            if (strOptString.length() != 0) {
                Intrinsics.checkNotNull(strOptString2);
                if (strOptString2.length() == 0 || (intOrNull = StringsKt.toIntOrNull(StringsKt.replace$default(strOptString, "MUSIC_", "", false, 4, (Object) null))) == null) {
                    return null;
                }
                jSONObject.put("rid", intOrNull.intValue());
                jSONObject.put("name", song.optString("SONGNAME", ""));
                String strOptString3 = song.optString("ARTIST", "");
                Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                jSONObject.put("artist", StringsKt.replace$default(strOptString3, "&", "、", false, 4, (Object) null));
                jSONObject.put("album", song.optString("ALBUM", ""));
                jSONObject.put("pic", getKwCover(song));
                jSONObject.put("duration", formatDuration(song.optInt("DURATION", 0)));
                jSONObject.put("quality", parseKwQualityInfo(strOptString2));
                return jSONObject;
            }
            return null;
        } catch (Exception e) {
            Log.e("KW_PROCESS", "处理酷我歌曲数据失败: " + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    private final JSONArray parseKwQualityInfo(String nMinfo) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        String str = nMinfo;
        if (str.length() == 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("size", "未知大小");
            jSONObject.put("quality", "普通音质MP3");
            jSONObject.put("level", Constants.COLLATION_STANDARD);
            jSONArray.put(jSONObject);
            return jSONArray;
        }
        List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{";"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            MatchResult matchResultFind$default = Regex.find$default(new Regex("level:(\\w+),bitrate:(\\d+),format:(\\w+),size:([\\w.]+)"), (String) it.next(), 0, 2, null);
            if (matchResultFind$default != null) {
                MatchResult.Destructured destructured = matchResultFind$default.getDestructured();
                destructured.getMatch().getGroupValues().get(1);
                String str2 = destructured.getMatch().getGroupValues().get(2);
                destructured.getMatch().getGroupValues().get(3);
                String str3 = destructured.getMatch().getGroupValues().get(4);
                JSONObject jSONObject2 = new JSONObject();
                switch (str2.hashCode()) {
                    case 48695:
                        if (!str2.equals("128")) {
                            break;
                        } else {
                            jSONObject2.put("size", formatKwFileSize(str3));
                            jSONObject2.put("quality", "普通音质MP3");
                            jSONObject2.put("level", Constants.COLLATION_STANDARD);
                            arrayList.add(jSONObject2);
                            break;
                        }
                    case 50609:
                        if (!str2.equals("320")) {
                            break;
                        } else {
                            jSONObject2.put("size", formatKwFileSize(str3));
                            jSONObject2.put("quality", "高音质MP3");
                            jSONObject2.put("level", "exhigh");
                            arrayList.add(jSONObject2);
                            break;
                        }
                    case 1537214:
                        if (!str2.equals("2000")) {
                            break;
                        } else {
                            jSONObject2.put("size", formatKwFileSize(str3));
                            jSONObject2.put("quality", "无损音质FLAC");
                            jSONObject2.put("level", "lossless");
                            arrayList.add(jSONObject2);
                            break;
                        }
                    case 1596796:
                        if (!str2.equals("4000")) {
                            break;
                        } else {
                            jSONObject2.put("size", formatKwFileSize(str3));
                            jSONObject2.put("quality", "超清母带FLAC");
                            jSONObject2.put("level", "jymaster");
                            arrayList.add(jSONObject2);
                            break;
                        }
                }
            }
        }
        CollectionsKt.reverse(arrayList);
        if (arrayList.isEmpty()) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("size", "未知大小");
            jSONObject3.put("quality", "普通音质MP3");
            jSONObject3.put("level", Constants.COLLATION_STANDARD);
            arrayList.add(jSONObject3);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            jSONArray.put((JSONObject) it2.next());
        }
        return jSONArray;
    }

    private final String getKwCover(JSONObject song) {
        String strOptString = song.optString("web_albumpic_short", "");
        Intrinsics.checkNotNull(strOptString);
        String str = strOptString;
        if (str.length() > 0) {
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "120", false, 2, (Object) null)) {
                return "https://img4.kuwo.cn/star/albumcover/" + StringsKt.replace$default(strOptString, "120", "800", false, 4, (Object) null);
            }
            return "https://img4.kuwo.cn/star/albumcover/" + strOptString;
        }
        String strOptString2 = song.optString("web_artistpic_short", "");
        Intrinsics.checkNotNull(strOptString2);
        String str2 = strOptString2;
        if (str2.length() <= 0) {
            return "";
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "120", false, 2, (Object) null)) {
            return "https://img1.kuwo.cn/star/starheads/" + StringsKt.replace$default(strOptString2, "120", "800", false, 4, (Object) null);
        }
        return "https://img1.kuwo.cn/star/starheads/" + strOptString2;
    }

    private final String formatKwFileSize(String sizeStr) {
        String str = sizeStr;
        if (str.length() == 0 || Intrinsics.areEqual(sizeStr, SessionDescription.SUPPORTED_SDP_VERSION)) {
            return "未知大小";
        }
        if (new Regex("[A-Za-z]").containsMatchIn(str)) {
            String upperCase = sizeStr.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return upperCase;
        }
        Double doubleOrNull = StringsKt.toDoubleOrNull(sizeStr);
        if (doubleOrNull != null) {
            double dDoubleValue = doubleOrNull.doubleValue();
            if (dDoubleValue > 0.0d) {
                int i = 0;
                String[] strArr = {"B", "KB", "MB", "GB"};
                while (dDoubleValue >= 1024.0d && i < 3) {
                    dDoubleValue /= 1024;
                    i++;
                }
                return (Math.round(dDoubleValue * 100) / 100) + " " + strArr[i];
            }
        }
        return "未知大小";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONArray extractQqSongs(JSONObject data) throws JSONException {
        if (data.has("item_song") && (data.get("item_song") instanceof JSONArray)) {
            JSONArray jSONArray = data.getJSONArray("item_song");
            Intrinsics.checkNotNullExpressionValue(jSONArray, "getJSONArray(...)");
            return jSONArray;
        }
        if (data.length() > 0) {
            try {
                Object obj = data.get(data.keys().next());
                if ((obj instanceof JSONObject) && ((JSONObject) obj).has("file")) {
                    JSONArray jSONArray2 = new JSONArray();
                    Iterator<String> itKeys = data.keys();
                    while (itKeys.hasNext()) {
                        jSONArray2.put(data.getJSONObject(itKeys.next()));
                    }
                    return jSONArray2;
                }
            } catch (Exception unused) {
            }
        }
        if (data.has("body") && data.getJSONObject("body").has("item_song")) {
            JSONObject jSONObject = data.getJSONObject("body");
            if (jSONObject.get("item_song") instanceof JSONArray) {
                JSONArray jSONArray3 = jSONObject.getJSONArray("item_song");
                Intrinsics.checkNotNullExpressionValue(jSONArray3, "getJSONArray(...)");
                return jSONArray3;
            }
        }
        if (data.has("song") && (data.get("song") instanceof JSONArray)) {
            JSONArray jSONArray4 = data.getJSONArray("song");
            Intrinsics.checkNotNullExpressionValue(jSONArray4, "getJSONArray(...)");
            return jSONArray4;
        }
        if (data.has("list") && (data.get("list") instanceof JSONArray)) {
            JSONArray jSONArray5 = data.getJSONArray("list");
            Intrinsics.checkNotNullExpressionValue(jSONArray5, "getJSONArray(...)");
            return jSONArray5;
        }
        Log.w("QQ_EXTRACT", "未找到歌曲列表，返回空数组");
        return new JSONArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject processQqSongData(JSONObject song) throws JSONException {
        String str;
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            if (song.has("file") && song.has("mid")) {
                JSONObject jSONObject2 = song.getJSONObject("file");
                jSONObject.put("rid", song.optString("mid", ""));
                String strOptString = song.optString("name", "");
                String strOptString2 = song.optString("title_extra", "");
                Intrinsics.checkNotNull(strOptString2);
                if (strOptString2.length() > 0) {
                    strOptString = strOptString + strOptString2;
                }
                jSONObject.put("name", strOptString);
                jSONObject.put("artist", getQqArtistName(song.optJSONArray("singer")));
                if (song.has("album")) {
                    JSONObject jSONObject3 = song.getJSONObject("album");
                    String strOptString3 = jSONObject3.optString("name", "");
                    Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                    String strOptString4 = jSONObject3.optString("mid", "");
                    Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
                    str = strOptString4;
                    str2 = strOptString3;
                } else {
                    str = "";
                }
                jSONObject.put("album", str2);
                jSONObject.put("pic", getQqCover(song, str));
                jSONObject.put("duration", formatDuration(song.optInt(bm.aY, 0)));
                Intrinsics.checkNotNull(jSONObject2);
                jSONObject.put("quality", parseQqQualityInfo(jSONObject2));
                return jSONObject;
            }
            return null;
        } catch (Exception e) {
            Log.e("QQ_PROCESS", "处理QQ歌曲数据失败: " + e.getMessage());
            return null;
        }
    }

    private final String getQqArtistName(JSONArray singers) {
        if (singers == null || singers.length() == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        int length = singers.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = singers.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("name", "");
                Intrinsics.checkNotNull(strOptString);
                if (strOptString.length() > 0) {
                    arrayList.add(strOptString);
                }
            }
        }
        return CollectionsKt.joinToString$default(arrayList, "、", null, null, 0, null, null, 62, null);
    }

    private final String getQqCover(JSONObject song, String albumId) {
        JSONObject jSONObjectOptJSONObject;
        if (albumId.length() > 0 && !Intrinsics.areEqual(albumId, "空")) {
            return "https://y.gtimg.cn/music/photo_new/T002R500x500M000" + albumId + ".jpg";
        }
        JSONArray jSONArrayOptJSONArray = song.optJSONArray("singer");
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0 && (jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0)) != null) {
            String strOptString = jSONObjectOptJSONObject.optString("mid", "");
            Intrinsics.checkNotNull(strOptString);
            if (strOptString.length() > 0) {
                return "https://y.gtimg.cn/music/photo_new/T001R500x500M000" + strOptString + ".jpg";
            }
        }
        return "";
    }

    private final JSONArray parseQqQualityInfo(JSONObject file) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        long jOptLong = file.optLong("size_128mp3", 0L);
        if (jOptLong > 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("size", formatFileSize(jOptLong));
            jSONObject.put("quality", "普通音质MP3");
            jSONObject.put("level", Constants.COLLATION_STANDARD);
            jSONArray.put(jSONObject);
        }
        long jOptLong2 = file.optLong("size_320mp3", 0L);
        if (jOptLong2 > 0) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("size", formatFileSize(jOptLong2));
            jSONObject2.put("quality", "高音质MP3");
            jSONObject2.put("level", "exhigh");
            jSONArray.put(jSONObject2);
        }
        long jOptLong3 = file.optLong("size_flac", 0L);
        if (jOptLong3 > 0) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("size", formatFileSize(jOptLong3));
            jSONObject3.put("quality", "无损音质FLAC");
            jSONObject3.put("level", "lossless");
            jSONArray.put(jSONObject3);
        }
        long jOptLong4 = file.optLong("size_hires", 0L);
        if (jOptLong4 > 0) {
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("size", formatFileSize(jOptLong4));
            jSONObject4.put("quality", "超清母带FLAC");
            jSONObject4.put("level", "jymaster");
            jSONArray.put(jSONObject4);
        }
        if (jSONArray.length() == 0) {
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("size", "未知大小");
            jSONObject5.put("quality", "普通音质MP3");
            jSONObject5.put("level", Constants.COLLATION_STANDARD);
            jSONArray.put(jSONObject5);
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, String> createMgSignature(String time, String text) throws NoSuchAlgorithmException {
        String str = text + "6cdc72a439cef99a3418d2a78aa28c73yyapp2d16148780a1dcc7408e06336b98cfd50963B7AA0D21511ED807EE5846EC87D20" + time;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNull(bArrDigest);
        return MapsKt.mapOf(TuplesKt.to("sign", ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.qingmusic.changqing.SecureKwApiModule$createMgSignature$sign$1
            public final CharSequence invoke(byte b) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str2 = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                return str2;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }
        }, 30, (Object) null)), TuplesKt.to("deviceId", "963B7AA0D21511ED807EE5846EC87D20"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject processMgSongData(JSONObject song) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            int iOptInt = song.optInt("songId", 0);
            if (iOptInt == 0) {
                return null;
            }
            jSONObject.put("rid", iOptInt);
            jSONObject.put("name", song.optString("name", ""));
            jSONObject.put("artist", getMgArtistName(song.optJSONArray("singerList")));
            jSONObject.put("album", song.optString("album", ""));
            jSONObject.put("pic", getMgCover(song));
            jSONObject.put("duration", formatDuration(getMgDuration(song)));
            jSONObject.put("quality", parseMgQualityInfo(song.optJSONArray("audioFormats")));
            return jSONObject;
        } catch (Exception e) {
            Log.e("MG_PROCESS", "处理咪咕歌曲数据失败: " + e.getMessage());
            return null;
        }
    }

    private final String getMgArtistName(JSONArray singers) {
        if (singers == null || singers.length() == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        int length = singers.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = singers.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("name", "");
                Intrinsics.checkNotNull(strOptString);
                if (strOptString.length() == 0) {
                    strOptString = jSONObjectOptJSONObject.optString("singerName", "");
                }
                Intrinsics.checkNotNull(strOptString);
                if (strOptString.length() > 0) {
                    Intrinsics.checkNotNull(strOptString);
                    arrayList.add(strOptString);
                }
            } else {
                String strOptString2 = singers.optString(i, "");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                if (strOptString2.length() > 0) {
                    String strOptString3 = singers.optString(i, "");
                    Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                    arrayList.add(strOptString3);
                }
            }
        }
        return CollectionsKt.joinToString$default(arrayList, "、", null, null, 0, null, null, 62, null);
    }

    private final String getMgCover(JSONObject song) {
        String[] strArr = {"img3", "img2", "img1"};
        for (int i = 0; i < 3; i++) {
            String strOptString = song.optString(strArr[i], "");
            Intrinsics.checkNotNull(strOptString);
            if (strOptString.length() > 0) {
                return !StringsKt.startsWith$default(strOptString, UriUtil.HTTP_SCHEME, false, 2, (Object) null) ? "http://d.musicapp.migu.cn" + strOptString : strOptString;
            }
        }
        return "";
    }

    private final int getMgDuration(JSONObject song) {
        String[] strArr = {"duration", SessionDescription.ATTR_LENGTH, "timeLength"};
        for (int i = 0; i < 3; i++) {
            int iOptInt = song.optInt(strArr[i], 0);
            if (iOptInt > 0) {
                return iOptInt > 1000 ? iOptInt / 1000 : iOptInt;
            }
        }
        return 0;
    }

    private final JSONArray parseMgQualityInfo(JSONArray audioFormats) throws JSONException {
        JSONArray jSONArray = audioFormats;
        JSONArray jSONArray2 = new JSONArray();
        if (jSONArray == null || audioFormats.length() == 0) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("size", "未知大小");
            jSONObject.put("quality", "普通音质MP3");
            jSONObject.put("level", Constants.COLLATION_STANDARD);
            jSONArray2.put(jSONObject);
            return jSONArray2;
        }
        int length = audioFormats.length();
        int i = 0;
        while (i < length) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("formatType", "");
                long jOptLong = jSONObjectOptJSONObject.optLong("asize", jSONObjectOptJSONObject.optLong("isize", 0L));
                JSONObject jSONObject2 = new JSONObject();
                if (strOptString != null) {
                    int iHashCode = strOptString.hashCode();
                    if (iHashCode != 2313) {
                        if (iHashCode != 2561) {
                            if (iHashCode != 2654) {
                                if (iHashCode == 2760633 && strOptString.equals("ZQ24")) {
                                    jSONObject2.put("size", formatFileSize(jOptLong));
                                    jSONObject2.put("quality", "超清母带FLAC");
                                    jSONObject2.put("level", "jymaster");
                                    jSONArray2.put(jSONObject2);
                                }
                            } else if (strOptString.equals("SQ")) {
                                jSONObject2.put("size", formatFileSize(jOptLong));
                                jSONObject2.put("quality", "无损音质FLAC");
                                jSONObject2.put("level", "lossless");
                                jSONArray2.put(jSONObject2);
                            }
                        } else if (strOptString.equals("PQ")) {
                            jSONObject2.put("size", formatFileSize(jOptLong));
                            jSONObject2.put("quality", "普通音质MP3");
                            jSONObject2.put("level", Constants.COLLATION_STANDARD);
                            jSONArray2.put(jSONObject2);
                        }
                    } else if (strOptString.equals("HQ")) {
                        jSONObject2.put("size", formatFileSize(jOptLong));
                        jSONObject2.put("quality", "高音质MP3");
                        jSONObject2.put("level", "exhigh");
                        jSONArray2.put(jSONObject2);
                    }
                }
            }
            i++;
            jSONArray = audioFormats;
        }
        if (jSONArray2.length() == 0) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("size", "未知大小");
            jSONObject3.put("quality", "普通音质MP3");
            jSONObject3.put("level", Constants.COLLATION_STANDARD);
            jSONArray2.put(jSONObject3);
        }
        return jSONArray2;
    }

    private final String decodeHtmlEntities(String str) {
        if (str.length() == 0) {
            return "";
        }
        return new Regex("&#x([0-9a-fA-F]+);").replace(new Regex("&#(\\d+);").replace(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, "&amp;", "&", false, 4, (Object) null), "&lt;", "<", false, 4, (Object) null), "&gt;", ">", false, 4, (Object) null), "&quot;", "\"", false, 4, (Object) null), "&#39;", "'", false, 4, (Object) null), "&nbsp;", " ", false, 4, (Object) null), new Function1<MatchResult, CharSequence>() { // from class: com.qingmusic.changqing.SecureKwApiModule.decodeHtmlEntities.1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                Integer intOrNull = StringsKt.toIntOrNull(match.getGroupValues().get(1));
                return intOrNull != null ? String.valueOf((char) intOrNull.intValue()) : match.getValue();
            }
        }), new Function1<MatchResult, CharSequence>() { // from class: com.qingmusic.changqing.SecureKwApiModule.decodeHtmlEntities.2
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                Integer intOrNull = StringsKt.toIntOrNull(match.getGroupValues().get(1), 16);
                return intOrNull != null ? String.valueOf((char) intOrNull.intValue()) : match.getValue();
            }
        });
    }

    private final String getArtistName(JSONArray singers) throws JSONException {
        String strOptString;
        if (singers == null || singers.length() == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        int length = singers.length();
        for (int i = 0; i < length; i++) {
            Object obj = singers.get(i);
            if (obj instanceof String) {
                Intrinsics.checkNotNull(obj);
                strOptString = (String) obj;
            } else if (!(obj instanceof JSONObject)) {
                strOptString = "";
            } else {
                JSONObject jSONObject = (JSONObject) obj;
                String strOptString2 = jSONObject.optString("name", "");
                if (strOptString2 == null) {
                    strOptString = jSONObject.optString("singer_name", "");
                    Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                } else {
                    strOptString = strOptString2;
                }
            }
            if (strOptString.length() > 0) {
                arrayList.add(decodeHtmlEntities(strOptString));
            }
        }
        return CollectionsKt.joinToString$default(arrayList, "、", null, null, 0, null, null, 62, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$getKwRankingData$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$getKwRankingData$1, reason: invalid class name and case insensitive filesystem */
    static final class C01591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $rankType;
        int label;
        final /* synthetic */ SecureKwApiModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01591(String str, int i, SecureKwApiModule secureKwApiModule, Promise promise, Continuation<? super C01591> continuation) {
            super(2, continuation);
            this.$rankType = str;
            this.$page = i;
            this.this$0 = secureKwApiModule;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01591(this.$rankType, this.$page, this.this$0, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            Response responseExecute;
            String strString;
            String str;
            String str2 = "16";
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("KW_RANKING", "开始本地获取酷我排行榜: " + this.$rankType + ", 页码: " + this.$page);
                String str3 = (String) MapsKt.mapOf(TuplesKt.to("飙升榜", "93"), TuplesKt.to("新歌榜", "17"), TuplesKt.to("热歌榜", "16"), TuplesKt.to("抖音榜", "158"), TuplesKt.to("万物榜", "176"), TuplesKt.to("畅听榜", "145")).get(this.$rankType);
                if (str3 != null) {
                    str2 = str3;
                }
                String str4 = "https://wapi.kuwo.cn/api/www/bang/bang/musicList?bangId=" + str2 + "&pn=" + this.$page + "&rn=30";
                Log.d("KW_RANKING", "请求URL: " + str4);
                responseExecute = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(str4).addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 Edg/113.0.1774.50").build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                strString = responseBodyBody != null ? responseBodyBody.string() : null;
            } catch (Exception e) {
                Log.e("KW_RANKING", "酷我榜单获取异常: " + e.getMessage());
                this.$promise.reject("KW_RANKING_ERROR", "KW ranking failed: " + e.getMessage(), e);
            }
            if (!responseExecute.isSuccessful() || (str = strString) == null || str.length() == 0) {
                throw new IOException("HTTP请求失败: " + responseExecute.code());
            }
            Log.d("KW_RANKING", "收到酷我API响应，长度: " + strString.length());
            JSONObject jSONObject = new JSONObject(strString);
            if (jSONObject.optInt("code", -1) != 200) {
                throw new IOException("酷我API返回错误: " + jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE, "未知错误"));
            }
            if (!jSONObject.has("data") || !jSONObject.getJSONObject("data").has("musicList")) {
                throw new IOException("响应数据结构错误");
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            JSONArray jSONArray = jSONObject2.getJSONArray("musicList");
            Log.d("KW_RANKING", "开始处理 " + jSONArray.length() + " 条歌曲数据");
            JSONArray jSONArray2 = new JSONArray();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                SecureKwApiModule secureKwApiModule = this.this$0;
                Intrinsics.checkNotNull(jSONObject3);
                JSONObject jSONObjectProcessKwRankingSong = secureKwApiModule.processKwRankingSong(jSONObject3);
                if (jSONObjectProcessKwRankingSong != null) {
                    jSONArray2.put(jSONObjectProcessKwRankingSong);
                }
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("code", 200);
            jSONObject4.put(NotificationCompat.CATEGORY_MESSAGE, this.$rankType + " 请求成功");
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("pic", jSONObject2.optString("img", ""));
            jSONObject5.put("num", jSONObject2.optInt("num", 0));
            jSONObject5.put("time", jSONObject2.optString("pub", ""));
            jSONObject5.put("musicList", jSONArray2);
            Unit unit = Unit.INSTANCE;
            jSONObject4.put("data", jSONObject5);
            Log.d("KW_RANKING", "酷我榜单处理完成，返回" + jSONArray2.length() + "条数据");
            this.$promise.resolve(jSONObject4.toString());
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void getKwRankingData(String rankType, int page, Promise promise) {
        Intrinsics.checkNotNullParameter(rankType, "rankType");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01591(rankType, page, this, promise, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject processKwRankingSong(JSONObject song) throws JSONException {
        String value;
        Integer intOrNull;
        Object objValueOf;
        try {
            JSONObject jSONObject = new JSONObject();
            String strOptString = song.optString("rid", "");
            Regex regex = new Regex("\\d+");
            Intrinsics.checkNotNull(strOptString);
            MatchResult matchResultFind$default = Regex.find$default(regex, strOptString, 0, 2, null);
            if (matchResultFind$default == null || (value = matchResultFind$default.getValue()) == null || (intOrNull = StringsKt.toIntOrNull(value)) == null) {
                return null;
            }
            jSONObject.put("rid", intOrNull.intValue());
            JSONObject jSONObjectOptJSONObject = song.optJSONObject("mvpayinfo");
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optInt("vid", 0) != 0) {
                objValueOf = Integer.valueOf(jSONObjectOptJSONObject.optInt("vid", 0));
            } else {
                objValueOf = "没有vid";
            }
            jSONObject.put("vid", objValueOf);
            jSONObject.put("name", song.optString("name", ""));
            jSONObject.put("artist", song.optString("artist", ""));
            jSONObject.put("album", song.optString("album", ""));
            jSONObject.put("time", song.optString("releaseDate", ""));
            jSONObject.put(bm.aY, song.optString("songTimeMinutes", ""));
            String strOptString2 = song.optString("pic", "");
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            jSONObject.put("pic", StringsKt.replace$default(strOptString2, "http://", "https://", false, 4, (Object) null));
            return jSONObject;
        } catch (Exception e) {
            Log.e("KW_RANKING_PROCESS", "处理酷我榜单歌曲失败: " + e.getMessage());
            return null;
        }
    }

    private final String formatDuration(int seconds) {
        if (seconds <= 0) {
            return "0分0秒";
        }
        return (seconds / 60) + "分" + (seconds % 60) + "秒";
    }

    private final String formatFileSize(long bytes) {
        if (bytes <= 0) {
            return "未知大小";
        }
        int i = 0;
        String[] strArr = {"B", "KB", "MB", "GB"};
        double d = bytes;
        while (d >= 1024.0d && i < 3) {
            d /= 1024;
            i++;
        }
        return (Math.round(d * 100) / 100) + " " + strArr[i];
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$getWyRankingData$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$getWyRankingData$1, reason: invalid class name and case insensitive filesystem */
    static final class C01611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $promise;
        final /* synthetic */ String $rankType;
        int label;
        final /* synthetic */ SecureKwApiModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01611(String str, SecureKwApiModule secureKwApiModule, Promise promise, Continuation<? super C01611> continuation) {
            super(2, continuation);
            this.$rankType = str;
            this.this$0 = secureKwApiModule;
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01611(this.$rankType, this.this$0, this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws JSONException, IOException {
            Response responseExecute;
            String strString;
            String str;
            String str2 = "3778678";
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Log.d("WY_RANKING", "开始本地获取网易云排行榜: " + this.$rankType);
                String str3 = (String) MapsKt.mapOf(TuplesKt.to("飙升榜", "19723756"), TuplesKt.to("热歌榜", "3778678"), TuplesKt.to("原创榜", "2884035"), TuplesKt.to("黑胶vip热歌榜", "7785066739"), TuplesKt.to("新歌榜", "3779629"), TuplesKt.to("说唱榜", "991319590"), TuplesKt.to("古典榜", "71384707"), TuplesKt.to("韩语榜", "745956260"), TuplesKt.to("日语榜", "5059644681"), TuplesKt.to("国风榜", "5059642708"), TuplesKt.to("中文DJ榜", "6886768100"), TuplesKt.to("网络热歌榜", "6723173524"), TuplesKt.to("蛋仔派对听歌榜", "8532443277")).get(this.$rankType);
                if (str3 != null) {
                    str2 = str3;
                }
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str4 = String.format("%d.%d.%d.%d", Arrays.copyOf(new Object[]{Boxing.boxInt(Random.INSTANCE.nextInt(0, 256)), Boxing.boxInt(Random.INSTANCE.nextInt(0, 256)), Boxing.boxInt(Random.INSTANCE.nextInt(0, 256)), Boxing.boxInt(Random.INSTANCE.nextInt(0, 256))}, 4));
                Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
                Map mapMapOf = MapsKt.mapOf(TuplesKt.to("cookie", "MUSIC_U=00506B9C7ADDBF0E4A92F2873A418E53651C384C27E05D3F2961A91AF47F3489B243F0CB842B5F2907CBF7686EC5D4D75027DA04C6AE664D88B6B0994C6071911F93B16B11F8324628CD5D88BCB372690DD17F97268BE59BC76CA9A352EE792B3FCFD008854696174C1647C5F0716EA33A3055C2F11A47455EE84098EC877376090E528A37F7867CFC1A8879984FA6B3FB6107D93D8D1F081F3B3FAE73D26CBA7FB2BC142D4E8B36A26E8A79008E2673F09DD2DC035C8BD613D1B814872A544BE29D6249484B96D3B70D435DC5A20EB16F7A13BBBB3800ADF3DF2F3DD709604C61CE7AFE3148B770317B35EF53462548F1FCA9E4EDDB38136984A19B76D37C4447183F216312C92857C2919FCE9E65D014514AA22A7DACADC52F875DA9F8B93094BBFB704D73345FC7D79180A0BDD2437C975E88C887C646BC2C53DF2DFAF56A9C5EFFFDD0B7A4496527AEC4B273BD93FEE8A97C03792F9B19433F1A91F195BA34"), TuplesKt.to("X-FORWARDED-FOR", str4), TuplesKt.to("CLIENT-IP", str4), TuplesKt.to("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"));
                OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).build();
                Request.Builder builderPost = new Request.Builder().url("https://interface3.music.163.com/api/v6/playlist/detail").post(RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("application/x-www-form-urlencoded"), "id=" + str2 + "&n=300&newDetailPage=1&newStyle=true&s=5"));
                for (Map.Entry entry : mapMapOf.entrySet()) {
                    builderPost.addHeader((String) entry.getKey(), (String) entry.getValue());
                }
                responseExecute = okHttpClientBuild.newCall(builderPost.build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                strString = responseBodyBody != null ? responseBodyBody.string() : null;
            } catch (Exception e) {
                Log.e("WY_RANKING", "网易云榜单获取异常: " + e.getMessage());
                this.$promise.reject("WY_RANKING_ERROR", "WY ranking failed: " + e.getMessage(), e);
            }
            if (!responseExecute.isSuccessful() || (str = strString) == null || str.length() == 0) {
                throw new IOException("HTTP请求失败: " + responseExecute.code());
            }
            Log.d("WY_RANKING", "收到网易云API响应，长度: " + strString.length());
            JSONObject jSONObject = new JSONObject(strString);
            if (!jSONObject.has("playlist") || !jSONObject.getJSONObject("playlist").has("tracks")) {
                throw new IOException("响应数据结构错误");
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("playlist");
            JSONArray jSONArray = jSONObject2.getJSONArray("tracks");
            Log.d("WY_RANKING", "开始处理 " + jSONArray.length() + " 条歌曲数据");
            JSONArray jSONArray2 = new JSONArray();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                SecureKwApiModule secureKwApiModule = this.this$0;
                Intrinsics.checkNotNull(jSONObject3);
                JSONObject jSONObjectProcessWyRankingSong = secureKwApiModule.processWyRankingSong(jSONObject3);
                if (jSONObjectProcessWyRankingSong != null) {
                    jSONArray2.put(jSONObjectProcessWyRankingSong);
                }
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("code", 200);
            jSONObject4.put(NotificationCompat.CATEGORY_MESSAGE, this.$rankType + " 请求成功");
            JSONObject jSONObject5 = new JSONObject();
            SecureKwApiModule secureKwApiModule2 = this.this$0;
            jSONObject5.put("pic", jSONObject2.optString("coverImgUrl", ""));
            jSONObject5.put("num", jSONObject2.optInt("trackCount", 0));
            jSONObject5.put("time", secureKwApiModule2.formatWyUpdateTime(jSONObject2.optLong("trackUpdateTime", 0L)));
            jSONObject5.put("musicList", jSONArray2);
            Unit unit = Unit.INSTANCE;
            jSONObject4.put("data", jSONObject5);
            Log.d("WY_RANKING", "网易云榜单处理完成，返回" + jSONArray2.length() + "条数据");
            this.$promise.resolve(jSONObject4.toString());
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final void getWyRankingData(String rankType, Promise promise) {
        Intrinsics.checkNotNullParameter(rankType, "rankType");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01611(rankType, this, promise, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject processWyRankingSong(JSONObject track) throws JSONException {
        Long longOrNull;
        String str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            String strOptString = track.optString("id", "");
            Intrinsics.checkNotNull(strOptString);
            if (strOptString.length() == 0 || (longOrNull = StringsKt.toLongOrNull(StringsKt.replace$default(strOptString, "-", "", false, 4, (Object) null))) == null) {
                return null;
            }
            long jLongValue = longOrNull.longValue();
            Log.d("WY_RANKING_PROCESS", "原始ID: " + strOptString + ", 清理后ID: " + jLongValue);
            jSONObject.put("rid", jLongValue);
            int iOptInt = track.optInt("mv", 0);
            jSONObject.put("vid", iOptInt > 0 ? Integer.valueOf(iOptInt) : "没有vid");
            jSONObject.put("name", track.optString("name", ""));
            jSONObject.put("artist", getWyArtistName(track.optJSONArray("ar")));
            JSONObject jSONObjectOptJSONObject = track.optJSONObject("al");
            String strOptString2 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("name", "") : null;
            if (strOptString2 == null) {
                strOptString2 = "";
            }
            jSONObject.put("album", strOptString2);
            jSONObject.put("time", formatWyPublishTime(track.optLong("publishTime", 0L)));
            jSONObject.put(bm.aY, formatDuration(track.optInt("dt", 0) / 1000));
            String strOptString3 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("picUrl", "") : null;
            if (strOptString3 != null) {
                str = strOptString3;
            }
            jSONObject.put("pic", str);
            return jSONObject;
        } catch (Exception e) {
            Log.e("WY_RANKING_PROCESS", "处理网易云榜单歌曲失败: " + e.getMessage());
            return null;
        }
    }

    private final String formatWyPublishTime(long timestamp) {
        if (timestamp <= 0) {
            return "";
        }
        try {
            String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(timestamp));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatWyUpdateTime(long timestamp) {
        if (timestamp <= 0) {
            return "";
        }
        try {
            String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(timestamp));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$setDetailConfig$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$setDetailConfig$1, reason: invalid class name and case insensitive filesystem */
    static final class C01671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $configJson;
        final /* synthetic */ Promise $promise;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01671(Promise promise, String str, Continuation<? super C01671> continuation) {
            super(2, continuation);
            this.$promise = promise;
            this.$configJson = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureKwApiModule.this.new C01671(this.$promise, this.$configJson, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    Object obj2 = SecureKwApiModule.this.configLock;
                    SecureKwApiModule secureKwApiModule = SecureKwApiModule.this;
                    String str = this.$configJson;
                    synchronized (obj2) {
                        secureKwApiModule.detailConfig = new JSONObject(str);
                        Log.d("DETAIL_CONFIG", "解析接口配置已更新");
                    }
                    this.$promise.resolve(Boxing.boxBoolean(true));
                } catch (Exception e) {
                    Log.e("DETAIL_CONFIG", "设置解析接口配置失败: " + e.getMessage());
                    this.$promise.reject("CONFIG_ERROR", "Failed to set detail config: " + e.getMessage(), e);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final void setDetailConfig(String configJson, Promise promise) {
        Intrinsics.checkNotNullParameter(configJson, "configJson");
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01671(promise, configJson, null), 3, null);
    }

    /* compiled from: SecureKwApiModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.qingmusic.changqing.SecureKwApiModule$getDetailConfig$1", f = "SecureKwApiModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qingmusic.changqing.SecureKwApiModule$getDetailConfig$1, reason: invalid class name and case insensitive filesystem */
    static final class C01581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $promise;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01581(Promise promise, Continuation<? super C01581> continuation) {
            super(2, continuation);
            this.$promise = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecureKwApiModule.this.new C01581(this.$promise, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String string;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    Object obj2 = SecureKwApiModule.this.configLock;
                    Promise promise = this.$promise;
                    SecureKwApiModule secureKwApiModule = SecureKwApiModule.this;
                    synchronized (obj2) {
                        JSONObject jSONObject = secureKwApiModule.detailConfig;
                        if (jSONObject == null || (string = jSONObject.toString()) == null) {
                            string = "{}";
                        }
                        promise.resolve(string);
                        Unit unit = Unit.INSTANCE;
                    }
                } catch (Exception e) {
                    this.$promise.reject("CONFIG_ERROR", "Failed to get detail config: " + e.getMessage(), e);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final void getDetailConfig(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        BuildersKt__Builders_commonKt.launch$default(this.ioScope, null, null, new C01581(promise, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildDetailUrl(String platform, String rid, String level) throws UnsupportedEncodingException {
        JSONObject jSONObject;
        synchronized (this.configLock) {
            jSONObject = this.detailConfig;
        }
        if (jSONObject == null) {
            throw new IllegalStateException("Detail config not set");
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("endpoints");
        if (jSONObjectOptJSONObject == null) {
            throw new IllegalStateException("No endpoints in config");
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(platform);
        if (jSONObjectOptJSONObject2 == null) {
            throw new IllegalStateException("Platform " + platform + " not found");
        }
        String strOptString = jSONObjectOptJSONObject2.optString("protocol", UriUtil.HTTPS_SCHEME);
        String strOptString2 = jSONObjectOptJSONObject2.optString("domain", "music.haitangw.cc");
        String strOptString3 = jSONObjectOptJSONObject2.optString("path", "");
        JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject("params");
        StringBuilder sb = new StringBuilder(strOptString + "://" + strOptString2 + strOptString3 + "?");
        String strValueOf = String.valueOf(System.currentTimeMillis());
        String strGenerateRandomString = generateRandomString(8);
        if (jSONObjectOptJSONObject3 != null) {
            Iterator<String> itKeys = jSONObjectOptJSONObject3.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.String");
                String str = next;
                String strOptString4 = jSONObjectOptJSONObject3.optString(str, "");
                Intrinsics.checkNotNull(strOptString4);
                String strReplace$default = StringsKt.replace$default(strOptString4, "{rid}", rid, false, 4, (Object) null);
                Intrinsics.checkNotNull(strReplace$default);
                String strReplace$default2 = StringsKt.replace$default(strReplace$default, "{level}", level, false, 4, (Object) null);
                Intrinsics.checkNotNull(strReplace$default2);
                String strReplace$default3 = StringsKt.replace$default(strReplace$default2, "{timestamp}", strValueOf, false, 4, (Object) null);
                Intrinsics.checkNotNull(strReplace$default3);
                String strReplace$default4 = StringsKt.replace$default(strReplace$default3, "{nonce}", strGenerateRandomString, false, 4, (Object) null);
                if (sb.charAt(sb.length() - 1) != '?') {
                    sb.append("&");
                }
                sb.append(str + "=" + URLEncoder.encode(strReplace$default4, "UTF-8"));
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient buildHttpClient(String platform) {
        return new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).build();
    }

    private final String generateRandomString(int length) {
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', 'z'), (Iterable) new CharRange('A', 'Z')), (Iterable) new CharRange('0', '9'));
        IntRange intRange = new IntRange(1, length);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(Integer.valueOf(Random.INSTANCE.nextInt(0, listPlus.size())));
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(Character.valueOf(((Character) listPlus.get(((Number) it2.next()).intValue())).charValue()));
        }
        return CollectionsKt.joinToString$default(arrayList3, "", null, null, 0, null, null, 62, null);
    }
}
