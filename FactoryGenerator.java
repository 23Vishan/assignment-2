public class FactoryGenerator {
    public static GroceryProductFactory getFactory (String type)
    {
        if (type == "Apple")
        {
            return new AppleFactory();
        }
        else{
            return null;
        }
    }
}