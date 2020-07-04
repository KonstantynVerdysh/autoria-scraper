package com.ua.verdysh.controller.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class ScraperHelper {
    private ScraperHelper() {}

    public static Document getDocument(String carUrl) {
        Document document = null;
        try {
            document = Jsoup.connect(carUrl).get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return document;
    }

    public static WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        return new FirefoxDriver();
    }
}
