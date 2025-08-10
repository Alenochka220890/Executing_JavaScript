package assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assertions {
    private static final Logger log = LogManager.getLogger(Assertions.class);
    private final MainPage mainPage;

    public Assertions(MainPage mainPage) {

        this.mainPage = mainPage;
    }

    public Assertions messageShouldBe(String expectedMessage) {
        String actualMessage = mainPage.getMessageText();
        log.info("Проверка сообщения: ожидаемое = '{}', фактическое = '{}'", expectedMessage, actualMessage);
        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        log.info("Проверка сообщения пройдена успешно");
        return this;
    }

    public Assertions textInputShouldBe(String expectedText) {
        String actualText = mainPage.getTextInputValue();
        log.info("Проверка текста в поле: ожидаемое = '{}', фактическое = '{}'", expectedText, actualText);
        assertEquals(expectedText, actualText, "Текст в поле не соответствует ожидаемому");
        log.info("Проверка текста в поле пройдена успешно");
        return this;
    }

    public Assertions modalShouldBeVisible() {
        assertTrue(mainPage.isModalDisplayed(), "Модальное окно не отображается");
        log.info("Проверка видимости модального окна пройдена успешно");
        return this;
    }

}