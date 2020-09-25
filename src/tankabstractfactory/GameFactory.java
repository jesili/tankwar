package tankabstractfactory;
import com.company.Direc;
import com.company.Group;
import com.company.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Direc direc, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Direc direc, Group group, TankFrame tf);
}
