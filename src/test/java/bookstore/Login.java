package bookstore;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class Login extends Browser {

    By userNameLocator = By.cssSelector("#userName");
    By pwdLocator = By.xpath("//input[@type='password']");
    By loginBttnLocator = By.id("login");
    By userHomePage = By.id("userName-value");

    public void signIn() throws InterruptedException {
        if(driver.findElement(userNameLocator).isDisplayed()) {
            driver.findElement(userNameLocator).sendKeys("testMc");
            driver.findElement(pwdLocator).sendKeys("t3st_T3ST!");
            driver.findElement(loginBttnLocator).click();
            waitForElement(userHomePage);
            assertEquals("testMc", driver.findElement(userHomePage).getText());
        } else {
            System.out.println("UserName element is not displayed!");
        }
    }

}
