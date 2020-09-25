package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameModel {
    Tank myTank = new Tank(200, 400, Direc.DOWN, Group.GOOD, this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));
        for (int i = 0; i < initTankCount; i++){
            tanks.add(new Tank(50 + i*80, 200, Direc.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌方坦克的数量：" + tanks.size(), 10, 80);
        g.setColor(c);

        myTank.paint(g);
        //size每次会重新计算，所以不会发生越界问题
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++)
                bullets.get(i).collideWith(tanks.get(j));
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
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

    public Tank getMainTank() {
        return myTank;
    }
}
