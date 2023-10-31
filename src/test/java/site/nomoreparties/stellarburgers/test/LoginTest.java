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
import org.api.CreateUserApi;
import org.api.LoginAndDeleteUserApi;
import org.api.json.UserRequest;
import site.nomoreparties.stellarburgers.TestProperties;
import site.nomoreparties.stellarburgers.pages.*;

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class LoginTest {
    BrowserSetUp browser = new BrowserSetUp();
    CreateUserApi userApi = new CreateUserApi();
    LoginAndDeleteUserApi delApi = new LoginAndDeleteUserApi();
    UserRequest userData = userApi.createUser();
    WebDriver driver;

    @Before
    public void setUpBrowser() {

        driver = browser.setUp();
    }

    @Test
    @DisplayName("Проверка входа в аккаунт по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPageButton() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get(MainStellarBurgerPage.mainUrl);
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
        driver.get(MainStellarBurgerPage.mainUrl);
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
        driver.get(RegistrationStellarBurgerPage.registerUrl);
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
        driver.get(ResetButtonStellarBurger.resetPasswordUrl);
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
