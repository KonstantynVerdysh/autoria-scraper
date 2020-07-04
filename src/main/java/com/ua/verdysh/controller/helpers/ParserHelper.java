package com.ua.verdysh.controller.helpers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParserHelper {
    private ParserHelper() {}

    public static String getElementText(Document document, String selector) {
        Elements elements = document.select(selector);
        String result = "";
        if (!elements.isEmpty()) {
            result = elements.first().text();
        }
        return result;
    }
}
