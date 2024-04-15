import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        for (int j = 0; j<cordList.length; j+=2){
            int counter = 0;
            ingredientBoxes[counter] = new Rectangle(cordList[j], cordList[j+1], images[counter].getWidth(), images[counter].getHeight());
        }
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

    public Rectangle[] getIngredientBoxes() {
        return ingredientBoxes;
    }

    public void setIngredientBox(Rectangle[] ingredientBoxes) {
        this.ingredientBoxes = ingredientBoxes;
    }
}