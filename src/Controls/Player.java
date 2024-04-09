package Controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {
    private static final int DEFAULT_SPEED = 20;
    private boolean isSpeedUp = false;
    private int currentSpeed;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    private final Set<Integer> keyPressed = new HashSet<>();

    public Player(){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/player.png")));
        image = imageIcon.getImage().getScaledInstance(153,160,Image.SCALE_SMOOTH);
        width = image.getWidth(null);
        height = image.getHeight(null);
        currentSpeed = DEFAULT_SPEED;
        visible = true;
        x = 40;
        y = 650;
    }
    public void move(){
        if(isSpeedUp){
            currentSpeed = DEFAULT_SPEED * 2;
        }else{
            currentSpeed = DEFAULT_SPEED;
        }
        if (keyPressed.contains(KeyEvent.VK_LEFT)) {
            x -= currentSpeed;
        }
        if (keyPressed.contains(KeyEvent.VK_RIGHT)) {
            x += currentSpeed;
        }
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SHIFT){
            isSpeedUp = true;
        }
        keyPressed.add(key);
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SHIFT){
            isSpeedUp = false;
        }
        keyPressed.remove(key);
    }
}
