package tv.twitch.android.settings.main;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import tv.purple.monolith.features.settings.bridge.PurpleTVSettingsHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class MainSettingsScreen {
    /* ... */

    public static final void MainSettingsScreen(final boolean z10, final boolean z11, final boolean z12, final boolean z13, final boolean z14, final String usernameString, String appVersionString, String appBuildDateString, final Function0<Unit> onRecapItemClicked, final Function0<Unit> onCreatorDashboardItemClicked, final Function0<Unit> onScheduleManagerClicked, final Function0<Unit> onStreamManagerItemClicked, final Function0<Unit> onSubscriptionsClicked, final Function0<Unit> onDropsAndRewardsClicked, final Function0<Unit> onTwitchTurboClicked, final Function0<Unit> onAccountItemClicked, final Function0<Unit> onPreferencesItemClicked, final Function0<Unit> onNotificationsItemClicked, final Function0<Unit> onContentPreferencesItemClicked, final Function0<Unit> onSecurityAndPrivacyItemClicked, final Function0<Unit> onConnectionsClicked, final Function0<Unit> onSystemItemClicked, final Function0<Unit> onCommunityGuidelinesItemClicked, final Function0<Unit> onHelpItemClicked, final Function0<Unit> onTermsOfServiceItemClicked, final Function0<Unit> onPrivacyNoticeItemClicked, final Function0<Unit> onEntityInformationClicked, final Function0<Unit> onLogoutClick, final Function0<Unit> onConfirmLogoutClick, Object composer, final int i10, final int i11, final int i12) {
        appVersionString = PurpleTVSettingsHook.hookAppVersionString(appVersionString); // TODO: __INJECT_CODE
        appBuildDateString = PurpleTVSettingsHook.hookAppBuildDateString(appBuildDateString); // TODO: __INJECT_CODE

        /* ... */
    }

    public static final void SystemAndLegalSection(final boolean z10, final boolean z11, final Function0<Unit> onSystemItemClicked, final Function0<Unit> onCommunityGuidelinesItemClicked, final Function0<Unit> onHelpItemClicked, final Function0<Unit> onTermsOfServiceItemClicked, final Function0<Unit> onPrivacyNoticeItemClicked, final Function0<Unit> onEntityInformationClicked, Object composer, final int i10) {
        int systemId = 0;

        /* ... */

        systemId = PurpleTVSettingsHook.getPurpleTVSettingsStringId(); // TODO: __INJECT_CODE

        /* ... */

        throw new VirtualImpl();
    }

    /* ... */
}