package bookstore;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.NoAlertPresentException;

import java.time.Duration;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class Browser {

    public static WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    By adID = By.id("close-fixedban");

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
        if(driver.findElement(adID).isDisplayed()) {
            handleAd(adID);
        }
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
    }

    public void waitForElement(final By element) {
        // Waiting for 5 seconds for an element to be present on the page, checking
        // for its presence once every 1 second.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5L))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class);
        WebElement find = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(element);
            }
        });
    }

    public void waitForAlert() throws NoAlertPresentException {
        // Waiting for 5 seconds for an element to be present on the page, checking
        // for its presence once every 1 second.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5L))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void assertElementText(String text, By element) {
        assertEquals(text, driver.findElement(element).getText());
    }

    public void assertAlert(String text) {
        Alert alert = driver.switchTo().alert();
        assertEquals(text, alert.getText());
        alert.accept();
    }

    public void handleAd(By element) {
        waitForElement(element);
        driver.findElement(element).click();
    }

    public void scrollToElement(By element) {
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

    public void zoomLevel(int zoom) {
        js.executeScript("document.body.style.zoom = '"+zoom+"%'");
    }

}
