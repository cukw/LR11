package com.selenium.tests.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    private static final String BASE_URL = "https://the-internet.herokuapp.com";
    private static final int TIMEOUT_SECONDS = 10;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        // ✅ HEADLESS MODE - браузер работает в фоне!
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");              // БЕЗ видимого окна
        options.addArguments("--disable-gpu");           // Отключи GPU
        options.addArguments("--window-size=1920,1080"); // Размер экрана
        options.addArguments("--no-sandbox");            // Linux совместимость
        options.addArguments("--disable-dev-shm-usage"); // Предотврати крахи
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
