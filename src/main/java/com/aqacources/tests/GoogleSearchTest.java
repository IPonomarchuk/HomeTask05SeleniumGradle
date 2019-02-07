package com.aqacources.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GoogleSearchTest {

    // Instance of WebDriver
    private WebDriver driver;

    /**
     * Set up method
     */
    @Before
    public void setUp() {

        // If you want to disable infobars please use this code
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        // Initialize path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Initialize instance of ChromeDriver and add options
        driver = new ChromeDriver(options);

        // Set 5 seconds for implicitly wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Maximize window
        driver.manage().window().maximize();
    }

    /**
     * Open Google page, search, find link, open QASymphony site, check result and quit
     */
    @Test
    public void testGoogleSearchTest() {

        // Open Google
        driver.get("https://google.com.ua");

        // Send phrase in search input
        driver.findElement(By.name("q")).sendKeys("Automation testing");

        // Search
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[3]/center/input[1]")).submit();

        // Open https://www.qasymphony.com
        driver.findElement(By.partialLinkText("https://www.qasymphony.com/")).click();

        // Check title
        Assert.assertTrue(driver.getTitle().contains("QASymphony"));
    }

    /**
     * After method, quit driver
     */
    @After
    public void tearDown() {

        // Quit from Driver. close() just close window,
        // quit() - close all window an driver
        driver.quit();
    }

}
