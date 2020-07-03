package com.ua.verdysh.model;

import java.util.List;

public class CarAdvertisement {
    private String url;
    private String priceUsd;
    private String priceUah;
    private String heading;
    private String city;
    private String seller;
    private List<String> photo;
    private String description;
    private String id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getPriceUah() {
        return priceUah;
    }

    public void setPriceUah(String priceUah) {
        this.priceUah = priceUah;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
