package eu.entropy.mediapedia.company.apigee;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ApigeeCompanyResult {
    @Expose
    private String action;
    @Expose
    private String application;
    @Expose
    private Params params;
    @Expose
    private String path;
    @Expose
    private String uri;
    @Expose
    private List<Entity> entities = new ArrayList<>();
    @Expose
    private Long timestamp;
    @Expose
    private Integer duration;
    @Expose
    private String organization;
    @Expose
    private String applicationName;

    /**
     *
     * @return
     * The action
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @param action
     * The action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     *
     * @return
     * The application
     */
    public String getApplication() {
        return application;
    }

    /**
     *
     * @param application
     * The application
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     *
     * @return
     * The params
     */
    public Params getParams() {
        return params;
    }

    /**
     *
     * @param params
     * The params
     */
    public void setParams(Params params) {
        this.params = params;
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
     * The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     * The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return
     * The entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     *
     * @param entities
     * The entities
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     * The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     *
     * @param organization
     * The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     *
     * @return
     * The applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     *
     * @param applicationName
     * The applicationName
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
