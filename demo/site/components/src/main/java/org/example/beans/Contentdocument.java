package org.example.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import java.util.Calendar;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import com.bloomreach.site.openui.rest.giphy.beans.Giphy;

/** 
 * TODO: Beanwriter: Failed to create getter for node type: giphy:giphyimagepicker
 */
@HippoEssentialsGenerated(internalName = "myproject:contentdocument")
@Node(jcrType = "myproject:contentdocument")
public class Contentdocument extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "myproject:title")
    public String getTitle() {
        return getSingleProperty("myproject:title");
    }

    @HippoEssentialsGenerated(internalName = "myproject:introduction")
    public String getIntroduction() {
        return getSingleProperty("myproject:introduction");
    }

    @HippoEssentialsGenerated(internalName = "myproject:publicationdate")
    public Calendar getPublicationdate() {
        return getSingleProperty("myproject:publicationdate");
    }

    @HippoEssentialsGenerated(internalName = "myproject:content")
    public HippoHtml getContent() {
        return getHippoHtml("myproject:content");
    }

    public String[] getImages() {
        return getBean("myproject:giphyimagepicker", Giphy.class).getImages();
    }
}
