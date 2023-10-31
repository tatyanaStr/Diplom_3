package site.nomoreparties.stellarburgers.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.BrowserSetUp;
import site.nomoreparties.stellarburgers.CreateUserApi;
import site.nomoreparties.stellarburgers.LoginAndDeleteUserApi;
import site.nomoreparties.stellarburgers.json.UserRequest;
import site.nomoreparties.stellarburgers.pages.LoginStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.MainStellarBurgerPage;
import site.nomoreparties.stellarburgers.pages.ProfileStellarBurgerPage;

public class ProfileTest {
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
    @DisplayName("Проверка перехода в личный кабинет")
    public void profileOpenTest() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get(LoginStellarBurgerPage.loginUrl);
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        Assert.assertEquals("Ошибка перехода в Личный Кабинет", profile.getUserEmail(), userData.getEmail());
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    public void profileLogoutTest() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get(LoginStellarBurgerPage.loginUrl);
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        profile.clicklogoutButton();
        login.waitForLoadPage();
        Assert.assertEquals("Ошибка выхода из аккаунта", login.getHeading(), "Вход");
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по кнопке Конструктор")
    public void profileConstructorTest() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get(LoginStellarBurgerPage.loginUrl);
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        profile.clickConstructorLink();
        main.waitForLoadPage();
        Assert.assertEquals("Ошибка перехода в конструктор", main.getHeading(), "Соберите бургер");
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по Лого")
    public void profileConstructorLogoTest() {
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        LoginStellarBurgerPage login = new LoginStellarBurgerPage(driver);
        ProfileStellarBurgerPage profile = new ProfileStellarBurgerPage(driver);
        driver.get(LoginStellarBurgerPage.loginUrl);
        login.waitForLoadPage();
        login.login(userData.getEmail(), userData.getPassword());
        main.waitForLoadPage();
        main.openProfilePage();
        profile.waitForLoadPage();
        profile.clickLogoLink();
        main.waitForLoadPage();
        Assert.assertEquals("Ошибка перехода в конструктор", main.getHeading(), "Соберите бургер");
    }

    @After
    public void teardown() {
        UserRequest user = new UserRequest(userData.getEmail(), userData.getPassword(), null);
        driver.quit();
        delApi.deleteUser(user);
    }
}
