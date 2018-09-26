package com.epam.lab.seleniumTask3.webElements;

import org.openqa.selenium.WebElement;

public class Input extends Element {
    public Input(WebElement element) {
        super(element);
    }

    public void print(String text) {
        element.clear();
        element.sendKeys(text);
    }
}
