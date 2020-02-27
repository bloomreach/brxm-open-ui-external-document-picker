package org.example.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import java.util.Calendar;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;

/** 
 * TODO: Beanwriter: Failed to create getter for node type: unsplashedpicker:unsplashed
 */
@HippoEssentialsGenerated(internalName = "myproject:newsdocument")
@Node(jcrType = "myproject:newsdocument")
public class Newsdocument extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "myproject:title")
    public String getTitle() {
        return getSingleProperty("myproject:title");
    }

    @HippoEssentialsGenerated(internalName = "myproject:introduction")
    public String getIntroduction() {
        return getSingleProperty("myproject:introduction");
    }

    @HippoEssentialsGenerated(internalName = "myproject:date")
    public Calendar getDate() {
        return getSingleProperty("myproject:date");
    }

    @HippoEssentialsGenerated(internalName = "myproject:author")
    public String getAuthor() {
        return getSingleProperty("myproject:author");
    }

    @HippoEssentialsGenerated(internalName = "myproject:source")
    public String getSource() {
        return getSingleProperty("myproject:source");
    }

    @HippoEssentialsGenerated(internalName = "myproject:location")
    public String getLocation() {
        return getSingleProperty("myproject:location");
    }

    @HippoEssentialsGenerated(internalName = "myproject:content")
    public HippoHtml getContent() {
        return getHippoHtml("myproject:content");
    }

    @HippoEssentialsGenerated(internalName = "myproject:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean("myproject:image", HippoGalleryImageSet.class);
    }
}
