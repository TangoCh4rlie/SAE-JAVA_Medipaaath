package Strucutre_Prof.PacementDesPoints;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.awt.Graphics2D;
public class AreteGraphe extends JPanel {
    private LCGraphe.MaillonGraphe origine;
    private LCGraphe.MaillonGraphe destination;
    public AreteGraphe(String arete, LCGraphe.MaillonGraphe origine, LCGraphe.MaillonGraphe destination) {
        super();
        this.origine = origine;
        this.destination = destination;
    }
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(java.awt.Color.BLACK);
        g2d.drawLine(this.origine.getCoordonnees().x + 15, this.origine.getCoordonnees().y + 15, this.destination.getCoordonnees().x + 15, this.destination.getCoordonnees().y + 15);
    }

}
