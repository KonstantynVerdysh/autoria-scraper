package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ParserHelper;
import com.ua.verdysh.controller.interfaces.Parser;
import org.jsoup.nodes.Document;

import java.util.List;

class AdvertisementParser implements Parser {
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
        return ParserHelper.getElementText(document, "div.price_value > strong");
    }
    @Override
    public String parsePriceUah() {
        return  ParserHelper.getElementText(document, "div.price_value--additional > span:nth-child(3) > span");
    }
    @Override
    public String parseHeading() {
        return document.select("h1").attr("title");
    }
    @Override
    public String parseCity() {
        return ParserHelper.getElementText(document, "section.seller.proven:nth-child(2) ul.checked-list.unstyle.mb-15 li.item:nth-child(1) > div.item_inner");
    }
    @Override
    public String parseSeller() {
        return ParserHelper.getElementText(document, "h4");
    }
    @Override
    public List<String> parsePhoto() {
        return document.select("div.photo-620x465 > picture > img").eachAttr("src");
    }
    @Override
    public String parseDescription() {
        return ParserHelper.getElementText(document, "dd[class='additional-data show-line'] div#full-description");
    }
}
