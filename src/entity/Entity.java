package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int entityLocationX, entityLocationY;
    public int entitySpeed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
            neutralUp, neutralDown, neutralLeft, neutralRight;
    public String direction, directionMemory;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}

