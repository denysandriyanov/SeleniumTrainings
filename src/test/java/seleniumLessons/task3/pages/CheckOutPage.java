package seleniumLessons.task3.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import seleniumLessons.task3.application.ApplicationContext;

public class CheckOutPage extends Page {

    private static final String removeBtn = "button[name='remove_cart_item']";
    private static final String itemsTable = ".cart.wrapper";

    public CheckOutPage(ApplicationContext appContext) 
    {
        super(appContext);
    }

    @Step
    public CheckOutPage openPage() 
    {
        if (!title().contains("Checkout"))
            open(appContext.getBaseUrl() + "/checkout");
        return this;
    }

    @Step
    public void removeItem()
    {
        this.openPage();
        $(removeBtn).click();     
        $(itemsTable).shouldBe(visible);         
    }
}
