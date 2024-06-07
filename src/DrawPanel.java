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
    private boolean kitchen;
    private Cooking c;
    private Button b;
    private Order o;
    private Screen s;
    private Door d;
    private Customer customer;
    private boolean mealDrawn;
    private boolean drawCustomerAgain;
    private boolean dragging;
    private int savedIndex;
    private boolean displayText;
    private String text;
    private int savedCustIndex;

    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        setFocusable(true);
        p = new Player(1, 50 ,10);
        ingredients = new Ingredients("lettuce,tomato,cheese,bread,flour,meat,fish,seaweed");
        c = new Cooking(ingredients);
        b = new Button();
        o = new Order();
        d = new Door();
        customer = new Customer(o,"freddy,lebron,palmer,jonesy,papa,spiderman,christian");
        kitchen = true;
        s = new Screen(true);
        mealDrawn = false;
        dragging = false;
        drawCustomerAgain = true;
        displayText = false;
        text = "";
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(kitchen){
            g.setColor(Color.WHITE);
            g.drawImage(s.getScreenImage(),0,0,null );
            g.drawImage(s.getPlateImage(), 1100,600,null);

            //Draw receipt and current order
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
                g.drawImage(c.getPlaceFrame(),(int) rec.getX(), (int) rec.getY(),null );
            }

            //draw the ingredients
            for (int i = 0; i<ingredients.getIngredientBoxes().length; i++){
                //g.drawRect(ingredients.getCordList()[i*2]+50, ingredients.getCordList()[i*2+1]+30, 0, 0);
                g.drawImage(ingredients.getImage(i), ingredients.getCordList()[i*2], ingredients.getCordList()[i*2+1], null);
                g.drawImage(ingredients.getImage(i), ingredients.getPermanentCordList()[i*2], ingredients.getPermanentCordList()[i*2+1], null);
            }

            //draw button to make meal
            g.drawImage(b.getButtonImage(),(int)b.getButtonHit().getX(), (int) b.getButtonHit().getY(),(int) b.getButtonHit().getWidth(), (int)b.getButtonHit().getHeight(),null);

            //Ingredients are checked for dragging and collision with frames
            c.checkCollision();
            if(dragging){
                Point p = getPointerInfo().getLocation();
                ingredients.updateIngredient(savedIndex,(int)p.getX()+90, (int) p.getY()+90);
                c.updateCollision();
            }
            if(mealDrawn){
                g.drawImage(c.getCurrMealImage(), (int)c.getCurrMealRec().getX(), (int) c.getCurrMealRec().getY(), null);
                if(o.checkOrder(c.getCurrUserMeal())){
                    c.setCurrUserMeal("");
                    o.generateOrder();
                    System.out.println(o.getPoints());
                }
            }
            g.drawImage(s.getDoorknobImage(),(int)s.getKnobBox().getX(),(int)s.getKnobBox().getY(),null);
            drawCustomerAgain = true;
        }


        if(!kitchen){
            if(d.getDoorBox().intersects(p.getPlayerBox())){
                kitchen = true;
                s.changeImage(true);
                p.setXPos(d.getDoorBox().x-100);
                p.setYPos(d.getDoorBox().y+400);
            }
            customer.updateNumDraw();
            customer.limitDraw();
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans", Font.BOLD, 25));
            g.drawImage(s.getScreenImage(),0,0,null);
            p.updateFiles((new File("saves/save1")));
            g.drawImage(p.getImage(), p.getXPos(), p.getYPos(), p.getImage().getWidth(), p.getImage().getHeight(), null);
            g.drawImage(d.getDoorImage(),545,40,null);
            if(customer.checkDraw() && customer.getNumDrawn() < 9 && drawCustomerAgain){
                for (int i = 0; i< customer.getNumDrawn();i++){
                    int index = customer.getRandomIndex();
                    customer.getUserIndexList().add(index);
                }
            }
            if(customer.getNumDrawn()>0){
                for (int index : customer.getUserIndexList()) {
                    g.drawImage(customer.getCustomerImages()[index], customer.getImageX(index), customer.getImageY(index), null);
                    if(customer.getCustomerBoxes()[index].intersects(p.getPlayerBox())) {
                        if(!displayText){
                            text = customer.getText();
                            savedCustIndex = index;
                            displayText = true;
                        }
                        g.drawImage(customer.getBubbleImage(), customer.getBubbleX(index), customer.getBubbleY(index), null);
                        g.drawString(text, customer.getBubbleX(index)+20, customer.getBubbleY(index)+135);
                    }
                }
                if(!customer.getCustomerBoxes()[savedCustIndex].intersects(p.getPlayerBox())){
                    displayText = false;
                }
            }
            drawCustomerAgain = false;

        }

        // is picture being dragged?
        // if it is, move the picture relative to the mouse movement
    }
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
                s.changeImage(!kitchen);
                kitchen = false;
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
    public void keyReleased(KeyEvent e) {

    }

}