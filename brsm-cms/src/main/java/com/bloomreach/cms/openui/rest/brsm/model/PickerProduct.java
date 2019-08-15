package com.bloomreach.cms.openui.rest.brsm.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PickerProduct {
    private Response response;
    private FacetCounts facetCounts;
    private Map<String, String> categoryMap;
    private List<Object> didYouMean;
    private String autoCorrectQuery;
    private Metadata metadata;

    @JsonProperty("response")
    public Response getResponse() { return response; }
    @JsonProperty("response")
    public void setResponse(Response value) { this.response = value; }

    @JsonProperty("facet_counts")
    public FacetCounts getFacetCounts() { return facetCounts; }
    @JsonProperty("facet_counts")
    public void setFacetCounts(FacetCounts value) { this.facetCounts = value; }

    @JsonProperty("category_map")
    public Map<String, String> getCategoryMap() { return categoryMap; }
    @JsonProperty("category_map")
    public void setCategoryMap(Map<String, String> value) { this.categoryMap = value; }

    @JsonProperty("did_you_mean")
    public List<Object> getDidYouMean() { return didYouMean; }
    @JsonProperty("did_you_mean")
    public void setDidYouMean(List<Object> value) { this.didYouMean = value; }

    @JsonProperty("autoCorrectQuery")
    public String getAutoCorrectQuery() { return autoCorrectQuery; }
    @JsonProperty("autoCorrectQuery")
    public void setAutoCorrectQuery(String value) { this.autoCorrectQuery = value; }

    @JsonProperty("metadata")
    public Metadata getMetadata() { return metadata; }
    @JsonProperty("metadata")
    public void setMetadata(Metadata value) { this.metadata = value; }
}
