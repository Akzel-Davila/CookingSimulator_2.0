import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static java.awt.MouseInfo.getPointerInfo;

class DrawPanel extends JPanel implements MouseListener,KeyListener, MouseMotionListener {
    private Player p;
    private Ingredients ingredients;
    private boolean dragging;
    private int savedIndex;
    private boolean screen1;
    private Cooking c;
    private Button b;
    private Order o;
    private boolean mealDrawn;
    private boolean submitted;
    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        setFocusable(true);
        p = new Player(1, 50 ,10);
        ingredients = new Ingredients("lettuce,tomato,cheese,bread");
        c = new Cooking(ingredients);
        b = new Button("kitchen");
        o = new Order();
        mealDrawn = false;
        dragging = false;
        screen1 = true;
        submitted = false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(screen1){
            g.drawRect(p.getXPos(), p.getYPos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100);
            g.drawImage(p.getImage(), p.getXPos(), p.getYPos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100, null);
            p.updateFiles((new File("saves/save1")));


            //Draw receipt and current order
            g.drawRect((int)o.getReceiptBox().getX(),(int)o.getReceiptBox().getY(),(int)o.getReceiptBox().getWidth(),(int)o.getReceiptBox().getHeight());
            g.drawImage(o.getReceiptImage(),(int) o.getReceiptBox().getX(),(int)o.getReceiptBox().getY(),null);
            g.setFont(new Font("Comic Sans", Font.BOLD, 20));
            ArrayList<String> text = o.getOrderText();
            int add = 625;
            for (String s : text) {
                g.drawString(s, 550, add);
                add+=30;
            }

            //draw the ingredients
            for (int i = 0; i<ingredients.getIngredientBoxes().length; i++){
                g.drawRect(ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], ingredients.getImage(i).getWidth(), ingredients.getImage(i).getHeight());
                g.drawImage(ingredients.getImage(i), ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], null);
            }

            //draw the boxes where ingredients are placed
            for(Rectangle rec: c.getFoodPlacement()){
                g.drawRect((int) rec.getX(), (int) rec.getY(), (int) rec.getWidth(), (int) rec.getHeight());
            }
            g.drawRect((int)b.getButtonHit().getX(), (int) b.getButtonHit().getY(), (int) b.getButtonHit().getWidth(), (int)b.getButtonHit().getHeight());
            g.drawImage(b.getButtonImage(),(int)b.getButtonHit().getX(), (int) b.getButtonHit().getY(),null);
            Point p = getPointerInfo().getLocation();
            c.checkCollision();
            if(dragging){
                ingredients.updateIngredient(savedIndex,(int)p.getX(), (int) p.getY());
                c.updateCollision();
            }
            if(mealDrawn){
                g.drawRect((int)c.getCurrMealRec().getX(), (int) c.getCurrMealRec().getY(), (int) c.getCurrMealRec().getWidth(), (int) c.getCurrMealRec().getHeight());
                g.drawImage(c.getCurrMealImage(), (int)c.getCurrMealRec().getX(), (int) c.getCurrMealRec().getY(), null);
            }
        }

        // is picture being dragged?
        // if it is, move the picture relative to the mouse movement
    }

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();
        int button = e.getButton();
        if(button == MouseEvent.BUTTON1) {
            for (int i = 0; i < ingredients.getIngredientBoxes().length; i++) {
                if (ingredients.getIngredientBoxes()[i].contains(clicked)) {
                    dragging = true;
                    savedIndex = i;
                }
            }
        }

    }

    public void mouseReleased(MouseEvent e) {
        if (dragging) {
            dragging = false;
            c.placeIngredient();
        }
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) {
        Point clicked = e.getPoint();
        int button = e.getButton();
        if(b.getButtonHit().contains(clicked) && button==MouseEvent.BUTTON1){
            if(!c.combineIngredient().equals(" ")){
                mealDrawn = true;
                c.setNewMeal(c.combineIngredient());
            }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }


    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            p.setYPos(p.getYPos()-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            p.setXPos(p.getXPos()-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            p.setXPos(p.getXPos()+10);
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            p.setYPos(p.getYPos()+10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}