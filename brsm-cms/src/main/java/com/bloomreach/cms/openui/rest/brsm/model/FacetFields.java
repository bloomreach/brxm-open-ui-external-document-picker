package com.bloomreach.cms.openui.rest.brsm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacetFields {
    private List<Category> category;
    private List<Availability> availability;
    private List<Availability> brand;
    private List<Availability> colour;
    private List<Availability> specialOffers;
    private List<Availability> newArrivals;
    private List<Availability> productGroups;
    private List<Availability> productType;
    private List<Availability> material;
    private List<Availability> themes;
    private List<Availability> collection;
    private List<Availability> season;
    private List<Availability> yarnWeight;
    private List<Availability> garment;
    private List<Availability> patternFor;
    private List<Availability> format;

    @JsonProperty("category")
    public List<Category> getCategory() { return category; }
    @JsonProperty("category")
    public void setCategory(List<Category> value) { this.category = value; }

    @JsonProperty("Availability")
    public List<Availability> getAvailability() { return availability; }
    @JsonProperty("Availability")
    public void setAvailability(List<Availability> value) { this.availability = value; }

    @JsonProperty("Brand")
    public List<Availability> getBrand() { return brand; }
    @JsonProperty("Brand")
    public void setBrand(List<Availability> value) { this.brand = value; }

    @JsonProperty("Colour")
    public List<Availability> getColour() { return colour; }
    @JsonProperty("Colour")
    public void setColour(List<Availability> value) { this.colour = value; }

    @JsonProperty("Special Offers")
    public List<Availability> getSpecialOffers() { return specialOffers; }
    @JsonProperty("Special Offers")
    public void setSpecialOffers(List<Availability> value) { this.specialOffers = value; }

    @JsonProperty("New Arrivals")
    public List<Availability> getNewArrivals() { return newArrivals; }
    @JsonProperty("New Arrivals")
    public void setNewArrivals(List<Availability> value) { this.newArrivals = value; }

    @JsonProperty("Product Groups")
    public List<Availability> getProductGroups() { return productGroups; }
    @JsonProperty("Product Groups")
    public void setProductGroups(List<Availability> value) { this.productGroups = value; }

    @JsonProperty("Product Type")
    public List<Availability> getProductType() { return productType; }
    @JsonProperty("Product Type")
    public void setProductType(List<Availability> value) { this.productType = value; }

    @JsonProperty("Material")
    public List<Availability> getMaterial() { return material; }
    @JsonProperty("Material")
    public void setMaterial(List<Availability> value) { this.material = value; }

    @JsonProperty("Themes")
    public List<Availability> getThemes() { return themes; }
    @JsonProperty("Themes")
    public void setThemes(List<Availability> value) { this.themes = value; }

    @JsonProperty("Collection")
    public List<Availability> getCollection() { return collection; }
    @JsonProperty("Collection")
    public void setCollection(List<Availability> value) { this.collection = value; }

    @JsonProperty("Season")
    public List<Availability> getSeason() { return season; }
    @JsonProperty("Season")
    public void setSeason(List<Availability> value) { this.season = value; }

    @JsonProperty("Yarn Weight")
    public List<Availability> getYarnWeight() { return yarnWeight; }
    @JsonProperty("Yarn Weight")
    public void setYarnWeight(List<Availability> value) { this.yarnWeight = value; }

    @JsonProperty("Garment")
    public List<Availability> getGarment() { return garment; }
    @JsonProperty("Garment")
    public void setGarment(List<Availability> value) { this.garment = value; }

    @JsonProperty("Pattern For")
    public List<Availability> getPatternFor() { return patternFor; }
    @JsonProperty("Pattern For")
    public void setPatternFor(List<Availability> value) { this.patternFor = value; }

    @JsonProperty("Format")
    public List<Availability> getFormat() { return format; }
    @JsonProperty("Format")
    public void setFormat(List<Availability> value) { this.format = value; }
}
