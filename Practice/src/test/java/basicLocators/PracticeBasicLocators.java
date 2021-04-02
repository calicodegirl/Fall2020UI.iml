package basicLocators;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PracticeBasicLocators {
    WebDriver driver;

    @Test @Ignore
    public void testClassLocator(){

        //navigate to google, find link "celebrating women..." and print out the text of the link
        // and verify link text contains "future leaders"

        //new way we set up a driver - independent from jar file
        //this is new way you set up your driver - same as System.setproperty did
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.google.com/");
        WebElement link = driver.findElement(By.className("NKcBbd"));
        String text = link.getText();
        System.out.println();

        Assert.assertTrue("Link text verification did not contain future leaders",text.contains("future leaders"));
    }

    //create a new test where you will navigate to this web-site
    //use tag name locator and find element and make sure it contains passionate
    @Test
    public void findElementByTag(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://www.practiceselenium.com/");
        WebElement element = driver.findElement(By.tagName("h1"));
        String text = element.getText();
        Assert.assertTrue("Link text verification did not contain passionate",text.contains("passionate"));
        if (text.contains("passionate")){
            System.out.println("Test passed");
        }else{
            System.out.println("Test failed");
        }
    }
    //go to google, click on gmail and verify url contain word mail
    @Test
    public void testLinkLocator() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        WebElement gmailLink = driver.findElement(By.linkText("Gmail")); //make sure you pass full text of the link
        gmailLink.click();
        Thread.sleep(3000); //it freezes the code for 3 seconds
        Assert.assertTrue(driver.getCurrentUrl().contains("mail"));
    }
    @Test
    public void testPartialLink() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get("https://www.google.com/");
        //using partial link text we will click on How Search works
        WebElement link = driver.findElement(By.partialLinkText("How Search"));
        link.click();
        Thread.sleep(2000);
        //Discover How Google Search Works
        String expected = "Discover How Google Search Works";
        Assert.assertTrue("Title verification failed. Expected " + expected + " and Actual: "+
                        driver.getTitle(), driver.getTitle().contains(expected));
    }

    //this is the method to clean up after your test, runs after every single test
    @After
    public void tearDown(){
        driver.close(); //we have to close browser after every single test
    }
}
