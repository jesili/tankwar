package com.company;

import java.util.Objects;

public class Main {

    public static void main(String[] args) throws InterruptedException{
	TankFrame tf = new TankFrame();
	int initTankCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));
	for (int i = 0; i < initTankCount; i++){
		tf.tanks.add(new Tank(50 + i*80, 200, Direc.DOWN, Group.BAD, tf));
	}
	while (true){
	    Thread.sleep(50);
	    tf.repaint();
    }
    }
}
