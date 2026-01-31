package com.selenium.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.tests.base.baseTest;
import com.selenium.tests.pages.LoginPage;

/**
 * Test cases for Form Authentication
 * https://the-internet.herokuapp.com/login
 */
public class FormAuthenticationTest extends baseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver, wait);

        // Action
        loginPage.navigateToLogin();
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        // Verification
        String message = loginPage.getSuccessMessage();
        Assert.assertTrue(message.contains("Welcome to the Secure Area"),
        "Success message should contain 'Welcome to the Secure Area'");

        // Verify URL changed to /secure
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/secure"),
                "URL should contain '/secure' after successful login");
    }

    @Test(description = "Verify error message with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver, wait);

        // Action
        loginPage.navigateToLogin();
        loginPage.enterUsername("invaliduser");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLoginButton();

        // Verification
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid credentials");

        // Verify still on login page
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/login"),
                "Should remain on login page after failed login");
    }

    @Test(description = "Verify error message with empty username")
    public void testLoginWithEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver, wait);

        // Action
        loginPage.navigateToLogin();
        loginPage.enterUsername("");  // Empty username
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        // Verification
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when username is empty");
    }
}
