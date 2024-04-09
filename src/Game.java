import Controls.Player;
import UI.Display;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.util.logging.Handler;


public class Game implements Runnable {
    private Thread thread;
    private  Player player;
    private final Display display;
    private boolean running,graphicsReady = false;
    public Graphics graphics;
    private Handler handler;
    private final int width,height;
    public Game(String title,int width,int height){
        this.width = width;
        this.height = height;
        this.display = new Display(title,width,height);
    }
    private void init(){
        display.createDisplay();
        player = new Player();
        display.getCanvas().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });
        display.getCanvas().setFocusable(true); // Make the canvas focusable
        display.getCanvas().requestFocusInWindow();
    }
    private void render(){
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bs.getDrawGraphics();
        graphics.clearRect(0,0,width,height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,width,height);
        graphics.drawImage(player.getImage(), player.getX(), player.getY(), display.getCanvas());
        if (!graphicsReady)
        {
            graphicsReady = true;
            display.show();
        }
        graphics.dispose();
        bs.show();
    }
    @Override
    public void run() {
        init();
        while (running){
            render();
            player.move();
            try {
                Thread.sleep(10); // Add a small delay to avoid excessive CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;
        running = false;
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
