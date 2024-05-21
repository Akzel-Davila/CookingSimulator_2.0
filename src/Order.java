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
    public Order() {
        menu = new HashMap<>();
        orders = new ArrayList<>();
        makeMenu(new File("meal/meals"));
        generateOrder();
        imageFileName = "images/receipt.jpg";
        receiptImage = readImage();
        receiptBox = new Rectangle(500,500,300,230);
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
        return userMeal.equals(currIngredientOrder);
    }
    public String getOrderText(){
        String text = "Current Order:" + currMealOrder + "\n";
        String temp = currIngredientOrder;
        for (int i = 0; i< currIngredientOrder.length(); i++){
            int commaNum = currIngredientOrder.indexOf(",");
            String ingredientName = temp.substring(1,commaNum);
            temp = temp.substring(commaNum);
            text = text + "\n" + ingredientName;
        }
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
}
