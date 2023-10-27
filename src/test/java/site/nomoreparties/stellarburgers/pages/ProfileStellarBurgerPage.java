package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileStellarBurgerPage {
    private WebDriver driver;
    private By constructorButton = By.xpath("//p[contains(text(), 'Конструктор')]/parent::a");
    private By logoutButton = By.xpath("//Button[contains(text(), 'Выход')]");
    private By logo = By.xpath(".//descendant::div/a[@href='/']");
    private By email = By.xpath(".//label[text()='Логин']/following-sibling::input");

    public ProfileStellarBurgerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(email));
    }
    public String getUserEmail(){
        return driver.findElement(email).getAttribute("value");
    }

    public void clickConstructorLink(){
        driver.findElement(constructorButton).click();
    }

    public void clickLogoLink(){
        driver.findElement(logo).click();
    }

    public void clicklogoutButton(){
        driver.findElement(logoutButton).click();
    }

}
