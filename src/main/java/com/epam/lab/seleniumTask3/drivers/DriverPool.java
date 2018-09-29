package com.epam.lab.seleniumTask3.drivers;

import com.epam.lab.seleniumTask3.parsers.PropertyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverPool {

    private ThreadLocal<WebDriver> localDriver = new ThreadLocal<>() {
        @Override
        protected WebDriver initialValue() {
            WebDriver chrome = new ChromeDriver();
            int wait = new PropertyParser("src/main/resources/driver.properties").getWaitTime();
            chrome.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
            return chrome;
        }

        @Override
        public void remove() {
            WebDriver driver = get();
            if (driver != null) driver.close();
            super.remove();
        }

        @Override
        public void set(WebDriver value) {
            throw new UnsupportedOperationException();
        }
    };

    public WebDriver getDriver(){
        return localDriver.get();
    }
}
