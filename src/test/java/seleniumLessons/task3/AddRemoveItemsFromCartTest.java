package seleniumLessons.task3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import seleniumLessons.task3.data.Cart;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AddRemoveItemsFromCartTest extends CommonTest {

    @ParameterizedTest
    @MethodSource("itemsProvider")
    void addRemoveItemsFromCartTes(Cart cart)
    {
        app.openMainPage();

        addItems(cart);
                
        removeItems(cart); 
    }

    private void removeItems(Cart cart)
    {
        for (int productCout = 1; productCout <= cart.getNumberOfItemsToAdd(); productCout++)
            app.removeItemFromCart();     

        assertThat("There should be no items in cart" , app.getItemsInCart(), is(0));
    }

    private void addItems(Cart cart)
    {
        for (int productCout = 1; productCout <= cart.getNumberOfItemsToAdd(); productCout++)
            app.addItemToCart(productCout);      

        assertThat("There should be " + cart.getNumberOfItemsToAdd() + " items in cart" , app.getItemsInCart(), is(cart.getNumberOfItemsToAdd()));
    }
    
    

}
