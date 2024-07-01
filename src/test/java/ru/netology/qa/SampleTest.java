package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SampleTest {

    private AndroidDriver driver;
    private String textToSet = "Netology";
    private String emptyField = "   ";

    private URL getUrl() {
        try {
            return new URL("http://192.168.56.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    public void setUp() {
        var options = new DesiredCapabilities();
        options.setCapability("platformName", "Android");
        options.setCapability("appium:deviceName", "Some name");
        options.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        options.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        options.setCapability("appium:automationName", "uiautomator2");
        driver = new AndroidDriver(this.getUrl(), options);
    }

    @Test
    public void testChangeText() {
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(textToSet);
        var el2 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.isDisplayed();
        el2.click();
        var el3 = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        el3.isDisplayed();
        Assertions.assertEquals(el3.getText(), textToSet);
    }

    @Test
    public void testEmptyField() {
        String el0 = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(emptyField);
        var el2 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.isDisplayed();
        el2.click();
        var el3 = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        el3.isDisplayed();
        Assertions.assertEquals(el3.getText(), el0);
    }

    @Test
    public void testOpenTextInAnotherActivity() throws InterruptedException {
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(textToSet);
        var el2 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.isDisplayed();
        el2.click();
        Thread.sleep(100);
        var el3 = driver.findElementById("ru.netology.testing.uiautomator:id/text");
        el3.isDisplayed();
        Assertions.assertEquals(el3.getText(), textToSet);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
