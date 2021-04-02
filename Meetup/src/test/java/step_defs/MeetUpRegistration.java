package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import utilities.DriverMeetUp;

import java.util.concurrent.TimeUnit;

public class MeetUpRegistration {
    WebDriver driver = DriverMeetUp.getDriver();
    MainPage mainPage = new MainPage();

//    @Given("^user is on the Meetup home page$")
//    public void user_is_on_the_Meetup_home_page() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://www.meetup.com/");
//    }

    @When("^User clicks on Join Meetup button$")
    public void user_clicks_on_Join_Meetup_button() {
        mainPage.clickJoinMeetup();

//        WebElement joinMeetUpLink = driver.findElement(By.xpath("//a[@aria-label='Join Meetup']"));
//        joinMeetUpLink.click();
//        String expectedUrl = "https://secure.meetup.com/register/";
//        Assert.assertTrue("The button Join Meetup is not clickable or it didn't navigate to new window", driver.getCurrentUrl().equals(expectedUrl));
    }

    @Then("^User should be able to see \"([^\"]*)\" option$")
    public void user_should_be_able_to_see_option(String signUpWithSocialMediaAccount) {
        WebElement signUpOptionLink = driver.findElement(By.xpath("//span[text()='" + signUpWithSocialMediaAccount + "'][1]"));
        Assert.assertTrue("The button sign up with social media is not displayed", signUpOptionLink.isDisplayed());
    }

    @Then("^user should be able to see sign up with email$")
    public void user_should_be_able_to_see_sign_up_with_email() {
        WebElement signUpWithEmailLink = driver.findElement(By.xpath("//p[@class='runningText']/a"));
        Assert.assertTrue("The button sign up with email is not displayed", signUpWithEmailLink.isDisplayed());
    }
}