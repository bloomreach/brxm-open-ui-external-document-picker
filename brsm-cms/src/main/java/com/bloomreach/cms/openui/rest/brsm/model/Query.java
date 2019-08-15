package com.bloomreach.cms.openui.rest.brsm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
    private Modification modification;
    private List<Object> didYouMean;

    @JsonProperty("modification")
    public Modification getModification() { return modification; }
    @JsonProperty("modification")
    public void setModification(Modification value) { this.modification = value; }

    @JsonProperty("didYouMean")
    public List<Object> getDidYouMean() { return didYouMean; }
    @JsonProperty("didYouMean")
    public void setDidYouMean(List<Object> value) { this.didYouMean = value; }
}
