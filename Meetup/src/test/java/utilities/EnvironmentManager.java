package utilities;

import org.openqa.selenium.interactions.Actions;

public class EnvironmentManager {
    public static String baseUrl;
    public static String userName;
    public static String password;
    public static void setUpEnvironment() throws Exception{
        switch (PropertiesReader.getProperty("environment").toLowerCase()){
            case "dev":
                DEV();
                break;
            case "qa":
                QA();
                break;
            case "prod":
                PROD();
                break;
            default:
                //here we can set Qa env-t to be default
                throw new Exception("Invalid environment in application.properties\n" +
                        "Please select from: Dev, QA, Prod");
        }

    }
    private static void DEV() {
        //to set up dev variables
        baseUrl = "https://www.dev-meetup.com/";
        userName = "TestUserDev";
        password = "test123";
    }
    private static void QA() {
        //to set up qa variables
        baseUrl = "https://www.qa-meetup.com/";
        userName = "TestUserQa";
        password = "test123";
    }
    private static void PROD() {
        //to set up prod variables
        baseUrl = "https://www.meetup.com/";
        userName = "ProdUser";
        password = "test123";
    }
}
