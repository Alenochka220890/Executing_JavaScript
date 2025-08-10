package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverSettings implements ISettings {

    @Override
    public AbstractDriverOptions settings(DesiredCapabilities desiredCapabilities, String... userArgs) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(userArgs); // Передаем все аргументы
        return chromeOptions;
    }

    @Override
    public AbstractDriverOptions settings() {
        return new ChromeOptions();
    }
}
