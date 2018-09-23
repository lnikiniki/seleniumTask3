package com.epam.lab.seleniumTask3.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailMain extends GmailPageObject {
    @FindBy(css = "div.z0")
    private WebElement composeButton;

    @FindBy(name = "to")
    private WebElement emailTo;

    @FindBy(name = "subjectbox")
    private WebElement subjectInput;

    @FindBy(css = "div[role = 'textbox']")
    private WebElement textArea;

    @FindBy(css = "td.gU.Up")
    private WebElement submitButton;

    @FindBy(css = "div[role = 'alertdialog']")
    private WebElement alertDialog;

    @FindBy(name = "ok")
    private WebElement alertSubmitButton;

    @FindBy(css = "div.vM")
    private WebElement emailAddressDeleteButton;

    @FindBy(css = "div.aoD.hl")
    private WebElement emailArea;

    @FindBy(xpath = "//div[@class='vh']/span[@class='aT']")
    private WebElement notification;

    public GmailMain(WebDriver driver) {
        super(driver);
    }

    public void sendEmail(String email, String subject, String text) {
        composeButton.click();
        emailTo.sendKeys(email);
        subjectInput.sendKeys(subject);
        textArea.sendKeys(text);
        submitSend();
    }

    public void submitSend() {
        submitButton.click();
    }

    public void changeEmail(String email) {
        deleteAddress();
        emailTo.sendKeys(email);
    }

    public boolean alertDialogIsPresent() {
        return alertDialog.isDisplayed();
    }

    public void submitAlert() {
        alertSubmitButton.click();
    }

    private void deleteAddress() {
        emailArea.sendKeys(" ");
        emailAddressDeleteButton.click();
    }

    public boolean checkEmail() {
        waitUntilVisible(notification);
        return notification.isDisplayed();
    }
}
