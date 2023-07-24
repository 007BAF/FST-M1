package Activites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Activity1 {
    WebDriver Driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NuLL");
        WebDriverManager.firefoxdriver().setup();
        Driver=new FirefoxDriver();
        wait= new WebDriverWait(Driver, Duration.ofSeconds(10));

       Driver.get("http://alchemy.hguy.co/orangehrm");
        //Driver.get("https://www.google.com");

    }

    @Test
    public void Verifytitle() {
        Assert.assertEquals(Driver.getTitle(), "OrangeHRM");
        System.out.println("TitleName"+Driver.getTitle());

    }
    @AfterClass
    public void tearDown(){
        Driver.close();
        System.out.println("Browswe closed");
    }
}

