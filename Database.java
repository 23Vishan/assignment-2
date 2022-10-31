import java.util.HashMap;

public class Database{

    public String findPrice(String input)
    {
        // Used to imititate the file which contains data
        HashMap<String, String> file = new HashMap<String, String>();
        file.put("Apple", "$1");
        file.put("Banana", "$2");
        file.put("Strawberry", "$3");
        file.put("Mango", "$4");
        
        //Searches through hasemap for input and outputs price
        for (String i : file.keySet()) {
            if (input.equals(i))
            return file.get(input);
          }
        return "Product Not In Database";
    }
}