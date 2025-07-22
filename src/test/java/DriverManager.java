import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private WebDriver wd;
    private static DriverManager instance;


    //Используем паттерн Singleton для того, чтобы у нас всегда был один экземпляр DriverManager
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    //Возвращает созданный драйвер
    public WebDriver getDriver() {
        if (wd == null) {
            throw new IllegalStateException("Создайте драйвер");
        }
        return wd;

    }

    //Настраиваем драйвер
    public void createDriver(Browser browser, boolean headless) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless");
                wd = new ChromeDriver(chromeOptions);
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                wd = new FirefoxDriver(firefoxOptions);
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                wd = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Выбран неверный тип браузера: " + browser);
        }

    }

    public void quitDriver() {
        if (wd != null) {
            wd.quit();
            wd = null;
        }
    }

    public enum Browser {
        CHROME, FIREFOX, EDGE
    }
}
