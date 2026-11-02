package lab5;

import java.awt.*;

public abstract class Shape {
    protected int x1, y1, x2, y2;
    protected boolean isSelected = false; 

    public void set(int x1, int y1, int x2, int y2) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
    }

    public void setSelected(boolean selected) { this.isSelected = selected; }
    public boolean isSelected() { return isSelected; }

    public abstract void show(Graphics2D g2d);
    public abstract String getName();

    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }
}

class PointShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        g2d.setColor(isSelected ? Color.RED : Color.BLACK); 
        g2d.fillRect(x1, y1, isSelected ? 5 : 3, isSelected ? 5 : 3);
    }
    @Override
    public String getName() { return "Точка"; }
}

class LineShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        g2d.setColor(isSelected ? Color.RED : Color.BLACK);
        g2d.setStroke(new BasicStroke(isSelected ? 3.0f : 1.0f));
        g2d.drawLine(x1, y1, x2, y2);
    }
    @Override
    public String getName() { return "Лінія"; }
}

class RectShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x2 - x1);
        int h = Math.abs(y2 - y1);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, w, h); 
        
        g2d.setColor(isSelected ? Color.RED : Color.BLACK); 
        g2d.setStroke(new BasicStroke(isSelected ? 3.0f : 1.0f));
        g2d.drawRect(x, y, w, h);
    }
    @Override
    public String getName() { return "Прямокутник"; }
}

class EllipseShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        int rx = Math.abs(x2 - x1);
        int ry = Math.abs(y2 - y1);

        g2d.setColor(isSelected ? Color.RED : Color.BLACK); 
        g2d.setStroke(new BasicStroke(isSelected ? 3.0f : 1.0f));
        g2d.drawOval(x1 - rx, y1 - ry, rx * 2, ry * 2); 
    }
    @Override
    public String getName() { return "Еліпс"; }
}
