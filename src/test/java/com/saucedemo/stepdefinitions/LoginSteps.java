package com.saucedemo.stepdefinitions;

import com.microsoft.playwright.*;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginSteps {
    Playwright playwright;
    Browser browser;
    Page page;
    LoginPage loginPage;

    @Given("I am on the SauceDemo login page")
    public void i_am_on_login_page() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
        loginPage = new LoginPage(page);
        loginPage.navigateTo();
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected() {
        Assert.assertTrue(page.url().contains("inventory.html"));
        browser.close();
        playwright.close();
    }

    @Then("I should see an error message {string}")
    public void i_should_see_error(String expectedError) {
        String actual = loginPage.getErrorMessageText();
        Assert.assertTrue(actual.contains(expectedError));
        browser.close();
        playwright.close();
    }
}