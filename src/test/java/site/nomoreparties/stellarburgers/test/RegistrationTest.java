package site.nomoreparties.stellarburgers.test;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.BrowserSetUp;
import org.api.LoginAndDeleteUserApi;
import org.api.json.UserRequest;
import site.nomoreparties.stellarburgers.TestProperties;
import site.nomoreparties.stellarburgers.pages.LoginStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.MainStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.ProfileStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.RegistrationStellarBurgerPage;

import java.util.Objects;

public class RegistrationTest {
    BrowserSetUp browser = new BrowserSetUp();
    LoginAndDeleteUserApi delApi = new LoginAndDeleteUserApi();
    Faker value = new Faker();
    UserRequest user = new UserRequest(value.internet().emailAddress(), value.internet().password(6, 10), value.name().firstName());
    WebDriver driver;
    @Before
    public void setUpBrowser() {

        driver = browser.setUp();
    }

    @Test
    @DisplayName("Проверка регистрации пользователя - успешная")
    public void successRegistrationTest() {
        RegistrationStellarBurgerPage reg = new RegistrationStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        driver.get(MainStellarBurgerPage.mainUrl);
        main.waitForLoadPage();
        main.openProfilePage();
        login.waitForLoadPage();
        login.scrollToElement();
        login.clickRegLink();
        reg.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        login.waitForLoadPage();
        login.login(user.getEmail(), user.getPassword());
        main.openProfilePage();
        profile.waitForLoadPage();
        Assert.assertEquals("Ошибка регистрации пользователя", profile.getUserEmail(), user.getEmail());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя - пароль менее 6 символов")
    public void incorrectPasswordLengthRegistrationTest() {
        user.setPassword("1234");
        RegistrationStellarBurgerPage reg = new RegistrationStellarBurgerPage(driver);
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        driver.get(MainStellarBurgerPage.mainUrl);
        main.waitForLoadPage();
        main.openProfilePage();
        login.waitForLoadPage();
        login.scrollToElement();
        login.clickRegLink();
        reg.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        reg.waitForErrorMessage();
        Assert.assertEquals("Ошибка регистрации пользователя", reg.getErrorMessage(), "Некорректный пароль");
    }

    @After
    public void teardown() {
        driver.quit();
        try {
            UserRequest userData = new UserRequest(user.getEmail(), user.getPassword(), null);
            delApi.deleteUser(userData);
        } catch (Exception e) {

        }
    }
}
