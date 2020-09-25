package com.company;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(com.company.Tank t) {
        int bx = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Direc[] direcs = Direc.values();
        for (Direc direc : direcs){
            new Bullet(bx, by, direc, t.group, t.tf);
        }
    }
}
