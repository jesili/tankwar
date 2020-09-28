package decorator;

import com.company.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(super.go.x, super.go.y, super.go.getWidth(), super.go.getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
