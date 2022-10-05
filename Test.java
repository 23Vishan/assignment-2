public class Test {
    public static void main (String args[])
    {
        // make apple factory
        GroceryProductFactory appleFactory = FactoryGenerator.getFactory("Apple");

        // create apples
        Product apple1 = appleFactory.makeProduct("Fuji Apple");
        apple1.make();
        Product apple2 = appleFactory.makeProduct("Gala Apple");
        apple2.make();
        
        // Find a way to output inventory
        appleFactory.returnInventory();
    }
}