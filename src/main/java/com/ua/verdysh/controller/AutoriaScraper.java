package com.ua.verdysh.controller;

import com.ua.verdysh.controller.helpers.ScraperHelper;
import com.ua.verdysh.model.Advertisement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class AutoriaScraper {
    private static final String WEBSITE = "https://auto.ria.com/";
    private static final int MAX_BRANDS = 5;
    private static final int FIRST_PAGE_OF_BRAND = 1;
    private static final int MAX_PAGE_OF_BRAND = 10;

    public List<Advertisement> getAdvertisements() {
        List<Advertisement> result = new ArrayList<>();
        List<String> cars = getCarsUrl();
        for (String carUrl : cars) {
            Document doc = ScraperHelper.getDocument(carUrl);
            result.add(getAdvertisement(doc));
        }
        return result;
    }

    Advertisement getAdvertisement(Document document) {
        AdvertisementGenerator generator = new AdvertisementGenerator();
        return generator.generateAdvertisement(document);
    }

    private List<String> getCarsUrl() {
        List<String> result = new ArrayList<>();
        List<String> brands = getBrandsUrl();
        for (int count = 0; count < MAX_BRANDS; count++) {
            result.addAll(getCarsOfBrandUrl(brands.get(count)));
        }
        return result;
    }

    private List<String> getBrandsUrl() {
        List<String> result = new ArrayList<>();
        WebDriver driver = ScraperHelper.getFirefoxDriver();
        driver.get(WEBSITE);
        for (WebElement element : driver.findElements(By.xpath("//a[@class='item-brands']"))) {
            result.add(element.getAttribute("href"));
        }
        driver.quit();
        return result;
    }

    private List<String> getCarsOfBrandUrl(String brandUrl) {
        List<String> result = new ArrayList<>();
        for (int count = FIRST_PAGE_OF_BRAND; count <= MAX_PAGE_OF_BRAND; count++) {
            Document document = ScraperHelper.getDocument(brandUrl + String.format("?page=%d", count));
            if (document != null) {
                for (Element element : document.select(".m-link-ticket")) {
                    result.add(element.attr("href"));
                }
            }
        }
        return result;
    }
}
