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

    public class Activity4 {
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
        public void AddEmployee() {
            WebElement MenuPIM= Driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']"));
             MenuPIM.click();
             WebElement Addemp=Driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']"));
            System.out.println("Add employee details");
            Addemp.click();
            WebElement FName=Driver.findElement(By.id("firstName"));
            FName.sendKeys("Ram");

             WebElement LName=Driver.findElement(By.id("lastName"));
            LName.sendKeys("sita");
            WebElement EID=Driver.findElement(By.id("employeeId"));


            EID.sendKeys("111");
            Driver.findElement(By.id("chkLogin")).isSelected();

            WebElement saveemp=Driver.findElement(By.id("btnSave"));
            saveemp.click();



        }
        @AfterClass
        public void tearDown(){
            Driver.close();
            System.out.println("Browser closed");
        }



    }



