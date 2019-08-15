package com.bloomreach.cms.openui.rest.brsm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private long numFound;
    private long start;
    private List<PickerProductItem> docs;

    @JsonProperty("numFound")
    public long getNumFound() { return numFound; }
    @JsonProperty("numFound")
    public void setNumFound(long value) { this.numFound = value; }

    @JsonProperty("start")
    public long getStart() { return start; }
    @JsonProperty("start")
    public void setStart(long value) { this.start = value; }

    @JsonProperty("docs")
    public List<PickerProductItem> getDocs() { return docs; }
    @JsonProperty("docs")
    public void setDocs(List<PickerProductItem> value) { this.docs = value; }
}
