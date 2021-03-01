package SeleniumTrainings;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleTest {
    
    WebDriver driver;
    
    @BeforeEach
    void setUp()
    {
        //ChromeOptions options = new ChromeOptions();
        //options.setCapability(null, false);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    
    @AfterEach
    void tearDown()
    {
     driver.quit();   
    }
    
    @Test 
    public void CheckSeleniumStringPresent() 
    {        
        driver.get(" http://158.101.173.161/");
        driver.findElement(By.name("q")).sendKeys("Selenium" + Keys.ENTER);
        Assertions.assertTrue(driver.findElement(By.cssSelector("h3")).getText().toLowerCase().contains("selenium"), "not found");
    }
}
