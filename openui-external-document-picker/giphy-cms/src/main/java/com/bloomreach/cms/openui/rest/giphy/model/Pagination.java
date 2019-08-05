package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Pagination {
    private Long totalCount;
    private Long count;
    private Long offset;

    @JsonProperty("total_count")
    public Long getTotalCount() { return totalCount; }
    @JsonProperty("total_count")
    public void setTotalCount(Long value) { this.totalCount = value; }

    @JsonProperty("count")
    public Long getCount() { return count; }
    @JsonProperty("count")
    public void setCount(Long value) { this.count = value; }

    @JsonProperty("offset")
    public Long getOffset() { return offset; }
    @JsonProperty("offset")
    public void setOffset(Long value) { this.offset = value; }
}
