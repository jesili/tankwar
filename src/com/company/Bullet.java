package com.company;

import tankabstractfactory.BaseBullet;

import java.awt.*;

public class Bullet extends BaseBullet {
    private static final int SPEED = 10;
    public static int WIDTH = ResourceMGR.bulletL.getWidth(), HEIGHT = ResourceMGR.bulletL.getHeight();
    Rectangle rect = new Rectangle();
    int x, y;
    private Direc direc;
    private boolean living = true;
    private TankFrame tf = null;
    private Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Direc direc, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.direc = direc;
        this.tf = tf;
        this.group = group;
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;
        tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!living){
            tf.bullets.remove(this);
        }
        switch (direc){
            case LEFT:
                g.drawImage(ResourceMGR.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMGR.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMGR.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMGR.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        switch (direc){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        this.rect.x = this.x;
        this.rect.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }


    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        //TODO: 用一个rect来记录子弹位置
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (this.rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int ex = tank.getX() + tank.WIDTH/2 - Explode.WIDTH/2;
            int ey = tank.getY() + tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(tf.gf.createExplode(ex, ey, tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
