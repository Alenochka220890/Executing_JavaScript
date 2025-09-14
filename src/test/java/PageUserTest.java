import assertions.Assertions;
import factory.WebDriverFactory;
import factory.settings.UserSettings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import pages.AuthPage;

import pages.UserPage;


public class PageUserTest {
    private WebDriver driver;
    String username = UserSettings.getUsername();
    String userpassword = UserSettings.getPassword();


    @BeforeEach
    public void init() throws InterruptedException {
        this.driver = new WebDriverFactory().create("--maximize");
        AuthPage authPage = new AuthPage(driver);
        authPage.open(); // открываем страницу авторизации
        authPage.login(username, userpassword);// заполняем форму авторизации


    }

    @AfterEach
    public void endDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Тест наличия пользователя на странице Пользователи")
    public void checkPageUser() throws InterruptedException {
        UserPage userPage = new UserPage(driver);
        userPage.open();//Открываем страницу пользователей
        userPage.getUsernameElement(username);//Получаем элемент с именем
        new Assertions(driver)
                .withUserPage()
                .userShouldBeVisible(username);//Проверяем, что пользователь присутствует на странице
    }


}

