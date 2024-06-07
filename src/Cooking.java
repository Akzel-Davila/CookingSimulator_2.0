import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Cooking {
    private Rectangle[] foodPlacement;
    private Ingredients food;
    private String [] placedName;
    private boolean [] placed;
    private HashMap<String, String> menu;
    private BufferedImage currMealImage ;
    private Rectangle currMealRec;
    private String currUserMeal;
    private BufferedImage placeFrame;

    public Cooking(Ingredients food){
        this.food = food;
        menu = new HashMap<>();
        makeMenu(new File("meal/meals"));
        foodPlacement = new Rectangle[4];
        placed = new boolean[4];
        placedName = new String[4];
        int add = 0;
        for (int i = 0; i< foodPlacement.length; i++){
            foodPlacement[i] = new Rectangle(800 ,300 + add ,100 ,100);
            add +=150;
        }
        placeFrame = readImage("images/itemFrame.jpg");
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
        while(s.hasNextLine()){
            String fileData = "";
            String currentLine = s.nextLine();
            int colonNum = currentLine.indexOf(":");
            currMeal = currentLine.substring(0,colonNum);
            fileData += currentLine.substring(colonNum+1);
            System.out.println(fileData);
            menu.put(fileData,currMeal);
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
        currUserMeal = Arrays.toString(placedName);
        System.out.println(currUserMeal);
        System.out.println(menu.get(currUserMeal));
        return menu.getOrDefault(currUserMeal, " ");
    }
    public void setNewMeal(String mealName){
        currMealImage = readImage("images/" + mealName + ".jpg");
        currMealRec = new Rectangle(1300,775, currMealImage.getWidth(),currMealImage.getHeight());
    }

    public BufferedImage getCurrMealImage() {
        return currMealImage;
    }
    public Rectangle getCurrMealRec() {
        return currMealRec;
    }

    public BufferedImage readImage(String imageFileName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(imageFileName));
            return image;
        }
        catch (IOException e) {
            System.out.println("Not working");
            return null;
        }
    }

    public String getCurrUserMeal() {
        return currUserMeal;
    }

    public void setCurrUserMeal(String currUserMeal) {
        this.currUserMeal = currUserMeal;
    }

    public Rectangle[] getFoodPlacement() {
        return foodPlacement;
    }

    public String[] getPlacedName() {
        return placedName;
    }
    public void setCurrMealImage(BufferedImage currMealImage) {
        this.currMealImage = currMealImage;
    }
    public void setCurrMealRec(Rectangle currMealRec) {
        this.currMealRec = currMealRec;
    }

    public BufferedImage getPlaceFrame() {
        return placeFrame;
    }

    public void setPlaceFrame(BufferedImage placeFrame) {
        this.placeFrame = placeFrame;
    }
}
