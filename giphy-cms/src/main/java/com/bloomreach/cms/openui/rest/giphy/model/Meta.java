package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Meta {
    private Long status;
    private String msg;
    private String responseID;

    @JsonProperty("status")
    public Long getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(Long value) { this.status = value; }

    @JsonProperty("msg")
    public String getMsg() { return msg; }
    @JsonProperty("msg")
    public void setMsg(String value) { this.msg = value; }

    @JsonProperty("response_id")
    public String getResponseID() { return responseID; }
    @JsonProperty("response_id")
    public void setResponseID(String value) { this.responseID = value; }
}
