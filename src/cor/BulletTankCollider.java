package cor;

import com.company.Bullet;
import com.company.Explode;
import com.company.GameObject;
import com.company.Tank;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            if (b.getGroup() == t.getGroup()) return false;
            //TODO: 用一个rect来记录子弹位置
            if (b.getRect().intersects(t.getRect())){
                t.die();
                b.die();
                int ex = t.getX() + t.WIDTH/2 - Explode.WIDTH/2;
                int ey = t.getY() + t.HEIGHT/2 - Explode.HEIGHT/2;
                new Explode(ex, ey);
                return true;
            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
                return collide(o2, o1);
        }else {
            return false;
        }
        return false;
    }
}
