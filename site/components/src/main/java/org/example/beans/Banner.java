package org.example.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoGalleryImageAdapter;

@XmlRootElement(name = "banner")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "myproject:bannerdocument")
@Node(jcrType = "myproject:bannerdocument")
public class Banner extends BaseDocument {
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

    @XmlJavaTypeAdapter(HippoGalleryImageAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myproject:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean("myproject:image", HippoGalleryImageSet.class);
    }

    @HippoEssentialsGenerated(internalName = "myproject:link")
    public HippoBean getLink() {
        return getLinkedBean("myproject:link", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "myproject:extdocpicker")
    public String getExtdocpicker() {
        return getSingleProperty("myproject:extdocpicker");
    }

    @HippoEssentialsGenerated(internalName = "myproject:md")
    public String getMd() {
        return getSingleProperty("myproject:md");
    }
}
