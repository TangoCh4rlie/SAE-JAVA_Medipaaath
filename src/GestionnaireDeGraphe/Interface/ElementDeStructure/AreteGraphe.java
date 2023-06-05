package GestionnaireDeGraphe.Interface.ElementDeStructure;

import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AreteGraphe extends JLabel implements MouseListener {
    private LCGraphe.MaillonGrapheSec arete;
    private LCGraphe.MaillonGraphe origine;
    private LCGraphe.MaillonGraphe destination;
    private String areteNom;
    private Color couleurArete;
    public AreteGraphe(String areteNom, LCGraphe.MaillonGraphe origine, LCGraphe.MaillonGraphe destination, LCGraphe.MaillonGrapheSec arete) {
        super();
        this.areteNom = areteNom;
        this.arete = arete;
        this.origine = origine;
        this.destination = destination;
        this.couleurArete = Color.BLACK;
        addMouseListener(this);
    }
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.couleurArete);
        g2d.drawLine(this.origine.getCoordonnees().x + 15, this.origine.getCoordonnees().y + 15, this.destination.getCoordonnees().x + 15, this.destination.getCoordonnees().y + 15);
        g2d.drawString(this.areteNom, (this.origine.getCoordonnees().x + this.destination.getCoordonnees().x) / 2, (this.origine.getCoordonnees().y + this.destination.getCoordonnees().y) / 2);
    }

    public LCGraphe.MaillonGraphe getOrigine() {
        return origine;
    }
    public LCGraphe.MaillonGraphe getDestination() {
        return destination;
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
//        when the mouse enter the label, the color of the edge change
//        Open a little pop-up with the caracteristics of the edge
//        todo faire un mini timer
        JToolTip toolTip = new JToolTip();
        toolTip.setTipText("Durée : " + this.arete.getDur());
        toolTip.setVisible(true);
        this.add(toolTip);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
