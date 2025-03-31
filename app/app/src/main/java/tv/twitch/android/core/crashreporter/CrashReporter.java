package tv.twitch.android.core.crashreporter;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;

import kotlin.jvm.internal.Intrinsics;
import tv.purple.monolith.features.tracking.bridge.BugsnagUtil;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.util.LogArg;
import tv.twitch.android.util.LogTag;

public final class CrashReporter {
    public static final CrashReporter INSTANCE = new CrashReporter();
    private static volatile Application application;
    private static volatile CrashReporterListener crashReporterListener;

    public interface CrashReporterListener {
        void didDetectCrashDuringPreviousExecution(String str);

        void reportNonFatalToTwitchAnalytics(Throwable th, String str);
    }

    private CrashReporter() {
    }

    public enum LogLevel {
        /* ... */
    }

    public final Application getApplication$core_crashreporter_release() {
        /* ... */

        throw new VirtualImpl();
    }

    public final String getSafeLogMessage(int i) {
        /* ... */

        throw new VirtualImpl();
    }

    public final String getSafeLogMessage(int i, LogArg... args) {
        /* ... */

        throw new VirtualImpl();
    }

    public final String getUnsafeLogMessage(int i, LogArg... args) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void init(Application application2, CrashReporterListener listener) {
        Intrinsics.checkNotNullParameter(application2, "application");
        Intrinsics.checkNotNullParameter(listener, "listener");
        application = application2;
        crashReporterListener = listener;
    }

    public final void e(LogTag tag, int i, LogArg... args) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void log(int i) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void log(int i, LogArg... args) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void log(LogLevel logLevel, LogTag tag, int i, LogArg... args) { // TODO: __REPLACE_METHOD
        String safeLogMessage = CrashReporterContextKt.getSafeLogMessage(application, i, (LogArg[]) Arrays.copyOf(args, args.length));
        BugsnagUtil.logEvent(logLevel.name(), tag.value + ": " + safeLogMessage);
    }

    public static final class NonFatalException extends Exception {
        private NonFatalException(String str) {
            /* ... */

            throw new VirtualImpl();
        }

        public NonFatalException(Context context, int i) {
            /* ... */

            throw new VirtualImpl();
        }

        public NonFatalException(Context context, int i, LogArg... args) {
            /* ... */

            throw new VirtualImpl();
        }
    }

    public final void nonFatal(int i) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void nonFatal(int i, LogArg... args) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void logException(Throwable throwable) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void logException(Throwable th, ExceptionType type) { // TODO: __REPLACE_METHOD
        BugsnagUtil.logException(th, "CrashReporter", type);
    }

    public final void setBool(String key, boolean z) { // TODO: __REPLACE_METHOD
        BugsnagUtil.setBool(key, z);
    }

    public final void setString(String key, String str) { // TODO: __REPLACE_METHOD
        BugsnagUtil.setTag(key, str);
    }

    public final void setInteger(String key, int i) { // TODO: __REPLACE_METHOD
        BugsnagUtil.setInteger(key, i);
    }

    public static /* synthetic */ void setPlayingItem$default(CrashReporter crashReporter, String str, String str2, String str3, String str4, int i, Object obj) {
        /* ... */

        throw new VirtualImpl();
    }

    public final void setPlayingItem(String str, String str2, String str3, String str4) {
        /* ... */

        throw new VirtualImpl();
    }

    public enum ExceptionType {
        NON_FATAL("non_fatal"),
        ANR("anr");

        private final String trackingValue;

        ExceptionType(String str) {
            this.trackingValue = str;
        }

        public final String getTrackingValue() {
            return this.trackingValue;
        }
    }
}