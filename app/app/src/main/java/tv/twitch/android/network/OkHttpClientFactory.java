package tv.twitch.android.network;

import tv.purple.monolith.core.bridge.CoreHook;
import tv.purple.monolith.core.models.flag.Flag;
import tv.purple.monolith.features.proxy.Proxy;
import tv.purple.monolith.features.proxy.bridge.ProxyHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class OkHttpClientFactory {
    /* ... */

    public OkHttpClientFactory() {
        if (Flag.STETHO_INTERCEPTOR.asBoolean()) { // TODO: __INJECT_CODE
            /* ... */
        }

        if (CoreHook.isOkHttpLoggingEnabled()) { // TODO: __INJECT_CODE
            throw new VirtualImpl();
        }

        /* ... */

        ProxyHook.maybeAddInterceptor(null); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}

