package com.saucedemo.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage
{
    private Page page;
    private final String sortDropdown = "[data-test='product-sort-container']";
    private final String productNames = "[data-test='inventory-item-name']";
    private final String productPrices = "[data-test='inventory-item-price']";
    private final String productItems = "[data-test='inventory-item']";
    private final String firstAddToCartButton = ".inventory_item button";
    private final String cartBadge = ".shopping_cart_badge";
    private final String menuButton = "#react-burger-menu-btn";
    private final String resetLink = "#reset_sidebar_link";
    private final String closeMenuButton = "#react-burger-cross-btn";

    public ProductsPage(Page page)
    {
        this.page = page;
    }
    public void resetAppState()
    {
        page.click(menuButton);
        page.click(resetLink);
        page.click(closeMenuButton);
        page.reload();
        page.waitForSelector(firstAddToCartButton);
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
    public int getProductCount()
    {
        return page.locator(productItems).count();
    }

    public String getFirstProductButtonText()
    {
        return page.locator(firstAddToCartButton).first().textContent();
    }

    public void addFirstProductToCart()
    {
        page.locator(firstAddToCartButton).first().click();
    }

    public void addProductToCartBySlug(String productSlug)
    {
        page.click("[data-test='add-to-cart-" + productSlug + "']");
    }

    public void addProductsToCart(String... productSlugs)
    {
        for (String slug : productSlugs)
        {
            addProductToCartBySlug(slug);
        }
    }

    public void removeProductFromCartBySlug(String productSlug)
    {
        page.click("[data-test='remove-" + productSlug + "']");
        page.locator(cartBadge).waitFor(new Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN));
    }

    public boolean isCartBadgeVisible()
    {
        return page.locator(cartBadge).isVisible();
    }

    public String getCartBadgeCount()
    {
        return page.locator(cartBadge).textContent();
    }

}
