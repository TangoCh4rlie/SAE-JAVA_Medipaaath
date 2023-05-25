package Strucutre_Prof.IHM;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class IhmSommetV2 extends JLabel implements MouseListener, MouseMotionListener {
    private ArrayList<Point> points;
    private List<LCGraphe.MaillonGraphe> listeSommet;

    public IhmSommetV2(List<Point> points, List<LCGraphe.MaillonGraphe> listeSommet) {
        this.points = new ArrayList<Point>(points);
        this.listeSommet = listeSommet;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int i = 0;
        for (Point point : points) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            g2d.setColor(Color.CYAN);
            g2d.fillOval(x, y, 30, 30);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(x, y,30,30);
            g2d.drawString(this.listeSommet.get(i++).getNom(), x+10, y+20);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
//    public static void main(String[] args) {
//        // Créer quelques points de test
//        ArrayList<Point> points = new ArrayList<>();
//        points.add(new Point(50, 50));
//        points.add(new Point(100, 100));
//        points.add(new Point(150, 200));
//
//        // Créer une fenêtre JFrame pour afficher le graphe
//        JFrame frame = new JFrame("Graph Drawing");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 300);
//
//        // Ajouter le composant GraphDrawing à la fenêtre
//        IhmSommetV2 graphDrawing = new IhmSommetV2(points);
//        frame.add(graphDrawing);
//
//        // Rendre la fenêtre visible
//        frame.setVisible(true);
//    }
}
