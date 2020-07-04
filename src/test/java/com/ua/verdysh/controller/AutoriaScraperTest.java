package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ScraperHelper;
import com.ua.verdysh.model.Advertisement;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutoriaScraperTest {
    private final AutoriaScraper scraper = new AutoriaScraper();

    @Test
    void generateAdvertisement_returnAutoriaAdvertisementWhenDocumentIsAutoriaCarPage() {
        Document document = ScraperHelper.getDocument("https://auto.ria.com/auto_vaz_21099_25736371.html");

        Advertisement advertisement = scraper.getAdvertisement(document);

        String expectedCity = "Ровно";
        String actualCity = advertisement.getCity();

        String expectedDescription = "Продаж 99 в хорошому стані звоніть стоїть газ обмін з моєю доплатою 2000$";
        String actualDescription = advertisement.getDescription();

        String expectedHeading = "ВАЗ 21099 2006";
        String actualHeading = advertisement.getHeading();

        List<String> expectedPhoto = Arrays.asList("https://cdn3.riastatic.com/photosnew/auto/photo/vaz_21099__305341533f.jpg",
                "https://cdn0.riastatic.com/photosnew/auto/photo/vaz_21099__305341550s.jpg",
                "https://cdn2.riastatic.com/photosnew/auto/photo/vaz_21099__305341577s.jpg",
                "https://cdn1.riastatic.com/photosnew/auto/photo/vaz_21099__305341561s.jpg");
        List<String> actualPhoto = advertisement.getPhoto();

        String expectedPriceUah = "67 600";
        String actualPriceUah = advertisement.getPriceUah();

        String expectedPriceUsd = "2 500 $";
        String actualPriceUsd = advertisement.getPriceUsd();

        String expectedUrl = "https://auto.ria.com/auto_vaz_21099_25736371.html";
        String actualUrl = advertisement.getUrl();

        String expectedSeller = "Сашко";
        String actualSeller = advertisement.getSeller();

        assertEquals(expectedCity, actualCity);
        assertEquals(expectedDescription, actualDescription);
        assertEquals(expectedHeading, actualHeading);
        assertEquals(expectedPhoto, actualPhoto);
        assertEquals(expectedPhoto.size(), actualPhoto.size());
        assertEquals(expectedPriceUah, actualPriceUah);
        assertEquals(expectedPriceUsd, actualPriceUsd);
        assertEquals(expectedUrl, actualUrl);
        assertEquals(expectedSeller, actualSeller);
    }
}