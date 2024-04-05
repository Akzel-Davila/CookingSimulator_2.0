import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Player {
    private String imageFileName;
    private BufferedImage image;
    private Rectangle playerBox;
    private boolean highlight;
    private int x_pos;
    private int y_pos;

    public Player(int playerNum, int x, int y) {
        this.imageFileName = "images/player_"+playerNum + ".jpg";
        this.image = readImage();
        this.playerBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        this.highlight = false;
        this.x_pos = x;
        this.y_pos = y;
    }

    public Rectangle getPlayerBox() {
        return playerBox;
    }

    public String getImageFileName() {
        return imageFileName;
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setPlayerBox(Rectangle playerBox) {
        this.playerBox = playerBox;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
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

