package tv.twitch.android.shared.chat.command;

import java.util.Set;

import tv.purple.monolith.features.logs.bridge.ChatLogsHook;
import tv.purple.monolith.models.exception.VirtualImpl;

public class ChatCommandInterceptorFactory {
    private final BlockAndUnblockCommandInterceptor blockAndUnblockCommandInterceptor;
    private final VoteCommandInterceptor voteCommandInterceptor;
    private final ChatCommandInterceptor logsCommandInterceptor; // TODO: __INJECT_FIELD

    /* ... */

    public ChatCommandInterceptorFactory() {
        voteCommandInterceptor = null;
        blockAndUnblockCommandInterceptor = null;

        /* ... */

        logsCommandInterceptor = ChatLogsHook.createLogsCommandInterceptor(voteCommandInterceptor, blockAndUnblockCommandInterceptor); // TODO: __INJECT_CODE

        throw new VirtualImpl();
    }

    /* ... */

    public final Set<ChatCommandInterceptor> getInterceptors() {
        return null; // TODO: __INJECT_LOGS_TO_SET
    }
}
