import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Button {
    private Rectangle buttonHit;
    private BufferedImage buttonImage;
    private String imageFileName;
    private boolean pressed;
    public Button(String screenName){
        pressed = false;
        switch (screenName){
            case "Kitchen":
                imageFileName = "kitchenButton";
                buttonHit = new Rectangle(100,100,200,200);
                break;
            case "ChangingRoom":
                imageFileName = "changingButton";
                buttonHit = new Rectangle(100,100,400,400);
                break;
        }
    }
}
