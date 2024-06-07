import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Customer {
    private Order o;
    private BufferedImage[] customerImages;
    private String[] imageFileNames;
    private final String bubbleName;
    private BufferedImage bubbleImage;
    private int numDrawn;
    private int customerNum;
    private int[] customerCords;
    ArrayList <Integer> userIndexList;
    private int customerCount;
    private Rectangle [] customerBoxes;

    public Customer(Order o, String names) {
        this.o = o;
        String[] customerNames = names.split(",");
        numDrawn = 0;
        customerNum = customerNames.length;
        customerCords = new int[customerNum*2];
        userIndexList = new ArrayList<>();
        customerImages = new BufferedImage[customerNum];
        imageFileNames = new String[customerNum];
        customerBoxes = new Rectangle[customerNum];
        customerCount = 0;
        for (int i = 0; i < imageFileNames.length; i++) {
            imageFileNames[i] = "customers/" + customerNames[i] + ".png";
            customerImages[i] = readImage(i);
        }
        for (int h = 0; h< customerCords.length;h+=2){
            int randX = getRandomX();
            int randY = getRandomY();
            customerCords[h] = randX;
            customerCords[h+1] = randY;
        }
        int counter = 0;
        for (int j = 0; j<customerNames.length; j++){
            customerBoxes[j] = new Rectangle(customerCords[counter], customerCords[counter+1], customerImages[j].getWidth(), customerImages[j].getHeight());
            counter+=2;
        }
        bubbleName = "images/bubble.png";
        bubbleImage = readImage();
    }

    public boolean checkDraw(){
        if(o.getPoints() >= 3 ){
            return true;
        }
        return false;
    }
    public String getText(){
        String text = "This ";
        String [] outcomes = {"STINKS", "Is Horrible", "Is Great", "RULES", "Is mid", "Sucks", "Is okay", "Smells", "Is Raw", "Is Perfect"};
        int randNum = (int)(Math.random() * o.getOrderHistory().size());
        int randStrNum = (int) (Math.random() * outcomes.length);
        text += o.getOrderHistory().get(randNum);
        text+= " " + outcomes[randStrNum];
        return text;
    }
    public void updateNumDraw(){
        numDrawn = o.getPoints()/3;
    }
    public int getRandomIndex(){
        return (int)(Math.random()*customerNum);
    }
    public int getImageX(int index){
        return customerCords[index*2];
    }
    public int getImageY(int index){
        return customerCords[index*2+1];
    }
    public int getBubbleX(int index){
        return getImageX(index)+10;
    }
    public int getBubbleY(int index){
        return getImageY(index) -200;
    }

    public int getRandomX(){
        return 100+(int)(Math.random()*1200);
    }
    public int getRandomY(){
        return 300+(int)(Math.random()*650);
    }
    public BufferedImage readImage(int i) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(imageFileNames[i]));
            return image;
        } catch (IOException e) {
            System.out.println("Not working");
            return null;
        }

    }
    public BufferedImage readImage() {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(bubbleName));
            return image;
        } catch (IOException e) {
            System.out.println("Not working");
            return null;
        }

    }
    public void addCustomerCount(){
        customerCount++;
    }

    public ArrayList<Integer> getUserIndexList() {
        return userIndexList;
    }
    public void limitDraw(){
        if(numDrawn>customerNum){
            numDrawn = customerNum;
        }
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }

    public BufferedImage[] getCustomerImages() {
        return customerImages;
    }

    public void setCustomerImages(BufferedImage[] customerImages) {
        this.customerImages = customerImages;
    }

    public String[] getImageFileNames() {
        return imageFileNames;
    }

    public void setImageFileNames(String[] imageFileNames) {
        this.imageFileNames = imageFileNames;
    }

    public int getNumDrawn() {
        return numDrawn;
    }

    public void setNumDrawn(int numDrawn) {
        this.numDrawn = numDrawn;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public int[] getCustomerCords() {
        return customerCords;
    }

    public void setCustomerCords(int[] customerCords) {
        this.customerCords = customerCords;
    }

    public String getBubbleName() {
        return bubbleName;
    }

    public BufferedImage getBubbleImage() {
        return bubbleImage;
    }

    public void setBubbleImage(BufferedImage bubbleImage) {
        this.bubbleImage = bubbleImage;
    }

    public Rectangle[] getCustomerBoxes() {
        return customerBoxes;
    }

    public void setCustomerBoxes(Rectangle[] customerBoxes) {
        this.customerBoxes = customerBoxes;
    }
}
