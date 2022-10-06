import java.io.*;
import java.util.Scanner;

public class GalaApple implements Product {

    String name;
    String price;

    public GalaApple() {
        this.name = "GalaApple";
        this.price = "$0.00";
        System.out.println("Created Gala Apple");
    }

    @Override
    public void setPrice(String filePath){
        try{
            // opens files and scanner
            File file = new File(filePath);
            Scanner scan = new Scanner(file);
    
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();

                // split line into two parts, name and price
                String[] result = line.split(" ");

                // if name equals name of this product
                if (result[0].equals(name))
                {
                    // sets price and break out of loop
                    price = result[1];
                    System.out.println("The price of this " + name + " is now " + price);
                    break;
                }
            }
            // close scanner
            scan.close();
            
        }
        // if file is not found
        catch(FileNotFoundException e)
        {
            System.out.println("Price sheet is not found.");
        }
    }
}