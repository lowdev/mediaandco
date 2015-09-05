package eu.entropy.mediapedia.company;

import android.os.Parcel;
import android.os.Parcelable;

public enum MediaType implements Parcelable {
    TV("tv"),
    RADIO("radio"),
    PAPER("paper"),
    NONE("none");

    private final String folderName;

    MediaType(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public static final Parcelable.Creator<MediaType> CREATOR = new Parcelable.Creator<MediaType>() {

        public MediaType createFromParcel(Parcel in) {
            MediaType mediaType = MediaType.values()[in.readInt()];
            return mediaType;
        }

        public MediaType[] newArray(int size) {
            return new MediaType[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ordinal());
        out.writeString(folderName);
    }
}
