package com.bloomreach.cms.openui.model;

import java.util.*;

import com.bloomreach.cms.openui.rest.PickerItem;
import com.fasterxml.jackson.annotation.*;

public class Result<T extends PickerItem> {
    private List<T> data;
    private List<Agg> aggs = new ArrayList<>();

    @JsonProperty("data")
    public List<T> getData() { return data; }
    @JsonProperty("data")
    public void setData(List<T> value) { this.data = value; }

    @JsonProperty("aggs")
    public List<Agg> getAggs() { return aggs; }
    @JsonProperty("aggs")
    public void setAggs(List<Agg> value) { this.aggs = value; }

    public Result(final List<T> data) {
        this.data = data;
    }

    public Result() {
    }
}
