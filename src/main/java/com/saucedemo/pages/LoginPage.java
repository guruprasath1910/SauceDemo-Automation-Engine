package com.saucedemo.pages;
import com.microsoft.playwright.Page;
public class LoginPage
{
    private Page page;
    private final String usernameInput = "#user-name";
    private final String passwordInput = "#password";
    private final String loginButton = "#login-button";
    private final String errorMessage = "h3[data-test='error']";
    private final String menuButton = "#react-burger-menu-btn";
    private final String logoutLink = "#logout_sidebar_link";

    public LoginPage(Page page)
    {
        this.page = page;
    }

    public void navigateTo()
    {
        page.navigate("https://www.saucedemo.com/");
    }

    public void login(String username, String password)
    {
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }

    public String getErrorMessageText()
    {
        return page.textContent(errorMessage);
    }

    public void logout()
    {
        page.click(menuButton);
        page.click(logoutLink);
    }
}
