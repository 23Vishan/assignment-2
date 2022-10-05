// this is an interface, used to create factories for different product types
public interface GroceryProductFactory {
    void returnInventory();
    Product makeProduct(String productName);
}
