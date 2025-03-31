package tv.twitch.android.shared.ui.elements.span;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.ref.WeakReference;

import tv.purple.monolith.models.exception.VirtualImpl;

public class GlideChatImageTarget extends SimpleTarget<Drawable> {
    private UrlDrawable mUrlDrawable;
    private volatile WeakReference<View> mContainer; // TODO: __INJECT_FIELD

    /* ... */

    public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
        /* ... */

        maybeInvalidateContainer(drawable); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    @Override
    public void onDestroy() { // TODO: __INJECT_METHOD
        super.onDestroy();
        clearContainer();
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) { // TODO: __INJECT_METHOD
        super.onLoadCleared(placeholder);
        clearContainer();
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) { // TODO: __INJECT_METHOD
        super.onLoadFailed(errorDrawable);
        clearContainer();
    }

    private Point scaleSquared(float f2, float f3, float f4) { // TODO: __REPLACE_METHOD
        float f5 = 1.0f;
        float f6 = f2 > f4 ? f4 / f2 : 1.0f;
        if (f3 > f4) {
            f5 = f4 / f3;
        }
        UrlDrawable drawable = mUrlDrawable;
        float min = f5;
        if (drawable == null || !drawable.isWide()) {
            min = Math.min(f6, f5);
        }

        return new Point((int) (f2 * min), (int) (f3 * min));
    }

    /* ... */

    public void maybeInvalidateContainer(Drawable drawable) { // TODO: __INJECT_METHOD
        if (drawable == null) {
            clearContainer();
            return;
        }

        Rect bounds = drawable.getBounds();
        if (bounds.right <= bounds.bottom) {
            clearContainer();
            return;
        }
        WeakReference<View> ref = mContainer;
        if (ref != null) {
            View view = ref.get();
            clearContainer();
            if (view instanceof TextView tv) {
                tv.setText(new SpannableStringBuilder(tv.getText()), TextView.BufferType.SPANNABLE);
            }
        }
    }

    public void setContainer(View view) { // TODO: __INJECT_METHOD
        clearContainer();
        if (view != null) {
            mContainer = new WeakReference<>(view);
        }
    }

    private void clearContainer() { // TODO: __INJECT_METHOD
        if (mContainer != null) {
            synchronized (this) {
                if (mContainer != null) {
                    mContainer.clear();
                    mContainer = null;
                }
            }
        }
    }
}
