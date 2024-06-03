import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {
    private BufferedImage screenImage;
    private BufferedImage plateImage;
    private BufferedImage doorknobImage;
    private Rectangle knobBox;
    private String imageFileName;
    public Screen(boolean room){
        if(room) {
            imageFileName = "images/kitchen.jpg";
        }
        else{
            imageFileName = "images/dining_room.jpg";
        }
        screenImage = readImage();
        imageFileName = "images/plate.png";
        plateImage = readImage();
        imageFileName = "images/doorknob.png";
        doorknobImage = readImage();
        knobBox = new Rectangle(50,700,doorknobImage.getWidth()-100,doorknobImage.getHeight()-100);
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
    public void changeImage(boolean kitchen){
        if(kitchen){
            imageFileName = "images/dining_room.jpg";
            screenImage = readImage();
        }
        if(!kitchen){
            imageFileName = "images/dining_room.jpg";
            screenImage = readImage();
        }
    }

    public BufferedImage getScreenImage() {
        return screenImage;
    }

    public BufferedImage getPlateImage() {
        return plateImage;
    }

    public void setScreenImage(BufferedImage screenImage) {
        this.screenImage = screenImage;
    }

    public void setPlateImage(BufferedImage plateImage) {
        this.plateImage = plateImage;
    }

    public BufferedImage getDoorknobImage() {
        return doorknobImage;
    }

    public void setDoorknob(BufferedImage doorknob) {
        this.doorknobImage = doorknob;
    }

    public Rectangle getKnobBox() {
        return knobBox;
    }

    public void setKnobBox(Rectangle knobBox) {
        this.knobBox = knobBox;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setDoorknobImage(BufferedImage doorknobImage) {
        this.doorknobImage = doorknobImage;
    }
}
