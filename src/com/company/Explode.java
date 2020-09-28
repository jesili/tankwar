package com.company;


import java.awt.*;

public class Explode extends GameObject{
    public static int WIDTH = ResourceMGR.explodes[0].getWidth(), HEIGHT = ResourceMGR.explodes[0].getHeight();
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMGR.explodes[step++], x, y, null);
        if (step >= ResourceMGR.explodes.length)  GameModel.getInstance().remove(this);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
