package drivers;


import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import utilities.PropertiesReader;
import java.io.IOException;

public class SampleDriver {
    private static WebDriver driver;
    private SampleDriver() {
    }
    public static WebDriver getDriver() {
        boolean headless = Boolean.parseBoolean(PropertiesReader.getProperty("headless"));
        if (driver == null){
            switch (PropertiesReader.getProperty("browser")){
                /*case "ff":
                    driver = FirefoxWebDriver.createFirefoxDriver(headless);
                    break;*/
                case "safari":
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                    break;
                case "headless chrome":
                    driver = ChromeWebDriver.createHeadlessChromeDriver();
                    break;
                default:
                    driver = ChromeWebDriver.createChromeDriver(headless);
            }
        }
        return driver;
    }
    public static void takesScreenshot(Scenario scenario) throws IOException {
        try {
            if (scenario.isFailed()){
                final byte[] screenshot = ((TakesScreenshot)SampleDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
        } catch (Exception e){
            System.out.println("Exception happened while taking screenshot" + e.getMessage());
        }
    }
    public static void closeDriver() {
        if(driver == null)
            return;
        try {
            driver.close();
            driver.quit();
            driver = null;
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}