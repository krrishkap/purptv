package tv.purple.monolith.bridge.di

import dagger.BindsInstance
import dagger.Subcomponent
import tv.twitch.android.core.activities.TwitchHiltActivity
import tv.twitch.android.shared.chat.messages.data.ChatMessageV2Parser
import tv.twitch.android.shared.chat.messages.ui.ChatMessageFactoryV2

/***
 * Provide ChatMessageFactoryV2 & ChatMessageV2Parser objects
 */
@Subcomponent(modules = [TwitchActivityModule::class])
interface TwitchActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: TwitchHiltActivity
        ): TwitchActivityComponent
    }

    fun provideChatMessageFactoryV2(): ChatMessageFactoryV2

    fun provideChatMessageV2Parser(): ChatMessageV2Parser
}