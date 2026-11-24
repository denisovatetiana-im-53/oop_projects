package lab4;

import java.awt.*;

abstract class Shape {
    protected int x1, y1, x2, y2;

    public void set(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public abstract void show(Graphics2D g2d);

    public void showRubber(Graphics2D g2d) {
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();

        g2d.setColor(Color.BLUE);

        float[] dash = {4.0f, 4.0f};

        g2d.setStroke(new BasicStroke(
            1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f,
            dash,
            0.0f
        ));

        show(g2d);

        g2d.setColor(oldColor);
        g2d.setStroke(oldStroke);
    }
}
