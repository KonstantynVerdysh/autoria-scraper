package com.ua.verdysh.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Advertisement {
    private String url;
    private String priceUsd;
    private String priceUah;
    private String heading;
    private String city;
    private String seller;
    private List<String> photo;
    private String description;
    private String id;
}
