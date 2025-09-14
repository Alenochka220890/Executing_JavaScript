package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishPage extends AbsBasePage {

    private final WebDriverWait wait;

    public WishPage(WebDriver driver) {
        super(driver, "/wishlists");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void buttonNewWish() {
        log.info("Клик по кнопке Создать новый список");
        $(By.xpath("//button[text()='Создать новый список']")).click();
        log.info("Форма Создать новый список желаний открыта");

    }

    public void createNewWish(String wishName, String wishDiscription) throws InterruptedException {
        log.info("Ввод названия желания: {}", wishName);
        $(By.cssSelector("input[class=\"form-control\"]")).sendKeys(wishName);
        log.info("Ввод описания: {}", wishDiscription);
        $(By.cssSelector("textarea[class=\"form-control\"]")).sendKeys(wishDiscription);
        $(By.cssSelector("button[type=\"submit\"]")).click();
    }

    public WebElement getWishElement(String wishname) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='card-title h5' and text()='" + wishname + "']")
        ));

    }

    public void getWishCart() {
        log.info("Клик по кнопке Просмотр");
        $(By.cssSelector("a[data-discover=true] button.btn-primary")).click();

    }

    public void addGift(String giftName, String giftDiscription) {
        log.info("Клик по кнопке Добавить подарок");
        WebElement addWishButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.mb-4.btn.btn-primary")));
        addWishButton.click();

        log.info("Ввод названия подарка: {}", giftName);
        $(By.xpath("//label[text()='Название']/../input")).sendKeys(giftName);
        log.info("Ввод названия описания: {}", giftDiscription);
        $(By.xpath("//label[text()='Описание']/../textarea")).sendKeys(giftDiscription);
        log.info("Ввод cсылки на магазин");
        $(By.xpath("//label[text()='Ссылка на магазин (необязательно)']/../input")).sendKeys("https://otus.ru/");
        log.info("Ввод цены");
        $(By.xpath("//label[text()='Цена (необязательно)']/../input")).sendKeys("500");
        log.info("Ввод ссылки на изображение");
        $(By.xpath("//label[text()='Ссылка на изображение (необязательно)']/../input")).sendKeys("https://postimg.cc/2bPQ1PR8");
        log.info("Клик по кнопке Добавить");
        $(By.cssSelector("button[type=\"submit\"].btn-primary")).click();

    }

    public WebElement getGiftElement(String giftname) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='card-title h5' and text()='" + giftname + "']")
        ));
    }

    public void reservGift(String giftname) {
        log.info("Клик по кнопке Зарезервировать для подарка: " + giftname);

        try {
            // Ждем полной загрузки страницы
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            // Ждем подгрузки кнопки Зарезервировать
            WebElement reservGift = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='" + giftname + "']/ancestor::div[contains(@class, 'card')]//button[contains(., 'Зарезервировать')]")));

            // Сlick через JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reservGift);
            log.info("Force click выполнен");


        } catch (Exception e) {
            log.error("Ошибка при клике: " + e.getMessage());
            throw new RuntimeException("Не удалось нажать на кнопку Зарезервировать", e);
        }
    }

    public WebElement getReservGift(String giftname) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='" + giftname + "']/ancestor::div[contains(@class, 'card')]//button[contains(., 'Снять резерв')]")));
    }


    public void getDeleteWishCart(String wishnamedel) {
        log.info("Удаление желания: {}", wishnamedel);

        try {
            // Ищем кнопку удаления карточки
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[contains(text(), '" + wishnamedel + "')]/ancestor::div[contains(@class, 'card')]//button[text()='Удалить']")));

            // Прокрутка к элементу с ожиданием завершения прокрутки
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", deleteButton);

            // Ждем завершения прокрутки и кликабельности элемента
            wait.until(ExpectedConditions.visibilityOf(deleteButton));
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

            // Клик через JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
            log.info("Клик по кнопке Удалить выполнен");

            // Ждем пока карточка удалится
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//*[contains(text(), '" + wishnamedel + "')]")));

            log.info("Карточка успешно удалена");

        } catch (Exception e) {
            log.error("Ошибка при удалении: {}", e.getMessage());
            throw new RuntimeException("Не удалось удалить желание: " + wishnamedel, e);
        }
    }
}




