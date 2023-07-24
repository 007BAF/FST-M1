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

public class Activity6 {
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

    @Test(priority = 1)
    public void Login() {
        WebElement username=Driver.findElement(By.id("txtUsername"));
        WebElement password=Driver.findElement(By.id("txtPassword"));
        WebElement loginbtn=Driver.findElement(By.id("btnLogin"));
        username.sendKeys("orange");
        password.sendKeys("orangepassword123");
        loginbtn.click();
        WebElement image=Driver.findElement(By.xpath("//img[@alt='OrangeHRM']"));
        System.out.println("ORAGE HRM image"+image.getText());

    }
    @Test(priority = 2)
    public void Directory() {
        WebElement MenuDir = Driver.findElement(By.xpath("//a[@id='menu_directory_viewDirectory']"));
        String expect="SearchDirectory";
        if (MenuDir.isEnabled()) {
            MenuDir.click();
            MenuDir.click();
            System.out.println("Is slected and clickable");
        }
         WebElement SearchDir = Driver.findElement(By.xpath("//div[@class='head']//h1"));
        System.out.println(SearchDir.getText());
        Assert.assertEquals(SearchDir.getText(),expect);





    }
    @AfterClass
    public void tearDown(){
        Driver.close();
        System.out.println("Browser closed");
    }



}



