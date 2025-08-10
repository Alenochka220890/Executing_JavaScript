import assertions.Assertions;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;


public class PageFullScreenTest {
    private WebDriver driver;


    @BeforeEach
    public void init() {

        this.driver = new WebDriverFactory().create("--maximize");
    }

    @AfterEach
    public void endDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Тест в режиме полного экрана")
    public void checkPageFullScreen() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.open(); // открываем главную страницу
        mainPage.writeName("фыв"); // заполняем имя
        mainPage.writeEmail("asdf@sdfg.rt"); // заполняем адрес почты
        mainPage.submitForm(); // отправляем форму
        new Assertions(mainPage).messageShouldBe("Форма отправлена с именем: фыв и email: asdf@sdfg.rt");

    }

}