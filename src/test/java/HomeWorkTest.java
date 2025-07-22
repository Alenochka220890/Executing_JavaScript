import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeWorkTest {

    private DriverManager driverManager;
    private static final String URL = "https://otus.home.kartushin.su/training.html";
    private static final Logger log = LogManager.getLogger(HomeWorkTest.class);

    @BeforeEach
    public void startDriver() {

        driverManager = DriverManager.getInstance();
        //Тут можно выбирать браузер CHROME, FIREFOX, EDGE
        driverManager.createDriver(DriverManager.Browser.CHROME,false);
    }

    @Test
    @DisplayName("Тест в режиме headless")
    public void fillText() throws InterruptedException {
        driverManager.quitDriver();
        driverManager.createDriver(DriverManager.Browser.CHROME,true);
        driverManager.getDriver().get(URL);
        WebElement label = driverManager.getDriver().findElement(By.id("textInput"));
        label.sendKeys("ОТУС");
        assertEquals("ОТУС", label.getAttribute("value"));
        label.clear();
    }


    @Test
    @DisplayName("Тест в режиме киоска")
    public void openModal() throws InterruptedException {
        driverManager.getDriver().manage().window().fullscreen();
        driverManager.getDriver().get(URL);
        WebElement modal = driverManager.getDriver().findElement(By.id("openModalBtn"));
        modal.click();
        log.debug("Произошел клик по кнопке");
        //Ожидание на поиск элемента
        assertTrue(driverManager.getDriver().findElement(By.id("closeModal")).isDisplayed(), "Модальное окно не найдено");


    }

    @Test
    @DisplayName("Тест в режиме полного экрана")
    public void fullScreen() throws InterruptedException {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().get(URL);
        WebElement name = driverManager.getDriver().findElement(By.id("name"));
        WebElement mail = driverManager.getDriver().findElement(By.id("email"));
        name.sendKeys("фыв");
        mail.sendKeys("asdf@sdfg.rt");
        WebElement submit = driverManager.getDriver().findElement(By.cssSelector("form button[type=\"submit\"]"));
        submit.click();
        WebElement message = driverManager.getDriver().findElement(By.id("messageBox"));
        assertEquals("Форма отправлена с именем: фыв и email: asdf@sdfg.rt", message.getText());

    }

    @AfterEach
    public void endDriver() {
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
        }

    }

}
