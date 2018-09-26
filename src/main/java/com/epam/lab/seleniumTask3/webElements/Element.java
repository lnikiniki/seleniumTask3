package com.epam.lab.seleniumTask3.webElements;

import com.epam.lab.seleniumTask3.parsers.PropertyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Element {
    WebElement element;
    private int wait;

    Element(WebElement element) {
        this.element = element;
        wait = new PropertyParser("src/main/resources/driver.properties").getWaitTime();
    }

    public void click() {
        element.click();
    }

    public void sendKeys(CharSequence... charSequences) {
        element.sendKeys(charSequences);
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    public void clear() {
        element.clear();
    }

    public void waitUntilVisible(WebDriver driver) {
        new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOf(element));

    }

    public void waitUntilClickable(WebDriver driver) {
        new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(element));
    }
}
