package GestionnaireDeGraphe.Interface.ElementDeStructure;

import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SommetGraphe extends JLabel implements MouseListener, MouseMotionListener {
    private LCGraphe.MaillonGraphe sommet;
    private int mouseX;
    private int mouseY;
    public SommetGraphe(LCGraphe.MaillonGraphe sommet) {
        super(sommet.getNom());
        this.sommet = sommet;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        int x = sommet.getCoordonnees().x;
//        int y = sommet.getCoordonnees().y;
//        g2d.setColor(Color.CYAN);
//        g2d.fillOval(x, y, 30, 30);
////        g2d.setColor(Color.BLACK);
////        g2d.setStroke(new BasicStroke(2));
//        g2d.drawOval(x, y,30,30);
////            TODO faire un label pour le nom au lieux de faire un drawString
////        g2d.drawString(sommet.getNom(), x+10, y+20);
//    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
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
        int deltaX = e.getX() - mouseX;
        int deltaY = e.getY() - mouseY;
        int newX = this.getX() + deltaX;
        int newY = this.getY() + deltaY;
        this.setLocation(newX, newY);
        this.sommet.setCoordonnees(new Point(newX, newY));
        getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
