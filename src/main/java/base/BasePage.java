package base;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    public AndroidDriver driver;
    public BasePage(AndroidDriver driver){
        this.driver = driver;
    }
    public final By emailTitle = By.xpath("//android.widget.TextView[@text='Email']");
    public final By welcomeScreenTitle = By.xpath("//*[@text='Featured experiences']");
    public final By profileIcon = By.xpath("//android.widget.TextView[@text='FN']");
    public void waitForVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


    public void sendTextToInputField(By by, String text){
        waitForVisibility(by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    public Boolean assertElementDisplayed(By by) {
        waitForVisibility(by);
        return driver.findElement(by).isDisplayed();
    }
    public void scrollAndValidateElements(By... screenLocators) {
        for (By screenLocator : screenLocators) {
            androidScrollToElement(screenLocator,"down");
            Assert.assertTrue(assertElementDisplayed(screenLocator));
        }
    }
    public void locateElementAndScrollAndValidateElements(String... elementTexts) {
        for (String ElementText : elementTexts) {
            By screenLocator=By.xpath("//*[@text='" + ElementText + "']");
            androidScrollToElement(screenLocator,"down");
            Assert.assertTrue(assertElementDisplayed(screenLocator));
        }
    }



    public void clickOnElement(By by){
        waitForVisibility(by);
        driver.findElement(by).click();
    }


    public void androidScrollToElement(By by, String direction) {
        int count = 0;
        boolean isDisplayed = false;

        while (!isDisplayed && count++ < 100) {
            try {
                isDisplayed = driver.findElement(by).isDisplayed();
            } catch (NoSuchElementException | AssertionError | IndexOutOfBoundsException e) {
                touchScroll(direction);
            }
        }
    }
    public void scrollToElement(By by){
        androidScrollToElement(by,"down");
    }

    public void androidScrollToElementFromBottom(By by, String direction) {
        int count = 0;
        boolean isDisplayed = false;

        while (!isDisplayed && count++ < 100) {
            try {
                isDisplayed = driver.findElement(by).isDisplayed();
            } catch (NoSuchElementException | AssertionError | IndexOutOfBoundsException e) {
                touchScrollFromBottom(direction);
            }
        }
    }

    private void touchScroll(String scrollDirectionUpOrDown) {
        Duration SCROLL_DUR = Duration.ofMillis(300);
        Dimension dimension = driver.manage().window().getSize();
        System.out.println("size = " + dimension);
        Point midPoint = new Point((int) (dimension.width * 0.4), (int) (dimension.height * 0.4));
        System.out.println("midPoint = "+ midPoint);
        int bottom = midPoint.y + (int) (midPoint.y * .5);
        int top = midPoint.y - (int) (midPoint.y * .2);


        switch (scrollDirectionUpOrDown.toUpperCase()) {
            case "UP":
                swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
                break;
            case "DOWN":
                swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
                break;
            default:
                throw new IllegalArgumentException(
                        "Incorrect scroll direction given: Direction must be [up], or [down]");
        }
    }

    private void touchScrollFromBottom(String scrollDirection) {
        Duration SCROLL_DUR = Duration.ofMillis(300);
        Dimension dimension = driver.manage().window().getSize();
        System.out.println("size = " + dimension);
        Point midPoint = new Point((int) (dimension.width * 0.5), (int) (dimension.height * 0.75));
        System.out.println("midPoint = "+ midPoint);
        int bottom = midPoint.y + (int) (midPoint.y * .2);
        int top = midPoint.y;
        System.out.println(scrollDirection);


        switch (scrollDirection.toUpperCase()) {
            case "UP":
                swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
                break;
            case "DOWN":
                swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
                break;
            default:
                throw new IllegalArgumentException(
                        "Incorrect scroll direction given: Direction must be [up], or [down]");
        }
    }

    public void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }
    public void waitForTime(int timeInMilleSecond){
        try{
            Thread.sleep(timeInMilleSecond);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    public void closeAndRelaunchApp() {
        String appPackage = driver.getCurrentPackage();
        driver.terminateApp(appPackage);
        waitForTime(2000);
        driver.activateApp(appPackage);
        waitForTime(5000);
        driver.activateApp(appPackage);
    }
    public void swipeToElement(By locator) {
        boolean elementFound = false;
        int attempts = 0;
        int maxAttempts = 5; // Set a maximum number of attempts to avoid infinite loop

        while (!elementFound && attempts < maxAttempts) {
            try {
                WebElement element = driver.findElement(locator);
                elementFound = element.isDisplayed();
            } catch (Exception e) {
                Dimension size = driver.manage().window().getSize();
                int startX = (int) (size.width * 0.8);
                int endX = (int) (size.width * 0.2);
                int startY = size.height / 2;

                new TouchAction<>(driver)
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(endX, startY))
                        .release()
                        .perform();

                attempts++;
            }
        }
        if (!elementFound) {
            throw new RuntimeException("Element not found after " + maxAttempts + " attempts");
        }
    }

}
