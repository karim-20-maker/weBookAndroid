package pages;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class P03HomeScreen extends BasePage {
    public P03HomeScreen(AndroidDriver driver) {
        super(driver);
    }
    private final By imageView = By.xpath("(//android.view.ViewGroup[@resource-id='__CAROUSEL_ITEM_0_READY__'])[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView");
    private final By BackCTA = By.xpath("//android.widget.Button[@content-desc='Dashboard, back']");
    public void checkFeaturesFunctionalities(){
        waitForTime(5000);
        clickOnElement(imageView);
        clickOnElement(BackCTA);
        driver.navigate().back();
        waitForVisibility(imageView);
    }
}
