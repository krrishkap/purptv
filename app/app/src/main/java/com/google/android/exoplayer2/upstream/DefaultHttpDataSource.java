package com.google.android.exoplayer2.upstream;

import com.google.common.base.Predicate;

import tv.purple.monolith.features.proxy.Proxy;
import tv.purple.monolith.models.exception.VirtualImpl;

public class DefaultHttpDataSource {
    private final String userAgent;

    /* ... */

    private DefaultHttpDataSource(String str, int i, int i2, boolean z, HttpDataSource.RequestProperties requestProperties, Predicate<String> predicate, boolean z2) {
        /* ... */

        throw new VirtualImpl();
    }

    public long open(DataSpec dataSpec) {
        dataSpec = Proxy.patchExoPlayerDataSpec(dataSpec); // TODO: __INJECT_CODE

        /* ... */

        return 0;
    }

    /* ... */
}
