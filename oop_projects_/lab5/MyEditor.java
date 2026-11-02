package lab5;

import java.awt.*;
import java.io.*;

public class MyEditor {
    private static MyEditor p_instance = null;

    private static final int MAX_SHAPES = 110; 
    private final Shape[] pshape = new Shape[MAX_SHAPES];
    private int shapeCount = 0;

    private Shape currentPrototype = null;
    private MyTable tableWindow = null;

    private MyEditor() {}

    public static synchronized MyEditor getInstance() {
        if (p_instance == null) {
            p_instance = new MyEditor();
        }
        return p_instance;
    }

    public void setTableWindow(MyTable tableWindow) {
        this.tableWindow = tableWindow;
    }

    public void start(Shape prototype) {
        this.currentPrototype = prototype;
    }

    public void addShape(int x1, int y1, int x2, int y2) {
        if (currentPrototype != null && shapeCount < MAX_SHAPES) {
            Shape newShape = cloneShape(currentPrototype);
            if (newShape != null) {
                newShape.set(x1, y1, x2, y2);
                pshape[shapeCount++] = newShape;

                saveToFile(newShape); 

                if (tableWindow != null) {
                    tableWindow.addRow(shapeCount - 1, newShape);
                }
            }
        }
    }

    public void selectShape(int index) {
        for (int i = 0; i < shapeCount; i++) {
            if (pshape[i] != null) {
                pshape[i].setSelected(i == index);
            }
        }
    }

    public void deleteShape(int index) {
        if (index >= 0 && index < shapeCount) {
            for (int i = index; i < shapeCount - 1; i++) {
                pshape[i] = pshape[i + 1];
            }
            pshape[shapeCount - 1] = null;
            shapeCount--;
            
            rewriteFile();
        }
    }

    private Shape cloneShape(Shape proto) {
        if (proto instanceof PointShape) return new PointShape();
        if (proto instanceof LineShape) return new LineShape();
        if (proto instanceof RectShape) return new RectShape();
        if (proto instanceof EllipseShape) return new EllipseShape();
        return null;
    }

    private void saveToFile(Shape s) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("shapes.txt", true))) {
            pw.println(s.getName() + "\t" + s.getX1() + "\t" + s.getY1() + "\t" + s.getX2() + "\t" + s.getY2());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("shapes.txt", false))) {
            for (int i = 0; i < shapeCount; i++) {
                if (pshape[i] != null) {
                    Shape s = pshape[i];
                    pw.println(s.getName() + "\t" + s.getX1() + "\t" + s.getY1() + "\t" + s.getX2() + "\t" + s.getY2());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void loadFromFile() {
        File file = new File("shapes.txt");
        if (!file.exists()) return;

        shapeCount = 0; 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null && shapeCount < MAX_SHAPES) {
                String[] parts = line.split("\t");
                if (parts.length == 5) {
                    String name = parts[0];
                    int x1 = Integer.parseInt(parts[1]);
                    int y1 = Integer.parseInt(parts[2]);
                    int x2 = Integer.parseInt(parts[3]);
                    int y2 = Integer.parseInt(parts[4]);

                    Shape s = null;
                    switch (name) {
                        case "Точка": s = new PointShape(); break;
                        case "Лінія": s = new LineShape(); break;
                        case "Прямокутник": s = new RectShape(); break;
                        case "Еліпс": s = new EllipseShape(); break;
                    }

                    if (s != null) {
                        s.set(x1, y1, x2, y2);
                        pshape[shapeCount++] = s;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Shape[] getShapes() { return pshape; }
    public int getShapeCount() { return shapeCount; }

    public void paint(Graphics2D g2d) {
        for (int i = 0; i < shapeCount; i++) {
            if (pshape[i] != null) {
                pshape[i].show(g2d);
            }
        }
    }
}