package com.company;


import java.awt.*;

public class Explode {
    public static int WIDTH = ResourceMGR.explodes[0].getWidth(), HEIGHT = ResourceMGR.explodes[0].getHeight();
    private int x, y;
    TankFrame tf = null;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMGR.explodes[step++], x, y, null);
        if (step >= ResourceMGR.explodes.length)  tf.explodes.remove(this);
    }
}
