import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.security.Key;
import java.util.ArrayList;
import java.awt.Font;
class DrawPanel extends JPanel implements MouseListener, KeyListener {
    Player p;
    public DrawPanel() {
        this.addMouseListener(this);
        setFocusable(true);
        this.addKeyListener(this);
        p = new Player(1, 50 ,10);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(p.getX_pos(), p.getY_pos(), p.getImage().getWidth(), p.getImage().getHeight());
        g.drawImage(p.getImage(), p.getX_pos(), p.getY_pos(), p.getImage().getWidth()-100, p.getImage().getHeight()-100, null);
        System.out.println(p.getX_pos());
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("huuh");
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            p.setX_pos(p.getX_pos()-10);
            p.setY_pos(p.getY_pos()-10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}