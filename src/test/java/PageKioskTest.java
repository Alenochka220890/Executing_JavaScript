import assertions.Assertions;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class PageKioskTest {
    private WebDriver driver;

    @BeforeEach
    public void init() {
        this.driver = new WebDriverFactory().create("--kiosk");
    }

    @AfterEach
    public void endDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Тест в режиме киоска")
    public void testModalWindow() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.openModal(); // открываем модальное окно
        new Assertions(mainPage).modalShouldBeVisible(); //Проверяем, что оно отобразилось
    }
}
