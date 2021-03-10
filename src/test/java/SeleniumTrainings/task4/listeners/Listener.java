package SeleniumTrainings.task4.listeners;



import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import java.io.File;
import java.io.IOException;


/**
 * Listens to WebDriver event and prints log
 * 
 */
public class Listener extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("[Searching:] " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("["+ by + "] has been found");
    }


    @Override
    public void onException(Throwable throwable, WebDriver driver) {

        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile,
                    new File(System.currentTimeMillis() + "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[Shit Happened:] "+throwable.getMessage().split(":")[0]);
    }

}