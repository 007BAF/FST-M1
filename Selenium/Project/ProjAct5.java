package Activites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Activity5 {

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
    public void Myinfo() {

        WebElement ViewDet=Driver.findElement(By.xpath("//a[@id='menu_pim_viewMyDetails']"));
        ViewDet.click();
        ViewDet.click();
        WebElement Editemp=Driver.findElement(By.xpath("//input[@id='btnSave']"));
            Editemp.click();
        WebElement EmpFullName= Driver.findElement(By.xpath("//input[@id='personal_txtEmpFirstName']"));
        EmpFullName.clear();
        EmpFullName.sendKeys("orange");
        WebElement Gender = Driver.findElement(By.id("personal_optGender_2"));
        Gender.click();
        System.out.println("gender slect");
        Select Nationality = new Select(Driver.findElement(By.id("personal_cmbNation")));
         Nationality.selectByVisibleText("Indian");
        WebElement editbtn =Driver.findElement(By.xpath("//input[@value='Save']"));
        editbtn.click();

        }
    @AfterClass
    public void tearDown(){
        Driver.close();
        System.out.println("Browser closed");
    }





}




