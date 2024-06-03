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
    private boolean kitchen;
    private Cooking c;
    private Button b;
    private Order o;
    private Screen s;
    private boolean mealDrawn;

    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        setFocusable(true);
        p = new Player(1, 50 ,10);
        ingredients = new Ingredients("lettuce,tomato,cheese,bread,rice,meat");
        c = new Cooking(ingredients);
        b = new Button("kitchen");
        o = new Order();
        kitchen = true;
        s = new Screen(true);
        mealDrawn = false;
        dragging = false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(kitchen){
            g.setColor(Color.WHITE);
            g.drawImage(s.getScreenImage(),0,0,null );
            g.drawImage(s.getPlateImage(), 1100,600,null);

            //Draw receipt and current order
            g.drawRect((int)o.getReceiptBox().getX(),(int)o.getReceiptBox().getY(),(int)o.getReceiptBox().getWidth()-150,(int)o.getReceiptBox().getHeight()-150);
            g.drawImage(o.getReceiptImage(),(int) o.getReceiptBox().getX(),(int)o.getReceiptBox().getY(),null);
            g.setFont(new Font("Comic Sans", Font.BOLD, 22));
            ArrayList<String> text = o.getOrderText();
            int add = 290;
            for (String s : text) {
                g.drawString(s, 1575, add);
                add+=20;
            }


            //draw the boxes where ingredients are placed
            for(Rectangle rec: c.getFoodPlacement()){
                g.drawRect((int) rec.getX(), (int) rec.getY(), (int) rec.getWidth(), (int) rec.getHeight());
                g.drawImage(c.getPlaceFrame(),(int) rec.getX(), (int) rec.getY(),null );
            }

            //draw the ingredients
            for (int i = 0; i<ingredients.getIngredientBoxes().length; i++){
                g.drawRect(ingredients.getCordList()[i*2]+50, ingredients.getCordList()[i*2+1]+30, 0, 0);
                g.drawImage(ingredients.getImage(i), ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], null);
                g.drawImage(ingredients.getImage(i), ingredients.getPermanentCordList()[i*2], ingredients.getPermanentCordList()[i*2+1], null);
            }
            g.drawRect((int)b.getButtonHit().getX(), (int) b.getButtonHit().getY(), (int) b.getButtonHit().getWidth(), (int)b.getButtonHit().getHeight());
            g.drawImage(b.getButtonImage(),(int)b.getButtonHit().getX(), (int) b.getButtonHit().getY(),(int) b.getButtonHit().getWidth(), (int)b.getButtonHit().getHeight(),null);
            c.checkCollision();
            if(dragging){
                Point p = getPointerInfo().getLocation();
                ingredients.updateIngredient(savedIndex,(int)p.getX()+90, (int) p.getY()+90);
                c.updateCollision();
            }
            if(mealDrawn){
                g.drawRect((int)c.getCurrMealRec().getX(), (int) c.getCurrMealRec().getY(), (int) c.getCurrMealRec().getWidth(), (int) c.getCurrMealRec().getHeight());
                g.drawImage(c.getCurrMealImage(), (int)c.getCurrMealRec().getX(), (int) c.getCurrMealRec().getY(), null);
                if(o.checkOrder(c.getCurrUserMeal())){
                    o.checkOrder(c.getCurrUserMeal());
                    c.setCurrUserMeal("");
                    o.generateOrder();
                }
            }
            g.drawRect((int)s.getKnobBox().getX(),(int)s.getKnobBox().getY(), (int) s.getKnobBox().getWidth(),(int) s.getKnobBox().getHeight());
            g.drawImage(s.getDoorknobImage(),(int)s.getKnobBox().getX(),(int)s.getKnobBox().getY(),null);
        }



        if(!kitchen){
            g.drawImage(s.getScreenImage(),0,0,null );
            p.updateFiles((new File("saves/save1")));
            g.drawRect(p.getXPos(), p.getYPos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100);
            g.drawImage(p.getImage(), p.getXPos(), p.getYPos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100, null);
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
            if(s.getKnobBox().contains(clicked)){
                System.out.println("working");
                s.changeImage(!kitchen);
                kitchen = false;
            }
        }
        System.out.println(kitchen);

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
            else{
                mealDrawn = false;
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