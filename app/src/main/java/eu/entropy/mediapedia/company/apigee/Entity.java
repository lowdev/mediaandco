package eu.entropy.mediapedia.company.apigee;

import com.google.gson.annotations.Expose;

import java.util.Map;

public class Entity {
    @Expose
    private String uuid;
    @Expose
    private String type;
    @Expose
    private String mediaType;
    @Expose
    private String name;
    @Expose
    private Long created;
    @Expose
    private Long modified;
    @Expose
    private String corporateName;
    @Expose
    private String country;
    @Expose
    private String logo;
    @Expose
    private Metadata metadata;
    @Expose
    private Map<String, Double> owners;
    @Expose
    private Map<String, Double>  assets;
    @Expose
    private ApigeeRevenue revenue;

    /**
     *
     * @return
     * The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     * The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The created
     */
    public Long getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(Long created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The modified
     */
    public Long getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(Long modified) {
        this.modified = modified;
    }

    /**
     *
     * @return
     * The corporateName
     */
    public String getCorporateName() {
        return corporateName;
    }

    /**
     *
     * @param corporateName
     * The corporateName
     */
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     *
     * @return
     * The metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     *
     * @param metadata
     * The metadata
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     *
     * @return
     * The owners
     */
    public Map<String, Double> getOwners() {
        return owners;
    }

    /**
     *
     * @param owners
     * The owners
     */
    public void setOwners(Map<String, Double> owners) {
        this.owners = owners;
    }

    /**
     *
     * @return
     * The assets
     */
    public Map<String, Double> getAssets() {
        return assets;
    }

    /**
     *
     * @param assets
     * The assets
     */
    public void setAssets(Map<String, Double> assets) {
        this.assets = assets;
    }

    /**
     *
     * @return
     * The revenue
     */
    public ApigeeRevenue getRevenue() {
        return revenue;
    }

    /**
     *
     * @param revenue
     * The revenue
     */
    public void setRevenue(ApigeeRevenue revenue) {
        this.revenue = revenue;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
