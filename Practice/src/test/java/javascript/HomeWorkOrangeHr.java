package javascript;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.Helper;

import static utilities.Helper.highlightElement;

public class HomeWorkOrangeHr {
    WebDriver driver;
    Actions actions;

    @Before
    public void setUp() {
        driver = Driver.getDriver();
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        WebElement credentials = driver.findElement(By.xpath("//span[text()='( Username : Admin | Password : admin123 )']"));
        actions = new Actions(driver);
        //copy Admin for username credential
        actions.moveToElement(credentials).moveByOffset(-30, 0).doubleClick().perform();
        actions.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();

        //paste Admin to username input
        WebElement inputUserName = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        highlightElement(inputUserName);
        actions.moveToElement(inputUserName).click().keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();

        //copy admin123 for username credential
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        highlightElement(inputPassword);
        actions.moveToElement(credentials).moveByOffset(70, 0).doubleClick().perform();
        actions.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();

        //paste admin123 to password input
        actions.moveToElement(inputPassword).click().keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();

        //click on Submit button
        WebElement loginButton = driver.findElement(By.xpath("//input[@name='Submit']"));
        highlightElement(loginButton);
        loginButton.click();

        //make sure it is landed to Dashboard section
        Assert.assertTrue("Log in was unsuccessful", driver.getCurrentUrl().equals("https://opensource-demo.orangehrmlive.com/index.php/dashboard"));

        //click on 'Assign Leave' button
        WebElement assignLeaveButton = driver.findElement(By.xpath("//span[text()='Assign Leave']"));
        highlightElement(assignLeaveButton);
        assignLeaveButton.click();

        //click on 'Employee name' input
        WebElement employeeNameInput = driver.findElement(By.xpath("//input[@id='assignleave_txtEmployee_empName']"));
        highlightElement(employeeNameInput);
        employeeNameInput.click();

        //type name Cecil, not fully - and then choose autogenerated name
        employeeNameInput.sendKeys("Cecil");
        //hover over on auto generated suggestion and click on it
        actions.moveToElement(driver.findElement(By.xpath("//li[@class='ac_even ac_over']"))).click().perform();

        //Leave type - choose US - Vacation
        WebElement dropdownLeaveType = driver.findElement(By.xpath("//select[@id='assignleave_txtLeaveType']"));
        highlightElement(dropdownLeaveType);
        dropdownLeaveType.click();

        Select selectedOption = new Select(dropdownLeaveType);
        selectedOption.selectByValue("1");
        actions.click().perform();

        //'From date' calendar image button
        WebElement fromDateButton = driver.findElement(By.xpath("//input[@id='assignleave_txtFromDate'][1]"));
        fromDateButton.click();

        //locate tomorrow day - using 'current day' @class
        WebElement tomorrowDay = driver.findElement(By.xpath("//td[contains(@class,'ui-datepicker-current-day')]/following-sibling::td[1]"));
        highlightElement(tomorrowDay);
        actions.moveToElement(tomorrowDay).click().perform();

        //Type any comment to 'Comment' section
        WebElement textAreaComment = driver.findElement(By.xpath("//textarea[@id='assignleave_txtComment']"));
        highlightElement(textAreaComment);
        actions.moveToElement(textAreaComment).click().sendKeys("Yeah! Vacation!").perform();

        //click on assign button
        WebElement assignButton = driver.findElement(By.id("assignBtn"));
        Helper.weClick(assignButton);

        //verify if popup modal is displayed
        WebElement popUpModal = driver.findElement(By.xpath("//div[@id='leaveBalanceConfirm']"));
        Helper.waitForElementToBeDisplayed(popUpModal);
        Assert.assertTrue(popUpModal.isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Employee does not have sufficient leave balance for leave request.']")).isDisplayed());
        Thread.sleep(3000);
    }
}
