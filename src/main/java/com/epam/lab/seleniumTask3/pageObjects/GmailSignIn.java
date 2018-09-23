package com.epam.lab.seleniumTask3.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailSignIn extends GmailPageObject {
    @FindBy(id = "identifierId")
    private WebElement emailInput;

    @FindBy(id = "identifierNext")
    private WebElement nextButton;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "div#passwordNext")
    private WebElement passwordNextButton;

    public GmailSignIn(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
        nextButton.click();
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
        waitUntilVisible(passwordNextButton);
        waitUntilClickable(passwordNextButton);
        passwordNextButton.click();
    }
}
