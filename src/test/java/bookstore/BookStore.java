package bookstore;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookStore extends Browser {

    By titleText = By.linkText("Git Pocket Guide");
    By findBooks = By.xpath("//span[@class='mr-2']");
    By addToYourCollectionBttn = By.xpath("//button[text()='Add To Your Collection']");

    @Test
    public void addBookToCollection() throws InterruptedException, NoAlertPresentException {
        Profile profile = new Profile();
        profile.goToBookStore();
        waitForElement(titleText);
        driver.findElement(titleText).click();
        waitForElement(addToYourCollectionBttn);
        driver.findElement(addToYourCollectionBttn).click();
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        assertEquals("Book added to your collection.", alert.getText());
        alert.accept();
    }

    public void getBookByPosition(int position) {
        List<WebElement> books = driver.findElements(findBooks);
        books.get(position).click();
    }

}
