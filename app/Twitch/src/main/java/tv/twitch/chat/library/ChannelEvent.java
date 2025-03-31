package tv.twitch.chat.library;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import tv.twitch.chat.library.model.ChatLiveMessage;

public class ChannelEvent {
    public ChannelEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ChannelEvent() {
    }

    public static final class ChatChannelMessagesCleared extends ChannelEvent {
        public static final ChatChannelMessagesCleared INSTANCE = new ChatChannelMessagesCleared();

        private ChatChannelMessagesCleared() {
            super(null);
        }
    }

    public static final class ChatChannelMessagesReceived extends ChannelEvent {
        public final List<ChatLiveMessage> getMessageList() {
            throw new NotImplementedError();
        }
    }

    public static final class ChatChannelUserMessagesCleared extends ChannelEvent { // TODO: __REPLACE_CLASS
        private final int clearUserId;
        private final IrcEventComponents components;


        public final int getClearUserId() {
            return this.clearUserId;
        }

        public ChatChannelUserMessagesCleared(int i) {
            super(null);
            this.clearUserId = i;
            this.components = null;
        }

        public ChatChannelUserMessagesCleared(int i, IrcEventComponents components) {
            super(null);
            this.clearUserId = i;
            this.components = components;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ChatChannelUserMessagesCleared that = (ChatChannelUserMessagesCleared) o;
            return clearUserId == that.clearUserId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(clearUserId);
        }

        @NonNull
        @Override
        public String toString() {
            return "ChatChannelUserMessagesCleared{" +
                    "clearUserId=" + clearUserId +
                    ", components=" + components +
                    '}';
        }
    }
}
