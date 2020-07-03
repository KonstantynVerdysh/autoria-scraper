package com.ua.verdysh.controller;

import com.ua.verdysh.model.CarAdvertisement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataScraperTest {
    private final DataScraper scraper = new DataScraper();

    @Test
    void scrapCarAdvertisement() {
        Document document = null;
        try {
            document = Jsoup.connect("https://auto.ria.com/auto_vaz_21099_25736371.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CarAdvertisement advertisement = scraper.scrapCarAdvertisement(document);

        String expectedCity = "Ровно";
        String actualCity = advertisement.getCity();
        assertEquals(expectedCity, actualCity);

        String expectedDescription = "Продаж 99 в хорошому стані звоніть стоїть газ обмін з моєю доплатою 2000$";
        String actualDescription = advertisement.getDescription();
        assertEquals(expectedDescription, actualDescription);

        String expectedHeading = "ВАЗ 21099 2006";
        String actualHeading = advertisement.getHeading();
        assertEquals(expectedHeading, actualHeading);

        List<String> expectedPhoto = Arrays.asList("https://cdn3.riastatic.com/photosnew/auto/photo/vaz_21099__305341533f.jpg", "https://cdn0.riastatic.com/photosnew/auto/photo/vaz_21099__305341550s.jpg", "https://cdn2.riastatic.com/photosnew/auto/photo/vaz_21099__305341577s.jpg", "https://cdn1.riastatic.com/photosnew/auto/photo/vaz_21099__305341561s.jpg");
        List<String> actualPhoto = advertisement.getPhoto();
        assertEquals(expectedPhoto, actualPhoto);
        assertEquals(expectedPhoto.size(), actualPhoto.size());

        String expectedPriceUah = "67 700";
        String actualPriceUah = advertisement.getPriceUah();
        assertEquals(expectedPriceUah, actualPriceUah);

        String expectedPriceUsd = "2 500 $";
        String actualPriceUsd = advertisement.getPriceUsd();
        assertEquals(expectedPriceUsd, actualPriceUsd);

        String expectedUrl = "https://auto.ria.com/auto_vaz_21099_25736371.html";
        String actualUrl = advertisement.getUrl();
        assertEquals(expectedUrl, actualUrl);

        String expectedSeller = "Сашко";
        String actualSeller = advertisement.getSeller();
        assertEquals(expectedSeller, actualSeller);
    }
}