package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsTest extends BaseTest
{
    @Test(priority = 1)
    public void testSortProductsAZ()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(page);
        productsPage.sortBy("az");

        List<String> names = productsPage.getProductNames();
        List<String> sortedNames = names.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(names, sortedNames, "Products are not sorted A-Z correctly.");
    }

    @Test(priority = 2)
    public void testSortProductsZA()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(page);
        productsPage.sortBy("za");

        List<String> names = productsPage.getProductNames();
        List<String> sortedDescending = names.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        Assert.assertEquals(names, sortedDescending, "Products are not sorted Z-A correctly.");
    }
}