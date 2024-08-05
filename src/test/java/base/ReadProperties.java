package base;



public class ReadProperties {
    public static final String Email = getPropertyFromEnv("email");
    public static final String Password = getPropertyFromEnv("password");
    public static final String InvalidPassword = getPropertyFromEnv("password2");


    private static String getPropertyFromEnv(String propertyName) {
        return System.getProperty(propertyName, utiles.PropertiesLoader.readEnvFile(propertyName));
    }



}
