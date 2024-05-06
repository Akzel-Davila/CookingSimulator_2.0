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
    String [] placedName;
    boolean [] placed;
    ArrayList<String> orders;
    Hashtable<String, String[]> menu;
    public Cooking(Ingredients food)  {
        this.food = food;
        menu = new Hashtable<>();
        orders = new ArrayList<>();
        makeMenu(new File("meal/meals"));
        foodPlacement = new Rectangle[4];
        placed = new boolean[4];
        placedName = new String[4];
        int add = 0;
        for (int i = 0; i< foodPlacement.length; i++){
            foodPlacement[i] = new Rectangle(400 ,100 + add ,100 ,100);
            add +=150;
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
        }
    }

    public Rectangle[] getFoodPlacement() {
        return foodPlacement;
    }
    public void checkCollision(){
        for (int i = 0; i<food.getIngredientBoxes().length; i++){
            if(foodPlacement[0].intersects(food.getIngredientBoxes()[i])){
                placed[0] = true;
                placedName[0] = food.getIngredientList()[i];
            }
            if(foodPlacement[1].intersects(food.getIngredientBoxes()[i])){
                placed[1] = true;
                placedName[1] = food.getIngredientList()[i];
            }
            if(foodPlacement[2].intersects(food.getIngredientBoxes()[i])){
                placed[2] = true;
                placedName[2] = food.getIngredientList()[i];
            }
            if(foodPlacement[3].intersects(food.getIngredientBoxes()[i])){
                placed[3] = true;
                placedName[3] = food.getIngredientList()[i];
            }
        }
        System.out.println(Arrays.toString(placedName));
        System.out.println(Arrays.toString(placed));
    }
    public void updateCollision(){
        for (int i = 0; i<food.getIngredientBoxes().length; i++){
            if(placed[0] && !foodPlacement[i].intersects(food.getIngredientBoxes()[i])){
                placed[0] = false;
                placedName[0] = null;
            }
            if(placed[1] && !foodPlacement[i].intersects(food.getIngredientBoxes()[i])){
                placed[1] = false;
                placedName[1] = null;
            }
            if(placed[2] && !foodPlacement[i].intersects(food.getIngredientBoxes()[i])){
                placed[2] = false;
                placedName[2] = null;
            }
            if(placed[3] && !foodPlacement[i].intersects(food.getIngredientBoxes()[i])){
                placed[3] = false;
                placedName[3] = null;
            }
        }
    }
}
