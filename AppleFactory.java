public class AppleFactory implements GroceryProductFactory {

    // keeps track of inventory
    int invTotal = 0;
    int invFuji = 0;
    int invGala = 0;
    int invHoney = 0;

    // is there any other way to output inventory?
    public void returnInventory()
    {
        System.out.println("Inventory");
        System.out.println(invTotal + " apples");
        System.out.println(invFuji + " fuji apples");
        System.out.println(invGala + " gala apples");
    }

    @Override
    public Product makeProduct(String productName) {
        if (productName == "Fuji Apple")
        {
            invTotal++;
            invFuji++;
            return new FujiApple();
        }
        else if (productName == "Gala Apple")
        {
            invTotal++;
            invGala++;
            return new GalaApple();
        }
        else
        {
            return null;
        }
    }
}