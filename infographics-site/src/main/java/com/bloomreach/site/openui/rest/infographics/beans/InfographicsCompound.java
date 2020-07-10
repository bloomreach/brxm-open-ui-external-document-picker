package com.bloomreach.site.openui.rest.infographics.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@Node(jcrType = "infographicspicker:infographics")
public class InfographicsCompound extends HippoCompound {

    public String[] getInfographicsPaths() {
        return getMultipleProperty("infographicspicker:infographicspaths");
    }

    public String getPath(){
        return getSingleProperty("infographicspicker:publicId");
    }
}
