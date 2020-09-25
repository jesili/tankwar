package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 400, Direc.DOWN, Group.GOOD,this);
    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> tanks = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();

    static final int GAME_WIDTH = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("gameWidth"))),
            GAME_HEIGHT = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("gameHeight")));
    public TankFrame(){
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("tank war");
        setResizable(false);
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //利用双缓冲解决屏幕闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌方坦克的数量：" + tanks.size(), 10, 80);
        g.setColor(c);

        myTank.paint(g);
        //size每次会重新计算，所以不会发生越界问题
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++){
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++){
            for (int j = 0; j < tanks.size(); j++)
                bullets.get(i).collideWith(tanks.get(j));
        }
        for (int i = 0; i < explodes.size(); i++){
            explodes.get(i).paint(g);
//            explodes.remove(i);
        }
        //如下方式会出错，因为用内部迭代器遍历不能进行删除，而用普通遍历可以删除
//        for (Bullet b : bullets){
//            b.paint(g);
//        }
        //用迭代器的remove方法也可以实现，
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ){
//            Bullet b = it.next();
//            if (!b.live) it.remove();
//        }

    }

    //内部类，或取按键控制坦克方向
    public class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
//            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir(){
            myTank.setMoving(true);
            if (bL) myTank.setDirec(Direc.LEFT);
            if (bR) myTank.setDirec(Direc.RIGHT);
            if (bU) myTank.setDirec(Direc.UP);
            if (bD) myTank.setDirec(Direc.DOWN);
            if (!bL && !bR && !bU && !bD){
                myTank.setMoving(false);
            }

        }
    }
}
