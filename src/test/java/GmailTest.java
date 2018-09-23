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
        email = xmlParser.getName("email");
        System.out.println(email);
        password = xmlParser.getName("password");
        System.out.println(password);
        XMLParser xmlParserEmail = new XMLParser("src/main/resources/email.xml");
        correctEmail = xmlParserEmail.getName("correct");
        System.out.println(correctEmail);
        incorrectEmail = xmlParserEmail.getName("incorrect");
        System.out.println(incorrectEmail);
        emailSubject = /*xmlParser.getName("subject");*/ "Test";
        emailText = /*xmlParser.getName("text");*/"Test";
    }

    @Test
    public void sendEmail() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        driver.get("https://gmail.com");
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
