package Strucutre_Prof;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        sommet = new JLabel(new ImageIcon("img/sommet.png"));
        sommet.setIcon(new ImageIcon(((ImageIcon) sommet.getIcon()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
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

    public static void main(String[] args) {
        TestPoint testPoint = new TestPoint();
    }
}
