package tv.twitch.android.shared.chat;

import java.util.Objects;

import kotlin.jvm.internal.DefaultConstructorMarker;
import tv.twitch.chat.library.IrcEventComponents;

public abstract class ChatChannelEvent {
    public ChatChannelEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ChatChannelEvent() {
    }

    public static final class ChannelUserMessagesCleared extends ChatChannelEvent { // TODO: __REPLACE_CLASS
        private final int channelId;
        private final int userId;

        private final IrcEventComponents components;

        public final int component1() {
            return this.channelId;
        }

        public final int component2() {
            return this.userId;
        }

        public final IrcEventComponents component3() {
            return this.components;
        }

        public final int getChannelId() {
            return this.channelId;
        }

        public final int getUserId() {
            return this.userId;
        }

        public IrcEventComponents getComponents() {
            return components;
        }

        public ChannelUserMessagesCleared(int i, int i2) {
            super(null);
            this.channelId = i;
            this.userId = i2;
            this.components = null;
        }

        public ChannelUserMessagesCleared(int channelId, int userId, IrcEventComponents components) {
            super(null);
            this.channelId = channelId;
            this.userId = userId;
            this.components = components;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ChannelUserMessagesCleared that = (ChannelUserMessagesCleared) o;
            return channelId == that.channelId && userId == that.userId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(channelId, userId);
        }
    }
}
