package regression;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01WelcomeScreen;
import pages.P02LoginScreen;
import pages.P03HomeScreen;
import pages.P04ProfileScreen;

import static base.ReadProperties.*;

public class Regression extends BaseTest {
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
    public void Tc_01validateWelcomeScreen(){
        welcome.checkWelcomeScreen();
    }
    @Test
    public void Tc_02validateBrowseByArabic(){
        welcome.CheckBrowseWithArabic();
    }
    @Test
    public void Tc_03validateLoginScreenElements(){
        welcome.checkWelcomeScreen();
        welcome.navigateToLoginScreen();
        login.checkLoginScreenElements();
    }
    @Test
    public void Tc_04validateLoginWithInvalidData(){
        login.checkLoginWithInvalidData(Email,InvalidPassword);
    }
    @Test
    public void Tc_05validateLoginSuccessfully(){

        login.checkLoginSuccessfully(Email,Password);
    }
    @Test
    public void Tc_06validateHomeScreen(){
        home.checkFeaturesFunctionalities();
    }
    @Test
    public void Tc_07validateProfileScreen(){
        profile.checkProfileScreen();
    }
}
