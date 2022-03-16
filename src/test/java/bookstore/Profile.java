package bookstore;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Profile extends Browser {

    By goToBookStoreBttn = By.xpath("//*[@type='button'][text()='Go To Book Store']");
    By bookStoreHeader = By.className("main-header");
    By adID = By.id("close-fixedban");
    By collectionSearchBar = By.id("searchBox");
    By deleteBookAction = By.id("delete-record-undefined");
    By booksTitles = By.xpath("//span[@class='mr-2']");
    By deleteAllBooksBttn = By.xpath("//button[text()='Delete All Books']");

    public void goToBookStore() throws InterruptedException {
        Login login = new Login();
        login.signIn();
        waitForElement(goToBookStoreBttn);
        if(driver.findElement(adID).isDisplayed()) {
            handleAd(adID);
        }
        scrollToElement(goToBookStoreBttn);
        driver.findElement(goToBookStoreBttn).click();
        waitForElement(bookStoreHeader);
        assertElementText("Book Store", bookStoreHeader);
    }

    public boolean isBookInCollection(String bookTitle) {
        driver.get("https://demoqa.com/profile");
        waitForElement(collectionSearchBar);
        driver.findElement(collectionSearchBar).sendKeys(bookTitle);
        boolean bookFound = false;
        List<WebElement> books = driver.findElements(booksTitles);
        if(books.size() > 0) {
            for(WebElement book : books) {
                if(book.getText().equalsIgnoreCase(bookTitle)) {
                    driver.findElement(deleteBookAction).click();
                    bookFound = true;
                    break;
                }
            }
        }
        return bookFound;
    }

    public void clearCollection() {
        driver.get("https://demoqa.com/profile");
        driver.findElement(deleteAllBooksBttn).click();
        /*
        Need to handle the iFrame
        to confirm the whole book collection deletion.
        */
    }

    public void deleteBookFromCollection(String bookTitle) {
        driver.get("https://demoqa.com/profile");
        waitForElement(collectionSearchBar);
        driver.findElement(collectionSearchBar).sendKeys(bookTitle);
        List<WebElement> books = driver.findElements(booksTitles);
        if(books.size() > 0) {
            for(WebElement book : books) {
                if(book.getText().equalsIgnoreCase(bookTitle)) {
                    driver.findElement(deleteBookAction).click();
                    break;
                }
            }
        }
        /*
        Need to handle the iFrame
        to confirm the whole book collection deletion.
        */
    }

}
