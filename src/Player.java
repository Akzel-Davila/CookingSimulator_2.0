import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private String imageFileName;
    private BufferedImage image;
    private Rectangle playerBox;
    String [] pos;
    private int xPos;
    private int yPos;

    public Player(int playerNum, int x, int y) {
        this.imageFileName = "images/player_"+ playerNum + ".jpg";
        this.image = readImage();
        this.playerBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
        pos = readSaves(new File("saves/save1"));
        System.out.println(Arrays.toString(pos));
        System.out.println(pos.length);
        if(pos.length > 1){
            this.xPos = Integer.parseInt(pos[0]);
            this.yPos = Integer.parseInt(pos[1]);
        }
        else{
            this.xPos = x;
            this.yPos = y;
        }
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

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
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
    public String[] readSaves(File f){
        Scanner s = null;
        try{
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("Not working");
            System.exit(1);
        }
        String fileData = "";
        while(s.hasNextLine()){
            String currentLine = s.nextLine();
            int colonNum = currentLine.indexOf(":") + 1;
            String line = currentLine.substring(colonNum);
            fileData += line + ",";
        }
        String[] fileArr = fileData.split(",");
        System.out.println(fileData);
        System.out.println(Arrays.toString(fileArr));
        return fileArr;
    }
    public void updateFiles(File f){
        String [] basicInfo = {"x:" + xPos, "y:" + yPos};
        PrintWriter writer = null;
        FileWriter edit;

        try{
            writer = new PrintWriter(f);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(1);
        }
        writer.flush();
        writer.close();
        try{
            edit = new FileWriter(f);
            for(String data : basicInfo){
                edit.write(data + "\n");
            }
            edit.close();
        }
        catch(IOException e){
            System.out.println("Not working");
            System.exit(1);
        }
    }

}

