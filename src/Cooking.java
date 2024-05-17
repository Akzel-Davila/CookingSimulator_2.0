import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Cooking {
    private Rectangle[] foodPlacement;
    private Ingredients food;
    private String [] placedName;
    private boolean [] placed;
    private ArrayList<String> orders;
    private HashMap<String[], String> menu;
    private BufferedImage currMealImage ;
    private Rectangle currMealRec;

    public Cooking(Ingredients food)  {
        this.food = food;
        menu = new HashMap<>();
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
            menu.put(ingredients,currMeal);
        }
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
    }
    public void updateCollision(){
        for (int i = 0; i<food.getIngredientBoxes().length; i++){
            if(placed[0] && !foodPlacement[0].intersects(food.getIngredientBoxes()[i])){
                if(placedName[0].equals(food.getIngredientList()[i])) {
                    placed[0] = false;
                    placedName[0] = null;
                }
            }
            if(placed[1] && !foodPlacement[1].intersects(food.getIngredientBoxes()[i])){
                if(placedName[1].equals(food.getIngredientList()[i])) {
                    placed[1] = false;
                    placedName[1] = null;
                }
            }
            if(placed[2] && !foodPlacement[2].intersects(food.getIngredientBoxes()[i])){
                if(placedName[2].equals(food.getIngredientList()[i])) {
                    placed[2] = false;
                    placedName[2] = null;
                }
            }
            if(placed[3] && !foodPlacement[3].intersects(food.getIngredientBoxes()[i])){
                if(placedName[3].equals(food.getIngredientList()[i])) {
                    placed[3] = false;
                    placedName[3] = null;
                }
            }
        }
    }
    public void placeIngredient(){
        if(placed[0]){
            int index = food.getIndex(placedName[0]);
            food.updateIngredient(index, (int)foodPlacement[0].getX()+150,(int) foodPlacement[0].getY()+150);
        }
        if(placed[1]){
            int index = food.getIndex(placedName[1]);
            food.updateIngredient(index, (int)foodPlacement[1].getX() +150,(int) foodPlacement[1].getY()+150);
        }
        if(placed[2]){
            int index = food.getIndex(placedName[2]);
            food.updateIngredient(index, (int)foodPlacement[2].getX()+150,(int) foodPlacement[2].getY()+150);
        }
        if(placed[3]){
            int index = food.getIndex(placedName[3]);
            food.updateIngredient(index, (int)foodPlacement[3].getX()+150,(int) foodPlacement[3].getY()+150);
        }
    }
    public String combineIngredient(){
        System.out.println(menu.keySet());
        System.out.println(placedName);
        return menu.getOrDefault(placedName, " ");
    }
    public Rectangle[] getFoodPlacement() {
        return foodPlacement;
    }

    public String[] getPlacedName() {
        return placedName;
    }
}
