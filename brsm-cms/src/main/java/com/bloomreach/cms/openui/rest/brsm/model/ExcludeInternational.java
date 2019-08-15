package com.bloomreach.cms.openui.rest.brsm.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ExcludeInternational {
    NO, YES;

    @JsonValue
    public String toValue() {
        switch (this) {
        case NO: return "No";
        case YES: return "Yes";
        }
        return null;
    }

    @JsonCreator
    public static ExcludeInternational forValue(String value) throws IOException {
        if (value.equals("No")) return NO;
        if (value.equals("Yes")) return YES;
        throw new IOException("Cannot deserialize ExcludeInternational");
    }
}
