package windowsIframes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MultipleWindows {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @Test
    public void amazonTest(){
        driver.get("https://www.amazon.com/");
        //I want to get the unique window handle assigned to amazon page
        String windowHandle = driver.getWindowHandle();
        System.out.println(windowHandle);
        //CDwindow-2D832D86B27B7E512288CB6BE4EF44F6 - 1st run
        //CDwindow-F82CC915D9D6DC0D196611DA4025406C - 2nd run
    }
    //http://www.practiceselenium.com/
    
    @Test
    public void testMultipleWindows(){
        driver.get("http://www.practiceselenium.com/");
        driver.findElement(By.xpath("//span[contains(text(), '25 years')]/a")).click();
        //after I clicked on the link new window opened up

        //1 step
        String mainPage = driver.getWindowHandle(); //id of main page
        //2 step
        Set<String> allOpenedWindows = driver.getWindowHandles();
        //3 step
        for(String windowHandle: allOpenedWindows){
            if(!windowHandle.equals(mainPage)){
                driver.switchTo().window(windowHandle);
            }
        }
        //click on the link on the new window
        driver.findElement(By.xpath("//a[text()='Choosing an Automation Solution']")).click();
        //here I may need to add some wait
        WebElement agendaHeading = driver.findElement(By.xpath("//h3/strong[text()='Agenda:']"));  //also this xpath: //strong[text()='Agenda:']
        Assert.assertTrue("Agenda is not displayed. Switch to the new window",agendaHeading.isDisplayed());

        //I need to switch back to main window
        driver.switchTo().window(mainPage);
        WebElement checkOutLink = driver.findElement(By.xpath("//a[text()='Check Out']"));
        Assert.assertTrue("Failed to switch back to the home page", checkOutLink.isDisplayed());
    }
    @Test
    public void multipleWindows(){
        driver.get("https://demoqa.com/links");
        driver.findElement(By.id("simpleLink")).click();
        //after the click new window has opened

        //in order to click on Join Now - I first need to switch to the new window
        String mainPage = driver.getWindowHandle(); //store unique id of first page
        Set<String> allOpenedWindows = driver.getWindowHandles(); //currently only 2 values - 2 windows are open

        for(String windowHandle :allOpenedWindows){
            if(!windowHandle.equals(mainPage)){
                driver.switchTo().window(windowHandle);
            }
        }
        //no we are on the new window and we can interact with its elements
        WebElement joinNowLink = driver.findElement(By.xpath("//a[@href='https://www.toolsqa.com/selenium-training/']"));
        joinNowLink.click();//click on Join now button
        //now I have 3 windows open

        String secondPage = driver.getWindowHandle();
        allOpenedWindows= driver.getWindowHandles(); //we need to reassign Set // now it will have 3 values

        for (String windowHandle: allOpenedWindows){
            if(!windowHandle.equals(mainPage) && !windowHandle.equals(secondPage)){
                driver.switchTo().window(windowHandle);
            }
        }
        Assert.assertTrue("No Online Selenium Training heading",driver.getTitle().contains("Online Selenium Training"));
        Assert.assertTrue("No Selenium Training on the page",driver.findElement(By.tagName("h1")).isDisplayed()); //h1[text()='Selenium Training']
    }
}
