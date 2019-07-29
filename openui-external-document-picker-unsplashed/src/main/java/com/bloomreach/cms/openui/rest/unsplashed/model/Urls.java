package com.bloomreach.cms.openui.rest.unsplashed.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Urls {
    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;

    @JsonProperty("raw")
    public String getRaw() { return raw; }
    @JsonProperty("raw")
    public void setRaw(String value) { this.raw = value; }

    @JsonProperty("full")
    public String getFull() { return full; }
    @JsonProperty("full")
    public void setFull(String value) { this.full = value; }

    @JsonProperty("regular")
    public String getRegular() { return regular; }
    @JsonProperty("regular")
    public void setRegular(String value) { this.regular = value; }

    @JsonProperty("small")
    public String getSmall() { return small; }
    @JsonProperty("small")
    public void setSmall(String value) { this.small = value; }

    @JsonProperty("thumb")
    public String getThumb() { return thumb; }
    @JsonProperty("thumb")
    public void setThumb(String value) { this.thumb = value; }
}
