import com.epam.lab.seleniumTask3.pageObjects.GmailMain;
import com.epam.lab.seleniumTask3.pageObjects.GmailSignIn;
import com.epam.lab.seleniumTask3.parsers.PropertyParser;
import com.epam.lab.seleniumTask3.parsers.XMLParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GmailTest {
    private int wait;
    private String url;
    private String email;
    private String password;
    private String correctEmail;
    private String incorrectEmail;
    private String emailSubject;
    private String emailText;

    @BeforeClass
    public void preparing() {
        PropertyParser propertyParser = new PropertyParser("src/main/resources/driver.properties");
        wait = propertyParser.getWaitTime();
        System.setProperty("webdriver.chrome.driver", propertyParser.getDriverPath());
        XMLParser xmlParser = new XMLParser("src/main/resources/signIn.xml");
        url = xmlParser.getName("url");
        email = xmlParser.getName("email");
        password = xmlParser.getName("password");
        XMLParser xmlParserEmail = new XMLParser("src/main/resources/email.xml");
        correctEmail = xmlParserEmail.getName("correct");
        incorrectEmail = xmlParserEmail.getName("incorrect");
        emailSubject = xmlParserEmail.getName("subject");
        emailText = xmlParserEmail.getName("text");
    }

    @Test
    public void sendEmail() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        driver.get(url);
        GmailSignIn signIn = new GmailSignIn(driver);
        signIn.enterEmail(email);
        signIn.enterPassword(password);
        GmailMain main = new GmailMain(driver);
        main.sendEmail(incorrectEmail, emailSubject, emailText);
        Assert.assertTrue(main.alertDialogIsPresent());
        main.submitAlert();
        main.changeEmail(correctEmail);
        main.submitSend();
        Assert.assertTrue(main.checkEmail());
    }
}
