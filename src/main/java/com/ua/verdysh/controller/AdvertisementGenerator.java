package com.ua.verdysh.controller;

import com.ua.verdysh.controller.interfaces.Parser;
import com.ua.verdysh.model.Advertisement;
import org.jsoup.nodes.Document;

class AdvertisementGenerator {
    Advertisement generateAdvertisement(Document document) {
        Advertisement advertisement = new Advertisement();
        Parser parser;

        if (document.location().contains("newauto")) {
            parser = new DealerAdvertisementParser(document);
        } else {
            parser = new AdvertisementParser(document);
        }

        advertisement.setUrl(parser.parseUrl());
        advertisement.setPriceUsd(parser.parsePriceUsd());
        advertisement.setPriceUah(parser.parsePriceUah());
        advertisement.setHeading(parser.parseHeading());
        advertisement.setCity(parser.parseCity());
        advertisement.setSeller(parser.parseSeller());
        advertisement.setPhoto(parser.parsePhoto());
        advertisement.setDescription(parser.parseDescription());
        advertisement.setId(parser.parseId());
        return advertisement;
    }
}
