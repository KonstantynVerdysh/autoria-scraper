package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ParserHelper;
import com.ua.verdysh.controller.interfaces.Parser;
import org.jsoup.nodes.Document;

import java.util.List;

class DealerAdvertisementParser implements Parser {
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
        return ParserHelper.getElementText(document, "section[class='price mb-15 mhide'] div.price_value");
    }
    @Override
    public String parsePriceUah() {
        return ParserHelper.getElementText(document, "div[class='price_value price_value--additional']");
    }
    @Override
    public String parseHeading() {
        return ParserHelper.getElementText(document, "h1");
    }
    @Override
    public String parseCity() {
        return ParserHelper.getElementText(document, "ul.checked-list.unstyle:nth-child(2) li.item:nth-child(1) > div.item_inner");
    }
    @Override
    public String parseSeller() {
        return ParserHelper.getElementText(document, "section[class='seller mb-15'] div.seller_info_area strong");
    }
    @Override
    public List<String> parsePhoto() {
        return document.select(".image-gallery-slides img").eachAttr("src");
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
