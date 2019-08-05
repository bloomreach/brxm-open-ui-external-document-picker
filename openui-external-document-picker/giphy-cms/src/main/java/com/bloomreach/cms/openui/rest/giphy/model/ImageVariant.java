package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageVariant {
    private String url;
    private String width;
    private String height;
    private String size;
    private String mp4;
    private String mp4Size;
    private String webp;
    private String webpSize;
    private String frames;
    private String hash;

    @JsonProperty("url")
    public String getURL() { return url; }
    @JsonProperty("url")
    public void setURL(String value) { this.url = value; }

    @JsonProperty("width")
    public String getWidth() { return width; }
    @JsonProperty("width")
    public void setWidth(String value) { this.width = value; }

    @JsonProperty("height")
    public String getHeight() { return height; }
    @JsonProperty("height")
    public void setHeight(String value) { this.height = value; }

    @JsonProperty("size")
    public String getSize() { return size; }
    @JsonProperty("size")
    public void setSize(String value) { this.size = value; }

    @JsonProperty("mp4")
    public String getMp4() { return mp4; }
    @JsonProperty("mp4")
    public void setMp4(String value) { this.mp4 = value; }

    @JsonProperty("mp4_size")
    public String getMp4Size() { return mp4Size; }
    @JsonProperty("mp4_size")
    public void setMp4Size(String value) { this.mp4Size = value; }

    @JsonProperty("webp")
    public String getWebp() { return webp; }
    @JsonProperty("webp")
    public void setWebp(String value) { this.webp = value; }

    @JsonProperty("webp_size")
    public String getWebpSize() { return webpSize; }
    @JsonProperty("webp_size")
    public void setWebpSize(String value) { this.webpSize = value; }

    @JsonProperty("frames")
    public String getFrames() { return frames; }
    @JsonProperty("frames")
    public void setFrames(String value) { this.frames = value; }

    @JsonProperty("hash")
    public String getHash() { return hash; }
    @JsonProperty("hash")
    public void setHash(String value) { this.hash = value; }
}
