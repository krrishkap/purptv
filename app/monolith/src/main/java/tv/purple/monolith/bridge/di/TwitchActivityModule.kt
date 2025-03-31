package tv.purple.monolith.bridge.di

import android.content.ContextWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.internal.GeneratedComponent
import dagger.hilt.internal.GeneratedComponentManager
import dagger.hilt.migration.DisableInstallInCheck
import dagger.internal.Provider
import tv.purple.monolith.core.compat.ClassCompat.callPrivateMethod
import tv.purple.monolith.core.compat.ClassCompat.getPrivateField
import tv.twitch.android.core.activities.TwitchHiltActivity
import tv.twitch.android.provider.experiments.helpers.MessageEffectsExperiment
import tv.twitch.android.routing.routers.WebViewDialogRouter
import tv.twitch.android.shared.badges.ChatBadgeProvider
import tv.twitch.android.shared.chat.messages.data.ChatMessageV2Parser
import tv.twitch.android.shared.chat.pub.messages.ChatMessageTokenizerWrapper
import tv.twitch.android.shared.chat.settings.preferences.ChatSettingsPreferencesFile
import tv.twitch.android.shared.chat.settings.readablecolors.ReadableColorsCache
import tv.twitch.android.shared.community.debug.CommunityDebugSharedPreferences
import tv.twitch.android.shared.emotes.utils.AnimatedEmotesPresenterUtils
import tv.twitch.android.shared.emotes.utils.AnimatedEmotesUrlUtil
import tv.twitch.android.shared.experiments.helpers.ClipEditorPortalExperimentImpl
import tv.twitch.android.shared.preferences.chatfilters.ChatFiltersPreferenceFile
import tv.twitch.android.shared.ui.elements.span.ISpanHelper
import tv.twitch.android.shared.ui.elements.span.annotation.AnnotationSpanHelper
import tv.twitch.android.shared.webview.WebViewDialogFragmentUtil
import tv.twitch.android.util.ColorUtil
import tv.twitch.android.util.NumberFormatUtil
import javax.inject.Named

@Suppress("MoveLambdaOutsideParentheses")
@Module
@DisableInstallInCheck
class TwitchActivityModule {
    @Suppress("UNCHECKED_CAST")
    @Provides
    @Named("activity")
    fun provideActivityGC(activity: TwitchHiltActivity): GeneratedComponent {
        return (activity as GeneratedComponentManager<GeneratedComponent>).generatedComponent()
    }

    @Provides
    fun provideAnimatedEmotesPresenterUtils(gc: GeneratedComponent): AnimatedEmotesPresenterUtils {
        val ref = gc.getPrivateField<Provider<ChatSettingsPreferencesFile>>(
            fieldName = "chatSettingsPreferencesFileProvider"
        ).get()

        return AnimatedEmotesPresenterUtils(ref)
    }

    @Provides
    fun provideChatBadgeProvider(gc: GeneratedComponent): ChatBadgeProvider {
        return gc.getPrivateField<Provider<ChatBadgeProvider>>(
            fieldName = "chatBadgeProvider"
        ).get()
    }

    @Provides
    fun provideChatFiltersPreferenceFile(gc: GeneratedComponent): ChatFiltersPreferenceFile {
        return gc.getPrivateField<Provider<ChatFiltersPreferenceFile>>("chatFiltersPreferenceFileProvider")
            .get()
    }

    @Provides
    fun provideChatMessageTokenizerWrapper(gc: GeneratedComponent): ChatMessageTokenizerWrapper {
        return gc.getPrivateField<Provider<ChatMessageTokenizerWrapper>>(
            fieldName = "chatMessageTokenizerWrapperImplProvider"
        ).get()
    }

    @Provides
    fun provideClipEditorPortalExperimentImpl(gc: GeneratedComponent): ClipEditorPortalExperimentImpl {
        return callPrivateMethod(
            obj = gc, methodName = "clipEditorPortalExperimentImpl"
        )
    }

    @Provides
    fun provideContextWrapper(@Named("activity") activity: GeneratedComponent): ContextWrapper {
        return activity.getPrivateField<Provider<ContextWrapper>>("provideContextWrapperProvider")
            .get()
    }

    @Provides
    fun provideCommunityDebugSharedPreferences(gc: GeneratedComponent): CommunityDebugSharedPreferences {
        return gc.getPrivateField<Provider<CommunityDebugSharedPreferences>>(
            fieldName = "communityDebugSharedPreferencesProvider"
        ).get()
    }

    @Provides
    fun provideNumberFormatUtil(gc: GeneratedComponent): NumberFormatUtil {
        return gc.getPrivateField<Provider<NumberFormatUtil>>(
            fieldName = "provideNumberFormatUtilProvider"
        ).get()
    }

    @Provides
    fun provideAnnotationSpanHelper(gc: GeneratedComponent): AnnotationSpanHelper {
        return callPrivateMethod(
            obj = gc,
            methodName = "annotationSpanHelper"
        )
    }

    @Provides
    fun provideISpanHelper(gc: GeneratedComponent): ISpanHelper {
        return callPrivateMethod(
            obj = gc,
            methodName = "spanHelper"
        )
    }

    @Provides
    fun provideColorUtil(gc: GeneratedComponent): ColorUtil {
        return gc.getPrivateField<Provider<ColorUtil>>(
            fieldName = "colorUtilProvider"
        ).get()
    }

    @Provides
    fun provideReadableColorsCache(gc: GeneratedComponent): ReadableColorsCache {
        return gc.getPrivateField<Provider<ReadableColorsCache>>(
            fieldName = "readableColorsCacheProvider"
        ).get()
    }

    @Provides
    fun provideWebViewDialogFragmentUtil(gc: GeneratedComponent): WebViewDialogRouter {
        return gc.getPrivateField<Provider<WebViewDialogFragmentUtil>>(
            fieldName = "webViewDialogFragmentUtilProvider"
        ).get()
    }

    @Provides
    fun provideMessageEffectsExperiment(): MessageEffectsExperiment {
        return MessageEffectsExperiment { false }
    }

    @Provides
    fun provideChatMessageV2Parser(
        aepu: AnimatedEmotesUrlUtil,
        cbp: ChatBadgeProvider,
        cfpf: ChatFiltersPreferenceFile,
        cspf: ChatSettingsPreferencesFile,
        cmtw: ChatMessageTokenizerWrapper,
        cepei: ClipEditorPortalExperimentImpl,
        cw: ContextWrapper,
        cdspp: CommunityDebugSharedPreferences
    ): ChatMessageV2Parser {
        return ChatMessageV2Parser(
            aepu,
            cbp,
            cfpf,
            cmtw,
            cspf,
            cepei,
            cdspp,
            cw,
            { false }
        )
    }
}