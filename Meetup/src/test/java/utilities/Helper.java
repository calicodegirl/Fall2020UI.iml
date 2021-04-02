package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    //this class will store reusable methods

    //actions click - mouse click
    public static void mouseClick(WebElement element){
        //I would first check if my element is clickable and visible, enable
        waitForElementToBeDisplayed(element);
        waitForElementClickable(element);
        Actions actions= new Actions(DriverMeetUp.getDriver());
        actions.click(element).perform();
    }

    public static void javascriptClick(WebElement element){
        waitForElementToBeDisplayed(element);
        waitForElementClickable(element);
        JavascriptExecutor js = (JavascriptExecutor)DriverMeetUp.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    //in case you will be demoing your framework, reusable method to highlight the element that we are interacting with
    public static void highlightElement(WebElement element) throws InterruptedException{
        String style = "border:4px dashed red";
        JavascriptExecutor js = (JavascriptExecutor)DriverMeetUp.getDriver();
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, style);
        Thread.sleep(3000); //only for demo purposes
    }

    public static void highlightElement(String id) throws InterruptedException{
        String style = "border:4px dashed red";
        JavascriptExecutor js = (JavascriptExecutor)DriverMeetUp.getDriver();
        js.executeScript("document.getElementById('"+id+"').setAttribute('style', arguments[0]);", style);
        Thread.sleep(3000); //only for demo purposes
    }
    //waits methods
    public static void waitForElementToBeDisplayed(WebElement element){
        new WebDriverWait(DriverMeetUp.getDriver(),10).until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitForElementClickable(WebElement element){
        new WebDriverWait(DriverMeetUp.getDriver(),10).until(ExpectedConditions.elementToBeClickable(element));

    }
    //I have a custom click method weClick()
    public static void weClick(WebElement element){
        waitForElementToBeDisplayed(element);
        waitForElementClickable(element);
        element.click();
    }

}
