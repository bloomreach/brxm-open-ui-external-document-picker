package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Analytics {
    private Onclick onload;
    private Onclick onclick;
    private Onclick onsent;

    @JsonProperty("onload")
    public Onclick getOnload() { return onload; }
    @JsonProperty("onload")
    public void setOnload(Onclick value) { this.onload = value; }

    @JsonProperty("onclick")
    public Onclick getOnclick() { return onclick; }
    @JsonProperty("onclick")
    public void setOnclick(Onclick value) { this.onclick = value; }

    @JsonProperty("onsent")
    public Onclick getOnsent() { return onsent; }
    @JsonProperty("onsent")
    public void setOnsent(Onclick value) { this.onsent = value; }
}
