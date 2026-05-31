package org.example.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReaderUtil {
    static Properties prop;
    static {
        try{
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/main/properties/config.properties");
            prop = new Properties();
            prop.load(fis);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
