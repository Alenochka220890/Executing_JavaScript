package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeDriverSettings;
import factory.settings.FirefoxDriverSettings;
import factory.settings.ISettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverFactory {
    private String browser = System.getProperty("browser", "firefox").toLowerCase().trim();

    //Создание экземпляра браузера с параметрами
    public WebDriver create(String... args) {
        switch (browser) {
            case "chrome": {
                ISettings settings = new ChromeDriverSettings();
                return new ChromeDriver((ChromeOptions) settings.settings(null, args));
            }
            case "firefox": {
                ISettings settings = new FirefoxDriverSettings();
                return new FirefoxDriver((FirefoxOptions) settings.settings(null, args));
            }

        }
        throw new BrowserNotSupportedException(browser);
    }
}
