package com.bloomreach.cms.openui.rest.brsm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacetRanges {
    private List<CustomerRating> price;
    private List<CustomerRating> oldPrice;
    private List<CustomerRating> customerRating;

    @JsonProperty("Price")
    public List<CustomerRating> getPrice() { return price; }
    @JsonProperty("Price")
    public void setPrice(List<CustomerRating> value) { this.price = value; }

    @JsonProperty("old price")
    public List<CustomerRating> getOldPrice() { return oldPrice; }
    @JsonProperty("old price")
    public void setOldPrice(List<CustomerRating> value) { this.oldPrice = value; }

    @JsonProperty("Customer Rating")
    public List<CustomerRating> getCustomerRating() { return customerRating; }
    @JsonProperty("Customer Rating")
    public void setCustomerRating(List<CustomerRating> value) { this.customerRating = value; }
}
