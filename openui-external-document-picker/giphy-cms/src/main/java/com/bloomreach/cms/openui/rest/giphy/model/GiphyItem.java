package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyItem {
    private Type type;
    private String id;
    private String slug;
    private String url;
    private String bitlyGIFURL;
    private String bitlyURL;
    private String embedURL;
    private String username;
    private String source;
//    private Rating rating;
    private String contentURL;
    private String sourceTLD;
    private String sourcePostURL;
    private Long isSticker;
    private String importDatetime;
    private String trendingDatetime;
    private Images images;
    private String title;
    private Analytics analytics;
    private User user;

    @JsonProperty("type")
    public Type getType() { return type; }
    @JsonProperty("type")
    public void setType(Type value) { this.type = value; }

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("slug")
    public String getSlug() { return slug; }
    @JsonProperty("slug")
    public void setSlug(String value) { this.slug = value; }

    @JsonProperty("url")
    public String getURL() { return url; }
    @JsonProperty("url")
    public void setURL(String value) { this.url = value; }

    @JsonProperty("bitly_gif_url")
    public String getBitlyGIFURL() { return bitlyGIFURL; }
    @JsonProperty("bitly_gif_url")
    public void setBitlyGIFURL(String value) { this.bitlyGIFURL = value; }

    @JsonProperty("bitly_url")
    public String getBitlyURL() { return bitlyURL; }
    @JsonProperty("bitly_url")
    public void setBitlyURL(String value) { this.bitlyURL = value; }

    @JsonProperty("embed_url")
    public String getEmbedURL() { return embedURL; }
    @JsonProperty("embed_url")
    public void setEmbedURL(String value) { this.embedURL = value; }

    @JsonProperty("username")
    public String getUsername() { return username; }
    @JsonProperty("username")
    public void setUsername(String value) { this.username = value; }

    @JsonProperty("source")
    public String getSource() { return source; }
    @JsonProperty("source")
    public void setSource(String value) { this.source = value; }

//    @JsonProperty("rating")
//    public Rating getRating() { return rating; }
//    @JsonProperty("rating")
//    public void setRating(Rating value) { this.rating = value; }

    @JsonProperty("content_url")
    public String getContentURL() { return contentURL; }
    @JsonProperty("content_url")
    public void setContentURL(String value) { this.contentURL = value; }

    @JsonProperty("source_tld")
    public String getSourceTLD() { return sourceTLD; }
    @JsonProperty("source_tld")
    public void setSourceTLD(String value) { this.sourceTLD = value; }

    @JsonProperty("source_post_url")
    public String getSourcePostURL() { return sourcePostURL; }
    @JsonProperty("source_post_url")
    public void setSourcePostURL(String value) { this.sourcePostURL = value; }

    @JsonProperty("is_sticker")
    public Long getIsSticker() { return isSticker; }
    @JsonProperty("is_sticker")
    public void setIsSticker(Long value) { this.isSticker = value; }

    @JsonProperty("import_datetime")
    public String getImportDatetime() { return importDatetime; }
    @JsonProperty("import_datetime")
    public void setImportDatetime(String value) { this.importDatetime = value; }

    @JsonProperty("trending_datetime")
    public String getTrendingDatetime() { return trendingDatetime; }
    @JsonProperty("trending_datetime")
    public void setTrendingDatetime(String value) { this.trendingDatetime = value; }

    @JsonProperty("images")
    public Images getImages() { return images; }
    @JsonProperty("images")
    public void setImages(Images value) { this.images = value; }

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("analytics")
    public Analytics getAnalytics() { return analytics; }
    @JsonProperty("analytics")
    public void setAnalytics(Analytics value) { this.analytics = value; }

    @JsonProperty("user")
    public User getUser() { return user; }
    @JsonProperty("user")
    public void setUser(User value) { this.user = value; }
}
