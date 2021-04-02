package step_defs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DriverMeetUp;
import utilities.EnvironmentManager;

import java.io.File;

public class Hooks {

    @Before
    public void setUp() throws Exception{
        //We don't need to set up our driver in @Before
        //Here we can set up our environment variables
        EnvironmentManager.setUpEnvironment();
    }

   @After
    public void tearDown(Scenario scenario){
        try{
            if (scenario.isFailed()){
                //we will take a screenshot
                final byte [] screenshot = ((TakesScreenshot)DriverMeetUp.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot,"image/png");
            }
        } catch (Exception e){
            System.out.println("The error happened while taking screenshot and cleaning up after the test");
            e.getMessage();
        }
        DriverMeetUp.closeDriver();
    }
}
