package Main;

import entity.Player;
import maps.MapsManager;

import javax.swing.*;
import java.awt.*;

public class GameSetup extends JPanel implements Runnable{
    public final int characterSize = 50;
    final int screenWidth = characterSize * 10;
    final int screenHeight = characterSize * 10;

    KeyInput keys = new KeyInput();
    Thread gameThread;
    Player player = new Player(this, keys);

    final double fps = 25;
    final int speedMultiplier = 3;

    MapsManager mapM = new MapsManager(this);

    public GameSetup() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.addKeyListener(keys);
        this.setFocusable(true);
    }

    public void BeginGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + interval;
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingDrawTime = nextDrawTime - System.nanoTime();
                remainingDrawTime = remainingDrawTime/1000000;
                Thread.sleep((long)remainingDrawTime);
                nextDrawTime += interval * speedMultiplier;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {

        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D)g;
        mapM.draw(graph);
        player.draw(graph);
        graph.dispose();
    }
}
