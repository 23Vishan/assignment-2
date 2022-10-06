public class BerryFactory implements GroceryProductFactory {

    // variables used to keep track of inventory
    int invTotal = 0;
    int invBlue = 0;
    int invStraw = 0;

    // returns inventory of this factory
    public void returnInventory()
    {
        System.out.println("-----");
        System.out.println("Inventory:");
        System.out.println(invTotal + " apples");
        System.out.println(invBlue + " blueberries");
        System.out.println(invStraw + " strawberries");
        System.out.println("-----");
    }

    @Override
    public Product makeProduct(String productName) {
        if (productName == "Blueberry")
        {
            invTotal++;
            invBlue++;
            return new Blueberry();
        }
        else if (productName == "Strawberry")
        {
            invTotal++;
            invStraw++;
            return new Strawberry();
        }
        else
        {
            System.out.println("This factory does not make the product '" + productName + "'");
            return null;
        }
    }
}