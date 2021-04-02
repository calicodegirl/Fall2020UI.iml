package saucelabs;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabsDriver {
    //this will be the class to create the driver that will be running on SauceLabs
    //first we need to connect our code to our Sauce Labs account
    //we need or username, access key and url

    //https://Calicodegirl:6d172e1b-0de7-4743-b66f-a5fdf3c52b45@ondemand.us-west-1.saucelabs.com:443/wd/hub

    private static final String USERNAME = ConfigReader.getProperty("saucelabsUsername");
    private static final String ACCESS_KEY = ConfigReader.getProperty("saucelabsAccessKey");
    private static final String URL = "https://"+USERNAME+":"+ACCESS_KEY+"@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    public static WebDriver loadSauceLabsDriver(){
        //we need to provide the information on the configuration to use to run our tests
        //DesiredCapabilities Class

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.WIN10); //-  better to use this
        capabilities.setCapability("platformName", "Windows 10");
        //capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        capabilities.setCapability("browserName", "firefox");
        capabilities.setCapability("browserVersion", "latest");
        WebDriver driver = null;
        try{
            driver = new RemoteWebDriver(
                    new URL(URL),capabilities
            );
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
      return driver;
    }
}
