package iframesAndAlerts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class PracticeIframes {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = Driver.getDriver();
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test
    public void test1() {
        driver.navigate().to("https://demoqa.com/frames");

        //Before checking if text1 is displayed we need to switch to the iframe that contains it

        //1 option - switch to iframe by web element
        WebElement iframe1 = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iframe1);

        WebElement text1 = driver.findElement(By.id("sampleHeading"));
        Assert.assertTrue(text1.isDisplayed());
        driver.switchTo().defaultContent();

        //2 option - switch to iframe by id or name
        //driver.switchTo().frame("frame2");

        //3 option - switch to iframe by index
        driver.switchTo().frame(1);
        WebElement text2 = driver.findElement(By.id("sampleHeading"));
        Assert.assertTrue(text2.isDisplayed());
    }

    //Using Actions class scroll down to the iframe that contains code
    //Hit Run
    //Verify the code runs in under 5 ms$

    @Test
    public void test2() {
        driver.navigate().to("https://leetcode.com/");
        Actions actions = new Actions(driver);

        //find a parent of iframe and scroll to it
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='playground-iframe']"))).perform();
        //now iframe in the view and driver is able to find it
        WebElement iframe = driver.findElement(By.xpath("//iframe[@height='400']"));

        driver.switchTo().frame(iframe);

        WebElement runButton = driver.findElement(By.xpath("//button[contains(@class,'btn-success')]"));
        runButton.click();

        //wait after this div is present

        WebElement runResultMessage = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='success result']")));

        String text = runResultMessage.getText();  //Finished in 4 ms ---> String[] arr ={Finished, in, 4, ms}
        for (String word : text.split(" ")) {
            if (Character.isDigit(word.charAt(0))) {
                int ms = Integer.parseInt(word);
                Assert.assertTrue(ms < 5);
            }
        }
    }

    @Test
    public void testNestedIframes()  {
        //Verify Child Iframe text first
        //Verify Parent frame text second
        //Verify main page text “Sample Nested Iframe page.”

        driver.navigate().to("https://demoqa.com/nestedframes");
        WebElement parentFrame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(parentFrame);

        WebElement childFrame = driver.findElement(By.xpath("//body[contains(text(),'Parent')]/*"));
        driver.switchTo().frame(childFrame);

        String textToVerify = driver.findElement(By.xpath("//p[contains(text(),'Child')]")).getText();

        Assert.assertEquals("Child Iframe", textToVerify);

        driver.switchTo().defaultContent();
        driver.switchTo().frame(parentFrame);
        textToVerify = driver.findElement(By.xpath("//body[contains(text(),'Parent')]")).getText();

        Assert.assertEquals("Parent frame", textToVerify);
        driver.switchTo().defaultContent();

        textToVerify = driver.findElement(By.xpath("//div[@id='framesWrapper']/*[1]")).getText();
        Assert.assertTrue(textToVerify.contains("Nested Iframe"));
    }
}
