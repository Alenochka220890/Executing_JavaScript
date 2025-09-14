import assertions.Assertions;
import factory.WebDriverFactory;
import factory.settings.UserSettings;
import factory.settings.WishSettings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.AuthPage;
import pages.WishPage;


public class PageWishTest {

    private WebDriver driver;
    String username = UserSettings.getUsername();
    String userpassword = UserSettings.getPassword();
    String wishname = WishSettings.getWishname();
    String wishDiscription = WishSettings.getWishdiscriptional();
    String wishnamedel = WishSettings.getWishnameDel();
    String wishDiscriptiondel = WishSettings.getWishdiscriptionalDel();
    String giftname = WishSettings.getGiftname();
    String giftDiscription = WishSettings.getGiftdiscriptional();

    @BeforeEach
    public void init() throws InterruptedException {
        this.driver = new WebDriverFactory().create("--incognito");
        AuthPage authPage = new AuthPage(driver);
        authPage.open(); // открываем страницу авторизации
        authPage.login(username, userpassword);// заполняем форму авторизации


    }

    @AfterEach
    public void endDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Тест создания желания")
    public void createWish() throws InterruptedException {
        WishPage wishPage = new WishPage(driver);
        wishPage.buttonNewWish();
        wishPage.createNewWish(wishname, wishDiscription);
        wishPage.getWishElement(wishname);
        new Assertions(driver)
                .wishUserPage()
                .wishShouldBeVisible(wishname);//Проверяем, что созданное желание присутствует на странице
    }

    @Test
    @DisplayName("Тест создания и резервации подарка")
    public void createPresent() throws InterruptedException {
        WishPage wishPage = new WishPage(driver);
        wishPage.getWishCart();
        wishPage.addGift(giftname, giftDiscription);
        new Assertions(driver)
                .wishUserPage()
                .giftShouldBeVisible(giftname);//Проверяем, что подарок  присутствует на странице
        wishPage.reservGift(giftname);
        new Assertions(driver)
                .wishUserPage()
                .giftShouldBeReserved(giftname);//Проверяем, что подарок зарезервирован


    }


    @Test
    @DisplayName("Тест удаления желания")
    public void deleteWish() throws InterruptedException {
        WishPage wishPage = new WishPage(driver);
        wishPage.buttonNewWish();
        wishPage.createNewWish(wishnamedel, wishDiscriptiondel);
        wishPage.getWishElement(wishnamedel);
        new Assertions(driver)
                .wishUserPage()
                .wishShouldBeVisible(wishnamedel);//Проверяем, что желание  присутствует на странице
        wishPage.getDeleteWishCart(wishnamedel);
        new Assertions(driver)
                .wishUserPage()
                .wishShouldBeDeleted(wishnamedel);//Проверяем, что желание удалено


    }

}
