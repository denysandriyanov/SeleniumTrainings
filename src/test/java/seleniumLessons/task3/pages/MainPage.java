package seleniumLessons.task3.pages;

import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;
import seleniumLessons.task3.application.ApplicationContext;


public class MainPage extends Page 
{
    private static final String productSelectorBtn = "#box-popular-products .product-column";
    private static final String declineCookiesBtn = "decline_cookies";
    private static final String cartBtn = "div.badge.quantity";

    public MainPage(ApplicationContext appContext) 
    {
        super(appContext);      
    }

    public MainPage openPage()
    {
        if (!title().contains("Online Store"))
            open(appContext.getBaseUrl());
        
        if ($(By.name(declineCookiesBtn)).isDisplayed())
            $(By.name(declineCookiesBtn)).click();
        
        return this;
    }

    public ProductPage selectProduct(int productIndex) 
    {
        this.openPage();
        $$(productSelectorBtn).get(productIndex).click(); 
        return new ProductPage(appContext);
    }
    
    public int getNumberOfItemsInCart() 
    {
        this.openPage();
        return $(cartBtn).getText().isEmpty() ? 0 : Integer.valueOf($(cartBtn).getText());
    }
}
