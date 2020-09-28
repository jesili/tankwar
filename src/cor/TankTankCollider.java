package cor;

import com.company.*;

public class TankTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank)o2;

            //TODO: 用一个rect来记录子弹位置
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
            if (t1.getRect().intersects(t2.getRect())){
                if (t1.getGroup() == t2.getGroup()){
//                    t1.changeDirec();
//                    t2.changeDirec();
                    t1.back();
                    t2.back();
                }
//                int ex = t.getX() + t.WIDTH/2 - Explode.WIDTH/2;
//                int ey = t.getY() + t.HEIGHT/2 - Explode.HEIGHT/2;
//                Explode e = new Explode(ex, ey, t.gm);
//                t.gm.add(e);
            }
        }else {
            return false;
        }
        return false;
    }
}
