import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Order {
    private ArrayList<String> orders;
    private HashMap<String, String> menu;
    private String currMealOrder;
    private String currIngredientOrder;
    private Rectangle receiptBox;
    private BufferedImage receiptImage;
    private String imageFileName;
    private int points;
    public Order() {
        menu = new HashMap<>();
        orders = new ArrayList<>();
        makeMenu(new File("meal/meals"));
        generateOrder();
        imageFileName = "images/receipt.png";
        receiptImage = readImage();
        receiptBox = new Rectangle(1500,120,300,230);
        points = 0;
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
            menu.put(currMeal, fileData);
        }
    }
    public void generateOrder(){
        int num = (int) (Math.random() * orders.size());
        currMealOrder = orders.get(num);
        currIngredientOrder = menu.get(currMealOrder);
    }
    public boolean checkOrder(String userMeal){
        if (userMeal.equals(currIngredientOrder)){
            points++;
        }
        return userMeal.equals(currIngredientOrder);
    }
    public ArrayList<String> getOrderText(){
        ArrayList <String> text = new ArrayList<>();
        text.add("Current Order: " + currMealOrder);
        String temp = currIngredientOrder;
        while(temp.contains(",")){
            int commaNum = temp.indexOf(",");
            String ingredientName = temp.substring(1,commaNum);
            text.add(ingredientName);
            temp = temp.substring(commaNum+1);
        }
        text.add(temp.substring(1,temp.indexOf("]")));
        return text;
    }
    public BufferedImage readImage() {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(imageFileName));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public Rectangle getReceiptBox() {
        return receiptBox;
    }

    public void setReceiptBox(Rectangle receiptBox) {
        this.receiptBox = receiptBox;
    }

    public BufferedImage getReceiptImage() {
        return receiptImage;
    }

    public void setReceiptImage(BufferedImage receiptImage) {
        this.receiptImage = receiptImage;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

