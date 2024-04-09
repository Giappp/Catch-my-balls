package UI;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width,height;
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
    }
    public void createDisplay(){
        Dimension dimension = new Dimension(width,height);

        frame = new JFrame(title);

        frame.setSize(dimension);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(dimension);
        canvas.setMaximumSize(dimension);
        canvas.setMinimumSize(dimension);
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }
    public void show(){
        frame.setVisible(true);
    }
    public JFrame getFrame()
    {
        return frame;
    }
    public Canvas getCanvas()
    {
        return canvas;
    }
    public void add(Component c){
        this.frame.add(c);
    }
}
