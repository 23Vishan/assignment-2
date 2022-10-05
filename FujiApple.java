import java.io.*;
import java.util.Scanner;

public class FujiApple implements Product {
    
    String name;
    String price;

    public FujiApple() {
        this.name = "FujiApple";
        this.price = "$0.00";
        System.out.println("Created Fuji Apple");
    }
}