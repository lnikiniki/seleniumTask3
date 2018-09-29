import com.epam.lab.seleniumTask3.businessObjects.GmailMainBO;
import com.epam.lab.seleniumTask3.businessObjects.GmailSignInBO;
import com.epam.lab.seleniumTask3.drivers.DriverPool;
import com.epam.lab.seleniumTask3.parsers.PropertyParser;
import com.epam.lab.seleniumTask3.parsers.XMLParser;
import org.testng.Assert;
import org.testng.annotations.*;

public class GmailTest {
    private String correctEmail;
    private String incorrectEmail;
    private String emailSubject;
    private String emailText;

    @DataProvider(name = "SignIn", parallel = true)
    public static Object[][] credentials(){
        return new XMLParser("src/main/resources/signIn.xml").getData();
    }

    @BeforeClass
    public void preparing() {
        System.setProperty("webdriver.chrome.driver", new PropertyParser("src/main/resources/driver.properties").getDriverPath());
        XMLParser emailDataParser = new XMLParser("src/main/resources/email.xml");
        correctEmail = emailDataParser.getName("correct");
        incorrectEmail = emailDataParser.getName("incorrect");
        emailSubject = emailDataParser.getName("subject");
        emailText = emailDataParser.getName("text");
    }

    @Test(dataProvider = "SignIn")
    public void sendEmail(String email, String password) {
        DriverPool dp = new DriverPool();
        GmailSignInBO signIn = new GmailSignInBO(dp.getDriver());
        signIn.signIn(email, password);
        GmailMainBO main = new GmailMainBO(dp.getDriver());
        main.composeEmail(incorrectEmail, emailSubject, emailText);
        Assert.assertTrue(main.alertIsDisplayed());
        main.submitAlert();
        main.changeEmail(correctEmail);
        Assert.assertTrue(main.notificationIsDisplayed());
        main.waitNotificationToDisappear();
        dp.getDriver().quit();
    }
}
