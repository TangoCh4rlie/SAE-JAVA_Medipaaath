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
    private List<IhmSommet> listeSommetADessiner;
    public IhmSommet(LCGraphe.MaillonGraphe sommet, List<IhmSommet> listeSommetADessiner, String nom) {
        super(nom);
        this.sommet = sommet;
        this.listeSommetADessiner = listeSommetADessiner;
        addMouseListener(this);
        addMouseMotionListener(this);

    }
//    TODO faire une liste er repaint a chaque fois
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.fillOval(sommet.getX(), sommet.getY(),30,30);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(sommet.getX(), sommet.getY(),30,30);
        g2d.drawString(sommet.getNom(), sommet.getX()+10, sommet.getY()+20);
        this.setVisible(true);
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

        // mise à jour de la position
        //setXAndY(x + deltaX - taille / 2, y + deltaY - taille / 2);
        this.sommet.setX(this.sommet.getX() + deltaX);
        this.sommet.setY(this.sommet.getY() + deltaY);
        this.setBounds(this.sommet.getX(), this.sommet.getY(), 30, 30);
        this.getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
