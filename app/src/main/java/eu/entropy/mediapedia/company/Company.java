package eu.entropy.mediapedia.company;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Company implements Parcelable {
    private String id;
    private String name;
    private String logo;
    private String revenue;
    private String unit;
    private Map<String, Double> owners;
    private Map<String, Double> assets;

    public Company() {
        owners = new HashMap<>();
        assets = new HashMap<>();
    }

    public Company(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.logo = builder.logo;
        this.revenue = builder.revenue;
        this.unit = builder.unit;
        this.owners = builder.owners;
        this.assets = builder.assets;
    }

    public String getId() {  return id; }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public Map<String, Double> getOwners() {
        return owners;
    }

    public Map<String, Double> getAssets() {
        return assets;
    }

    public String getUnit() {
        return unit;
    }

    public String getRevenue() {
        return revenue;
    }

    public boolean hasInformation() {
        return 0 != owners.size() + assets.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(logo);
        dest.writeMap(owners);
        dest.writeMap(assets);
        dest.writeString(revenue);
        dest.writeString(unit);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    protected Company(Parcel in) {
        id = in.readString();
        name = in.readString();
        logo = in.readString();
        owners = in.readHashMap(Double.class.getClassLoader());
        assets = in.readHashMap(Double.class.getClassLoader());
        revenue = in.readString();
        unit = in.readString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String logo;
        private String revenue;
        private String unit;
        private Map<String, Double> owners = new HashMap<>();
        private Map<String, Double> assets = new HashMap<>();

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public Builder revenue(String revenue) {
            this.revenue = revenue;
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder assets(Map<String, Double> assets) {
            this.assets = assets;
            return this;
        }

        public Builder owners(Map<String, Double> owners) {
            this.owners = owners;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

    public static Company NOT_FOUND = builder().name("Not found").build();
}
