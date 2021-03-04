package SeleniumTrainings.task2;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Task 2
 */
public class Task2Test {

    static WebDriver driver;

    @BeforeAll
    static void setUp()
    { 
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown()
    {
        driver.quit();   
    }

    /**
     * Opens page, enters user name and password, tries to log in
     * Verifies log in is successful after 30 sec timeout
     * @throws InterruptedException 
     */
    @Test 
    void LogInToAdminPannelAndCheckTitle() throws InterruptedException 
    {        
        driver.get("http://158.101.173.161/admin/");
        WebElement logInForm = driver.findElement(By.id("box-login"));

        WebElement userName = logInForm.findElement(By.name("username"));
        userName.clear();
        userName.sendKeys("testadmin");

        WebElement userPass = logInForm.findElement(By.name("password"));
        userPass.clear();
        userPass.sendKeys("R8MRDAYT_test");

        driver.findElement(By.cssSelector("button.btn[type='submit']")).click();
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.id("widget-discussions")));
        
        Thread.sleep(1000);

        // let's check page title 
        Assertions.assertEquals("Dashboard | Selenium Test Store", driver.getTitle(), "Title does not match");
    }

    /**
     * Opens each menu item and check presence of header element
     */
    @Test 
    void OpenMenusAndCheckHeader() 
    {        
        //get menu items count
        int mainMenusCount = driver.findElements(By.cssSelector("#box-apps-menu li.app")).size();

        //cycle through all main menus
        for (int mainMenuIndex = 0; mainMenuIndex <= mainMenusCount - 1; mainMenuIndex++)
        {            
            //get current menu object by it's index
            WebElement menu =  driver.findElements(By.cssSelector("#box-apps-menu li.app")).get(mainMenuIndex); 

            //store menu name
            String menuItemName = menu.getAttribute("data-code"); 
            
            //open menu
            menu.click();
            
            //check that header object is present after main menu is opened
            this.checkHeaderForMenuItemAndAssert(driver, menuItemName);
         
            //in case DOM was changed after click, or index of item was changed we may end up having wrong object id's, so let's make sure this will not happen 
            //get object id again, this time by it's data-code value 
            menu = driver.findElement(By.cssSelector("#box-apps-menu li.app[data-code='" + menuItemName + "']"));
        
            //get sub menu count for current main menu
            int subMenusCount = driver.findElements(By.cssSelector("#box-apps-menu li.app[data-code='" + menuItemName + "'] li.doc")).size();

            //cycle through all sub menus
            for (int subMenuIndex = 0; subMenuIndex <= subMenusCount - 1; subMenuIndex++)
            {
                //get current sub menu object
                WebElement subMenu =  driver.findElements(By.cssSelector("#box-apps-menu li.app[data-code='" + menuItemName + "'] li.doc")).get(subMenuIndex); 

                //store menu name
                String subMenuItemName = subMenu.getAttribute("data-code"); 

                //open menu
                subMenu.click();
                
                //check that header object is present after main menu is opened
                this.checkHeaderForMenuItemAndAssert(driver, subMenuItemName);              
            }
        }
    }


    /**
     * Checks for presence of "div.panel-heading" element for given menu item and asserts in case not found
     * 
     * @param driver the Webdriver to use
     * @param menuItem menuItem that is opened
     */
    public void checkHeaderForMenuItemAndAssert(WebDriver driver, String menuItem)
    {
        System.out.println("Checking " + menuItem);
        Assertions.assertFalse(driver.findElements(By.cssSelector("div.panel-heading")).size() < 0, "Header object was not found for '" + menuItem + "' menu");
    }
}
