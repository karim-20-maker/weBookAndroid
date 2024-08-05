package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01WelcomeScreen;

public class M01WelcomeScreenTest extends BaseTest {
    P01WelcomeScreen welcome;
    @BeforeClass
    public void initObjects(){
        welcome= new P01WelcomeScreen(driver);
    }
    @Test
    public void Tc_01validateWelcomeScreen(){
        welcome.checkWelcomeScreen();
    }
    @Test
    public void Tc_02validateBrowseByArabic(){
        welcome.CheckBrowseWithArabic();
    }

}
