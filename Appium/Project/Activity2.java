package liveproject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Activity2 {
    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired capabilities
        UiAutomator2Options options = new UiAutomator2Options().
                setPlatformName("android").
                setAutomationName("UiAutomator2").
                setAppPackage("com.google.android.keep").
                setAppActivity(".activities.BrowseActivity").
                noReset();

        //Appium server url
        URL serverURL = new URL("http://localhost:4723/wd/hub");
        //open app
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void createNewNote() throws InterruptedException {
        String StrTitle = "Test Auto";
        String StrDesc = "This is an automation test";
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("New text note"))).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Title']")).sendKeys(StrTitle);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Note']")).sendKeys(StrDesc);
        driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.keep:id/browse_note_interior_content")));
        WebElement title = driver.findElement(AppiumBy.id("com.google.android.keep:id/index_note_title"));
        String titleVal = title.getAttribute("text");
        Assert.assertEquals(StrTitle, titleVal);
        WebElement Desc = driver.findElement(AppiumBy.id("com.google.android.keep:id/index_note_text_description"));
        String DescVal = Desc.getAttribute("text");
        Assert.assertEquals(StrDesc, DescVal);


    }

    @Test
    public void deleteNote() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.google.android.keep:id/browse_note_interior_content")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.google.android.keep:id/bs_action_button"))).click();
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("android.widget.TextView[@text='Delete']")));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Delete']")).click();
    }


}
