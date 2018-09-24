package com.epam.lab.seleniumTask3.pageObjects;

import com.epam.lab.seleniumTask3.parsers.PropertyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class GmailPageObject {
    private WebDriver driver;
    private int wait;

    GmailPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PropertyParser propertyParser = new PropertyParser("src/main/resources/driver.properties");
        wait = propertyParser.getWaitTime();
    }

    void waitUntilVisible(WebElement element){
        new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOf(element));
    }

    void waitUntilClickable(WebElement element){
        new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(element));
    }
}
