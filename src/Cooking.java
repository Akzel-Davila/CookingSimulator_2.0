import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class Cooking {
    Rectangle[] foodPlacement;
    Ingredients food;
    String [] placed;
    ArrayList<String> orders;
    Hashtable<String, String[]> menu;
    public Cooking(Ingredients food)  {
        this.food = food;
        menu = new Hashtable<>();
        orders = new ArrayList<>();
        makeMenu(new File("meal/meals"));
        foodPlacement = new Rectangle[4];
        System.out.println(foodPlacement.length);
        int add = 0;
        for (int i = 0; i< foodPlacement.length; i++){
            foodPlacement[i] = new Rectangle(100,100 + add ,100 ,100);
            add +=100;
        }
    }
    public void makeMenu(File f){
        Scanner s = null;
        try{
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("Not working");
            System.exit(1);
        }
        String currMeal;
        String [] ingredients;
        while(s.hasNextLine()){
            String fileData = "";
            String currentLine = s.nextLine();
            int colonNum = currentLine.indexOf(":");
            currMeal = currentLine.substring(0,colonNum);
            orders.add(currMeal);
            fileData += currentLine.substring(colonNum+1);
            ingredients = fileData.split(",");
            menu.put(currMeal, ingredients);
            System.out.println(currentLine);
        }
    }

    public Rectangle[] getFoodPlacement() {
        return foodPlacement;
    }
}
