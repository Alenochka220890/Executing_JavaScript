import assertions.Assertions;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class PageHeadlessTest {
    private WebDriver driver;

    @BeforeEach
    public void init() {
        this.driver = new WebDriverFactory().create("--headless");
    }

    @AfterEach
    public void endDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Тест в режиме headless")
    public void testHeadlessInput() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.writeTextInput("ОТУС"); // заполняем поле "textInput"
        new Assertions(mainPage).textInputShouldBe("ОТУС"); // проверяем, что текст введен корректно
    }
}
