package com.company;

import decorator.RectDecorator;
import decorator.TailDecorator;

import java.awt.*;

import java.util.Random;

public class Tank extends GameObject{

    int oldx, oldy;

    Direc direc = Direc.DOWN;
    private static final int SPEED = 3;
    public static int WIDTH = ResourceMGR.goodTankU.getWidth(), HEIGHT = ResourceMGR.goodTankU.getHeight();

    Rectangle rect = new Rectangle();
    private Random random = new Random();

    private boolean moving = true;

    private boolean living = true;
    Group group = Group.BAD;
    public Rectangle getRect() {
        return rect;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Direc getDirec() {
        return direc;
    }

    public void setDirec(Direc direc) {
        this.direc = direc;
    }

    public Tank(int x, int y, Direc direc, Group group) {
        this.x = x;
        this.y = y;
        this.direc = direc;

        this.group = group;
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if (!living) GameModel.getInstance().remove(this);
        switch (direc){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMGR.goodTankL: ResourceMGR.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMGR.goodTankR: ResourceMGR.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMGR.goodTankU: ResourceMGR.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMGR.goodTankD: ResourceMGR.badTankD, x, y, null);
                break;
//            default:
//                break;
        }
        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move(){
        oldx = x;
        oldy = y;
        if (!moving) return;
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

        if (this.group == Group.BAD && random.nextInt(10) > 8) this.fire();
        if (this.group == Group.BAD && random.nextInt(10) > 7)
            randomDirec();
        boundsCheck();
        this.rect.x = this.x;
        this.rect.y = this.y;

    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_HEIGHT - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDirec() {
        this.direc = Direc.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        GameModel.getInstance().add(new RectDecorator(new TailDecorator(new Bullet(bx, by, this.direc, this.group))));
    }

    public void die() {
        this.living = false;
    }

    public void changeDirec(){

        Direc randDir = Direc.values()[random.nextInt(4)];
        while (this.direc == randDir){
            randDir = Direc.values()[random.nextInt(4)];
        }
        this.direc = randDir;
    }

    public void back(){
        x = oldx;
        y = oldy;
    }
}
