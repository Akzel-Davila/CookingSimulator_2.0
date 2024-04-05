import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ingredients {
    private String imageFileName;
    private BufferedImage image;
    private BufferedImage draggedImage;
    private String draggedImageFileName;
    private boolean dragged;
    private Rectangle ingredientBox;
    public Ingredients(String ingredientName){
        this.imageFileName = "images/player_" + ingredientName + ".jpg";
        this.image = readImage();
        this.ingredientBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
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
}
