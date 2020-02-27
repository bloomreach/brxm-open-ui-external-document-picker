package org.example.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoBean;

@HippoEssentialsGenerated(internalName = "myproject:bannerdocument")
@Node(jcrType = "myproject:bannerdocument")
public class Bannerdocument extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "myproject:title")
    public String getTitle() {
        return getSingleProperty("myproject:title");
    }

    @HippoEssentialsGenerated(internalName = "myproject:content")
    public HippoHtml getContent() {
        return getHippoHtml("myproject:content");
    }

    @HippoEssentialsGenerated(internalName = "myproject:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean("myproject:image", HippoGalleryImageSet.class);
    }

    @HippoEssentialsGenerated(internalName = "myproject:link")
    public HippoBean getLink() {
        return getLinkedBean("myproject:link", HippoBean.class);
    }
}
