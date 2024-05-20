import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Order {
    private ArrayList<String> orders;
    private HashMap<String, String> menu;
    private String currMealOrder;
    private String currIngredientOrder;
    private String userOrder;
    public Order() {
        menu = new HashMap<>();
        orders = new ArrayList<>();
    }
    public void makeMenu(File f) {
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Not working");
            System.exit(1);
        }
        String currMeal;
        while (s.hasNextLine()) {
            String fileData = "";
            String currentLine = s.nextLine();
            int colonNum = currentLine.indexOf(":");
            currMeal = currentLine.substring(0, colonNum);
            orders.add(currMeal);
            fileData += currentLine.substring(colonNum + 1);
            menu.put(fileData, currMeal);
        }
    }
    public void generateOrder(){
        int num = (int) (Math.random() * orders.size());
        currMealOrder = orders.get(num);
    }

    public boolean checkOrder(){
        return userOrder.equals(currIngredientOrder);
    }

}

