package com.ua.verdysh;

import com.ua.verdysh.controller.AutoriaScraper;
import com.ua.verdysh.controller.TableCreator;
import com.ua.verdysh.model.Advertisement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutoriaScraper scraper = new AutoriaScraper();
        TableCreator creator = new TableCreator();

        List<Advertisement> advertisements = scraper.getAdvertisements();
        creator.createTable(advertisements);
    }
}
