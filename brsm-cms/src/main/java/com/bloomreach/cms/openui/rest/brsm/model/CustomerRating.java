package com.bloomreach.cms.openui.rest.brsm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRating {
    private long count;
    private End start;
    private End end;

    @JsonProperty("count")
    public long getCount() { return count; }
    @JsonProperty("count")
    public void setCount(long value) { this.count = value; }

    @JsonProperty("start")
    public End getStart() { return start; }
    @JsonProperty("start")
    public void setStart(End value) { this.start = value; }

    @JsonProperty("end")
    public End getEnd() { return end; }
    @JsonProperty("end")
    public void setEnd(End value) { this.end = value; }
}
