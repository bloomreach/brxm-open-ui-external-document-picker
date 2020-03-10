package com.bloomreach.cms.openui.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Agg {
    private String name;
    private String label;
    private String type;
    private List<String> values;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("label")
    public String getLabel() { return label; }
    @JsonProperty("label")
    public void setLabel(String value) { this.label = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("values")
    public List<String> getValues() { return values; }
    @JsonProperty("values")
    public void setValues(List<String> value) { this.values = value; }
}
