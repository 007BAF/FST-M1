package Activites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {
    WebDriver Driver;

        @BeforeClass
        public void setUp() {
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NuLL");
            WebDriverManager.firefoxdriver().setup();
            Driver=new FirefoxDriver();
            Driver.get("http://alchemy.hguy.co/orangehrm");

        }

        @Test
        public void GetURL() {
          String   url="http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login";
            System.out.println("URL Is :"+Driver.getCurrentUrl());
            Assert.assertEquals(Driver.getCurrentUrl(),url);
        }
        @AfterClass
        public void tearDown(){
            Driver.close();
            System.out.println("Browser Closed");

        }
    }


