package site.nomoreparties.stellarburgers.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.BrowserSetUp;
import site.nomoreparties.stellarburgers.RandomValue;
import site.nomoreparties.stellarburgers.api.LoginAndDeleteUserApi;
import site.nomoreparties.stellarburgers.api.json.UserRequest;
import site.nomoreparties.stellarburgers.pages.LoginStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.MainStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.ProfileStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.RegistrationStellarBurgerPage;

@RunWith(Parameterized.class)
public class RegistrationTest {
    BrowserSetUp browser = new BrowserSetUp();
    RandomValue value = new RandomValue();
    LoginAndDeleteUserApi delApi = new LoginAndDeleteUserApi();
    UserRequest user = new UserRequest(value.email(), value.password(6), value.name(6));
    WebDriver driver;
    private final String BROWSERS;

    public RegistrationTest(String browsers) {
        this.BROWSERS = browsers;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][]{
                {"Yandex"},
                {"Chrome"}
        };
    }

    @Before
    public void setUpBrowser() {
        if (BROWSERS == "Yandex") {
            driver = browser.yaDriver();
        } else if (BROWSERS == "Chrome") {
            driver = browser.chromeDriver();
        }
    }

    @Test
    @DisplayName("Проверка регистрации пользователя - успешная")
    public void successRegistrationTest() {
        RegistrationStellarBurgerPage reg = new RegistrationStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
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
        driver.get("https://stellarburgers.nomoreparties.site/");
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
