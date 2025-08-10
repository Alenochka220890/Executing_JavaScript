package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage {

    public MainPage(WebDriver driver) {
        super(driver, "/training.html");
        log.info("Открыта главная страница");
    }

    public MainPage writeName(String name) {
        log.info("Ввод имени: {}", name);
        $(By.id("name")).sendKeys(name);
        return this;
    }

    public MainPage writeEmail(String email) {
        log.info("Ввод email: {}", email);
        $(By.id("email")).sendKeys(email);
        return this;
    }

    public void submitForm() {
        log.info("Клик по кнопке отправки");
        $(By.cssSelector("form button[type=\"submit\"]")).click();
        log.info("Форма отправлена");
    }


    public String getMessageText() {
        String messageText = $(By.id("messageBox")).getText();
        log.info("Получили текст: {}", messageText);
        return messageText;
    }


    public void writeTextInput(String text) {
        log.info("Ввод текста '{}' в поле textInput", text);
        $(By.id("textInput")).sendKeys(text);
        log.info("Текст '{}' успешно введен", text);
    }

    public String getTextInputValue() {
        String value = $(By.id("textInput")).getAttribute("value");
        log.info("Получено значение поля textInput: '{}'", value);
        return value;
    }

    public void openModal() {
        log.info("Клик по кнопке открытия модального окна");
        $(By.id("openModalBtn")).click();
        log.info("Модальное окно открыто");
    }

    public boolean isModalDisplayed() {
        boolean isDisplayed = $(By.id("closeModal")).isDisplayed();
        log.info("Модальное окно {}отображается", isDisplayed ? "" : "не ");
        return isDisplayed;
    }
}
