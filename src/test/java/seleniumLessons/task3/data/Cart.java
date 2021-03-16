package seleniumLessons.task3.data;

public class Cart 
{
    private int numberOfItemsToAdd;

    public static Builder newEntity() 
    {
        return new Cart().new Builder();
    }

    public int getNumberOfItemsToAdd() 
    {
        return numberOfItemsToAdd;
    }

    public class Builder 
    {
        public Builder withItemsToAdd(int numberOfItemsToAdd)
        {
            Cart.this.numberOfItemsToAdd = numberOfItemsToAdd;
            return this;
        }

        public Cart build()
        {
            return Cart.this;
        }
    }
}