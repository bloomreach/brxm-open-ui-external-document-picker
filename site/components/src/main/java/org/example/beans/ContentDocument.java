package org.example.beans;

import java.util.Calendar;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;

@XmlRootElement(name = "contentdocument")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "myproject:contentdocument")
@Node(jcrType = "myproject:contentdocument")
public class ContentDocument extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myproject:introduction")
    public String getIntroduction() {
        return getSingleProperty("myproject:introduction");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "myproject:title")
    public String getTitle() {
        return getSingleProperty("myproject:title");
    }

    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myproject:content")
    public HippoHtml getContent() {
        return getHippoHtml("myproject:content");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "myproject:publicationdate")
    public Calendar getPublicationDate() {
        return getSingleProperty("myproject:publicationdate");
    }

    @HippoEssentialsGenerated(internalName = "myproject:externaldockerpicker")
    public String getExternaldockerpicker() {
        return getSingleProperty("myproject:externaldockerpicker");
    }
}
