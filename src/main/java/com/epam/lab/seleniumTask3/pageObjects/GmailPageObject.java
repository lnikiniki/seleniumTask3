package com.epam.lab.seleniumTask3.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class GmailPageObject {
    WebDriver driver;

    GmailPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    void waitUntilVisible(WebElement element){
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));
    }

    void waitUntilClickable(WebElement element){
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(element));
    }
}
