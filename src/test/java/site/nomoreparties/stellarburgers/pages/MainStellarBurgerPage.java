package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class MainStellarBurgerPage {
    private WebDriver driver;
    private By heading = By.xpath("//h1[text()='Соберите бургер']");
    private By goToProfile = By.xpath("//a[@href='/account']");
    private By loginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private By bun = By.xpath("//span[text()='Булки']/parent::div");
    private By bunElement = By.xpath("//h2[text()='Булки']/following-sibling::ul/a[1]");
    private By sauceElement = By.xpath("//h2[text()='Соусы']/following-sibling::ul/a[1]");
    private By fillingElement = By.xpath("//h2[text()='Начинки']/following-sibling::ul/a[1]");
    private By sauce = By.xpath("//span[text()='Соусы']/parent::div");
    private By filling = By.xpath("//span[text()='Начинки']/parent::div");
    private By closePopup = By.xpath(".//h2[text()='Детали ингредиента']//parent::*/following-sibling::button");

    public MainStellarBurgerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(heading));
    }

    public void clickBunTab() {
        driver.findElement(bun).click();
    }

    public void clickSauceTab() {
        driver.findElement(sauce).click();
    }

    public void clickFillingTab() {
        driver.findElement(filling).click();
    }

    public void openProfilePage() {
        driver.findElement(goToProfile).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public Boolean isBunVisible() {
        return isVisibleInViewport(driver.findElement(bunElement));
    }

    public Boolean isSauceVisible() {
        return isVisibleInViewport(driver.findElement(sauceElement));
    }

    public Boolean isFillingVisible() {
        return isVisibleInViewport(driver.findElement(fillingElement));
    }

    private Boolean isVisibleInViewport(WebElement element) {

        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }
}
