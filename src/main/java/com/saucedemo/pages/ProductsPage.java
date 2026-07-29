package com.saucedemo.pages;
import com.microsoft.playwright.Page;
import java.util.List;
public class ProductsPage
{
    private Page page;
    private final String sortDropdown = "[data-test='product-sort-container']";
    private final String productNames = "[data-test='inventory-item-name']";

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
}
