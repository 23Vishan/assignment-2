import java.io.*;
import java.util.Scanner;

public class FujiApple implements Product {
    
    String name;
    String price;

    public FujiApple() {
        this.name = "FujiApple";
        this.price = "$0.00";
    }

    @Override
    public void setPrice(String filePath){
            File file = new File(filePath);
            Scanner scan = new Scanner(file);
    
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();

                String[] result = line.split(" ");

                if (result[0].equals(name))
                {
                    price = result[1];
                    break;
                }
            }
            scan.close();
    }
}