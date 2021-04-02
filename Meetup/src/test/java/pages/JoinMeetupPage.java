package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverMeetUp;

public class JoinMeetupPage {
    public JoinMeetupPage() {
        PageFactory.initElements(DriverMeetUp.getDriver(), this);
    }

    @FindBy(xpath = "//form/a[@id='facebookAuthButton']")
    public WebElement continueWithFacebookButton;

    //here we will add the rest of the elements from this page that we used in scenario

}
