package eu.entropy.mediapedia.company.apigee;

import com.google.gson.annotations.Expose;

public class Metadata {
    @Expose
    private Connecting connecting;
    @Expose
    private String path;
    @Expose
    private Connections connections;

    /**
     *
     * @return
     * The connecting
     */
    public Connecting getConnecting() {
        return connecting;
    }

    /**
     *
     * @param connecting
     * The connecting
     */
    public void setConnecting(Connecting connecting) {
        this.connecting = connecting;
    }

    /**
     *
     * @return
     * The path
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     * The path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return
     * The connections
     */
    public Connections getConnections() {
        return connections;
    }

    /**
     *
     * @param connections
     * The connections
     */
    public void setConnections(Connections connections) {
        this.connections = connections;
    }
}
