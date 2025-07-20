import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeWork {

    WebDriver wd;
    private static final Logger log = LogManager.getLogger(HomeWork.class);

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void startDriver() {

        ChromeOptions options = new ChromeOptions();
        wd = new ChromeDriver(options);
        log.info("Запуск браузера");
        //Ожидание на поиск элемента
        wd.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        //Ожидание на загрузку страницы
        wd.manage().timeouts().pageLoadTimeout(Duration.ofMillis(5000));

    }

    @Test
    @DisplayName("Тест в режиме headless")
    public void fillText() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-headless");
        wd = new ChromeDriver(options);
        wd.get("https://otus.home.kartushin.su/training.html");
        WebElement label = wd.findElement(By.id("textInput"));
        label.sendKeys("ОТУС");
        assertEquals("ОТУС", label.getAttribute("value"));
        label.clear();
    }

    ;

    @Test
    @DisplayName("Тест в режиме киоска")
    public void openModal() throws InterruptedException {
        wd.manage().window().fullscreen();
        wd.get("https://otus.home.kartushin.su/training.html");
        WebElement modal = wd.findElement(By.id("openModalBtn"));
        modal.click();
        log.debug("Произошел клик по кнопке");
        //Ожидание на поиск элемента
        assertTrue(wd.findElement(By.id("closeModal")).isDisplayed(), "Модальное окно не найдено");


    }

    @Test
    @DisplayName("Тест в режиме полного экрана")
    public void fullScreen() throws InterruptedException {
        wd.manage().window().maximize();
        wd.get("https://otus.home.kartushin.su/training.html");
        WebElement name = wd.findElement(By.id("name"));
        WebElement mail = wd.findElement(By.id("email"));
        name.sendKeys("фыв");
        mail.sendKeys("asdf@sdfg.rt");
        WebElement submit = wd.findElement(By.xpath("//form/button[@type='submit']"));
        submit.click();
        WebElement message = wd.findElement(By.id("messageBox"));
        assertEquals("Форма отправлена с именем: фыв и email: asdf@sdfg.rt", message.getText());

    }

    @AfterEach
    public void endDriver() {
        if (wd != null) {
            wd.quit();
        }

    }

}
