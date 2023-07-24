package Activites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Activity3 {
    WebDriver Driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NuLL");
        WebDriverManager.firefoxdriver().setup();
        Driver=new FirefoxDriver();
        Driver.get("http://alchemy.hguy.co/orangehrm");
        wait= new WebDriverWait(Driver, Duration.ofSeconds(10));


    }

    @Test
    public void Login() {
        WebElement username=Driver.findElement(By.id("txtUsername"));
        WebElement password=Driver.findElement(By.id("txtPassword"));
        WebElement loginbtn=Driver.findElement(By.id("btnLogin"));
        username.sendKeys("orange");
        password.sendKeys("orangepassword123");
        loginbtn.click();
        WebElement welcomemsg=Driver.findElement(By.id("welcome"));
        System.out.println(welcomemsg.getText());

    }
    @AfterClass
    public void tearDown(){
        Driver.close();
        System.out.println("Browser closed");
    }
}

