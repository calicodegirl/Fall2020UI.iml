package basicLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class HomeWorkBasicLocators {
    WebDriver driver;
    @Before
    public void beforeHook(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
    @Test
    public void byPartialLinkText()  {
        driver.get("http://www.practiceselenium.com/");
        WebElement link = driver.findElement(By.partialLinkText("Talk Tea"));
        link.click();
        Assert.assertTrue("Test failure: input field for the name is not displayed", driver.findElement(By.name("name")).isDisplayed());
    }
    @Test
    //Verify “We’re passionate about tea” is displayed
    public void verifyByTag(){
        driver.get("http://www.practiceselenium.com/");
        WebElement tag = driver.findElement(By.tagName("h1"));
        String expected = tag.getText();
        Assert.assertTrue("Test failure: verify by tag", expected.equals("We're passionate about tea. "));
    }
    @Test
    public void byClassName(){
        driver.get("http://www.practiceselenium.com/");
        WebElement checkOut = driver.findElement(By.partialLinkText("Check Out"));
        checkOut.click();
        WebElement cancel = driver.findElement(By.className("btn"));
        cancel.click();
        String expectedUrl = "http://www.practiceselenium.com/menu.html";
        Assert.assertEquals("Test Failure : didn't click on Cancel",expectedUrl,driver.getCurrentUrl());
    }
    @After
    public void tearDown(){
        driver.close();
    }
}
//Set up the driver, maximize the window, add timeouts before every single test
//Task #1 – partial link text
//1. Go to “http://www.practiceselenium.com/”
//2. Click on Let’s Talk Tea
//3. Verify input field for the name is displayed
//Task #2 – tag
//1. Go to “http://www.practiceselenium.com/”
//2. Verify “We’re passionate about tea” is displayed
//Task #3 – class name
//1. Go to “http://www.practiceselenium.com/”
//2. Click on Check Out
//3. Click Cancel