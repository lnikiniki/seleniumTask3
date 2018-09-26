package com.epam.lab.seleniumTask3.pageObjects;

import com.epam.lab.seleniumTask3.webElements.Button;
import com.epam.lab.seleniumTask3.webElements.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailMain extends GmailPageObject {
    @FindBy(css = "div.z0")
    private Button composeButton;

    @FindBy(name = "to")
    private Input emailTo;

    @FindBy(name = "subjectbox")
    private Input subjectInput;

    @FindBy(css = "div[role = 'textbox']")
    private Input textArea;

    @FindBy(css = "td.gU.Up")
    private Button submitButton;

    @FindBy(css = "div[role = 'alertdialog']")
    private WebElement alertDialog;

    @FindBy(name = "ok")
    private Button alertSubmitButton;

    @FindBy(css = "div.vM")
    private Button emailAddressDeleteButton;

    @FindBy(css = "div.aoD.hl")
    private Input emailArea;

    @FindBy(css = "div.vh > span.aT")
    private WebElement notification;

    public GmailMain(WebDriver driver) {
        super(driver);
    }

    public void sendEmail(String email, String subject, String text) {
        composeButton.click();
        emailTo.print(email);
        subjectInput.print(subject);
        textArea.print(text);
        submitSend();
    }

    public void submitSend() {
        submitButton.click();
    }

    public void changeEmail(String email) {
        deleteAddress();
        emailTo.print(email);
    }

    public boolean alertDialogIsPresent() {
        return alertDialog.isDisplayed();
    }

    public void submitAlert() {
        alertSubmitButton.click();
    }

    private void deleteAddress() {
        emailArea.sendKeys(" ");
        emailAddressDeleteButton.waitUntilVisible(driver);
        emailAddressDeleteButton.waitUntilClickable(driver);
        emailAddressDeleteButton.click();
    }

    public boolean checkEmail() {
        waitUntilVisible(notification);
        return notification.isDisplayed();
    }
}
