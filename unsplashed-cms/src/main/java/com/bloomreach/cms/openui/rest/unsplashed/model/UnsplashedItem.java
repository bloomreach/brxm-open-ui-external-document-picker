package com.bloomreach.cms.openui.rest.unsplashed.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsplashedItem {
    private String id;
    private String createdAt;
    private String updatedAt;
    private Long width;
    private Long height;
    private String color;
    private String description;
    private String altDescription;
    private Urls urls;
    private Links links;
    private List<Tag> tags;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("created_at")
    public String getCreatedAt() { return createdAt; }
    @JsonProperty("created_at")
    public void setCreatedAt(String value) { this.createdAt = value; }

    @JsonProperty("updated_at")
    public String getUpdatedAt() { return updatedAt; }
    @JsonProperty("updated_at")
    public void setUpdatedAt(String value) { this.updatedAt = value; }

    @JsonProperty("width")
    public Long getWidth() { return width; }
    @JsonProperty("width")
    public void setWidth(Long value) { this.width = value; }

    @JsonProperty("height")
    public Long getHeight() { return height; }
    @JsonProperty("height")
    public void setHeight(Long value) { this.height = value; }

    @JsonProperty("color")
    public String getColor() { return color; }
    @JsonProperty("color")
    public void setColor(String value) { this.color = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("alt_description")
    public String getAltDescription() { return altDescription; }
    @JsonProperty("alt_description")
    public void setAltDescription(String value) { this.altDescription = value; }

    @JsonProperty("urls")
    public Urls getUrls() { return urls; }
    @JsonProperty("urls")
    public void setUrls(Urls value) { this.urls = value; }

    @JsonProperty("links")
    public Links getLinks() { return links; }
    @JsonProperty("links")
    public void setLinks(Links value) { this.links = value; }

    @JsonProperty("tags")
    public List<Tag> getTags() { return tags; }
    @JsonProperty("tags")
    public void setTags(List<Tag> value) { this.tags = value; }
}
