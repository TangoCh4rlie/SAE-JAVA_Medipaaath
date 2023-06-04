package Strucutre_Prof.Interface.ElementDeStructure;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.awt.*;

public class AreteGraphe extends JLabel {
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
    }
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.arete.getCouleur());
        g2d.drawLine(this.origine.getCoordonnees().x + 15, this.origine.getCoordonnees().y + 15, this.destination.getCoordonnees().x + 15, this.destination.getCoordonnees().y + 15);
        g2d.drawString(this.areteNom, (this.origine.getCoordonnees().x + this.destination.getCoordonnees().x) / 2, (this.origine.getCoordonnees().y + this.destination.getCoordonnees().y) / 2);
    }

    public LCGraphe.MaillonGraphe getOrigine() {
        return origine;
    }
    public LCGraphe.MaillonGraphe getDestination() {
        return destination;
    }
}
