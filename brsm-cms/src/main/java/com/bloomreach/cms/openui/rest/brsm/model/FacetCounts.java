package com.bloomreach.cms.openui.rest.brsm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacetCounts {
    private FacetRanges facetRanges;
    private FacetFields facetFields;
    private FacetQueries facetQueries;

    @JsonProperty("facet_ranges")
    public FacetRanges getFacetRanges() { return facetRanges; }
    @JsonProperty("facet_ranges")
    public void setFacetRanges(FacetRanges value) { this.facetRanges = value; }

    @JsonProperty("facet_fields")
    public FacetFields getFacetFields() { return facetFields; }
    @JsonProperty("facet_fields")
    public void setFacetFields(FacetFields value) { this.facetFields = value; }

    @JsonProperty("facet_queries")
    public FacetQueries getFacetQueries() { return facetQueries; }
    @JsonProperty("facet_queries")
    public void setFacetQueries(FacetQueries value) { this.facetQueries = value; }
}
