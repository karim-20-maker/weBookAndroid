package pages;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class P02LoginScreen extends BasePage {
    public P02LoginScreen(AndroidDriver driver) {
        super(driver);
    }
    private final By emailInputField =By.xpath("(//*[@class='android.widget.EditText'])[1]");
    private final By passwordTitle = By.xpath("//android.widget.TextView[@text='Password']");
    private final By passwordInputField = By.xpath("(//*[@class='android.widget.EditText'])[2]");
    private final By loginCTA = By.xpath("//android.widget.TextView[@text='Login']");
    private final By signup = By.xpath("//android.widget.TextView[@text='Sign up']");
    private final By errorMessage = By.xpath("//android.widget.TextView[@text='Email or password is incorrect']");
    public void checkLoginScreenElements(){
        scrollAndValidateElements(emailTitle,emailInputField,passwordTitle,passwordInputField,loginCTA,signup);
    }
    private void fillLoginForm(String email , String password){
        sendTextToInputField(emailInputField,email);
        sendTextToInputField(passwordInputField,password);
        clickOnElement(loginCTA);
    }
    public void checkLoginWithInvalidData(String email , String password){
        fillLoginForm(email,password);
        waitForVisibility(errorMessage);

    }
    public void checkLoginSuccessfully(String email , String password){
        fillLoginForm(email,password);
        waitForVisibility(welcomeScreenTitle);

    }

}
