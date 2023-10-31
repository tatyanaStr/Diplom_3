package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResetButtonStellarBurger {
    private WebDriver driver;
    public static final String resetPasswordUrl = "https://stellarburgers.nomoreparties.site/forgot-password";
    private By regHeading = By.xpath("//h2[contains(text(), 'Восстановление пароля')]");
    private By loginLink = By.xpath("//a[@href=\"/login\" and contains(text(), 'Войти')]");

    public ResetButtonStellarBurger(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(regHeading));
    }

    public void clickLoginLink(){
        driver.findElement(loginLink).click();
    }
}
