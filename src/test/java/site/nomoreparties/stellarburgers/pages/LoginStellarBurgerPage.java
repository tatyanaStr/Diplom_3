package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStellarBurgerPage {
    private WebDriver driver;
    public static final String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    private By loginHeading = By.xpath("//h2[contains(text(), 'Вход')]");
    private By email = By.xpath(".//label[text()='Email']/following-sibling::input");
    private By password = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private By loginButton = By.xpath("//Button[contains(text(), 'Войти')]");
    private By regLink = By.xpath("//a[@href='/register' and contains(text(), 'Зарегистрироваться')]");

    public LoginStellarBurgerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginHeading));
    }

    public void fillEmailField(String email){
        driver.findElement(this.email).sendKeys(email);
    }

    public void fillPasswordField(String password){
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void clickRegLink(){
        driver.findElement(regLink).click();
    }

    public void login(String email, String password){
        waitForLoadPage();
        fillEmailField(email);
        fillPasswordField(password);
        clickLoginButton();
    }

    public String getHeading(){
        return driver.findElement(loginHeading).getText();
    }

    public void scrollToElement() {

        WebElement element = driver.findElement(regLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

    }
}
