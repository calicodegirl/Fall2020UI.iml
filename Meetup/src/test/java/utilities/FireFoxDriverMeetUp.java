package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FireFoxDriverMeetUp {
    public static WebDriver loadFireFoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if(PropertiesReader.getProperty("headless").equalsIgnoreCase("true")) {
            options.setHeadless(true);
        }
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        return driver;
    }
}
