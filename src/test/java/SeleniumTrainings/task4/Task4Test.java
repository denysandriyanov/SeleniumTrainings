package SeleniumTrainings.task4;

import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import SeleniumTrainings.task4.listeners.Listener;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Task 2
 */
public class Task4Test {

    static EventFiringWebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void setUp()
    { 
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        wait = new WebDriverWait(driver,10);
        driver.register(new Listener());
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
     * @throws InterruptedException 
     */
    @Test 
    void newWindowlTest() throws InterruptedException 
    {      
        this.LogIn();
        this.openEachCountryForEditingAndVerifyNeweWindowIsOpened();
    }



    /**
     * Opens page, enters user name and password, tries to log in
     * Verifies log in is successful after 30 sec timeout
     * @throws InterruptedException 
     */
    void LogIn() throws InterruptedException 
    {        
        driver.get("http://158.101.173.161/admin/?app=countries&doc=countries");
        driver.findElement(By.id("box-login")).findElement(By.name("username")).sendKeys("testadmin");
        driver.findElement(By.id("box-login")).findElement(By.name("password")).sendKeys("R8MRDAYT_test");
        driver.findElement(By.cssSelector("button.btn[type='submit']")).click();


        new WebDriverWait(driver,30).until(ExpectedConditions.titleContains("Countries"));
        Thread.sleep(1000);
    }


    /**
     * Opens each country in table for editing and opens external link in separate window
     * Verifies new window was opened
     */
    void openEachCountryForEditingAndVerifyNeweWindowIsOpened() throws InterruptedException 
    {        
        int countriesQuantity = driver.findElements(By.cssSelector(".data-table tbody tr")).size();


        for (int i = 0; i < countriesQuantity; i++)
        {            
            driver.findElements(By.cssSelector(".data-table tbody tr")).get(i).findElement(By.cssSelector("a")).click();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("div.panel-heading"), "Edit Country"));

            String currentWidowId = driver.getWindowHandle();
            Set<String> windowIds = driver.getWindowHandles();
            driver.findElement(By.className("fa-external-link")).click();
            String newWindowId = wait.until(newWindowOpened(windowIds));
            Assertions.assertNotNull(newWindowId);

            driver.switchTo().window(newWindowId).close();             
            driver.switchTo().window(currentWidowId);
            driver.navigate().back();

            new WebDriverWait(driver,30).until(ExpectedConditions.titleContains("Countries"));

        }
    }


    /**
     * Waits for new window handle appears
     * 
     */
    private ExpectedCondition<String> newWindowOpened(Set<String> windows) 
    {
        return new ExpectedCondition<String>()
        {
            @Override
            public String apply(WebDriver input)
            {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }

        };
    }



}
