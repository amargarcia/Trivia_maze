package maps;

import Main.GameSetup;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class MapsManager {

    GameSetup gs;
    Maps[] map;

    public MapsManager(GameSetup gs) {
        this.gs = gs;
        map = new Maps[1];
        getMap();
    }

    public void getMap() {
        try {
            map[0] = new Maps();
            map[0].image = ImageIO.read(new FileInputStream("/Users/amg/TriviaMaze/src/images/rooms/room_21.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(map[0].image, null, 0, 0);
    }
}
