package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ParserHelper;
import com.ua.verdysh.controller.interfaces.Parsable;
import org.jsoup.nodes.Document;

import java.util.List;

class AdvertisementParser implements Parsable {

    private static final String PRICE_USD_SELECTOR = "div.price_value > strong";
    private static final String PRICE_UAH_SELECTOR = "div.price_value--additional > span:nth-child(3) > span";
    private static final String HEADING_SELECTOR = "h1";
    private static final String CITY_SELECTOR = "section.seller.proven:nth-child(2) ul.checked-list.unstyle.mb-15 li.item:nth-child(1) > div.item_inner";
    private static final String SELLER_SELECTOR = "h4";
    private static final String PHOTO_SELECTOR = "div.photo-620x465 > picture > img";
    private static final String DESCRIPTION_SELECTOR = "dd[class='additional-data show-line'] div#full-description";
    private static final String ID_SELECTOR = "section.m-padding.mb-20 ul.mb-10-list.unstyle.size13.mb-15 li:nth-child(2) span";
    private Document document;

    AdvertisementParser(Document document) {
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
        return  ParserHelper.getElementText(document, PRICE_UAH_SELECTOR);
    }

    @Override
    public String parseHeading() {
        return document.select(HEADING_SELECTOR).attr("title");
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
        return ParserHelper.getElementText(document, DESCRIPTION_SELECTOR);
    }

    @Override
    public String parseId() {
        return ParserHelper.getElementText(document,ID_SELECTOR);
    }
}
