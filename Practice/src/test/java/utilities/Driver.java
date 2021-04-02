package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import saucelabs.SauceLabsDriver;

import java.util.concurrent.TimeUnit;



public class Driver {
    //this will be the class that will totally control our browser and driver
    //it will make sure that there is only one driver instance running at a time

    //initialize a logger for every class separately nd add the class you are working in
    //so that logger uses this class name when logging stuff from this class
    private static final Logger LOGGER = LogManager.getLogger(Driver.class);
    private Driver(){
    }
    private static WebDriver driver;
    public static WebDriver getDriver(){
        LOGGER.debug("Initializing a web driver for Selenium 3.141.59");
        if(driver==null){
            //here we will create a new fresh driver based on the browser property
            LOGGER.info("Loading " + ConfigReader.getProperty("browser").toLowerCase() + " browser");
            switch (ConfigReader.getProperty("browser").toLowerCase()){

                default:
                    //here is set up for Chrome
                    driver = ChromeWebDriver.loadChromeDriver();
                    break;
                case "firefox":
                    //here will be a code to set up firefox browser
                    driver = FirefoxWebDriver.loadFirefoxDriver();
                    WebDriverManager.firefoxdriver().setup();
                    break;
                case "safari":
                    //here is set up for safari
                    driver=new SafariDriver();
                    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    break;
                case "ie":
                    //here is set up for ie
                    WebDriverManager.iedriver().setup();
                    driver=new InternetExplorerDriver();
                    driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    break;
                case "saucelabs":
                    driver = SauceLabsDriver.loadSauceLabsDriver();
                    break;
            }
        }
        //if it is not null, we simply return existing driver
        return driver;
    }

    //2 - for closing yur driver
    public static void closeDriver(){
        try {
            if (driver != null) {
                LOGGER.info("Closing driver");
                driver.close();
                driver.quit();
                driver = null;
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
