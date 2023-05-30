package Strucutre_Prof.IHM;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class IhmSommet extends JLabel implements MouseListener, MouseMotionListener {
    private LCGraphe.MaillonGraphe sommet;
    public IhmSommet(LCGraphe.MaillonGraphe sommet){
        this.sommet = sommet;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = (int) this.sommet.getCoordonnees().getX();
        int y = (int) this.sommet.getCoordonnees().getY();
        g2d.setColor(Color.CYAN);
        g2d.fillOval(x, y, 30, 30);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x, y,30,30);
//            TODO faire un label pour le nom au lieux de faire un drawString
//        g2d.drawString(this.listeSommet.get(i++).getNom(), x+10, y+20);
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
//        int deltaX = e.getX();
//        int deltaY = e.getY();
//
//        // mise à jour de la position
//        //setXAndY(x + deltaX - taille / 2, y + deltaY - taille / 2);
//        this.sommet.setX(this.sommet.getX() + deltaX);
//        this.sommet.setY(this.sommet.getY() + deltaY);
//        this.setBounds(this.sommet.getX(), this.sommet.getY(), 30, 30);
//        this.getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
