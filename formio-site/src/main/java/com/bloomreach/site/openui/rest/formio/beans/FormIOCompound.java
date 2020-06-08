package com.bloomreach.site.openui.rest.formio.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoCompound;

@Node(jcrType = "formiopicker:formio")
public class FormIOCompound extends HippoCompound {

    public String[] getFromIOPaths() {
        return getMultipleProperty("formiopicker:formiopaths");
    }

    public String getPath(){
        return getSingleProperty("formiopicker:path");
    }
}
