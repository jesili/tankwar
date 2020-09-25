package tankabstractfactory;

import com.company.*;

public class DefaultFactory extends GameFactory{
    public BaseTank createTank(int x, int y, Direc direc, Group group, TankFrame tf) {
        return new Tank(x, y, direc, group, tf);
    }

    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    public BaseBullet createBullet(int x, int y, Direc direc, Group group, TankFrame tf) {
        return new Bullet(x, y, direc, group, tf);
    }
}
