package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import pages.SearchResultsPage;
import utilities.DriverMeetUp;

import java.util.List;

public class SearchFunctionalitySteps {
   // WebDriver driver = DriverMeetUp.getDriver();
    MainPage mainPage = new MainPage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();

    @When("^the user executes the search for \"([^\"]*)\" in \"([^\"]*)\"$")
    public void the_user_executes_the_search_for_in(String searchCriteria, String location) throws InterruptedException{
        mainPage.search(searchCriteria,location);
        Thread.sleep(5000);
    }

    @Then("^verify the resultset contains search criteria in the title$")
    public void verify_the_resultset_contains_search_criteria_in_the_title() {
        String expectedTitle = "Foodie Rome";
        List<WebElement> searchResults = searchResultsPage.onlineEventLinks;

        for(int i =0;i<searchResults.size();i++){
            System.out.println(searchResults.get(i).getText());
            Assert.assertTrue(searchResults.get(i).getText().contains(expectedTitle));
        }
    }
}
