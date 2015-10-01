package eu.entropy.mediapedia.company.apigee;

import com.google.gson.annotations.Expose;

public class ApigeeRevenue {
    @Expose
    private String value;
    @Expose
    private String unit;
    @Expose
    private String currency;
    @Expose
    private int year;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
