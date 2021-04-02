package advancedLocators;

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

public class HomeWorkCheckBoxXpath {
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void containsDesktopTextInSelectedCheckBox() throws InterruptedException {
        driver.get("https://demoqa.com/checkbox");
        driver.findElement(By.xpath("//button[@aria-label='Toggle']")).click();
        driver.findElement(By.xpath("//label[@for='tree-node-desktop']")).click();
        Thread.sleep(3000);
        String result = driver.findElement(By.xpath("//div[@id='result']")).getText();
        Assert.assertTrue("Test failure: checkbox text doesn't contain \"desktop\"", result.contains("desktop"));
    }

    @Test
    public void yesInRadioButtonIsSelected() throws InterruptedException {
        driver.get("https://demoqa.com/radio-button");
        driver.findElement(By.xpath("//label[contains(text(),'Yes')]")).click();
        Thread.sleep(3000);
        Assert.assertTrue("Test failure: Yes is not selected", driver.findElement(By.xpath("//input[@type='radio'][@id='yesRadio']")).isSelected());
    }
}
//https://demoqa.com/checkbox
//Navigate to the page
//Click on the arrow to show nested folders
//Select Desktop folder
//Verify the result text contains “desktop”
//*Look up how to check if radio button is selected in Selenium. Add to radio buttons an assertion
// that would verify yes radio button is selected