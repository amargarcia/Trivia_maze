package entity;

import Main.GameSetup;
import Main.KeyInput;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {
    GameSetup setup;
    KeyInput keys;

    public Player (GameSetup setup, KeyInput keys) {
        this.setup = setup;
        this.keys = keys;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        entityLocationX = 340;
        entityLocationY = 360;
        entitySpeed = 20;
        direction = "neutral";
        directionMemory = "left";
        keys.neutral = true;
    }

    public void getPlayerImage() {

        try {
            // up sprites
            this.up1 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_up1.png"));
            this.up2 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_up2.png"));
            this.neutralUp = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_up_neutral.png"));
            // down sprites
            this.down1 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_down1.png"));
            this.down2 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_down2.png"));
            this.neutralDown = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_down_neutral.png"));
            // right sprites
            this.right1 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_right1.png"));
            this.right2 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_right2.png"));
            this.neutralRight = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_right_neutral.png"));
            // left sprites
            this.left1 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_left1.png"));
            this.left2 = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_left2.png"));
            this.neutralLeft = ImageIO.read(new FileInputStream("../TriviaMaze/src/images/players/player_left_neutral.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keys.up || keys.down || keys.right || keys.left || !keys.neutral) {
            if (keys.up) {
                direction = "up";
                entityLocationY -= entitySpeed;
            } else if (keys.right) {
                direction = "right";
                entityLocationX += entitySpeed;
            } else if (keys.left) {
                direction = "left";
                entityLocationX -= entitySpeed;
            } else if (keys.down) {
                direction = "down";
                entityLocationY += entitySpeed;
            }
            spriteCounter++;
            if (spriteCounter > 2) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (!keys.up && !keys.down && !keys.right && !keys.left && keys.neutral) {
            switch (direction) {
                case "down" -> directionMemory = "down";
                case "up" -> directionMemory = "up";
                case "left" -> directionMemory = "left";
                case "right" -> directionMemory = "right";
            }
            direction = "";
        }
    }
    public void draw(Graphics2D graph) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            default -> {
                image = switch (directionMemory) {
                    case "up" -> neutralUp;
                    case "down" -> neutralDown;
                    case "left" -> neutralLeft;
                    case "right" -> neutralRight;
                    default -> image;
                };
                direction = directionMemory;
            }
        }
        graph.drawImage(image, entityLocationX, entityLocationY, setup.characterSize, setup.characterSize, null);
    }
}
