package eu.entropy.mediapedia.company.apigee;

import com.google.gson.annotations.Expose;

public class Connecting {

    @Expose
    private String has;

    /**
     *
     * @return
     * The has
     */
    public String getHas() {
        return has;
    }

    /**
     *
     * @param has
     * The has
     */
    public void setHas(String has) {
        this.has = has;
    }
}
