package tv.twitch.android.models.chat;

import android.os.Parcel;
import android.os.Parcelable;

import kotlin.jvm.internal.Intrinsics;

public class MessageBadge implements Parcelable { // TODO: __REMOVE_FINAL
    public static final Parcelable.Creator<MessageBadge> CREATOR = new Creator();
    private final String name;
    private final String version;

    public static final class Creator implements Parcelable.Creator<MessageBadge> {
        @Override
        public final MessageBadge createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new MessageBadge(parcel.readString(), parcel.readString());
        }

        @Override
        public final MessageBadge[] newArray(int i) {
            return new MessageBadge[i];
        }
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.version;
    }

    public final MessageBadge copy(String name, String version) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        return new MessageBadge(name, version);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessageBadge)) {
            return false;
        }
        MessageBadge messageBadge = (MessageBadge) obj;
        return Intrinsics.areEqual(this.name, messageBadge.name) && Intrinsics.areEqual(this.version, messageBadge.version);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.version.hashCode();
    }

    public String toString() {
        return "MessageBadge(name=" + this.name + ", version=" + this.version + ")";
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.name);
        out.writeString(this.version);
    }

    public MessageBadge(String name, String version) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        this.name = name;
        this.version = version;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }
}