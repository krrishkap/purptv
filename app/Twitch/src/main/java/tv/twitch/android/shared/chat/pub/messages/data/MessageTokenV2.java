package tv.twitch.android.shared.chat.pub.messages.data;

import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class MessageTokenV2 {
    public MessageTokenV2(DefaultConstructorMarker var1) {
        throw new NotImplementedError();
    }

    /* ... */

    public static class EmoteToken extends MessageTokenV2 {
        private final String emoteUrl;
        private final String id;
        private final String text;

        public EmoteToken(String var1, String var2, String var3) {
            super(null);
            this.id = var1;
            this.text = var2;
            this.emoteUrl = var3;
        }

        public final String getEmoteUrl() {
            return this.emoteUrl;
        }

        public final String getId() {
            return this.id;
        }

        public final String getText() {
            return this.text;
        }
    }

    public static final class TextToken extends MessageTokenV2 {
        private final String text;

        public TextToken(String var1) {
            super(null);
            this.text = var1;
        }

        public final String getText() {
            return this.text;
        }
    }

    public static final class MentionToken extends MessageTokenV2 {
        private final boolean isLocalUser;
        private final String text;
        private final String userName;

        public MentionToken(String var1, String var2, boolean var3) {
            super(null);
            this.text = var1;
            this.userName = var2;
            this.isLocalUser = var3;
        }

        public final String getUserName() {
            return this.userName;
        }
    }

    public static final class UrlToken extends MessageTokenV2 {
        private final boolean hidden;
        private final String url;

        public UrlToken(String var1, boolean var2) {
            super(null);
            this.url = var1;
            this.hidden = var2;
        }

        /* ... */

        public final boolean getHidden() {
            return this.hidden;
        }

        public final String getUrl() {
            return this.url;
        }

        /* ... */
    }

    /* ... */
}
