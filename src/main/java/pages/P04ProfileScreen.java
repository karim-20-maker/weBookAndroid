package pages;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class P04ProfileScreen extends BasePage {
    public P04ProfileScreen(AndroidDriver driver) {
        super(driver);
    }
    private final By nameTitle = By.xpath("//*[@text='Profile set-up']");
    private final By myProfileOption = By.xpath("//*[@text='My profile']");
    private final By MyInfoTitle = By.xpath("//*[@text='My Info']");
    private final By editInfo = By.xpath("//*[@text='Edit Info']");
    private final By cityInput = By.xpath("(//*[@class='android.widget.EditText'])[3]");
    private final By saveInfo = By.xpath("//*[@text='Save Updates']");
    public void checkProfileScreen(){
        clickOnElement(profileIcon);
        waitForVisibility(nameTitle);
        scrollToElement(myProfileOption);
        clickOnElement(myProfileOption);
        waitForVisibility(MyInfoTitle);
        scrollToElement(editInfo);
        clickOnElement(editInfo);
        sendTextToInputField(cityInput,"Alexandria");
        clickOnElement(saveInfo);


    }
}
