package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

import static utiles.PropertiesLoader.readPropertyFile;

public class BaseTest{
    public AndroidDriver driver;
    public String remote = System.getProperty("remote");
    Properties capsProp = readPropertyFile("config/AndroidCaps.properties");
    public String AppPackage = capsProp.getProperty("appPackage");

    @BeforeTest
    public void setupCaps() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName",capsProp.getProperty("platformName"));
        caps.setCapability("automationName",capsProp.getProperty("automationName"));
        caps.setCapability("platformVersion",capsProp.getProperty("platformVersion"));
        caps.setCapability("deviceName",capsProp.getProperty("deviceName"));
        caps.setCapability("appPackage",capsProp.getProperty("appPackage"));
        caps.setCapability("appActivity",capsProp.getProperty("appActivity"));
        caps.setCapability("appium:autoGrantPermissions","true");

        URL url = new URL("http://127.0.0.1:4723/");
        driver = new AndroidDriver(url,caps);
        driver.activateApp(capsProp.getProperty("appPackage"));

    }

    @AfterSuite
    public void closeApp(){
        if(driver != null)
            driver.quit();
    }


}