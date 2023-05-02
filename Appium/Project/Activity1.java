package liveproject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Activity1 {
    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired capabilities
        UiAutomator2Options options = new UiAutomator2Options().
                setPlatformName("android").
                setAutomationName("UiAutomator2").
                setAppPackage("com.google.android.apps.tasks").
                setAppActivity(".ui.TaskListsActivity").
                noReset();

        //Appium server url
        URL serverURL = new URL("http://localhost:4723/wd/hub");
        //open app
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @Test
    public void addNewTask() throws InterruptedException {
        //test parameter: tasks that needs to be added
        String[] arr = {"Complete Activity with Google Tasks", "Complete Activity with Google Keep", "Complete the second Activity Google Keep"};
        //Putting for loop for adding the given task in the test parameter
        for (int i = 0; i < arr.length; i++) {
            //identifying and clicking on New Task Button
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.apps.tasks:id/tasks_fab")));
            WebElement AddNewTaskBtn = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/tasks_fab"));
            AddNewTaskBtn.click();
            //waiting for text box to appear
            Thread.sleep(2000);
            //identifying and entering task in New Task text box
            WebElement NewTaskTxtBox = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='New task']"));
            NewTaskTxtBox.sendKeys(arr[i]);
            //clicking on save button
            WebElement SaveBtn = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Save']"));
            SaveBtn.click();
        }
        //fetching the added task and validating with test param
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.google.android.apps.tasks:id/task_name']")));
        List<WebElement> FrameLayouts = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.google.android.apps.tasks:id/task_name']"));
        int Size = FrameLayouts.size();
        System.out.println(Size);
        int j = arr.length - 1;
        for (WebElement Frame : FrameLayouts) {
            String TaskName = Frame.getAttribute("text");
            System.out.println(TaskName);
            //validating the Tasks which are added
            Assert.assertEquals(TaskName, arr[j]);
            j = j - 1;

        }

    }

    @Test
    public void clearTask() throws InterruptedException {
        //fetching the added tasks
        List<WebElement> TaskList = driver.findElements(AppiumBy.xpath("//android.view.View[@content-desc='Mark as complete']"));
        int count = TaskList.size();
        //selecting the tasks
        for (WebElement TaskName : TaskList) {
            TaskName.click();
            Thread.sleep(2000);
        }
        //clicking on more actions
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='More options']")).click();
        //clicking on delete all options
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Delete all completed tasks']")).click();
        //and deleting the Tasks
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@text='Delete']"))).click();

    }
}