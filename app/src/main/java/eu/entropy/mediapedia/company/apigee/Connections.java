package eu.entropy.mediapedia.company.apigee;

import com.google.gson.annotations.Expose;

public class Connections {
    @Expose
    private String ispresentin;

    /**
     *
     * @return
     * The ispresentin
     */
    public String getIspresentin() {
        return ispresentin;
    }

    /**
     *
     * @param ispresentin
     * The ispresentin
     */
    public void setIspresentin(String ispresentin) {
        this.ispresentin = ispresentin;
    }
}
