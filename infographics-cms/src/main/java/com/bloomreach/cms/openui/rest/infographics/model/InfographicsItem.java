package com.bloomreach.cms.openui.rest.infographics.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfographicsItem {

    private String id;
    private String title;
    private int themeId;
    private boolean published;
    private String thumbnailUrl;
    private Date dateModified;
    private String publicUrl;
    private String embedResponsive;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(final int themeId) {
        this.themeId = themeId;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(final boolean published) {
        this.published = published;
    }

    @JsonProperty("thumbnail_url")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("thumbnail_url")
    public void setThumbnailUrl(final String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @JsonProperty("date_modified")
    public Date getDateModified() {
        return dateModified;
    }

    @JsonProperty("date_modified")
    public void setDateModified(final Date dateModified) {
        this.dateModified = dateModified;
    }

    @JsonProperty("url")
    public String getPublicUrl() {
        return publicUrl;
    }

    @JsonProperty("url")
    public void setPublicUrl(final String publicUrl) {
        this.publicUrl = publicUrl;
    }

    @JsonProperty("embed_responsive")
    public String getEmbedResponsive() {
        return embedResponsive;
    }

    @JsonProperty("embed_responsive")
    public void setEmbedResponsive(final String embedResponsive) {
        this.embedResponsive = embedResponsive;
    }
}
