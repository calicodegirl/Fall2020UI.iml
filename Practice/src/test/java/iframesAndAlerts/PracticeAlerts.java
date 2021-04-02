package iframesAndAlerts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class PracticeAlerts {
    WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(PracticeAlerts.class);

    @Before
    public void setUp() {
        driver = Driver.getDriver();
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test
    public void testSimpleAlert() throws InterruptedException {
        LOGGER.info("Testing simple alert");
        driver.navigate().to("https://demoqa.com/alerts");
        LOGGER.info("Navigated to https://demoqa.com/alerts");
        //Task:

        //trigger first alert
        LOGGER.info("triggered the first alert");
        LOGGER.debug("trigger the first alert with id alertButton");
        driver.findElement(By.id("alertButton")).click();

        //now alert is present on the screen, we cannot inspect it
        LOGGER.debug("waiting for 3 s for the alert to popup");
        Thread.sleep(3000);
        LOGGER.debug("Switching to alert");
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        LOGGER.debug("Got the alert text " + alertText);
        //verify the text of alert
        Assert.assertEquals("You clicked a button", alertText);

        //with simple alert - we can only click 'Ok'
        alert.accept();

        LOGGER.debug("Click OK");

        driver.findElement(By.id("timerAlertButton")).click();

        LOGGER.debug("Waiting for 5 seconds the timeAlertButton to appear");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        // without wait - this line will fail because the alert  is not ont the screen right away
        driver.switchTo().alert();
        alert.accept();
        LOGGER.debug("Accepting Alert");
        LOGGER.info("SimpleAlert Test closure");
    }

    @Test
    public void testPromptAlert() {
        driver.navigate().to("https://demoqa.com/alerts");
        driver.findElement(By.id("promtButton")).click();
        Alert alert = driver.switchTo().alert();

        //since it's a prompt - I have to type some info in it
        String name= "Assel";
        alert.sendKeys(name);
        alert.accept();

        WebElement result = driver.findElement(By.id("promptResult"));
        Assert.assertTrue(result.getText().contains(name));
    }

    @Test
    public void testConfirmationAlert(){
        driver.navigate().to("https://demoqa.com/alerts");
        driver.findElement(By.id("confirmButton")).click();
        Alert alert = driver.switchTo().alert();
        // dismiss - means cancel
        alert.dismiss();
        WebElement result = driver.findElement(By.id("confirmResult"));
        Assert.assertTrue(result.getText().endsWith("Cancel"));
    }

    @Test
    public void practiceSimpleAlert() {
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText= ");
        WebElement alertBoxButton = driver.findElement(By.cssSelector("#alertBox"));
        alertBoxButton.click();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains("This is a simple alert box!"));
        alert.accept();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Selenium WebDriver Practice']")).isDisplayed());
    }

    @Test
    public void practiceConfirmAlert(){
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText= ");
        WebElement confirmBoxButton = driver.findElement(By.id("confirmBox"));
        confirmBoxButton.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.dismiss();
        //Assert.assertTrue();
    }
}
