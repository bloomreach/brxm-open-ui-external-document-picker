package com.bloomreach.cms.openui.rest.giphy.model;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    private ImageVariant original;

    @JsonProperty("original")
    public ImageVariant getOriginal() { return original; }
    @JsonProperty("original")
    public void setOriginal(ImageVariant value) { this.original = value; }


}
