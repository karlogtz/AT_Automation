package bookstore;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookStore extends Browser {

    String bookTitleToSearch = "Git Pocket Guide";
    By booksTitles = By.xpath("//span[@class='mr-2']");
    By addToYourCollectionBttn = By.xpath("//button[text()='Add To Your Collection']");
    By adID = By.id("close-fixedban");

    @Test
    public void addBookToCollection() throws InterruptedException, NoAlertPresentException {
        Profile profile = new Profile();
        profile.goToBookStore();
        if(driver.findElement(adID).isDisplayed()) {
            handleAd(adID);
        }
        Thread.sleep(1000);
        getBook(bookTitleToSearch);
        waitForElement(addToYourCollectionBttn);
        //scrollToElement(addToYourCollectionBttn);
        driver.findElement(addToYourCollectionBttn).click();
        waitForAlert();
        assertAlert("Book added to your collection.");
        assertTrue(profile.isBookInCollection(bookTitleToSearch));
    }

    public void getBook(String bookTitle) {
        boolean bookFound = false;
        List<WebElement> books = driver.findElements(booksTitles);
        if(books.size() > 0) {
            for(WebElement book : books) {
                if(book.getText().equalsIgnoreCase(bookTitle)) {
                    book.click();
                    bookFound = true;
                    break;
                }
            }
        }
        System.out.println("Book was found? " + bookFound);
    }

}
