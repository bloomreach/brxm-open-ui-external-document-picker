package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class User {
    private String avatarURL;
    private String bannerURL;
    private String bannerImage;
    private String profileURL;
    private String username;
    private String displayName;
    private Boolean isVerified;

    @JsonProperty("avatar_url")
    public String getAvatarURL() { return avatarURL; }
    @JsonProperty("avatar_url")
    public void setAvatarURL(String value) { this.avatarURL = value; }

    @JsonProperty("banner_url")
    public String getBannerURL() { return bannerURL; }
    @JsonProperty("banner_url")
    public void setBannerURL(String value) { this.bannerURL = value; }

    @JsonProperty("banner_image")
    public String getBannerImage() { return bannerImage; }
    @JsonProperty("banner_image")
    public void setBannerImage(String value) { this.bannerImage = value; }

    @JsonProperty("profile_url")
    public String getProfileURL() { return profileURL; }
    @JsonProperty("profile_url")
    public void setProfileURL(String value) { this.profileURL = value; }

    @JsonProperty("username")
    public String getUsername() { return username; }
    @JsonProperty("username")
    public void setUsername(String value) { this.username = value; }

    @JsonProperty("display_name")
    public String getDisplayName() { return displayName; }
    @JsonProperty("display_name")
    public void setDisplayName(String value) { this.displayName = value; }

    @JsonProperty("is_verified")
    public Boolean getIsVerified() { return isVerified; }
    @JsonProperty("is_verified")
    public void setIsVerified(Boolean value) { this.isVerified = value; }
}
