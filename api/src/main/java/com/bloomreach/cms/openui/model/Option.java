package com.bloomreach.cms.openui.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Option {

    private String value;
    private String label;

    public Option(final String value, final String label) {
        this.value = value;
        this.label = label;
    }

    public Option() {
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(final String value) {
        this.value = value;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(final String label) {
        this.label = label;
    }
}
