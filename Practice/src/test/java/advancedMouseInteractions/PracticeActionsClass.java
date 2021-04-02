package advancedMouseInteractions;

import com.github.javafaker.Faker;
import cucumber.api.java.en_old.Ac;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.security.Key;

import static utilities.Helper.highlightElement;

public class PracticeActionsClass {
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
        //we will practice double click and rightClick
        driver.navigate().to("https://demoqa.com/buttons");
        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));

        //in order to use Actions Class
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).contextClick(rightClickButton).perform();

        WebElement doubleClickMessage = driver.findElement(By.id("doubleClickMessage"));
        Assert.assertTrue(doubleClickMessage.isDisplayed());

        //actions.contextClick(rightClickButton).perform();
        WebElement rightClickMessage = driver.findElement(By.id("rightClickMessage"));
        Assert.assertTrue(rightClickMessage.isDisplayed());
    }

    @Test
    public void test2() {
        driver.navigate().to("https://demoqa.com/tool-tips");
        WebElement greenButtonToHoverOver = driver.findElement(By.id("toolTipButton"));
        //in order tp hover over the web element we use moveToElement()
        Actions actions = new Actions(driver);
        actions.moveToElement(greenButtonToHoverOver).perform();
        ////button[@aria-describedby='buttonToolTip']
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.attributeToBe(greenButtonToHoverOver, "aria-describedby", "buttonToolTip"));
        Assert.assertTrue("Tool Tip is not displayed", greenButtonToHoverOver.getAttribute("aria-describedby").equals("buttonToolTip"));
    }

    @Test
    public void testHoverOver() {
        driver.navigate().to("https://demoqa.com/tool-tips");
        WebElement contraryTetHoverOver = driver.findElement(By.xpath("//a[text()='Contrary']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(contraryTetHoverOver).perform();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.attributeToBe(contraryTetHoverOver, "aria-describedby", "contraryTexToolTip"));
        Assert.assertTrue("Tool Tip is not displayed", contraryTetHoverOver.getAttribute("aria-describedby").equals("contraryTexToolTip"));

    }

    @Test
    public void test3() {
        driver.navigate().to("https://demoqa.com/slider");
        WebElement sliderBlueCircle = driver.findElement(By.xpath("//input[@min='0']"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(sliderBlueCircle).moveByOffset(30, 0).release().perform();
        int newSliderValue = Integer.parseInt(sliderBlueCircle.getAttribute("value"));
        System.out.println(newSliderValue);
        Assert.assertTrue(newSliderValue > 25);
    }

    @Test
    public void testDragAndDrop() {
        driver.navigate().to("https://demoqa.com/droppable");
        //1. we will use built into selenium drag and drop method
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.xpath("//div[@id='simpleDropContainer']/div[@id='droppable']"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        WebElement successfulDrop = target.findElement(By.xpath("./p"));
        Assert.assertTrue(successfulDrop.getText().equals("Dropped!"));
    }

    @Test
    public void testDragAndDropTwo() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/droppable");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.xpath("//div[@id='simpleDropContainer']/div[@id='droppable']"));

        Actions actions = new Actions(driver);
        //actions.clickAndHold(source).moveToElement(target).release().perform();
        actions.clickAndHold(source).moveByOffset(280, 25).release().perform();
        Thread.sleep(3000);

        WebElement successfulDrop = target.findElement(By.xpath("./p"));
        Assert.assertTrue(successfulDrop.getText().equals("Dropped!"));
    }

    @Test
    public void testProgressBar() {
        driver.navigate().to("https://demoqa.com/progress-bar");
        WebElement startButton = driver.findElement(By.id("startStopButton"));
        WebElement progressBar = driver.findElement(By.xpath("//div[@role='progressbar']"));
        startButton.click();

        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.attributeToBe(progressBar, "aria-valuenow", "100"));

        WebElement resetButton = driver.findElement(By.id("resetButton"));
        Assert.assertTrue(resetButton.getText().equals("Reset"));

        resetButton.click();
        Assert.assertTrue(progressBar.getAttribute("aria-valuenow").equals("0"));
    }

    @Test
    public void testHoverOverAndClick() {
        driver.navigate().to("https://demoqa.com/menu#");
        WebElement menuItemTwo = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions actions = new Actions(driver);

        actions.moveToElement(menuItemTwo).perform();
        WebElement subSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        actions.moveToElement(subSubList).perform();
        WebElement subSubItem1 = driver.findElement(By.xpath("//a[text()='Sub Sub Item 1']"));
        subSubItem1.click();
    }

    //control + c    control + v    copy-paste
    @Test
    public void testControlKeys() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/automation-practice-form");
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        highlightElement(firstNameInput);

        Faker faker = new Faker();
        firstNameInput.sendKeys(faker.funnyName().name());
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).perform();
        actions.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();

        highlightElement(lastNameInput);

        actions.sendKeys(Keys.TAB).perform();
        actions.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();
        System.out.println(lastNameInput.getAttribute("value"));
        Assert.assertTrue(lastNameInput.getAttribute("value").equals(firstNameInput.getAttribute("value")));
    }

}
