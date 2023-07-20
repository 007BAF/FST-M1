package TestNGAct;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

public class Act7 {

        WebDriver driver;
        WebDriverWait wait;

        @BeforeClass
        public void beforeClass() {
            // Set up the Firefox driver
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NuLL");
            WebDriverManager.firefoxdriver().setup();
            //Create a new instance of the Firefox driver
            driver = new FirefoxDriver();

            //Open browser
            driver.get("https://www.training-support.net/selenium/login-form");
        }

        @DataProvider(name = "Authentication")
        public static Object[][] credentials() {
            return new Object[][] { { "admin", "password" }};
        }

        @Test (dataProvider = "Authentication")
        public void loginTestCase(String username, String password) {
            //Find username and pasword fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));

            //Enter values
            usernameField.sendKeys(username);
            passwordField.sendKeys(password);

            //Click Log in
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            //Assert Message
            String loginMessage = driver.findElement(By.id("action-confirmation")).getText();
            Assert.assertEquals(loginMessage, "Welcome Back, admin");
        }

        @AfterClass
        public void afterClass() {
            //Close browser
            driver.close();
        }


}