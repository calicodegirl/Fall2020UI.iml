package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.SampleDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MeetUpRegistrationSteps {
    WebDriver driver = SampleDriver.getDriver();

    @Given("^the user is on the meetup home page$")
    public void theUserIsOnTheMeetupHomePage() {
        driver.get("https://www.meetup.com/");
    }

    @When("^user clicks on \"([^\"]*)\"$")
    public void user_clicks_on(String JoinMeetup) {
        WebElement button = driver.findElement(By.xpath("//a[text()='Join Meetup']"));
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }


    @Then("^user should be able to see email$")
    public void user_should_be_able_to_see_email() {
        Assert.assertTrue("Email option is not displayed", driver.findElement(By.id("register-trigger--withEmail")).isDisplayed());
    }


    @Then("^user should be able to see \"([^\"]*)\" option$")
    public void userShouldBeAbleToSeeOption(String button) {
        Assert.assertTrue("Social media button is not displayed",
                driver.findElement(By.xpath("//span[text()='Continue with " + button + "']")).isDisplayed());
    }
}