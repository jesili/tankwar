package com.company;


import java.awt.*;

public class Explode {
    public static int WIDTH = ResourceMGR.explodes[0].getWidth(), HEIGHT = ResourceMGR.explodes[0].getHeight();
    private int x, y;
    GameModel gm = null;
    private int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMGR.explodes[step++], x, y, null);
        if (step >= ResourceMGR.explodes.length)  gm.explodes.remove(this);
    }
}
