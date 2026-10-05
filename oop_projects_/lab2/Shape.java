package lab2;

import java.awt.*;
public abstract class Shape {
    protected int x1, y1, x2, y2;


    public void set(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }


    public abstract void show(Graphics g);
}




class PointShape extends Shape {
    @Override
    public void show(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x1, y1, 2, 2);
    }
}




class LineShape extends Shape {
    @Override
    public void show(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
}




class RectShape extends Shape {
    @Override
    public void show(Graphics g) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);


        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);


        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}




class EllipseShape extends Shape {
    @Override
    public void show(Graphics g) {
        int rx = Math.abs(x2 - x1);
        int ry = Math.abs(y2 - y1);


        g.setColor(Color.BLACK);
        g.drawOval(x1 - rx, y1 - ry, rx * 2, ry * 2);
    }
}

