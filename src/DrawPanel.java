import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.io.File;

import static java.awt.MouseInfo.getPointerInfo;

class DrawPanel extends JPanel implements MouseListener,KeyListener, MouseMotionListener {
    Player p;
    Ingredients ingredients;
    boolean dragging = false;
    int savedIndex;
    boolean screen1 = true;
    Cooking c;
    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        setFocusable(true);
        p = new Player(1, 50 ,10);
        ingredients = new Ingredients("lettuce,tomato");
        c = new Cooking(ingredients);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(screen1){
            g.drawRect(p.getXPos(), p.getYPos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100);
            g.drawImage(p.getImage(), p.getXPos(), p.getYPos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100, null);
            p.updateFiles((new File("saves/save1")));
            for (int i = 0; i<ingredients.getIngredientBoxes().length; i++){
                g.drawRect(ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], ingredients.getImage(i).getWidth(), ingredients.getImage(i).getHeight());
                g.drawImage(ingredients.getImage(i), ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], null);
            }
            for(Rectangle rec: c.getFoodPlacement()){
                g.drawRect((int) rec.getX(), (int) rec.getY(), (int) rec.getWidth(), (int) rec.getHeight());
            }
            Point p = getPointerInfo().getLocation();
            if(dragging){
                c.placeIngredient();
                ingredients.updateIngredient(savedIndex,(int)p.getX(), (int) p.getY());
                c.updateCollision();
            }
            c.checkCollision();
            c.updateCollision();
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
        if (dragging)
            dragging = false;
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
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