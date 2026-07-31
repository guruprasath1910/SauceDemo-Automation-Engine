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


    @Test(priority = 3)
    public void testSortPriceLowToHigh()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(page);
        productsPage.sortBy("lohi");

        List<Double> prices = productsPage.getProductPrices();
        List<Double> sortedAscending = prices.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(prices, sortedAscending, "Prices are not sorted low to high correctly.");
    }

    @Test(priority = 4)
    public void testSortPriceHighToLow()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(page);
        productsPage.sortBy("hilo");

        List<Double> prices = productsPage.getProductPrices();
        List<Double> sortedDescending = prices.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        Assert.assertEquals(prices, sortedDescending, "Prices are not sorted high to low correctly.");
    }

    @Test(priority = 5)
    public void testProductCount()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(page);
        int count = productsPage.getProductCount();

        Assert.assertEquals(count, 6, "Expected 6 products on the inventory page, but found a different count.");
    }

    @Test(priority = 6)
    public void testAddItemToCart()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(page);
        productsPage.addFirstProductToCart();

        String buttonText = productsPage.getFirstProductButtonText();
        Assert.assertEquals(buttonText, "Remove", "Button text did not change to 'Remove' after adding to cart.");

        String badgeCount = productsPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, "1", "Cart badge did not show '1' after adding an item.");
    }

    @Test(priority = 7)
    public void testAddMultipleItemsToCart()
    {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(page);
        productsPage.addProductsToCart(3);

        String badgeCount = productsPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, "3", "Cart badge count did not match number of items added to cart.");
    }
}
