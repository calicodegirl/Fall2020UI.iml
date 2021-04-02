package sprintFour;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class PhpTravelsAdmin {
    WebDriver driver;

    @Before
    public void setUp() {
        driver=Driver.getDriver();
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test
    public void testLoginWithValidUsernameAndPassword() {
        driver.get("https://www.phptravels.net/admin");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("admin@phptravels.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demoadmin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement expectedText = driver.findElement(By.xpath("//strong[starts-with(text(),'Hi Admin!')]"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[starts-with(text(),'Hi Admin!')]")));
        Assert.assertTrue(expectedText.isDisplayed());
    }
    @Test
    public void blogIsDisplayedAndEnabled() {
        driver.navigate().to("https://www.phptravels.net/admin");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("admin@phptravels.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demoadmin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement blogButton = driver.findElement(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]")));
        Assert.assertTrue(blogButton.isDisplayed());
        Assert.assertTrue(blogButton.isEnabled());
    }

    @Test
    public void addButtonIsDisplayedAndEnabled() {
        driver.get("https://www.phptravels.net/admin");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("admin@phptravels.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demoadmin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]")).click();
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(addButton));
        Assert.assertTrue(addButton.isDisplayed());
        Assert.assertTrue(addButton.isEnabled());
    }

    @Test
    public void testInputFieldIsDisplayed() {
        driver.get("https://www.phptravels.net/admin");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("admin@phptravels.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demoadmin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]")).click();

        driver.findElement(By.xpath("//form[@class='add_button']/*[1]")).click();
        WebElement postTitle = driver.findElement(By.xpath("//input[@name='title']"));
        Assert.assertTrue(postTitle.isDisplayed());
    }

    @Test
    public void testUrlAndInputText() {
        driver.get("https://www.phptravels.net/admin");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("admin@phptravels.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demoadmin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]")).click();

        driver.findElement(By.xpath("//form[@class='add_button']/*[1]")).click();

        String expectedUrl = "https://www.phptravels.net/admin/blog/add";
        Assert.assertEquals("Navigating to wrong URL", expectedUrl, driver.getCurrentUrl());

        WebElement postTitle = driver.findElement(By.xpath("//input[@name='title']"));
        postTitle.sendKeys("Greatness of Grand Canyon");

        String expected = "Greatness of Grand Canyon";
        Assert.assertTrue("Test Failed: cannot pass value to the input field", expected.contains(postTitle.getText()));
    }
}
