package com.bloomreach.site.openui.rest.giphy.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@Node(jcrType = "giphy:giphyimagepicker")
public class Giphy extends HippoCompound {

    public String[] getImages() {
        return getMultipleProperty("giphy:images");
    }

    public String getOpenUi(){
        return getSingleProperty("giphy:gifs");
    }
}
