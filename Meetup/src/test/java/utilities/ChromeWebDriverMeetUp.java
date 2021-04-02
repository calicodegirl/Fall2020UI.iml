package utilities;

import cucumber.runtime.snippets.Concatenator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeWebDriverMeetUp {
    public static WebDriver loadChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        if(PropertiesReader.getProperty("headless").equalsIgnoreCase("true")){
            options.addArguments("--headless");
        }
        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-gpu");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
}
