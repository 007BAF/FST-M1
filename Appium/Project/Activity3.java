package liveproject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import static liveproject.ActionBase.doSwipe;

public class Activity3 {
    AndroidDriver driver;
    WebDriverWait wait;


    @BeforeClass
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        //server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //driver initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        //open selenium page
        driver.get("https://www.training-support.net/selenium");
    }

        @Test
        public void webAppTest() {
        Dimension dims=driver.manage().window().getSize();

        //wait for the page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View")));

        //scroll to end by setting the start and end pointers
            Point start=new Point((int)(dims.width*0.5),(int)(dims.height*0.9));
            Point end=new Point((int)(dims.width*0.5),(int)(dims.height*0.6));
            doSwipe(driver,start,end,100);

            //Locate Element and Click it
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath
                    ("//android.widget.TextView[Contains(@text,'To-Do List')"))).click();

            //wait for the page to load
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath
                    ("//android.widget.EditText[@resource-id='taskInput']")));

            //Find Element on the page


            WebElement addTaskInput = driver.findElement(AppiumBy.xpath
                    ("//android.widget.EditText[@resource-id='taskInput']"));

            WebElement addTaskButton=driver.findElement(AppiumBy.xpath
                    ("//android.widget.Button[@text='Add Task']"));

            //Enter Tasks
            addTaskInput.sendKeys("Add tasks to list");
            addTaskButton.click();
            addTaskInput.sendKeys("Get the number of tasks");
            addTaskButton.click();
            addTaskInput.sendKeys("Clear the list");
            addTaskButton.click();

            //clear the list
            driver.findElement(AppiumBy.xpath(
                    "//android.widget.TextView[contains(@text, 'Clear List')]")).click();

            //Assertion
            List<WebElement> tasksadded = driver.findElements(AppiumBy.xpath(
                    "//android.view.View[@resource-id='taskInput'']"));
            Assert.assertEquals(tasksadded.size(), 0);
        }

    @AfterClass
    public void quitdriver() {
        driver.quit();
    }

    }

