package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01WelcomeScreen;
import pages.P02LoginScreen;
import pages.P03HomeScreen;

import static base.ReadProperties.*;

public class M03HomeScreenTest extends BaseTest {
    P01WelcomeScreen welcome;
    P02LoginScreen login;
    P03HomeScreen home;
    @BeforeClass
    public void initObjects(){
        welcome= new P01WelcomeScreen(driver);
        login = new P02LoginScreen(driver);
        home = new P03HomeScreen(driver);
    }
    @Test
    public void Tc_01validateHomeScreen(){
        welcome.checkWelcomeScreen();
        welcome.navigateToLoginScreen();
        login.checkLoginScreenElements();
        login.checkLoginSuccessfully(Email,Password);
        home.checkFeaturesFunctionalities();



    }


}
