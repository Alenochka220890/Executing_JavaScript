import assertions.Assertions;
import factory.settings.LanguageSettings;
import factory.settings.UserSettings;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;


import static factory.settings.DateSettings.generateRandomDate;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PageRegTest {
    private WebDriver driver;
    String username = UserSettings.getUsername();
    String useremail = UserSettings.getEmail();
    String userpassword = UserSettings.getPassword();
    String[] testData = generateRandomDate();
    String userInput = testData[0];     // для поля ввода
    String systemOutput = testData[1];
    String randomLevel = LanguageSettings.generateLevellanguage();

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
        MainPage mainPage = new MainPage(driver);
        mainPage.open(); // открываем главную страницу
        mainPage.writeName(username); // заполняем имя
        mainPage.writeEmail(useremail);// заполняем адрес почты
        mainPage.writePassword(userpassword);// заполняем пароль
        mainPage.writeConfirmPassword(userpassword);// подтверждаем пароль
        mainPage.writeBirthday(userInput);// вводим дату рождения
        mainPage.writeLvllanguage(randomLevel);// выбираем уровень языка
        mainPage.submitForm(); // отправляем форму
        assertFalse(mainPage.isAlertDisplayed(), "Алерт о несовпадении пароля не должен отображаться");
        new Assertions(mainPage).messageShouldBe("Имя пользователя: " + username + "\n" +
                "Электронная почта: " + useremail + "\n" +
                "Дата рождения: " + systemOutput + "\n" +
                "Уровень языка: " + randomLevel);

    }

}