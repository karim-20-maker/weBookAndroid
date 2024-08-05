package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01WelcomeScreen;
import pages.P02LoginScreen;
import pages.P03HomeScreen;
import pages.P04ProfileScreen;

import static base.ReadProperties.Email;
import static base.ReadProperties.Password;

public class M04ProfileScreenTest extends BaseTest {
    P01WelcomeScreen welcome;
    P02LoginScreen login;
    P03HomeScreen home;
    P04ProfileScreen profile;
    @BeforeClass
    public void initObjects(){
        welcome= new P01WelcomeScreen(driver);
        login = new P02LoginScreen(driver);
        home = new P03HomeScreen(driver);
        profile = new P04ProfileScreen(driver);
    }
    @Test
    public void Tc_01validateProfileScreen(){
        welcome.checkWelcomeScreen();
        welcome.navigateToLoginScreen();
        login.checkLoginScreenElements();
        login.checkLoginSuccessfully(Email,Password);
        profile.checkProfileScreen();



    }
}
