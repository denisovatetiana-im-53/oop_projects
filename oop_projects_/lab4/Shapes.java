package lab4;

import java.awt.*;

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
    
    @Override
    public void showRubber(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        float[] dash = { 4.0f, 4.0f };
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        g2d.drawLine(x1, y1, x2, y2);
    }
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
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, w, h);
    }
}

class EllipseShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        int rx = Math.abs(x2 - x1);
        int ry = Math.abs(y2 - y1);
        
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x1 - rx, y1 - ry, rx * 2, ry * 2); 
    }
}

class LineOOShape extends Shape {
    private final LineShape line = new LineShape();
    private final EllipseShape circle1 = new EllipseShape();
    private final EllipseShape circle2 = new EllipseShape();

    @Override
    public void set(int x1, int y1, int x2, int y2) {
        super.set(x1, y1, x2, y2);
        line.set(x1, y1, x2, y2);
        int r = 8;
        circle1.set(x1, y1, x1 + r, y1 + r);
        circle2.set(x2, y2, x2 + r, y2 + r);
    }

    @Override
    public void show(Graphics2D g2d) {
        line.show(g2d);
        circle1.show(g2d);
        circle2.show(g2d);
    }
}

class CubeShape extends Shape {
    private final RectShape frontRect = new RectShape();
    private final RectShape backRect = new RectShape();
    private final LineShape l1 = new LineShape();
    private final LineShape l2 = new LineShape();
    private final LineShape l3 = new LineShape();
    private final LineShape l4 = new LineShape();

    @Override
    public void set(int x1, int y1, int x2, int y2) {
        super.set(x1, y1, x2, y2);
        int w = Math.abs(x2 - x1);
        int h = Math.abs(y2 - y1);
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int offset = Math.min(w, h) / 3;

        frontRect.set(x, y + offset, x + w - offset, y + h);
        backRect.set(x + offset, y, x + w, y + h - offset);

        l1.set(x, y + offset, x + offset, y);
        l2.set(x + w - offset, y + offset, x + w, y);
        l3.set(x, y + h, x + offset, y + h - offset);
        l4.set(x + w - offset, y + h, x + w, y + h - offset);
    }

    @Override
    public void show(Graphics2D g2d) {
        frontRect.show(g2d);
        backRect.show(g2d);
        l1.show(g2d);
        l2.show(g2d);
        l3.show(g2d);
        l4.show(g2d);
    }
}