package assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Assertions {
    private static final Logger log = LogManager.getLogger(Assertions.class);
    private final MainPage mainPage;

    public Assertions(MainPage mainPage) {

        this.mainPage = mainPage;
    }

    public Assertions messageShouldBe(String expectedMessage) {
        String actualMessage = mainPage.getMessageTextName();
        log.info("Проверка сообщения: ожидаемое = '{}', фактическое = '{}'", expectedMessage, actualMessage);
        assertEquals(expectedMessage, actualMessage, "Сообщение не соответствует ожидаемому");
        log.info("Проверка сообщения пройдена успешно");
        return this;
    }

}