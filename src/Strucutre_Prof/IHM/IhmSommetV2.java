package Strucutre_Prof.IHM;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IhmSommetV2 extends JLabel implements MouseListener, MouseMotionListener {
    private List<Point> points;
    private List<LCGraphe.MaillonGraphe> listeSommet;
    private HashMap<String, Point> mapSommetPoint;
    private LCGraphe.MaillonGraphe sommet;

    public IhmSommetV2(LCGraphe.MaillonGraphe sommet) {
        super(sommet.getNom());
        this.sommet = sommet;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = sommet.getCoordonnees().x;
        int y = sommet.getCoordonnees().y;
        g2d.setColor(Color.CYAN);
        g2d.fillOval(x, y, 30, 30);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x, y,30,30);
//            TODO faire un label pour le nom au lieux de faire un drawString
        g2d.drawString(sommet.getNom(), x+10, y+20);
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
        int deltaX = e.getX();
        int deltaY = e.getY();
        this.sommet.setCoordonnees(new Point(deltaX, deltaY));
        getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
