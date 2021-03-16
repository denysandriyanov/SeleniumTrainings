package seleniumLessons.task3.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import seleniumLessons.task3.application.ApplicationContext;

public class ProductPage extends Page
{
    private static final String addToCartButton = "//button[@name='add_cart_product']";
    private static final String itemsAddedText = "div.badge.quantity";
    
    public ProductPage(ApplicationContext appContext) 
    {
        super(appContext);
    }

    public void addToCart() 
    {
        int itemsBeforeAdd = $(itemsAddedText).getText().isEmpty() ? 0 : Integer.valueOf($(itemsAddedText).getText());
        $x(addToCartButton).click();
        $(itemsAddedText).shouldHave(text(String.valueOf(itemsBeforeAdd + 1)));
    }   
}
