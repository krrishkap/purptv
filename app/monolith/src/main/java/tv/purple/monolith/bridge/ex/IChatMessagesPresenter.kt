
package tv.purple.monolith.bridge.ex

import io.reactivex.Single
import tv.twitch.android.core.mvp.rxutil.DisposeOn

interface IChatMessagesPresenter {
    // fun addChatHistoryMessage(messageInfo: ChatMessageInfo, channelId: Int) // FIXME: new_version

    fun <T> directSubscribeBridge(
        var1: Single<T>?,
        var2: Function1<T, Unit>?,
        var3: Function1<Throwable?, Unit>?,
        var4: DisposeOn?
    )
}