package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Onclick {
    private String url;

    @JsonProperty("url")
    public String getURL() { return url; }
    @JsonProperty("url")
    public void setURL(String value) { this.url = value; }
}
