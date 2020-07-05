package com.ua.verdysh.controller.interfaces;

import java.util.List;

public interface Parsable {
    String parseUrl();
    String parsePriceUsd();
    String parsePriceUah();
    String parseHeading();
    String parseCity();
    String parseSeller();
    List<String> parsePhoto();
    String parseDescription();
    String parseId();
}
