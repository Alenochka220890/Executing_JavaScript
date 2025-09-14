package assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RegPage;
import pages.UserPage;
import pages.WishPage;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Assertions {


    private static final Logger log = LogManager.getLogger(Assertions.class);
    private final WebDriver driver;
    private RegPage regPage;
    private UserPage userPage;
    private WishPage wishPage;

    public Assertions(WebDriver driver) {
        this.driver = driver;
    }

    public Assertions withRegPage() {
        this.regPage = new RegPage(driver);
        return this;
    }

    public Assertions withUserPage() {
        this.userPage = new UserPage(driver);
        return this;
    }

    public Assertions wishUserPage() {
        this.wishPage = new WishPage(driver);
        return this;
    }

    public Assertions urlShouldBe(String expectedUrl) {
        String actualUrl = regPage.getActualUrl();
        log.info("Проверка URL после регистрации: ожидаемый = '{}', фактический = '{}'", expectedUrl, actualUrl);
        assertEquals(expectedUrl, actualUrl, "URL не соответствует ожидаемому");
        log.info("Проверка URL пройдена успешно");
        return this;
    }

    public Assertions userShouldBeVisible(String username) {
        log.info("Проверка, что пользователь '{}' отображается на странице", username);

        WebElement userElement = userPage.getUsernameElement(username);

        assertTrue(userElement.isDisplayed(),
                "Пользователь '" + username + "' должен быть виден на странице");

        log.info("Пользователь '{}' успешно отображается", username);
        return this;
    }

    public Assertions wishShouldBeVisible(String wishname) {
        log.info("Проверка, что желание '{}' отображается на странице", wishname);

        WebElement wishElement = wishPage.getWishElement(wishname);

        assertTrue(wishElement.isDisplayed(),
                "Желание '" + wishname + "' должно быть видно на странице");

        log.info("Желание '{}' успешно отображается", wishname);
        return this;
    }

    public Assertions giftShouldBeVisible(String giftname) {
        log.info("Проверка, что подарок '{}' отображается на странице", giftname);

        WebElement wishElement = wishPage.getGiftElement(giftname);

        assertTrue(wishElement.isDisplayed(),
                "Желание '" + giftname + "' должно быть видно на странице");

        log.info("Желание '{}' успешно отображается", giftname);
        return this;
    }

    public Assertions giftShouldBeReserved(String giftname){
        log.info("Проверка, что подарок '{}' зарезервирован", giftname);

        WebElement reservGift = wishPage.getReservGift(giftname);

        assertTrue(reservGift.isDisplayed(),
                "Подарок '" + giftname + "' зарезервирован");

        return this;
    }


public Assertions wishShouldBeDeleted(String wishnamedel) {
    log.info("Проверка, что желание '{}' удалено", wishnamedel);

    List<WebElement> wishes = driver.findElements(
            By.xpath("//*[contains(text(), '" + wishnamedel + "')]"));

    assertTrue(wishes.isEmpty(),
            "Желание '" + wishnamedel + "' все еще присутствует в DOM. Найдено элементов: " + wishes.size());

    return this;
}

}