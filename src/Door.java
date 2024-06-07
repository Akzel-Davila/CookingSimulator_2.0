import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Door {
    private Rectangle doorBox;
    private BufferedImage doorImage;
    private String imageFileName;
    public Door(){
        imageFileName = "images/door.jpg";
        doorImage = readImage();
        doorBox = new Rectangle(545, 40, doorImage.getWidth(), doorImage.getHeight());
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

    public Rectangle getDoorBox() {
        return doorBox;
    }

    public void setDoorBox(Rectangle doorBox) {
        this.doorBox = doorBox;
    }

    public BufferedImage getDoorImage() {
        return doorImage;
    }

    public void setDoorImage(BufferedImage doorImage) {
        this.doorImage = doorImage;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
