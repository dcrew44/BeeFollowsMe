package com.example.beefollowsme;

public class Bee {
    public int currentX;
    public int currentY;
    public int targetX;
    public int targetY;
    public int easeInto;
    public final int YOFFSET = 200;
    public final int XOFFSET = 100;
    public Bee(int x, int y, int tX, int tY, int ease){
        currentX = x;
        currentY = y;
        targetX = tX;
        targetY = tY;
        easeInto = ease;
    }

    public void setNewTarget(int x, int y){
        targetX = x-XOFFSET;
        targetY = y-YOFFSET;
    }

    public void move() {
        int distanceX = targetX - currentX;
        int distanceY = targetY - currentY;
        currentX += distanceX / easeInto;
        currentY += distanceY / easeInto;
    }

}
