package com.company;

import cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();
    static {
        INSTANCE.init();
    }
    Tank myTank;
//    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    private List<GameObject> objects = new ArrayList<>();

    public static GameModel getInstance(){
        return INSTANCE;
    }

    private GameModel() { }

    private void init(){
        myTank = new Tank(200, 400, Direc.DOWN, Group.GOOD);
        int initTankCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));
        for (int i = 0; i < initTankCount; i++){
            new Tank(50 + i*80, 200, Direc.DOWN, Group.BAD);
        }
    }
    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }
    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
//        g.drawString("敌方坦克的数量：" + tanks.size(), 10, 80);
//        g.setColor(c);

        myTank.paint(g);
        //size每次会重新计算，所以不会发生越界问题
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        for (int i = 0; i < objects.size(); i++){
            for (int j = i + 1; j < objects.size(); j++){
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }

    }

    public Tank getMainTank() {
        return myTank;
    }
}
