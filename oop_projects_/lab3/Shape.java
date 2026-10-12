package lab3;

import java.awt.*;


public abstract class Shape {
    protected int x1, y1, x2, y2;

    public void set(int x1, int y1, int x2, int y2) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
    }

    public abstract void show(Graphics2D g2d);
}

class PointShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x1, y1, 2, 2);
    }
}


class LineShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, y1, x2, y2);
    }
}


class RectShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        int x = x1 - width;
        int y = y1 - height;

        g2d.setColor(Color.GRAY); 
        g2d.fillRect(x, y, width * 2, height * 2);
        
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width * 2, height * 2);
    }
}


class EllipseShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        g2d.setColor(Color.WHITE); 
        g2d.fillOval(x, y, width, height);
        
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, width, height);
    }
}