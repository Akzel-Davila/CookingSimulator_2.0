import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {
    private BufferedImage screenImage;
    private String imageFileName;
    public Screen(boolean room){
        if(room) {
            imageFileName = "images/kitchen.jpg";
        }
        else{
            imageFileName = "images/dining_room.jpg";
        }
        screenImage = readImage();
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

    public BufferedImage getScreenImage() {
        return screenImage;
    }
}
