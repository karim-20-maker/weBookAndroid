package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01WelcomeScreen;
import pages.P02LoginScreen;

import static base.ReadProperties.*;

public class M02LoginScreenTest extends BaseTest {
    P01WelcomeScreen welcome;
    P02LoginScreen login;
    @BeforeClass
    public void initObjects(){
        welcome= new P01WelcomeScreen(driver);
        login = new P02LoginScreen(driver);
    }
    @Test
    public void Tc_01validateLoginScreenElements(){
        welcome.checkWelcomeScreen();
        welcome.navigateToLoginScreen();
        login.checkLoginScreenElements();

    }
    @Test
    public void Tc_02validateLoginWithInvalidData(){
        login.checkLoginWithInvalidData(Email,InvalidPassword);
    }
    @Test
    public void Tc_03validateLoginSuccessfully(){
        login.checkLoginSuccessfully(Email,Password);
    }


}
