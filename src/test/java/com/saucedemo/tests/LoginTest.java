package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest
{
    @Test(priority = 1)
    public void testValidLogin()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(page.url().contains("inventory.html"), "Login failed: URL did not update!");
    }

    @Test(priority= 2)
    public void testInvalidLogin()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();

        loginPage.login("standard_user", "wrong_password");
        String error = loginPage.getErrorMessageText();
        Assert.assertTrue(error.contains("Username and password do not match"), "Expected error message not displayed.");

    }

    @Test(priority= 3)
    public void testLockedOutUserLogin()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();

        loginPage.login("locked_out_user", "secret_sauce");
        String error = loginPage.getErrorMessageText();
        Assert.assertTrue(error.contains("Sorry, this user has been locked out"), "Expected lockout error message not displayed.");
    }

    @Test(priority= 4)
    public void testLogout()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");
        loginPage.logout();
        Assert.assertTrue(page.url().equals("https://www.saucedemo.com/"),"Logout failed: user was not redirected to the login page.");
    }
}