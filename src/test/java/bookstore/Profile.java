package bookstore;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class Profile extends Browser {

    By goToBookStoreBttn = By.xpath("//*[@type='button'][text()='Go To Book Store']");
    By bookStoreHeader = By.className("main-header");

    public void goToBookStore() throws InterruptedException {
        Login login = new Login();
        login.signIn();
        waitForElement(goToBookStoreBttn);
        driver.findElement(goToBookStoreBttn).click();
        waitForElement(bookStoreHeader);
        assertEquals("Book Store", driver.findElement(bookStoreHeader).getText());
    }

}
