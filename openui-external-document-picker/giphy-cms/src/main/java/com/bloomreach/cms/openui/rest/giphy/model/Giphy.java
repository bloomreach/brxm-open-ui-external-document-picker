package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Giphy {
    private List<GiphyItem> data;
    private Pagination pagination;
    private Meta meta;

    @JsonProperty("data")
    public List<GiphyItem> getData() { return data; }
    @JsonProperty("data")
    public void setData(List<GiphyItem> value) { this.data = value; }

    @JsonProperty("pagination")
    public Pagination getPagination() { return pagination; }
    @JsonProperty("pagination")
    public void setPagination(Pagination value) { this.pagination = value; }

    @JsonProperty("meta")
    public Meta getMeta() { return meta; }
    @JsonProperty("meta")
    public void setMeta(Meta value) { this.meta = value; }
}
