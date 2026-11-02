package lab4;

import java.awt.*;

public class MyEditor {
    private static final int MAX_SHAPES = 110;
    private final Shape[] pshape = new Shape[MAX_SHAPES];
    private int shapeCount = 0;

    private Shape currentPrototype = null; 

    public void start(Shape prototype) {
        this.currentPrototype = prototype;
    }

    public void addShape(int x1, int y1, int x2, int y2) {
        if (currentPrototype != null && shapeCount < MAX_SHAPES) {
            Shape newShape = cloneShape(currentPrototype);
            if (newShape != null) {
                newShape.set(x1, y1, x2, y2);
                pshape[shapeCount++] = newShape;
            }
        }
    }

    private Shape cloneShape(Shape proto) {
        if (proto instanceof PointShape) return new PointShape();
        if (proto instanceof LineOOShape) return new LineOOShape();
        if (proto instanceof LineShape) return new LineShape();
        if (proto instanceof RectShape) return new RectShape();
        if (proto instanceof EllipseShape) return new EllipseShape();
        if (proto instanceof CubeShape) return new CubeShape();
        return null;
    }

    public void paint(Graphics2D g2d) {
        for (int i = 0; i < shapeCount; i++) {
            if (pshape[i] != null) {
                pshape[i].show(g2d);
            }
        }
    }

    public Shape getCurrentPrototype() {
        return currentPrototype;
    }
}