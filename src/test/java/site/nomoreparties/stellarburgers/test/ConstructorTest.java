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
import site.nomoreparties.stellarburgers.pages.MainStellarBurgerPage;

@RunWith(Parameterized.class)
public class ConstructorTest {
    BrowserSetUp browser = new BrowserSetUp();
    WebDriver driver;
    private final String BROWSERS;

    public ConstructorTest(String browser) {
        this.BROWSERS = browser;
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
    @DisplayName("Проверка работы перехода к разделу: Начинки")
    public void constructorFillingTest(){
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        main.waitForLoadPage();
        main.clickFillingTab();
        try{
            Thread.sleep(1000);
        }
        catch (Exception e){
        }
        Assert.assertEquals("Ошибка к переходу к Начинкам", main.isFillingVisible(), true);
    }

    @Test
    @DisplayName("Проверка работы перехода к разделу: Соусы")
    public void constructorSauceTest(){
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        main.waitForLoadPage();
        main.clickFillingTab();
        main.clickSauceTab();
        try{
            Thread.sleep(1000);
        }
        catch (Exception e){
        }
        Assert.assertEquals("Ошибка к переходу к Соусам", main.isSauceVisible(), true);
    }

    @Test
    @DisplayName("Проверка работы перехода к разделу: Булки")
    public void constructorBunTest(){
        MainStellarBurgerPage main = new MainStellarBurgerPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        main.waitForLoadPage();
        main.clickFillingTab();
        main.clickBunTab();
        try{
            Thread.sleep(1000);
        }
        catch (Exception e){
        }
        Assert.assertEquals("Ошибка к переходу к Булкам", main.isBunVisible(), true);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
