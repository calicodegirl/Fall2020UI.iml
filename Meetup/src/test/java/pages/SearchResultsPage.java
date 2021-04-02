package pages;

import jdk.internal.dynalink.linker.LinkerServices;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverMeetUp;

import java.util.List;

public class SearchResultsPage {
    public SearchResultsPage() {
        PageFactory.initElements(DriverMeetUp.getDriver(), this);
    }

    @FindAll({
            @FindBy(xpath = "//p[@class='css-1jy1jkx']")
    })
    public List<WebElement> onlineEventLinks;
}
