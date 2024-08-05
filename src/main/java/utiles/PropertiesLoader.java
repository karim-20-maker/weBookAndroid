package utiles;

import java.io.*;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties properties;
    public static Properties readPropertyFile(String configFilePath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(configFilePath));
            properties = new Properties();

            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + configFilePath);
        }
        return properties;
    }
    public static String readEnvFile(String key){
        File file = new File("config/env.properties");
        return getPropertyValue(key,file);
    }
    private static  String getPropertyValue(String key , File file){
        properties = new Properties();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            properties.load(bufferedReader);
        }catch (FileNotFoundException fileExc){
            fileExc.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }
}