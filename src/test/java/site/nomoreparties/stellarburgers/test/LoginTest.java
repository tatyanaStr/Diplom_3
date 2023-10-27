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
import site.nomoreparties.stellarburgers.api.CreateUserApi;
import site.nomoreparties.stellarburgers.api.LoginAndDeleteUserApi;
import site.nomoreparties.stellarburgers.api.json.UserRequest;
import site.nomoreparties.stellarburgers.pages.*;

@RunWith(Parameterized.class)
public class LoginTest {
    BrowserSetUp browser = new BrowserSetUp();
    CreateUserApi userApi = new CreateUserApi();
    LoginAndDeleteUserApi delApi = new LoginAndDeleteUserApi();
    UserRequest userData = userApi.createUser();
    WebDriver driver;
    private final String BROWSERS;

    public LoginTest(String browsers) {
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
        if (BROWSERS == "Yandex"){
            driver = browser.yaDriver();
        }else if(BROWSERS == "Chrome"){
            driver= browser.chromeDriver();
        }
    }

    @Test
    @DisplayName("Проверка входа в аккаунт по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPageButton() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        main.waitForLoadPage();
        main.clickLoginButton();
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        Assert.assertEquals("Ошибка входа в аккаунт", profile.getUserEmail(),  userData.getEmail());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку «Личный кабинет»")
    public void loginFromProfile() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        main.waitForLoadPage();
        main.openProfilePage();
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        Assert.assertEquals("Ошибка входа в аккаунт", profile.getUserEmail(),  userData.getEmail());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку в форме регистрации")
    public void loginFromRegPage() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        RegistrationStellarBurgerPage reg = new RegistrationStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site/register");
        reg.waitForLoadPage();
        reg.scrollToElement();
        reg.clickSignInLink();
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        Assert.assertEquals("Ошибка входа в аккаунт", profile.getUserEmail(),  userData.getEmail());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку в форме восстановления пароля")
    public void loginFromResetPage() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ResetButtonStellarBurger reset = new ResetButtonStellarBurger(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        reset.waitForLoadPage();
        reset.clickLoginLink();
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        Assert.assertEquals("Ошибка входа в аккаунт", profile.getUserEmail(),  userData.getEmail());
    }

    @After
    public void teardown() {
        driver.quit();
        delApi.deleteUser(userData);
    }
}
