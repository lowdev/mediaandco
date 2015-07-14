package eu.entropy.mediapedia.company;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Company implements Parcelable {
    private String id;
    private String name;
    private String logo;
    private int logoDrawableId;
    private List<String> owners;
    private List<String> assets;

    public Company() {
        logo = "";
        owners = new ArrayList<>();
        assets = new ArrayList<>();
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

    public List<String> getOwners() {
        return owners;
    }

    public List<String> getAssets() {
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
        dest.writeStringList(owners);
        dest.writeStringList(assets);

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
    private Company(Parcel in) {
        id = in.readString();
        name = in.readString();;
        logo = in.readString();
        logoDrawableId = in.readInt();

        owners = new ArrayList<>();
        in.readStringList(owners);

        assets = new ArrayList<>();
        in.readStringList(assets);
    }
}
