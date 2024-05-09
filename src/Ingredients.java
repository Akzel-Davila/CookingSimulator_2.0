import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Ingredients {
    private String [] ingredientList;
    private String [] imageFileNames;
    private BufferedImage [] images;
    private int[] cordList;
    private BufferedImage draggedImage;
    private String draggedImageFileName;
    private boolean dragged;
    private Rectangle[] ingredientBoxes;
    public Ingredients(String ingredientNames){
        dragged = false;
        int startX = 0;
        int startY = 0;
        ingredientList = ingredientNames.split(",");
        cordList = new int[ingredientList.length * 2];
        images = new BufferedImage[ingredientList.length];
        imageFileNames = new String[ingredientList.length];
        ingredientBoxes = new Rectangle[ingredientList.length];
        for (int h = 0; h < cordList.length; h+=2){
            cordList[h] = startX;
            cordList[h+1] = startY;
            startX += 10;
            startY += 10;
        }
        for (int i = 0; i<ingredientList.length; i++){
            imageFileNames[i] = "images/ingredient_" + ingredientList[i] + ".jpg";
            images[i] = readImage(i);
        }
        for (int j = 0; j<ingredientList.length; j++){
            int counter = 0;
            ingredientBoxes[j] = new Rectangle(cordList[counter], cordList[counter+1], images[j].getWidth(), images[j].getHeight());
        }
    }
    public void updateIngredient(int index, int x, int y){
        cordList[index*2] = x-150;
        cordList[index*2+1] = y-150;
        ingredientBoxes[index] = new Rectangle(cordList[index*2],cordList[index*2+1], images[index].getWidth(), images[index].getHeight());
    }
    public Rectangle[] getIngredientBoxes() {
        return ingredientBoxes;
    }

    public int[] getCordList() {
        return cordList;
    }

    public void setCordList(int[] cordList) {
        this.cordList = cordList;
    }

    public String[] getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(String[] ingredientList) {
        this.ingredientList = ingredientList;
    }
    public BufferedImage getImage(int i) {
        return images[i];
    }

    public int getIndex(String name){
        for (int i = 0; i< ingredientList.length; i++){
            if(ingredientList[i].equals(name)){
                return i;
            }
        }
        return -1;
    }


    public BufferedImage readImage(int i) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(imageFileNames[i]));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

}