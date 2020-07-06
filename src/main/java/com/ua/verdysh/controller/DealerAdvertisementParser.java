package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ParserHelper;
import com.ua.verdysh.controller.interfaces.Parsable;
import org.jsoup.nodes.Document;

import java.util.List;

class DealerAdvertisementParser implements Parsable {

    private static final String PRICE_USD_SELECTOR = "section[class='price mb-15 mhide'] div.price_value";
    private static final String PRICE_UAH_SELECTOR = "div[class='price_value price_value--additional']";
    private static final String HEADING_SELECTOR = "h1";
    private static final String CITY_SELECTOR = "ul.checked-list.unstyle:nth-child(2) li.item:nth-child(1) > div.item_inner";
    private static final String SELLER_SELECTOR = "section[class='seller mb-15'] div.seller_info_area strong";
    private static final String PHOTO_SELECTOR = ".image-gallery-slides img";
    private Document document;

    DealerAdvertisementParser(Document document) {
        this.document = document;
    }

    @Override
    public String parseUrl() {
        return document.location();
    }

    @Override
    public String parsePriceUsd() {
        return ParserHelper.getElementText(document, PRICE_USD_SELECTOR);
    }

    @Override
    public String parsePriceUah() {
        return ParserHelper.getElementText(document, PRICE_UAH_SELECTOR);
    }

    @Override
    public String parseHeading() {
        return ParserHelper.getElementText(document, HEADING_SELECTOR);
    }

    @Override
    public String parseCity() {
        return ParserHelper.getElementText(document, CITY_SELECTOR);
    }

    @Override
    public String parseSeller() {
        return ParserHelper.getElementText(document, SELLER_SELECTOR);
    }

    @Override
    public List<String> parsePhoto() {
        return document.select(PHOTO_SELECTOR).eachAttr("src");
    }

    @Override
    public String parseDescription() {
        return "";
    }

    @Override
    public String parseId() {
        return "";
    }
}
