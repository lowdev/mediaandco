package eu.entropy.mediapedia.company;

import android.os.Parcel;
import android.os.Parcelable;

public class Revenue implements Parcelable {
    private String value;
    private String unit;
    private String currency;
    private int year;

    private Revenue(Builder builder) {
        this.value = builder.value;
        this.unit = builder.unit;
        this.currency = builder.currency;
        this.year = builder.year;
    }

    protected Revenue(Parcel in) {
        this.value = in.readString();
        this.unit = in.readString();
        this.currency = in.readString();
        this.year = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeString(unit);
        dest.writeString(currency);
        dest.writeInt(year);
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public int getYear() {
        return year;
    }

    public static final Parcelable.Creator<Revenue> CREATOR = new Parcelable.Creator<Revenue>() {
        public Revenue createFromParcel(Parcel in) {
            return new Revenue(in);
        }
        public Revenue[] newArray(int size) {
            return new Revenue[size];
        }
    };

    public static Revenue NONE = null;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String value;
        private String unit;
        private String currency;
        private int year;

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Revenue build() {
            return new Revenue(this);
        }
    }
}
