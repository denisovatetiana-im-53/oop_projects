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

class WireRectShape extends RectShape {
    @Override
    public void show(Graphics2D g2d) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x2 - x1);
        int h = Math.abs(y2 - y1);

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

class FilledEllipseShape extends EllipseShape {
    @Override
    public void show(Graphics2D g2d) {
        int rx = Math.abs(x2 - x1);
        int ry = Math.abs(y2 - y1);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x1 - rx, y1 - ry, rx * 2, ry * 2);
        super.show(g2d);
    }
}

class LineOOShape extends Shape {
    private LineShape line = new LineShape();
    private FilledEllipseShape circle1 = new FilledEllipseShape();
    private FilledEllipseShape circle2 = new FilledEllipseShape();

    @Override
    public void set(int x1, int y1, int x2, int y2) {
        super.set(x1, y1, x2, y2);
        line.set(x1, y1, x2, y2);
        int r = 5;
        circle1.set(x1, y1, x1 + r, y1 + r);
        circle2.set(x2, y2, x2 + r, y2 + r);
    }

    @Override
    public void show(Graphics2D g2d) {
        line.show(g2d);
        circle1.show(g2d);
        circle2.show(g2d);
    }

    @Override
    public void showRubber(Graphics2D g2d) {
        line.showRubber(g2d);
        circle1.showRubber(g2d);
        circle2.showRubber(g2d);
    }
}

class CubeShape extends Shape {
    private WireRectShape backRect = new WireRectShape();
    private WireRectShape frontRect = new WireRectShape();
    private LineShape line1 = new LineShape();
    private LineShape line2 = new LineShape();
    private LineShape line3 = new LineShape();
    private LineShape line4 = new LineShape();

    @Override
    public void set(int x1, int y1, int x2, int y2) {
        super.set(x1, y1, x2, y2);

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x2 - x1);
        int h = Math.abs(y2 - y1);

        int offsetX = w / 3;
        int offsetY = h / 3;

        backRect.set(x + offsetX, y, x + w, y + h - offsetY);

        frontRect.set(x, y + offsetY, x + w - offsetX, y + h);

        line1.set(x, y + offsetY, x + offsetX, y);
        line2.set(x + w - offsetX, y + offsetY, x + w, y);
        line3.set(x, y + h, x + offsetX, y + h - offsetY);
        line4.set(x + w - offsetX, y + h, x + w, y + h - offsetY);
    }

    @Override
    public void show(Graphics2D g2d) {
        backRect.show(g2d);
        frontRect.show(g2d);
        line1.show(g2d);
        line2.show(g2d);
        line3.show(g2d);
        line4.show(g2d);
    }

    @Override
    public void showRubber(Graphics2D g2d) {
        backRect.showRubber(g2d);
        frontRect.showRubber(g2d);
        line1.showRubber(g2d);
        line2.showRubber(g2d);
        line3.showRubber(g2d);
        line4.showRubber(g2d);
    }
}