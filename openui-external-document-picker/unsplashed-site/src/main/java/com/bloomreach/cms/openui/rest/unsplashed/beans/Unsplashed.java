package com.bloomreach.cms.openui.rest.unsplashed.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@Node(jcrType = "unsplashedpicker:unsplashed")
public class Unsplashed extends HippoCompound {

    public String[] getImages() {
        return getMultipleProperty("unsplashedpicker:images");
    }

    public String getOpenUi(){
        return getSingleProperty("unsplashedpicker:unsplashedimgs");
    }
}
