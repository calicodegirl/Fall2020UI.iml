package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverMeetUp;
import utilities.EnvironmentManager;

public class BasicValidationSteps {
    //WebDriver driver = DriverMeetUp.getDriver();

    @Given("^the User is on the meetup homepage$")
    public void the_User_is_on_the_meetup_homepage() {
        DriverMeetUp.getDriver().get(EnvironmentManager.baseUrl);

//        WebDriverManager.chromedriver().setup();
//        driver=new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.meetup.com/");
    }

    @Then("^verify the title contains \"([^\"]*)\"$")
    public void verify_the_title_contains(String expectedTitle){
        WebDriverWait wait = new WebDriverWait(DriverMeetUp.getDriver(),3);
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        //if our title takes couple seconds to load
        //our test will pass, it will wait for 2 seconds
        //but if our title is different at all, this wait throw an TimeOutException

        String actualTitle = DriverMeetUp.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @Then("^verify the URL contains \"([^\"]*)\"$")
    public void verify_the_URL_contains(String expectedUrl){
        String actualUrl = DriverMeetUp.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.equals(expectedUrl));
    }
}
