package com.lordOfPlanet.demo.autoTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SettingsTest {
   protected static WebDriver driver;
    protected static String title;
    @BeforeEach
    public  void  propertySet(){
        /*
        Set your chromedriver path here,please
         */
        System.setProperty("webdriver.chrome.driver","Your Path");
        driver=new ChromeDriver();
    }
    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
