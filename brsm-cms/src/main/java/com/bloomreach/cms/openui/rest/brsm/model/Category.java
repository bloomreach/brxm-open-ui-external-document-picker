package com.bloomreach.cms.openui.rest.brsm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private long count;
    private String crumb;
    private String catName;
    private String parent;
    private String catID;
    private String treePath;

    @JsonProperty("count")
    public long getCount() { return count; }
    @JsonProperty("count")
    public void setCount(long value) { this.count = value; }

    @JsonProperty("crumb")
    public String getCrumb() { return crumb; }
    @JsonProperty("crumb")
    public void setCrumb(String value) { this.crumb = value; }

    @JsonProperty("cat_name")
    public String getCatName() { return catName; }
    @JsonProperty("cat_name")
    public void setCatName(String value) { this.catName = value; }

    @JsonProperty("parent")
    public String getParent() { return parent; }
    @JsonProperty("parent")
    public void setParent(String value) { this.parent = value; }

    @JsonProperty("cat_id")
    public String getCatID() { return catID; }
    @JsonProperty("cat_id")
    public void setCatID(String value) { this.catID = value; }

    @JsonProperty("tree_path")
    public String getTreePath() { return treePath; }
    @JsonProperty("tree_path")
    public void setTreePath(String value) { this.treePath = value; }
}
