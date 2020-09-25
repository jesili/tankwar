package tankabstractfactory;

import com.company.Direc;
import com.company.Group;
import com.company.TankFrame;

public class RectFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Direc direc, Group group, TankFrame tf) {
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direc direc, Group group, TankFrame tf) {
        return null;
    }
}
