import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.security.Key;
import java.util.ArrayList;
import java.awt.Font;
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
        g.drawRect(50,50,100,100);
        g.drawImage(ingredients.getImage(0), 50,50,null);
    }

    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();
        if (e.getButton() == 1){
            if (p.getPlayerBox().contains(clicked)){
                System.out.println("ahhhh");
            }
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseDragged(MouseEvent e) {
        Point clicked = e.getPoint();
        for (Rectangle hitBox: ingredients.getIngredientBoxes()){
            if (hitBox.contains(clicked)){
                g.drawRect(50,50,100,100);
                g.drawImage(ingredients.getImage(0), 50,50,null);
            }
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