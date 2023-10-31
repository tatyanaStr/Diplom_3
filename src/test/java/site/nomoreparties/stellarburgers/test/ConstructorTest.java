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
import site.nomoreparties.stellarburgers.TestProperties;
import site.nomoreparties.stellarburgers.pages.MainStellarBurgerPage;

import java.util.Objects;

public class ConstructorTest {
    BrowserSetUp browser = new BrowserSetUp();
    WebDriver driver;

    @Before
    public void setUpBrowser() {
        driver = browser.setUp();
    }

    @Test
    @DisplayName("Проверка работы перехода к разделу: Начинки")
    public void constructorFillingTest(){
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        driver.get(main.mainUrl);
        main.waitForLoadPage();
        main.clickFillingTab();
        main.waitForFillingVisible();
        Assert.assertTrue("Ошибка к переходу к Начинкам", main.isFillingVisible());
    }

    @Test
    @DisplayName("Проверка работы перехода к разделу: Соусы")
    public void constructorSauceTest(){
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        driver.get(main.mainUrl);
        main.waitForLoadPage();
        main.clickFillingTab();
        main.clickSauceTab();
        main.waitForSauseVisible();
        Assert.assertTrue("Ошибка к переходу к Соусам", main.isSauceVisible());
    }

    @Test
    @DisplayName("Проверка работы перехода к разделу: Булки")
    public void constructorBunTest(){
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        driver.get(main.mainUrl);
        main.waitForLoadPage();
        main.clickFillingTab();
        main.clickBunTab();
        main.waitForBunVisible();
        Assert.assertTrue("Ошибка к переходу к Булкам", main.isBunVisible());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
