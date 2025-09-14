import assertions.Assertions;
import factory.settings.UserSettings;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.RegPage;

public class PageRegTest {
    private WebDriver driver;
    String username = UserSettings.getUsername();
    String useremail = UserSettings.getEmail();
    String userpassword = UserSettings.getPassword();
    String loginUrl = "https://wishlist.otus.kartushin.su/login";

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
    @DisplayName("Тест формы регистрации")
    public void checkPageRegister() throws InterruptedException {
        RegPage regPage = new RegPage(driver);
        regPage.open(); // открываем главную страницу
        regPage.writeName(username);// заполняем имя
        regPage.writeEmail(useremail);// заполняем адрес почты
        regPage.writePassword(userpassword);// заполняем пароль
        regPage.submitForm(); // отправляем форму
        new Assertions(driver)
                .withRegPage()
                .urlShouldBe(loginUrl);

    }

}