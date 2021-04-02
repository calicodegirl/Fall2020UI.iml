package javascript;

import com.github.javafaker.Faker;
import gherkin.lexer.Th;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;
import utilities.Helper;
import static utilities.Helper.highlightElement;

public class PracticeJavaScript {
    //https://www.etsy.com/
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
    public void etsyTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.location='https://www.etsy.com/'");

        WebElement signInButton = driver.findElement(By.cssSelector(".select-signin"));
       // js.executeScript("arguments[0].setAttribute('style', arguments[1]);", signInButton, "border: 2px solid green");
        highlightElement(signInButton);
        //Thread.sleep(2000);


        js.executeScript("arguments[0].click();", signInButton);
        Thread.sleep(3000);


       // js.executeScript("document.getElementById('join_neu_email_field').setAttribute('style', arguments[0]);", "border: 2px solid blue");
        highlightElement("join_neu_email_field");
        //Thread.sleep(3000);

        Faker faker = new Faker();
        String fakeEmail = faker.internet().emailAddress();
        String fakePassword = faker.internet().password();

        js.executeScript("document.getElementById('join_neu_email_field').value=arguments[0];", fakeEmail);

        js.executeScript("document.getElementById('join_neu_password_field').setAttribute('style', arguments[0]);", "border: 2px solid yellow");

        highlightElement("join_neu_password_field");
        //Thread.sleep(3000);

        js.executeScript("document.getElementById('join_neu_password_field').value=arguments[0];", fakePassword);

        WebElement submitButton = driver.findElement(By.xpath("//button[@name='submit_attempt']"));
        //js.executeScript("arguments[0].setAttribute('style', arguments[1]);", submitButton, "border:2px solid fuxia");
        highlightElement(submitButton);
        //Thread.sleep(3000);

        js.executeScript("arguments[0].click();", submitButton);

        Thread.sleep(4000);
    }
}
