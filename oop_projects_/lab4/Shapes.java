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

    @Override
    public void showRubber(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        float[] dash = { 4.0f, 4.0f };
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        
        int rx = Math.abs(x2 - x1);
        int ry = Math.abs(y2 - y1);
        g2d.drawOval(x1 - rx, y1 - ry, rx * 2, ry * 2);
    }
}

class LineOOShape extends LineShape {
    @Override
    public void show(Graphics2D g2d) {
        super.show(g2d);
        int r = 8;
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x1 - r/2, y1 - r/2, r, r);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x1 - r/2, y1 - r/2, r, r);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(x2 - r/2, y2 - r/2, r, r);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x2 - r/2, y2 - r/2, r, r);
    }
}

class CubeShape extends Shape {
    @Override
    public void show(Graphics2D g2d) {
        int w = Math.abs(x2 - x1);
        int h = Math.abs(y2 - y1);
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        
        int offset = Math.min(w, h) / 3;

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y + offset, w - offset, h - offset); 
        g2d.drawRect(x + offset, y, w - offset, h - offset); 
        
        g2d.drawLine(x, y + offset, x + offset, y);
        g2d.drawLine(x + w - offset, y + offset, x + w, y);
        g2d.drawLine(x, y + h, x + offset, y + h - offset);
        g2d.drawLine(x + w - offset, y + h, x + w, y + h - offset);
    }
}