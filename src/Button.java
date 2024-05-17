import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Button {
    private Rectangle buttonHit;
    private BufferedImage buttonImage;
    private String imageFileName;
    private boolean pressed;
    public Button(String screenName){
        pressed = false;
        switch (screenName) {
            case "kitchen" -> {
                imageFileName = "images/kitchenButton.jpg";
                buttonHit = new Rectangle(100, 100, 150, 113);
                System.out.println("1");
            }
            case "changingRoom" -> {
                imageFileName = "images/changingButton.jpg";
                buttonHit = new Rectangle(300, 100, 400, 400);
                System.out.println("2");
            }
        }
        buttonImage = readImage();
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

    public BufferedImage getButtonImage() {
        return buttonImage;
    }

    public void setButtonImage(BufferedImage buttonImage) {
        this.buttonImage = buttonImage;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public Rectangle getButtonHit() {
        return buttonHit;
    }

    public void setButtonHit(Rectangle buttonHit) {
        this.buttonHit = buttonHit;
    }
}
