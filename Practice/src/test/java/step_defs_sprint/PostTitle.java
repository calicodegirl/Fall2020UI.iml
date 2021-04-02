package step_defs_sprint;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class PostTitle {
    WebDriver driver;

    @Given("^user is navigated to phptravels home page$")
    public void userIsNavigatedToPhptravelsHomePage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://www.phptravels.net/admin");
        String expectedUrl="https://www.phptravels.net/admin";
        Assert.assertEquals("User is not navigated to home page of phphtravels",expectedUrl,driver.getCurrentUrl());
    }

    @When("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_and(String userName, String password) {
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        //driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @And("^user clicks on Submit button$")
    public void userClicksOnSubmitButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//        WebDriverWait wait = new WebDriverWait(driver,5);  //strong[starts-with(text(),'Hi Admin!')]
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2/*[1]")));
//        WebElement expectedText = driver.findElement(By.xpath("//h2/*[1]"));
//        Assert.assertTrue(expectedText.getText().contains("Hi"));
    }
    @Then("^user should see Blog button$")
    public void userShouldSeeBlogButton() throws InterruptedException{
        WebElement blogButton = driver.findElement(By.xpath("//button[@class='btn btn-success btn-block']"));
        Thread.sleep(5000);
        Assert.assertTrue(blogButton.isDisplayed());
    }

    @And("^user clicks on Blog button$")
    public void userClicksOnBlogButton() {
        driver.findElement(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]")).click();
    }
    @Then("^user should see Add Button$")
    public void userShouldSeeAddButton() {
        driver.findElement(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]")).click();
        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(addButton));
        Assert.assertTrue(addButton.isDisplayed());
        Assert.assertTrue(addButton.isEnabled());
    }

    @And("^user clicks on Add Button$")
    public void userClicksOnAddButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
    }

    @Then("^user is navigated to \"([^\"]*)\"$")
    public void user_is_navigated_to(String expectedUrl) {
        //String expectedUrl = "https://www.phptravels.net/admin/blog/add";
        driver.findElement(By.xpath("//form[@action='https://www.phptravels.net/admin/blog/']/*[1]")).click();
        driver.findElement(By.xpath("//form[@class='add_button']/*[1]")).click();
        Assert.assertEquals("Navigating to wrong URL", expectedUrl, driver.getCurrentUrl());
    }

    @Then("^user input in Post Tile field$")
    public void user_input_in_Post_Tile_field(){
        WebElement postTitle = driver.findElement(By.xpath("//input[@name='title']"));
        Assert.assertTrue(postTitle.isDisplayed());
        postTitle = driver.findElement(By.xpath("//input[@name='title']"));
        postTitle.sendKeys("Greatness of Grand Canyon");
        String expected = "Greatness of Grand Canyon";
        Assert.assertTrue("Test Failed: cannot pass value to the input field", expected.contains(postTitle.getText()));
    }
}
