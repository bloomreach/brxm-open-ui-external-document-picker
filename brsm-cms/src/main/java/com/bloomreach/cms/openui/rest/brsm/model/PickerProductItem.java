package com.bloomreach.cms.openui.rest.brsm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PickerProductItem {
    private double salePrice;
    private double price;
    private long invQty;
    private String description;
    private ExcludeInternational excludeInternational;
    private String title;
    private String url;
    private String pid;
    private String thumbImage;
    private long noBazaarvoiceReviews;
    private ExcludeInternational useBulkShippingAgent;
    private double bazaarvoiceStarRating;
    private long vatRate;
    private String itemNo;
    private ExcludeInternational docNew;
    private String brand;
    private List<String> excludedCountries;

    @JsonProperty("sale_price")
    public double getSalePrice() { return salePrice; }
    @JsonProperty("sale_price")
    public void setSalePrice(double value) { this.salePrice = value; }

    @JsonProperty("price")
    public double getPrice() { return price; }
    @JsonProperty("price")
    public void setPrice(double value) { this.price = value; }

    @JsonProperty("InvQty")
    public long getInvQty() { return invQty; }
    @JsonProperty("InvQty")
    public void setInvQty(long value) { this.invQty = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("ExcludeInternational")
    public ExcludeInternational getExcludeInternational() { return excludeInternational; }
    @JsonProperty("ExcludeInternational")
    public void setExcludeInternational(ExcludeInternational value) { this.excludeInternational = value; }

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("url")
    public String getURL() { return url; }
    @JsonProperty("url")
    public void setURL(String value) { this.url = value; }

    @JsonProperty("pid")
    public String getPID() { return pid; }
    @JsonProperty("pid")
    public void setPID(String value) { this.pid = value; }

    @JsonProperty("thumb_image")
    public String getThumbImage() { return thumbImage; }
    @JsonProperty("thumb_image")
    public void setThumbImage(String value) { this.thumbImage = value; }

    @JsonProperty("NoBazaarvoiceReviews")
    public long getNoBazaarvoiceReviews() { return noBazaarvoiceReviews; }
    @JsonProperty("NoBazaarvoiceReviews")
    public void setNoBazaarvoiceReviews(long value) { this.noBazaarvoiceReviews = value; }

    @JsonProperty("UseBulkShippingAgent")
    public ExcludeInternational getUseBulkShippingAgent() { return useBulkShippingAgent; }
    @JsonProperty("UseBulkShippingAgent")
    public void setUseBulkShippingAgent(ExcludeInternational value) { this.useBulkShippingAgent = value; }

    @JsonProperty("BazaarvoiceStarRating")
    public double getBazaarvoiceStarRating() { return bazaarvoiceStarRating; }
    @JsonProperty("BazaarvoiceStarRating")
    public void setBazaarvoiceStarRating(double value) { this.bazaarvoiceStarRating = value; }

    @JsonProperty("VATRate")
    public long getVatRate() { return vatRate; }
    @JsonProperty("VATRate")
    public void setVatRate(long value) { this.vatRate = value; }

    @JsonProperty("ItemNo")
    public String getItemNo() { return itemNo; }
    @JsonProperty("ItemNo")
    public void setItemNo(String value) { this.itemNo = value; }

    @JsonProperty("New")
    public ExcludeInternational getDocNew() { return docNew; }
    @JsonProperty("New")
    public void setDocNew(ExcludeInternational value) { this.docNew = value; }

    @JsonProperty("brand")
    public String getBrand() { return brand; }
    @JsonProperty("brand")
    public void setBrand(String value) { this.brand = value; }

    @JsonProperty("ExcludedCountries")
    public List<String> getExcludedCountries() { return excludedCountries; }
    @JsonProperty("ExcludedCountries")
    public void setExcludedCountries(List<String> value) { this.excludedCountries = value; }
}
