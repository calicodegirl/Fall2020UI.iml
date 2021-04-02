package advancedLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PracticeAdvancedLocators {
    WebDriver driver;
    @Before
    public void setUp(){
        //This is set-up method for every single test annotation
        //we initialize the driver, maximize the window, set timeout
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test1(){
        //Try out findElements() method:
        driver.get("https://www.devxschool.com/");
        //print out the text of the first link of the page
        WebElement firstLink = driver.findElement(By.tagName("a"));
        System.out.println("First link is " + firstLink.getAttribute("href"));

        //we want to Print out text of every single link on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for(WebElement link : links){
            System.out.println(link.getAttribute("href"));
        }
    }
    @Test
    public void printIphoneBrands() throws InterruptedException{
        driver.get("https://www.amazon.com/");
        //locate input field nand type iphone
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("iphone" + Keys.ENTER);
        Thread.sleep(3000);
        List<WebElement> brands = driver.findElements(By.cssSelector("li[id^='p_89/']"));
        for(WebElement brand: brands){
            System.out.println(brand.getText());
        }
    }

    @Test
    public void printRadioButtontext() throws InterruptedException{
        driver.get("https://demoqa.com/radio-button");
        String question = driver.findElement(By.xpath("//div[@class='mb-3']")).getText();
        System.out.println("The question on hte page is " + question);
        driver.findElement(By.xpath("//label[@for='yesRadio']")).click();
        String resultText = driver.findElement(By.xpath("//span[@class='text-success']")).getText();
        Assert.assertEquals("Yes", resultText.trim());
    }
    //Navigate to the page
    //Click on the arrow to show nested folders
    //Select Desktop folder
    //Verify the result text contains “desktop”
    @Test
    public void checkBoxUsingXpath(){
        driver.get("https://demoqa.com/checkbox");

    }

    //navigate to https://opensource-demo.orangehrmlive.com/
    //login
    //verify Paul is in the welcome text
    @Test
    public void testWelcomeText() throws InterruptedException{
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("admin123");
        driver.findElement(By.xpath("//div[@id='divLoginButton']/input")).click();

        Thread.sleep(3000);
        String welcomeText = driver.findElement(By.xpath("//a[starts-with(text(),'Welcome')]")).getText();
        Assert.assertTrue("Welcome text contains wrong name",welcomeText.contains("Paul"));
    }

}
