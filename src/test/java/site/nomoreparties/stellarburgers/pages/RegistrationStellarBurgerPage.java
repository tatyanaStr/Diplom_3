package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationStellarBurgerPage {
    private WebDriver driver;
    private By regHeading = By.xpath("//h2[contains(text(), 'Регистрация')]");
    private By name = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private By email = By.xpath(".//label[text()='Email']/following-sibling::input");
    private By password = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private By regButton = By.xpath("//Button[contains(text(), 'Зарегистрироваться')]");
    private By signInLink = By.xpath("//a[@href=\"/login\" and contains(text(), 'Войти')]");
    private By errorMessage = By.xpath("//p[contains(text(), 'Некорректный пароль')]");

    public RegistrationStellarBurgerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(regHeading));
    }

    public void fillNameField(String name){
        driver.findElement(this.name).sendKeys(name);
    }

    public void fillEmailField(String email){
        driver.findElement(this.email).sendKeys(email);
    }

    public void fillPasswordField(String password){
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickRegistrationButton(){
        driver.findElement(regButton).click();
    }

    public void waitForErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }

    public void clickSignInLink(){
        driver.findElement(signInLink).click();
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

    public void fillRegistrationForm(String name, String email, String password){
        waitForLoadPage();
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        clickRegistrationButton();
    }

    public void scrollToElement() {

        WebElement element = driver.findElement(signInLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

    }
}
