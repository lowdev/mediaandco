package eu.entropy.mediapedia.company;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Stakeholder is a company who has a stake.
 */
public class Stakeholder implements Parcelable, Comparable<Stakeholder> {

    private Company company;
    
    private double stake;

    public Stakeholder(Company company, double stake) {
        this.company = company;
        this.stake = stake;
    }

    public Company getCompany() { return company; }

    public String getName() {
        return company.getName();
    }

    public String getLogo() {
        return company.getLogo();
    }

    public double getStake() {
        return stake;
    }

    public boolean hasInformation() {
        return company.hasInformation();
    }

    public int getLogoDrawableId() {
        return company.getLogoDrawableId();
    }

    @Override
    public int compareTo(Stakeholder stakeholder) {
        return Double.compare(stakeholder.stake, stake);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(company, flags);
        dest.writeDouble(stake);
    }               

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Stakeholder> CREATOR = new Parcelable.Creator<Stakeholder>() {
        public Stakeholder createFromParcel(Parcel in) {
            return new Stakeholder(in);
        }

        public Stakeholder[] newArray(int size) {
            return new Stakeholder[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Stakeholder(Parcel in) {
        company = in.readParcelable(Company.class.getClassLoader());
        stake = in.readDouble();
    }
}
