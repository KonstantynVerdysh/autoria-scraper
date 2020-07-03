package com.ua.verdysh.controller;

import com.ua.verdysh.model.CarAdvertisement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AutoRiaWebScraper {
    private final static int MAX_BRANDS = 5;
    private final static int MAX_PAGES_OF_BRAND = 10;

    public List<CarAdvertisement> scrap() {
        List<String> cars = new ArrayList<>();
        List<String> brands = getBrandsUrl();
        for (int count = 0; count < MAX_BRANDS; count++) {
            cars.addAll(getCarsUrl(brands.get(count)));
        }
        List<CarAdvertisement> carAdvertisements = new ArrayList<>();
        for (String carUrl : cars) {
            carAdvertisements.add(generateCarAdvertisement(carUrl));
        }
        return carAdvertisements;
    }

    private List<String> getBrandsUrl() {
        List<String> result = new ArrayList<>();
        System.setProperty("webdriver.gecko.driver", "/home/user/IdeaProjects/autoria-scraper/src/main/resources/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://auto.ria.com/");
        for (WebElement element : driver.findElements(By.xpath("//a[@class='item-brands']"))) {
            result.add(element.getAttribute("href"));
        }
        driver.quit();
        return result;
    }

    private List<String> getCarsUrl(String brandUrl) {
        List<String> result = new ArrayList<>();
        try {
            for (int count = 1; count <= MAX_PAGES_OF_BRAND; count++) {
                Document document = Jsoup.connect(brandUrl + String.format("?page=%d", count)).get();
                for (Element element : document.select(".m-link-ticket")) {
                    result.add(element.attr("href"));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private CarAdvertisement generateCarAdvertisement(String carUrl) {
        CarAdvertisement result = new CarAdvertisement();
        result.setUrl(carUrl);

        if (carUrl.contains("newauto")) {
            return generateDealerCarAdvertisement(result);
        }

        try {
            Document document = Jsoup.connect(carUrl).get();

            Elements priceUsdElement = document.select("div.price_value > strong");
            String priceUsd = ifExistGetText(priceUsdElement);

            Elements priceUahElement = document.select("div.price_value--additional > span:nth-child(3) > span");
            String priceUah = ifExistGetText(priceUahElement);

            String heading = document.select("h1").attr("title");

            Elements cityElement = document.select("section.seller.proven:nth-child(2) ul.checked-list.unstyle.mb-15 li.item:nth-child(1) > div.item_inner");
            String city = ifExistGetText(cityElement);

            Elements sellerElement = document.select("h4");
            String seller = ifExistGetText(sellerElement);

            List<String> photo = document.select("div.photo-620x465 > picture > img").eachAttr("src");

            Elements descriptionElement = document.select("dd[class='additional-data show-line'] div#full-description");
            String description = ifExistGetText(descriptionElement);

            result.setPriceUsd(priceUsd);
            result.setPriceUah(priceUah);
            result.setHeading(heading);
            result.setCity(city);
            result.setSeller(seller);
            result.setPhoto(photo);
            result.setDescription(description);
            result.setId("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private CarAdvertisement generateDealerCarAdvertisement(CarAdvertisement advertisement) {
        try {
            Document document = Jsoup.connect(advertisement.getUrl()).get();

            Elements priceUsdElement = document.select("section[class='price mb-15 mhide'] div.price_value");
            String priceUsd = ifExistGetText(priceUsdElement);

            Elements priceUahElement = document.select("div[class='price_value price_value--additional']");
            String priceUah = ifExistGetText(priceUahElement);

            Elements headingElement = document.select("h1");
            String heading = ifExistGetText(headingElement);

            Elements cityElement = document.select("section.seller.mb-15:nth-child(1) ul.checked-list.unstyle:nth-child(2) li.item:nth-child(1) > div.item_inner7");
            String city = ifExistGetText(cityElement);

            Elements sellerElement = document.select("section[class='seller mb-15'] div.seller_info_area strong");
            String seller = ifExistGetText(sellerElement);

            List<String> photo = document.select(".image-gallery-slides img").eachAttr("src");

            advertisement.setPriceUsd(priceUsd);
            advertisement.setPriceUah(priceUah);
            advertisement.setHeading(heading);
            advertisement.setCity(city);
            advertisement.setSeller(seller);
            advertisement.setPhoto(photo);
            advertisement.setDescription("");
            advertisement.setId("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return advertisement;
    }

    private String ifExistGetText(Elements elements) {
        String result = "";
        if (!elements.isEmpty()) {
            result = elements.first().text();
        }
        return result;
    }
}
