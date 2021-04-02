package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import utilities.DriverMeetUp;
import utilities.EnvironmentManager;

import java.util.concurrent.TimeUnit;

public class LoginFlowSteps {
    WebDriver driver = DriverMeetUp.getDriver();
    MainPage mainPage = new MainPage();

//    @Given("^the User is on the Meetup homepage$")
//    public void the_User_is_on_the_Meetup_homepage() {
//        driver.get(EnvironmentManager.baseUrl);
//    }

    @Then("^User should be able to see \"([^\"]*)\" button$")
    public void user_should_be_able_to_see_button(String button) {
        if (button.equalsIgnoreCase("Log in")) {
            Assert.assertTrue(mainPage.loginButton.isDisplayed());
        } else if (button.equalsIgnoreCase("sign up")) {
            Assert.assertTrue(mainPage.signUpButton.isDisplayed());
        } else if (button.equalsIgnoreCase("join Meetup")) {
            Assert.assertTrue(mainPage.joinMeetupButton.isDisplayed());
        }
        // WebElement buttonToVerify = driver.findElement(By.xpath("//a[text()='" + button + "'][1]"));
    }
}
