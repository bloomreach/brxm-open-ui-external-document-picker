package com.bloomreach.cms.openui.rest.unsplashed.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {
    private String self;
    private String html;
    private String download;
    private String downloadLocation;

    @JsonProperty("self")
    public String getSelf() { return self; }
    @JsonProperty("self")
    public void setSelf(String value) { this.self = value; }

    @JsonProperty("html")
    public String getHTML() { return html; }
    @JsonProperty("html")
    public void setHTML(String value) { this.html = value; }

    @JsonProperty("download")
    public String getDownload() { return download; }
    @JsonProperty("download")
    public void setDownload(String value) { this.download = value; }

    @JsonProperty("download_location")
    public String getDownloadLocation() { return downloadLocation; }
    @JsonProperty("download_location")
    public void setDownloadLocation(String value) { this.downloadLocation = value; }
}
