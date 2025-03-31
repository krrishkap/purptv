package tv.twitch.android.shared.ui.elements;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class GlideHelper$generateGlideRequestListener$1 implements RequestListener<Drawable> {

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(Drawable drawable, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
        if (drawable instanceof Animatable) { // TODO: __CHANGE_TYPE
            drawable.setCallback(null);
        }

        return false;
    }
}
