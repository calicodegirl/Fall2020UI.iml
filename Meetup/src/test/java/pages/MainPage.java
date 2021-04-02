package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverMeetUp;

import java.util.List;

public class MainPage {
    //in this class we will store all the elements from the main page of Meetup
    public MainPage() {
        //this line will initialize all the web elements on this page
        PageFactory.initElements(DriverMeetUp.getDriver(), this);
    }
    //here we will add all the elements that belong to main page

    @FindBy(xpath = "//a[text()='Log in' and contains(@href, 'secure')]")
    public WebElement loginButton;

    @FindBy(xpath = "//a[text()='Sign up' and contains(@href, 'secure')]")
    public WebElement signUpButton;

    @FindBy(xpath = "//a[text()='Join Meetup' and contains(@href, 'secure')]")
    public WebElement joinMeetupButton;
    @FindBy(id = "tracking--searchComponentInput")
    public WebElement searchInputfield;

    @FindBy(id = "tracking--searchComponentButton")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@aria-label='Search for location by city or zip code']")
    public WebElement locationInputField;

    //should match all of them:
    @FindBys({
            @FindBy(xpath = "//a[@role='navigation']"),
            @FindBy(xpath = "//a[@aria-label='Online']")
    })
    public WebElement onlineLink;


    @FindAll({
            @FindBy(xpath = "//a[@role='navigation']"), //it will return first element matching this
            @FindBy(xpath = "//a[@aria-label='Online']")
    })
    public List<WebElement> someElement;

    //here we can add more elements that you are using in your tests
    //In the page class we can add. basic methods that you perform on the elements of this page

    public void clickJoinMeetup() {
        joinMeetupButton.click();
    }

    public void search(String searchCriteria, String location) {
        searchInputfield.sendKeys(searchCriteria);
        if (location != null) {
            // locationInputField.clear();
            locationInputField.sendKeys(Keys.COMMAND + "a" + Keys.DELETE);
            locationInputField.sendKeys(location);
        }
        searchButton.click();
    }
}
