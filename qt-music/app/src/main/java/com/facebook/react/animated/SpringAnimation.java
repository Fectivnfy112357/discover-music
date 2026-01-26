package com.facebook.react.animated;

import com.doublesymmetry.trackplayer.service.MusicService;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.layoutanimation.SimpleSpringInterpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpringAnimation.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\t\b\u0000\u0018\u0000 %2\u00020\u0001:\u0002%&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0002J\u0010\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\bH\u0002J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000fR\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/facebook/react/animated/SpringAnimation;", "Lcom/facebook/react/animated/AnimationDriver;", "config", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "currentLoop", "", "currentState", "Lcom/facebook/react/animated/SpringAnimation$PhysicsState;", "displacementFromRestThreshold", "", "endValue", "initialVelocity", "isAtRest", "", "()Z", "isOvershooting", "iterations", "lastTime", "", "originalValue", "overshootClampingEnabled", "restSpeedThreshold", SimpleSpringInterpolator.PARAM_SPRING_DAMPING, "springMass", "springStarted", "springStiffness", "startValue", "timeAccumulator", "advance", "", "realDeltaTime", "getDisplacementDistanceForState", MusicService.STATE_KEY, "resetConfig", "runAnimationStep", "frameTimeNanos", "Companion", "PhysicsState", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SpringAnimation extends AnimationDriver {
    private static final double MAX_DELTA_TIME_SEC = 0.064d;
    private int currentLoop;
    private final PhysicsState currentState;
    private double displacementFromRestThreshold;
    private double endValue;
    private double initialVelocity;
    private int iterations;
    private long lastTime;
    private double originalValue;
    private boolean overshootClampingEnabled;
    private double restSpeedThreshold;
    private double springDamping;
    private double springMass;
    private boolean springStarted;
    private double springStiffness;
    private double startValue;
    private double timeAccumulator;

    public SpringAnimation(ReadableMap config) {
        Intrinsics.checkNotNullParameter(config, "config");
        PhysicsState physicsState = new PhysicsState(0.0d, 0.0d, 3, null);
        this.currentState = physicsState;
        physicsState.setVelocity(config.getDouble("initialVelocity"));
        resetConfig(config);
    }

    /* compiled from: SpringAnimation.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/animated/SpringAnimation$PhysicsState;", "", "position", "", "velocity", "(DD)V", "getPosition", "()D", "setPosition", "(D)V", "getVelocity", "setVelocity", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class PhysicsState {
        private double position;
        private double velocity;

        public PhysicsState() {
            this(0.0d, 0.0d, 3, null);
        }

        public static /* synthetic */ PhysicsState copy$default(PhysicsState physicsState, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                d = physicsState.position;
            }
            if ((i & 2) != 0) {
                d2 = physicsState.velocity;
            }
            return physicsState.copy(d, d2);
        }

        /* renamed from: component1, reason: from getter */
        public final double getPosition() {
            return this.position;
        }

        /* renamed from: component2, reason: from getter */
        public final double getVelocity() {
            return this.velocity;
        }

        public final PhysicsState copy(double position, double velocity) {
            return new PhysicsState(position, velocity);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PhysicsState)) {
                return false;
            }
            PhysicsState physicsState = (PhysicsState) other;
            return Double.compare(this.position, physicsState.position) == 0 && Double.compare(this.velocity, physicsState.velocity) == 0;
        }

        public int hashCode() {
            return (Double.hashCode(this.position) * 31) + Double.hashCode(this.velocity);
        }

        public String toString() {
            return "PhysicsState(position=" + this.position + ", velocity=" + this.velocity + ")";
        }

        public PhysicsState(double d, double d2) {
            this.position = d;
            this.velocity = d2;
        }

        public /* synthetic */ PhysicsState(double d, double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2);
        }

        public final double getPosition() {
            return this.position;
        }

        public final double getVelocity() {
            return this.velocity;
        }

        public final void setPosition(double d) {
            this.position = d;
        }

        public final void setVelocity(double d) {
            this.velocity = d;
        }
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.springStiffness = config.getDouble("stiffness");
        this.springDamping = config.getDouble("damping");
        this.springMass = config.getDouble("mass");
        this.initialVelocity = this.currentState.getVelocity();
        this.endValue = config.getDouble("toValue");
        this.restSpeedThreshold = config.getDouble("restSpeedThreshold");
        this.displacementFromRestThreshold = config.getDouble("restDisplacementThreshold");
        this.overshootClampingEnabled = config.getBoolean("overshootClamping");
        int i = config.hasKey("iterations") ? config.getInt("iterations") : 1;
        this.iterations = i;
        this.hasFinished = i == 0;
        this.currentLoop = 0;
        this.timeAccumulator = 0.0d;
        this.springStarted = false;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void runAnimationStep(long frameTimeNanos) {
        ValueAnimatedNode valueAnimatedNode = this.animatedValue;
        if (valueAnimatedNode == null) {
            throw new IllegalArgumentException("Animated value should not be null".toString());
        }
        long j = frameTimeNanos / 1000000;
        if (!this.springStarted) {
            if (this.currentLoop == 0) {
                this.originalValue = valueAnimatedNode.nodeValue;
                this.currentLoop = 1;
            }
            this.currentState.setPosition(valueAnimatedNode.nodeValue);
            this.startValue = this.currentState.getPosition();
            this.lastTime = j;
            this.timeAccumulator = 0.0d;
            this.springStarted = true;
        }
        advance((j - this.lastTime) / 1000.0d);
        this.lastTime = j;
        valueAnimatedNode.nodeValue = this.currentState.getPosition();
        if (isAtRest()) {
            int i = this.iterations;
            if (i == -1 || this.currentLoop < i) {
                this.springStarted = false;
                valueAnimatedNode.nodeValue = this.originalValue;
                this.currentLoop++;
                return;
            }
            this.hasFinished = true;
        }
    }

    private final double getDisplacementDistanceForState(PhysicsState state) {
        return Math.abs(this.endValue - state.getPosition());
    }

    private final boolean isAtRest() {
        return Math.abs(this.currentState.getVelocity()) <= this.restSpeedThreshold && (getDisplacementDistanceForState(this.currentState) <= this.displacementFromRestThreshold || this.springStiffness == 0.0d);
    }

    private final boolean isOvershooting() {
        return this.springStiffness > 0.0d && ((this.startValue < this.endValue && this.currentState.getPosition() > this.endValue) || (this.startValue > this.endValue && this.currentState.getPosition() < this.endValue));
    }

    private final void advance(double realDeltaTime) {
        double dSin;
        double dSin2;
        if (isAtRest()) {
            return;
        }
        double d = MAX_DELTA_TIME_SEC;
        if (realDeltaTime <= MAX_DELTA_TIME_SEC) {
            d = realDeltaTime;
        }
        this.timeAccumulator += d;
        double d2 = this.springDamping;
        double d3 = this.springMass;
        double d4 = this.springStiffness;
        double d5 = -this.initialVelocity;
        double dSqrt = d2 / (2 * Math.sqrt(d4 * d3));
        double dSqrt2 = Math.sqrt(d4 / d3);
        double dSqrt3 = Math.sqrt(1.0d - (dSqrt * dSqrt)) * dSqrt2;
        double d6 = this.endValue - this.startValue;
        double d7 = this.timeAccumulator;
        if (dSqrt < 1.0d) {
            double dExp = Math.exp((-dSqrt) * dSqrt2 * d7);
            double d8 = dSqrt * dSqrt2;
            double d9 = d5 + (d8 * d6);
            double d10 = d7 * dSqrt3;
            dSin2 = this.endValue - ((((d9 / dSqrt3) * Math.sin(d10)) + (Math.cos(d10) * d6)) * dExp);
            dSin = ((d8 * dExp) * (((Math.sin(d10) * d9) / dSqrt3) + (Math.cos(d10) * d6))) - (((Math.cos(d10) * d9) - ((dSqrt3 * d6) * Math.sin(d10))) * dExp);
        } else {
            double dExp2 = Math.exp((-dSqrt2) * d7);
            double d11 = this.endValue - (((((dSqrt2 * d6) + d5) * d7) + d6) * dExp2);
            dSin = dExp2 * ((d5 * ((d7 * dSqrt2) - 1)) + (d7 * d6 * dSqrt2 * dSqrt2));
            dSin2 = d11;
        }
        this.currentState.setPosition(dSin2);
        this.currentState.setVelocity(dSin);
        if (isAtRest() || (this.overshootClampingEnabled && isOvershooting())) {
            if (this.springStiffness > 0.0d) {
                double d12 = this.endValue;
                this.startValue = d12;
                this.currentState.setPosition(d12);
            } else {
                double position = this.currentState.getPosition();
                this.endValue = position;
                this.startValue = position;
            }
            this.currentState.setVelocity(0.0d);
        }
    }
}
