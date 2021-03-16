package seleniumLessons.task3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import seleniumLessons.task3.application.LiteCartApplication;
import seleniumLessons.task3.data.Cart;
import static com.codeborne.selenide.Selenide.open;
import java.util.stream.Stream;

public class CommonTest
{
    protected static LiteCartApplication app;

    @BeforeAll
    public static void startApplication() 
    {
        open();
        app = new LiteCartApplication();
    }

    @AfterAll
    public static void stopApplication()
    {
        app.closeApp(); 
    }

    static Stream<Cart> itemsProvider()
    {
        return Stream.of(Cart.newEntity().withItemsToAdd(1).build(),
                         Cart.newEntity().withItemsToAdd(2).build(),
                         Cart.newEntity().withItemsToAdd(3).build());
    }

}
