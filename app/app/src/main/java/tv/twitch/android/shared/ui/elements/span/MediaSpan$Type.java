package tv.twitch.android.shared.ui.elements.span;

import tv.purple.monolith.features.chat.bridge.ChatHook;

public enum MediaSpan$Type {
    AnimatedBit(24.0F),
    Badge(18.0F),
    Emote(24.0F),
    GigantifiedEmote(72.0F),
    LargeIcon(40.0F),
    MediumEmote(20.0F),
    Reward(18.0F),
    SmallEmote(14.0F);

    private final float sizeDp;

    MediaSpan$Type(float f2) {
        this.sizeDp = f2;
    }

    public final float getSizeDp() { // TODO: __REPLACE_METHOD
        return ChatHook.hookMediaSpanSizeDp(this.sizeDp);
    }
}
