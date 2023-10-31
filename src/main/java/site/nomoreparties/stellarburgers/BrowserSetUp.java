package site.nomoreparties.stellarburgers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;
import java.util.Objects;

public class BrowserSetUp {


    public WebDriver setUp(){
        WebDriver driver = null;
        var properties = new TestProperties();
        var browserName = properties.defineBrowser();

        if (Objects.equals(browserName, "Yandex")){
            driver = yaDriver();
        }else if(Objects.equals(browserName, "Chrome")){
            driver = chromeDriver();
        }
        return driver;
    }

    public WebDriver chromeDriver(){
        String userDirectory = Paths.get("").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", userDirectory + "\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    public WebDriver yaDriver(){
        String userDirectory = Paths.get("").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", userDirectory + "\\resources\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }
}
