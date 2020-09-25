package tankabstractfactory;

import com.company.ResourceMGR;
import com.company.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {
    public static int WIDTH = ResourceMGR.explodes[0].getWidth(), HEIGHT = ResourceMGR.explodes[0].getHeight();
    private int x, y;
    TankFrame tf = null;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    @Override
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 10*step, 10*step);
        step++;
        if (step >= 5)  tf.explodes.remove(this);
        g.setColor(c);
    }
}
