package site.nomoreparties.stellarburgers;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperties {
    private Properties props = new Properties();
    public TestProperties() {
        try {
            props.load(new FileInputStream("src/main/resources/test.properties"));
        } catch (Exception e) {

        }
    }

    public String defineBrowser() {
        return props.getProperty("browser");
    }
}
