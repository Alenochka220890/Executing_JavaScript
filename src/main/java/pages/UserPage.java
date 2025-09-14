package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserPage extends AbsBasePage {

    private final WebDriverWait wait;

    public UserPage(WebDriver driver) {
        super(driver, "/users");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    public void open() {
        super.open(); // открываем страницу
        waitForPageToLoad(); // ждем загрузки
        log.info("Открыта страница Пользователи");
    }

    private void waitForPageToLoad() {
        wait.until(ExpectedConditions.urlContains("/users"));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'card')]")
        ));
    }

    public WebElement getUsernameElement(String username) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='card-title h5' and text()='" + username + "']")
        ));
    }

}

