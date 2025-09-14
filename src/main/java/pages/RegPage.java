package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegPage extends AbsBasePage {
    private final WebDriverWait wait;
    String loginUrl = "https://wishlist.otus.kartushin.su/login";

    public RegPage(WebDriver driver) {
        super(driver, "/register");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Открыта страница регистрации");
    }

    public RegPage writeName(String name) {
        log.info("Ввод имени: {}", name);
        $(By.cssSelector("input[type=\"text\"]")).sendKeys(name);
        return this;
    }

    public RegPage writeEmail(String email) {
        log.info("Ввод email: {}", email);
        $(By.cssSelector("input[type=\"email\"]")).sendKeys(email);
        return this;
    }

    public RegPage writePassword(String userpassword) {
        log.info("Ввод пароля: {}", userpassword);
        $(By.cssSelector("input[type=\"password\"]")).sendKeys(userpassword);
        return this;
    }


    public void submitForm() {
        log.info("Клик по кнопке Зарегистрироваться");
        $(By.cssSelector("button[type=\"submit\"]")).click();
        log.info("Форма отправлена");
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(getActualUrl())));
    }


    public String getActualUrl() {
        String currentUrl = driver.getCurrentUrl();
        log.info("URL: {}", currentUrl);
        return currentUrl;
    }

}
