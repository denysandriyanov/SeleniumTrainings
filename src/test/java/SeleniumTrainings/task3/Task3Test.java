package SeleniumTrainings.task3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Task 2
 */
public class Task3Test {

    static WebDriver driver;
    static WebDriverWait wait;

    static final int ITEMS_TO_ADD = 3; 

    @BeforeAll
    static void setUp()
    { 
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().window().maximize();
    }

    @AfterAll
    static void tearDown()
    {
        driver.quit();   
    }

    /**
     * Adds Items to cart
     * Removes all items from 
     * Verifies no items in cart left after operation
     */
    @Test 
    void CartRemovalTest() 
    {      
        driver.get("http://158.101.173.161/");
        
        this.addItemsToCartAndCheckOut();
        this.removeAllItemsFromCart();
            
        Assertions.assertEquals("", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.badge.quantity"))).getText(), "There should be no items in cart after removal");
    }


    /**
     * Adds Items to cart
     */
    private void addItemsToCartAndCheckOut()
    {       
        for (int productCout = 1; productCout <= ITEMS_TO_ADD; productCout++)
        {
            driver.findElements(By.cssSelector("#box-popular-products .product-column")).get(productCout).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='add_cart_product']"))).click();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("div.badge.quantity"), String.valueOf(productCout)));
            driver.navigate().back();  
        }
        driver.findElement(By.cssSelector("div.badge.quantity")).click();
    }

    /**
     * Removes all items from cart
     */
    private void removeAllItemsFromCart()
    {
        while (driver.findElements(By.cssSelector("a.btn-default")).size() == 0)
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='remove_cart_item']"))).click();
            wait.until(ExpectedConditions.attributeToBe(By.cssSelector(".cart.wrapper"), "opacity", "1"));           
        }
        
        driver.findElement(By.cssSelector("a.btn-default")).click();
    }
}
