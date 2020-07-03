package com.ua.verdysh;

import com.ua.verdysh.controller.DataScraper;
import com.ua.verdysh.controller.TableCreator;

public class Main {
    public static void main(String[] args) {
        DataScraper scraper = new DataScraper();
        TableCreator creator = new TableCreator();

        creator.createTable(scraper.scrapAds());
    }
}
