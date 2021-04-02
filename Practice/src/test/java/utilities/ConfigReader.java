package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //This class will read from configuration.properties
    //Then it will pass those values to our tests
    private static Properties properties;
    //we need to load our configuration.properties into properties variable
    static {
        try {
            //first of all, we need to provide a path to our properties file
            String path = "src/test/resources/configuration.properties";

            FileInputStream inputStream = new FileInputStream(path);
            //now we need to load into properties variable
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close(); //make sure we close inputStream
        } catch (IOException e){
            //in case this exception happens we want to know where it is coming from and the reason for it
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key).trim();
    }

}
