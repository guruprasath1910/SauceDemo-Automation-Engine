package com.saucedemo.pages;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage
{
    private Page page;
    private final String sortDropdown = "[data-test='product-sort-container']";
    private final String productNames = "[data-test='inventory-item-name']";
    private final String productPrices = "[data-test='inventory-item-price']";

    public ProductsPage(Page page)
    {
        this.page = page;
    }
    public void sortBy(String option)
    {
        page.selectOption(sortDropdown, option);
    }
    public List<String> getProductNames()
    {
        return page.locator(productNames).allTextContents();
    }
    public List<Double> getProductPrices()
    {
        return page.locator(productPrices).allTextContents()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());
    }

}
