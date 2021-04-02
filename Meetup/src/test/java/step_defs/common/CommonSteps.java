package step_defs.common;

import cucumber.api.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CommonSteps {
    WebDriver driver;

//    @Given("^the User is on the Meetup homepage$")
//    public void the_User_is_on_the_Meetup_homepage() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
//        driver.get("https://www.meetup.com/");
//    }
}
