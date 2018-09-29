package com.epam.lab.seleniumTask3.businessObjects;

import com.epam.lab.seleniumTask3.pageObjects.GmailSignIn;
import org.openqa.selenium.WebDriver;

public class GmailSignInBO {
    private GmailSignIn gmailSignIn;

    public GmailSignInBO(WebDriver driver){
        gmailSignIn = new GmailSignIn(driver);
    }

    public void signIn(String email, String password){
        gmailSignIn.openPage();
        gmailSignIn.printEmail(email);
        gmailSignIn.clickNextButton();
        gmailSignIn.printPassword(password);
        gmailSignIn.clickPasswordNextButton();
    }
}
