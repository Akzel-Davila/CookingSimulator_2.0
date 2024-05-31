import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Customer {
    private Order o;
    private BufferedImage[] customerImages;
    private String[] imageFileNames;
    private int numDrawn;
    private int customerNum;

    public Customer(Order o, String names) {
        this.o = o;
        String[] customerNames = names.split(",");
        numDrawn = 0;
        customerNum = 8;
        customerImages = new BufferedImage[customerNum];
        imageFileNames = new String[customerNum];
        for (int i = 0; i < imageFileNames.length; i++) {
            imageFileNames[i] = "images/" + customerNames[i] + ".jpg";
            customerImages[i] = readImage(i);
        }
    }

    public boolean checkDraw(){
        if(o.getPoints() %3 == 0){
            numDrawn ++;
            return true;
        }
        return false;
    }
    public BufferedImage getRandomImage(){
        int randIdx = (int)(Math.random()*9);
        return customerImages[randIdx];
    }
    public int getRandomX(){
        return 400+(int)(Math.random()*300);
    }
    public int getRandomY(){
        return 400+(int)(Math.random()*300);
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
}
