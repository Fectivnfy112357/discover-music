package coil;

import android.graphics.Bitmap;
import coil.EventListener;
import coil.decode.DecodeResult;
import coil.decode.Decoder;
import coil.fetch.FetchResult;
import coil.fetch.Fetcher;
import coil.request.ErrorResult;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.SuccessResult;
import coil.size.Size;
import coil.transition.Transition;
import kotlin.Metadata;

/* compiled from: EventListener.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 +2\u00020\u0001:\u0002+,J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0017J \u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0017J*\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0010H\u0017J \u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0017J\u001a\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0017J\u0018\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0017J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0017H\u0017J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0017J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u001cH\u0017J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u001fH\u0017J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H\u0017J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020%H\u0017J\u0018\u0010&\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020%H\u0017J\u0018\u0010'\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)H\u0017J\u0018\u0010*\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)H\u0017¨\u0006-"}, d2 = {"Lcoil/EventListener;", "Lcoil/request/ImageRequest$Listener;", "decodeEnd", "", "request", "Lcoil/request/ImageRequest;", "decoder", "Lcoil/decode/Decoder;", "options", "Lcoil/request/Options;", "result", "Lcoil/decode/DecodeResult;", "decodeStart", "fetchEnd", "fetcher", "Lcoil/fetch/Fetcher;", "Lcoil/fetch/FetchResult;", "fetchStart", "keyEnd", "output", "", "keyStart", "input", "", "mapEnd", "mapStart", "onCancel", "onError", "Lcoil/request/ErrorResult;", "onStart", "onSuccess", "Lcoil/request/SuccessResult;", "resolveSizeEnd", "size", "Lcoil/size/Size;", "resolveSizeStart", "transformEnd", "Landroid/graphics/Bitmap;", "transformStart", "transitionEnd", "transition", "Lcoil/transition/Transition;", "transitionStart", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public interface EventListener extends ImageRequest.Listener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final EventListener NONE = new EventListener() { // from class: coil.EventListener$Companion$NONE$1
        @Override // coil.EventListener
        public void decodeEnd(ImageRequest imageRequest, Decoder decoder, Options options, DecodeResult decodeResult) {
            EventListener.DefaultImpls.decodeEnd(this, imageRequest, decoder, options, decodeResult);
        }

        @Override // coil.EventListener
        public void decodeStart(ImageRequest imageRequest, Decoder decoder, Options options) {
            EventListener.DefaultImpls.decodeStart(this, imageRequest, decoder, options);
        }

        @Override // coil.EventListener
        public void fetchEnd(ImageRequest imageRequest, Fetcher fetcher, Options options, FetchResult fetchResult) {
            EventListener.DefaultImpls.fetchEnd(this, imageRequest, fetcher, options, fetchResult);
        }

        @Override // coil.EventListener
        public void fetchStart(ImageRequest imageRequest, Fetcher fetcher, Options options) {
            EventListener.DefaultImpls.fetchStart(this, imageRequest, fetcher, options);
        }

        @Override // coil.EventListener
        public void keyEnd(ImageRequest imageRequest, String str) {
            EventListener.DefaultImpls.keyEnd(this, imageRequest, str);
        }

        @Override // coil.EventListener
        public void keyStart(ImageRequest imageRequest, Object obj) {
            EventListener.DefaultImpls.keyStart(this, imageRequest, obj);
        }

        @Override // coil.EventListener
        public void mapEnd(ImageRequest imageRequest, Object obj) {
            EventListener.DefaultImpls.mapEnd(this, imageRequest, obj);
        }

        @Override // coil.EventListener
        public void mapStart(ImageRequest imageRequest, Object obj) {
            EventListener.DefaultImpls.mapStart(this, imageRequest, obj);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onCancel(ImageRequest imageRequest) {
            EventListener.DefaultImpls.onCancel(this, imageRequest);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onError(ImageRequest imageRequest, ErrorResult errorResult) {
            EventListener.DefaultImpls.onError(this, imageRequest, errorResult);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onStart(ImageRequest imageRequest) {
            EventListener.DefaultImpls.onStart(this, imageRequest);
        }

        @Override // coil.EventListener, coil.request.ImageRequest.Listener
        public void onSuccess(ImageRequest imageRequest, SuccessResult successResult) {
            EventListener.DefaultImpls.onSuccess(this, imageRequest, successResult);
        }

        @Override // coil.EventListener
        public void resolveSizeEnd(ImageRequest imageRequest, Size size) {
            EventListener.DefaultImpls.resolveSizeEnd(this, imageRequest, size);
        }

        @Override // coil.EventListener
        public void resolveSizeStart(ImageRequest imageRequest) {
            EventListener.DefaultImpls.resolveSizeStart(this, imageRequest);
        }

        @Override // coil.EventListener
        public void transformEnd(ImageRequest imageRequest, Bitmap bitmap) {
            EventListener.DefaultImpls.transformEnd(this, imageRequest, bitmap);
        }

        @Override // coil.EventListener
        public void transformStart(ImageRequest imageRequest, Bitmap bitmap) {
            EventListener.DefaultImpls.transformStart(this, imageRequest, bitmap);
        }

        @Override // coil.EventListener
        public void transitionEnd(ImageRequest imageRequest, Transition transition) {
            EventListener.DefaultImpls.transitionEnd(this, imageRequest, transition);
        }

        @Override // coil.EventListener
        public void transitionStart(ImageRequest imageRequest, Transition transition) {
            EventListener.DefaultImpls.transitionStart(this, imageRequest, transition);
        }
    };

    /* compiled from: EventListener.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static void decodeEnd(EventListener eventListener, ImageRequest imageRequest, Decoder decoder, Options options, DecodeResult decodeResult) {
        }

        public static void decodeStart(EventListener eventListener, ImageRequest imageRequest, Decoder decoder, Options options) {
        }

        public static void fetchEnd(EventListener eventListener, ImageRequest imageRequest, Fetcher fetcher, Options options, FetchResult fetchResult) {
        }

        public static void fetchStart(EventListener eventListener, ImageRequest imageRequest, Fetcher fetcher, Options options) {
        }

        public static void keyEnd(EventListener eventListener, ImageRequest imageRequest, String str) {
        }

        public static void keyStart(EventListener eventListener, ImageRequest imageRequest, Object obj) {
        }

        public static void mapEnd(EventListener eventListener, ImageRequest imageRequest, Object obj) {
        }

        public static void mapStart(EventListener eventListener, ImageRequest imageRequest, Object obj) {
        }

        public static void onCancel(EventListener eventListener, ImageRequest imageRequest) {
        }

        public static void onError(EventListener eventListener, ImageRequest imageRequest, ErrorResult errorResult) {
        }

        public static void onStart(EventListener eventListener, ImageRequest imageRequest) {
        }

        public static void onSuccess(EventListener eventListener, ImageRequest imageRequest, SuccessResult successResult) {
        }

        public static void resolveSizeEnd(EventListener eventListener, ImageRequest imageRequest, Size size) {
        }

        public static void resolveSizeStart(EventListener eventListener, ImageRequest imageRequest) {
        }

        public static void transformEnd(EventListener eventListener, ImageRequest imageRequest, Bitmap bitmap) {
        }

        public static void transformStart(EventListener eventListener, ImageRequest imageRequest, Bitmap bitmap) {
        }

        public static void transitionEnd(EventListener eventListener, ImageRequest imageRequest, Transition transition) {
        }

        public static void transitionStart(EventListener eventListener, ImageRequest imageRequest, Transition transition) {
        }
    }

    void decodeEnd(ImageRequest request, Decoder decoder, Options options, DecodeResult result);

    void decodeStart(ImageRequest request, Decoder decoder, Options options);

    void fetchEnd(ImageRequest request, Fetcher fetcher, Options options, FetchResult result);

    void fetchStart(ImageRequest request, Fetcher fetcher, Options options);

    void keyEnd(ImageRequest request, String output);

    void keyStart(ImageRequest request, Object input);

    void mapEnd(ImageRequest request, Object output);

    void mapStart(ImageRequest request, Object input);

    @Override // coil.request.ImageRequest.Listener
    void onCancel(ImageRequest request);

    @Override // coil.request.ImageRequest.Listener
    void onError(ImageRequest request, ErrorResult result);

    @Override // coil.request.ImageRequest.Listener
    void onStart(ImageRequest request);

    @Override // coil.request.ImageRequest.Listener
    void onSuccess(ImageRequest request, SuccessResult result);

    void resolveSizeEnd(ImageRequest request, Size size);

    void resolveSizeStart(ImageRequest request);

    void transformEnd(ImageRequest request, Bitmap output);

    void transformStart(ImageRequest request, Bitmap input);

    void transitionEnd(ImageRequest request, Transition transition);

    void transitionStart(ImageRequest request, Transition transition);

    /* compiled from: EventListener.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcoil/EventListener$Factory;", "", "create", "Lcoil/EventListener;", "request", "Lcoil/request/ImageRequest;", "Companion", "coil-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface Factory {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final Factory NONE = new Factory() { // from class: coil.EventListener$Factory$$ExternalSyntheticLambda0
            @Override // coil.EventListener.Factory
            public final EventListener create(ImageRequest imageRequest) {
                return EventListener.Factory.DefaultImpls.m208NONE$lambda0(imageRequest);
            }
        };

        EventListener create(ImageRequest request);

        /* compiled from: EventListener.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public static final class DefaultImpls {
            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: NONE$lambda-0, reason: not valid java name */
            public static EventListener m208NONE$lambda0(ImageRequest imageRequest) {
                return EventListener.NONE;
            }
        }

        /* compiled from: EventListener.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0005"}, d2 = {"Lcoil/EventListener$Factory$Companion;", "", "()V", "NONE", "Lcoil/EventListener$Factory;", "coil-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }
        }
    }

    /* compiled from: EventListener.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0005"}, d2 = {"Lcoil/EventListener$Companion;", "", "()V", "NONE", "Lcoil/EventListener;", "coil-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
