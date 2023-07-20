package TestNGAct;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
public class Act5 {

        WebDriver driver;
        //Include alwaysRun property on the @BeforeTest
        //to make sure the page opens
        @BeforeTest(alwaysRun = true)
        public void beforeMethod() {
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NuLL");
            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();

            driver.get("https://www.training-support.net/selenium/target-practice");
        }

        @Test (groups = {"HeaderTests", "ButtonTests"})
        public void pageTitleTest() {
            String title = driver.getTitle();
            System.out.println("Title is: " + title);
            Assert.assertEquals(title, "Target Practice");

        }

        @Test (dependsOnMethods = {"pageTitleTest"}, groups = {"HeaderTests"})
        public void HeaderTest1() {
            WebElement header3 = driver.findElement(By.cssSelector("h3#third-header"));
            Assert.assertEquals(header3.getText(), "Third header");
            System.out.println(header3.getText());
        }

        @Test (dependsOnMethods = {"pageTitleTest"}, groups = {"HeaderTests"})
        public void HeaderTest2() {
            WebElement header5 = driver.findElement(By.cssSelector("h5.ui.green.header"));
            Assert.assertEquals(header5.getCssValue("color"), "rgb(33, 186, 69)");
            System.out.println(header5.getText());
        }

        @Test (dependsOnMethods = {"pageTitleTest"}, groups = {"ButtonTests"})
        public void ButtonTest1() {
            WebElement button1 = driver.findElement(By.cssSelector("button.olive"));
            Assert.assertEquals(button1.getText(), "Olive");
        }

        @Test (dependsOnMethods = {"pageTitleTest"}, groups = {"ButtonTests"})
        public void ButtonTest2() {
            WebElement button2 = driver.findElement(By.cssSelector("button.brown"));
            Assert.assertEquals(button2.getCssValue("color"), "rgb(255, 255, 255)");
        }

        //Include alwaysRun property on the @AfterTest
        //to make sure the page closes
        @AfterTest(alwaysRun = true)
        public void afterMethod() {
            //Close the browser
            driver.close();
        }


}