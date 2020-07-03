package com.ua.verdysh;

import com.ua.verdysh.controller.AutoRiaWebScraper;
import com.ua.verdysh.controller.TableCreator;

public class Main {
    public static void main(String[] args) {
        AutoRiaWebScraper scraper = new AutoRiaWebScraper();
        TableCreator creator = new TableCreator();

        creator.createTable(scraper.scrapAds());
    }
}
