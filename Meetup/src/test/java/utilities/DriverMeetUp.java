package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class DriverMeetUp {
    private static WebDriver driver;
    private DriverMeetUp(){

    }
    public static WebDriver getDriver(){
        if (driver==null){
           switch (PropertiesReader.getProperty("browser").toLowerCase()){
               default:
                   driver= ChromeWebDriverMeetUp.loadChromeDriver();
                   break;
               case "firefox":
                   driver = FireFoxDriverMeetUp.loadFireFoxDriver();
                   break;
               case "safari":
                   driver = new SafariDriver();
                   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                   driver.manage().window().maximize();
                   break;
               case "ie":
                   driver = new InternetExplorerDriver();
                   driver.manage().timeouts().implicitlyWait(9,TimeUnit.SECONDS);
                   driver.manage().window().maximize();
                   break;
           }
        }
        return driver;
    }
    public static void closeDriver(){
        try{
            if(driver!=null) {
                driver.close();
                driver.quit();
                driver=null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
