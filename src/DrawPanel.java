import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.security.Key;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Arrays;

class DrawPanel extends JPanel implements MouseListener,KeyListener, MouseMotionListener {
    Player p;
    Ingredients ingredients;
    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        setFocusable(true);
        p = new Player(1, 50 ,10);
        ingredients = new Ingredients("lettuce");
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(p.getX_pos(), p.getY_pos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100);
        g.drawImage(p.getImage(), p.getX_pos(), p.getY_pos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100, null);
        for (int i = 0; i<ingredients.getIngredientBoxes().length; i++){
            g.drawRect(ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], ingredients.getImage(i).getWidth(), ingredients.getImage(i).getHeight());
            g.drawImage(ingredients.getImage(i), ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], null);
        }
    }

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();
        int saved_index = 0;
        boolean dragging = false;
        int startX = clicked.x;
        int startY = clicked.y;
        for (int i = 0; i < ingredients.getIngredientBoxes().length; i++){
            if (!dragging &&ingredients.getIngredientBoxes()[i].contains(clicked)){
                dragging = true;
                saved_index = i;
            }
        }
        if (dragging){
            int endX = getMousePosition().x - startX;
            int endY = getMousePosition().y - startY;
            ingredients.updateIngredient(saved_index, endX*10, endY*10);
            System.out.println(Arrays.toString(ingredients.getCordList()));
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseDragged(MouseEvent e) {
        Point clicked = e.getPoint();
        int saved_index = 0;
        boolean dragging = ingredients.isDragged();
        int startX = clicked.x;
        int startY = clicked.y;
        for (int i = 0; i < ingredients.getIngredientBoxes().length; i++){
            if (!dragging &&ingredients.getIngredientBoxes()[i].contains(clicked)){
                dragging = true;
                saved_index = i;
            }
        }
        if (dragging){
            int endX = getMousePosition().x - startX;
            int endY = getMousePosition().y - startY;
            ingredients.updateIngredient(saved_index, endX*10, endY*10);
            System.out.println(Arrays.toString(ingredients.getCordList()));
        }
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
            p.setY_pos(p.getY_pos()-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            p.setX_pos(p.getX_pos()-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            p.setX_pos(p.getX_pos()+10);
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            p.setY_pos(p.getY_pos()+10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}