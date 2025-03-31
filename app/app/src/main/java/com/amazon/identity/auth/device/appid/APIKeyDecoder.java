package com.amazon.identity.auth.device.appid;

import android.content.Context;
import android.content.pm.PackageManager;

import com.amazon.identity.auth.device.utils.HashAlgorithm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import tv.purple.monolith.core.SpoofingUtil;
import tv.purple.monolith.models.exception.VirtualImpl;

public class APIKeyDecoder {
    /* ... */

    private static void verifyPayload(String str, JSONObject jSONObject, Context context) throws SecurityException, JSONException, PackageManager.NameNotFoundException, CertificateException, NoSuchAlgorithmException, IOException {
        str = SpoofingUtil.spoofPackageName(str); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    private static void verifySignature(String str, String str2, HashAlgorithm hashAlgorithm, Context context) {
        str = SpoofingUtil.spoofRealPackageName(str); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}
