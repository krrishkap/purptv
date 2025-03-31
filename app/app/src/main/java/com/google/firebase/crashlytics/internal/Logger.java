package com.google.firebase.crashlytics.internal;

import tv.purple.monolith.core.DebugLogger;
import tv.purple.monolith.models.exception.VirtualImpl;

public class Logger {
    static final Logger DEFAULT_LOGGER = new Logger("FirebaseCrashlytics");
    private int logLevel = 4;
    private final String tag;

    public Logger(String str) {
        this.tag = str;
    }

    public static Logger getLogger() {
        throw new VirtualImpl();
    }

    private boolean canLog(int i) {
        throw new VirtualImpl();
    }

    public void d(String str, Throwable th) { // TODO: __REPLACE_METHOD
        DebugLogger.debugCrashlytics(str, th);
    }

    public void v(String str, Throwable th) { // TODO: __REPLACE_METHOD
        DebugLogger.debugCrashlytics(str, th);
    }

    public void i(String str, Throwable th) { // TODO: __REPLACE_METHOD
        DebugLogger.debugCrashlytics(str, th);
    }

    public void w(String str, Throwable th) { // TODO: __REPLACE_METHOD
        DebugLogger.debugCrashlytics(str, th);
    }

    public void e(String str, Throwable th) { // TODO: __REPLACE_METHOD
        DebugLogger.debugCrashlytics(str, th);
    }

    public void d(String str) {
        d(str, null);
    }

    public void v(String str) {
        v(str, null);
    }

    public void i(String str) {
        i(str, null);
    }

    public void w(String str) {
        w(str, null);
    }

    public void e(String str) {
        e(str, null);
    }

}
