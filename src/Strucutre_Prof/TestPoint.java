package Strucutre_Prof;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TestPoint extends JFrame {

    private JPanel content;
    private JLabel sommet;

    public TestPoint() {
        this.setTitle("test");
        this.initComponent();
        this.initEventListener();
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponent() {
        content = new JPanel(null);
        sommet = new JLabel("S12");
//        sommet.setIcon(new ImageIcon(((ImageIcon) sommet.getIcon()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        sommet.setBounds(50, 150, 40, 40);
        content.add(sommet);
        this.add(content);
    }

    private void initEventListener() {
        Point p = new Point();
        this.sommet.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                p.x = e.getX();
                p.y = e.getY();
            }
        });
        this.sommet.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - p.x;
                int dy = e.getY() - p.y;
                sommet.setLocation(sommet.getX() + dx, sommet.getY() + dy);
            }
        });

    }
    public class PaintPanel extends JPanel {
        private final int pointLimit;

        private final List<Point> points;

        public PaintPanel() {
            this.points = new ArrayList<Point>();
            this.pointLimit = 6;

//            this.addMouseListener(new CirclePaintTest.PaintPanel.CircleMouseListener());
            this.setPreferredSize(new Dimension(400, 400));
        }

        public boolean isComplete() {
            return points.size() >= pointLimit;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Point pp = null;
            Point p0 = null;

            for (int i = 0; i < points.size(); i++) {
                g.setColor(Color.CYAN);

                Point p = points.get(i);
                g.fillOval(p.x - 20, p.y - 20, 40, 40);

                pp = p;
            }

//            if (drawLines && (points.size() > 1)) {
//                p0 = points.get(0);
//                pp = p0;
//                g.setColor(Color.BLACK);
//                for (int i = 1; i < points.size(); i++) {
//                    Point p = points.get(i);
//                    g.drawLine(pp.x, pp.y, p.x, p.y);
//                    pp = p;
//                }
//                g.drawLine(pp.x, pp.y, p0.x, p0.y);
//            }
        }
    }

    public static void main(String[] args) {
        TestPoint testPoint = new TestPoint();
    }
}
