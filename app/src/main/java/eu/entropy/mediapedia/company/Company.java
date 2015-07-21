package eu.entropy.mediapedia.company;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Company implements Parcelable {
    private String id;
    private String name;
    private String logo;
    private int logoDrawableId;
    private Map<String, Double> owners;
    private Map<String, Double>  assets;

    public Company() {
        logo = "";
        owners = new HashMap<>();
        assets = new HashMap<>();
    }

    public String getId() {  return id; }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public int getLogoDrawableId() {
        return logoDrawableId;
    }

    public Map<String, Double> getOwners() {
        return owners;
    }

    public Map<String, Double> getAssets() {
        return assets;
    }

    public void setLogoDrawableId(int logoDrawableId) {
        this.logoDrawableId = logoDrawableId;
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
        dest.writeInt(logoDrawableId);
        dest.writeMap(owners);
        dest.writeMap(assets);
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
        name = in.readString();;
        logo = in.readString();
        logoDrawableId = in.readInt();
        owners = in.readHashMap(Double.class.getClassLoader());
        assets = in.readHashMap(Double.class.getClassLoader());
    }
}
