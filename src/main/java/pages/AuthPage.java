package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage extends AbsBasePage {
    private WebDriverWait wait;


    public AuthPage(WebDriver driver) {
        super(driver, "/login");
        log.info("Открыта страница авторизации");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    }


    public AuthPage login(String name, String userpassword) {
        String loginUrl = driver.getCurrentUrl();
        log.info("Ввод имени: {}", name);
        $(By.cssSelector("input[type=\"text\"]")).sendKeys(name);
        log.info("Ввод пароля: {}", userpassword);
        $(By.cssSelector("input[type=\"password\"]")).sendKeys(userpassword);
        log.info("Клик по кнопке Войти");
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=\"submit\"]")));
        loginButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(loginUrl))); //Ожидаем смену URL

        return this;
    }


    }

