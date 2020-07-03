package com.ua.verdysh;

import com.ua.verdysh.controller.AutoRiaWebScraper;
import com.ua.verdysh.controller.TableCreator;
import com.ua.verdysh.model.CarAdvertisement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutoRiaWebScraper scraper = new AutoRiaWebScraper();
        TableCreator creator = new TableCreator();

        List<CarAdvertisement> advertisements = scraper.scrap();
        creator.createTable(advertisements, "auto.xls");
    }
}
