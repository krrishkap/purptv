package com.google.android.gms.cast.framework;

import android.content.Context;

import java.util.List;

public interface OptionsProvider {
    List<Object> getAdditionalSessionProviders(Context context);

    Object getCastOptions(Context context);
}