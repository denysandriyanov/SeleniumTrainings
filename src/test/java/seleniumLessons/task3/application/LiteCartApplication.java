package seleniumLessons.task3.application;

import io.qameta.allure.Step;
import ru.qatools.properties.PropertyLoader;
import seleniumLessons.task3.Config;
import seleniumLessons.task3.pages.CheckOutPage;
import seleniumLessons.task3.pages.MainPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class LiteCartApplication
{

    private final MainPage mainPage;
    private final CheckOutPage checkOutPage;

    public LiteCartApplication()
    {
        Config config = PropertyLoader.newInstance().populate(Config.class);
        ApplicationContext appContext = new ApplicationContext();
        appContext.setBaseUrl(config.getBaseUrl());
        
        mainPage = new MainPage(appContext);
        checkOutPage = new CheckOutPage(appContext);
    }

    @Step
    public void openMainPage() 
    {
        mainPage.openPage();
    }
    
    @Step
    public void removeItemFromCart()
    {
        checkOutPage.removeItem();
    }

    @Step
    public void addItemToCart(int productIndex) 
    {
        mainPage.selectProduct(productIndex).addToCart();
    }

    @Step
    public int getItemsInCart() 
    {
        return mainPage.getNumberOfItemsInCart();
    }


    @Step
    public void closeApp() 
    {
        closeWebDriver();
    }
 
}
