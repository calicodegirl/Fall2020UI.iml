package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilities.DriverMeetUp;

public class GoogleTitleVerify {
    WebDriver driver = DriverMeetUp.getDriver();

    @When("^the user navigates to google$")
    public void the_user_navigates_to_google() {
        driver.navigate().to("https://www.google.com/");
        //DriverMeetUp.getDriver().navigate().to("https://www.google.com/");
    }

    @When("^the user searches for \"([^\"]*)\"$")
    public void the_user_searches_for(String searchCriteria) throws InterruptedException {
        driver.findElement(By.name("q")).sendKeys(searchCriteria + Keys.ENTER);
        Thread.sleep(5000);
    }

    @Then("^verify \"([^\"]*)\" is in the title of the page$")
    public void verify_is_in_the_title_of_the_page(String searchCriteria) {
        Assert.assertTrue(driver.getTitle().toLowerCase().contains(searchCriteria));
    }
}
