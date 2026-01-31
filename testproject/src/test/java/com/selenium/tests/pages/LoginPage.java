package com.selenium.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model for Login page
 * https://the-internet.herokuapp.com/login
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    
    private By successMessage = By.xpath("//h4[@class='subheader']");
    
    private By errorMessage = By.xpath("//div[@class='flash error']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToLogin() {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }

    /**
     * Helper метод: ждёт элемент и игнорирует stale element exceptions
     */
    private WebElement waitForElement(By locator) {
        return wait.ignoring(StaleElementReferenceException.class)
                   .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void enterUsername(String username) {
        WebElement element = waitForElement(usernameField);
        element.clear();
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = waitForElement(passwordField);
        element.clear();
        element.sendKeys(password);
    }

    public void clickLoginButton() {
        waitForElement(loginButton).click();
        
        // Ждём пока страница загрузится после логина
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getSuccessMessage() {
        try {
            return waitForElement(successMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return waitForElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
