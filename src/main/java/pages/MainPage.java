package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;


public class MainPage extends AbsBasePage {

    public MainPage(WebDriver driver) {
        super(driver, "/form.html");
        log.info("Открыта главная страница");
    }

    public MainPage writeName(String name) {
        log.info("Ввод имени: {}", name);
        $(By.id("username")).sendKeys(name);
        return this;
    }

    public MainPage writeEmail(String email) {
        log.info("Ввод email: {}", email);
        $(By.id("email")).sendKeys(email);
        return this;
    }

    public MainPage writePassword(String userpassword) {
        log.info("Ввод пароля: {}", userpassword);
        $(By.id("password")).sendKeys(userpassword);
        return this;
    }

    public MainPage writeConfirmPassword(String userpassword) {
        log.info("Подтверждение пароля: {}", userpassword);
        $(By.id("confirm_password")).sendKeys(userpassword);
        return this;
    }

    public MainPage writeBirthday(String userbirthday) {
        log.info("Ввод даты рождения: {}", userbirthday);
        $(By.id("birthdate")).sendKeys(userbirthday);
        return this;
    }


    public String writeLvllanguage(String randomLevel) {
        log.info("Выбор случайного уровня языка: {}", randomLevel);
        $(By.cssSelector("option[value=\"" + randomLevel + "\"]")).click();
        return randomLevel;
    }


    public void submitForm() {
        log.info("Клик по кнопке Зарегистрироваться");
        $(By.cssSelector("[value=\"Зарегистрироваться\"]")).click();
        log.info("Форма отправлена");
    }


    public String getMessageTextName() {
        String messageTextName = $(By.id("output")).getText();
        log.info("Получили текст: {}", messageTextName);
        return messageTextName;
    }

    public boolean isAlertDisplayed() {
        try {
            driver.switchTo().alert(); // пытаемся переключиться на алерт
            log.info("Алерт о несовпадении пароля отображается");
            return true;
        } catch (NoAlertPresentException e) {
            log.info("Алерт о несовпадении пароля не отображается");
            return false;
        }
    }
}
