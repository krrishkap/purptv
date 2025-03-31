package tv.twitch.android.shared.ui.menus.togglemenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.view.View;

import kotlin.NotImplementedError;
import tv.twitch.android.core.mvp.lifecycle.VisibilityProvider;
import tv.twitch.android.shared.ui.menus.SettingActionListener;
import tv.twitch.android.shared.ui.menus.SettingsPreferencesController;
import tv.twitch.android.shared.ui.menus.core.MenuModel;


public class ToggleMenuModel extends MenuModel.SingleItemModel { // TODO: __REMOVE_FINAL
    private String orangeKey = null; // TODO: __INJECT_FIELD

    public ToggleMenuModel(String primaryText, CharSequence secondaryText, String auxiliaryText, Spannable auxiliaryTextSpan, boolean toggleState, boolean isEnabled, Drawable drawable, String eventName, boolean includeBackground, String pillText, Integer pillColor, Integer pillTextColor, SettingsPreferencesController.SettingsPreference settingsPreference, View.OnClickListener onClickListener, Integer topMarginDimenRes) {
        super(null, null, null, null, null, null);
    }

    public final String getEventName() {
        return null;
    }

    @Override
    public ToggleMenuRecyclerItem toRecyclerAdapterItem(Context context, SettingActionListener settingActionListener, VisibilityProvider visibilityProvider) {
        return null;
    }

    public boolean getToggleState() { // TODO: __REMOVE_FINAL
        throw new NotImplementedError();
    }

    public void setOrangeKey(String key) { // TODO: __INJECT_METHOD
        orangeKey = key;
    }

    public String getOrangeKey() { // TODO: __INJECT_METHOD
        return orangeKey;
    }
}
