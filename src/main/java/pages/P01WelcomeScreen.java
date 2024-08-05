package pages;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class P01WelcomeScreen extends BasePage {
    public P01WelcomeScreen(AndroidDriver driver) {
        super(driver);
    }
    private final By loginCTA = By.xpath("(//*[@class='android.widget.TextView'])[3]");
    private final By browseFirst = By.xpath("(//*[@class='android.widget.TextView'])[4]");
    private final By browseBy = By.xpath("(//*[@class='android.widget.TextView'])[1]");
    private final By HeaderTitle = By.xpath("(//*[@class='android.widget.TextView'])[2]");
    public void checkWelcomeScreen(){
        waitForVisibility(browseFirst);
        Assert.assertTrue(driver.findElement(browseBy).getText().contains("تصفح بالعربية"));
        Assert.assertTrue(driver.findElement(HeaderTitle).getText().contains("Your Super App for Fun Things"));
        Assert.assertTrue(driver.findElement(loginCTA).getText().contains("Log In / Sign Up"));
        Assert.assertEquals(driver.findElement(browseFirst).getText(),"Browse First");
    }
    public void CheckBrowseWithArabic(){
        clickOnElement(browseBy);
        waitForVisibility(By.xpath("//*[@text='Browse in English']"));
        Assert.assertTrue(driver.findElement(browseBy).getText().contains("Browse in English"));
        Assert.assertTrue(driver.findElement(HeaderTitle).getText().contains("تطبيقك الشامل لأمتع التجارب"));
        Assert.assertTrue(driver.findElement(loginCTA).getText().contains(" دخول / تسجيل"));
        Assert.assertEquals(driver.findElement(browseFirst).getText(),"تصفح أولاً");
        clickOnElement(browseBy);

    }
    public void navigateToLoginScreen(){
        clickOnElement(loginCTA);
        waitForVisibility(emailTitle);
    }
}
