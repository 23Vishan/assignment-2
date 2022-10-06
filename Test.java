public class Test {
    public static void main (String args[])
    {
        // make apple factory
        GroceryProductFactory appleFactory = FactoryGenerator.getFactory("Apple");

        // create apples
        Product apple1 = appleFactory.makeProduct("Fuji Apple");
        Product apple2 = appleFactory.makeProduct("Gala Apple");
        Product apple3 = appleFactory.makeProduct("Banana");

        // creates berry factory
        GroceryProductFactory berryFactory = FactoryGenerator.getFactory("Berry");

        // creates berries
        Product berry1 = berryFactory.makeProduct("Blueberry");
        Product berry2 = berryFactory.makeProduct("Strawberry");

        // outputs inventory
        appleFactory.returnInventory();
        
        // sets price based on the given price sheet
        apple1.setPrice("C://Users//visha//Desktop//Assignment//PriceSheet.txt");
        apple2.setPrice("C://Users//visha//Desktop//Assignment//PriceSheet.txt");
        apple2.setPrice("C://Users//visha//Desktop//Assignment//PriceSheet2.txt");
        apple2.setPrice("C://Users//visha//Desktop//Assignment//PriceSheet3.txt");

        berry1.setPrice("C://Users//visha//Desktop//Assignment//PriceSheet.txt");
        berry2.setPrice("C://Users//visha//Desktop//Assignment//PriceSheet.txt");

        // outputs inventory
        berryFactory.returnInventory();
    }
}