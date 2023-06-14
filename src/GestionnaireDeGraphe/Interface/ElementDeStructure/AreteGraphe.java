package GestionnaireDeGraphe.Interface.ElementDeStructure;

import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AreteGraphe extends JLabel {
    /**
     * Classe qui permet de dessiner les arêtes du graphe
     */
    private LCGraphe.MaillonGrapheSec arete;
    private LCGraphe.MaillonGraphe origine;
    private LCGraphe.MaillonGraphe destination;
    private String areteNom;
    private Color couleurActuelle;
    private Color couleurAreteNormal = Color.BLACK;
    public AreteGraphe(String areteNom, LCGraphe.MaillonGraphe origine, LCGraphe.MaillonGraphe destination, LCGraphe.MaillonGrapheSec arete) {
        super();
        this.areteNom = areteNom;
        this.arete = arete;
        this.origine = origine;
        this.destination = destination;
        this.couleurActuelle = arete.getCouleur();
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.couleurActuelle);
        g2d.drawLine(this.origine.getCoordonnees().x + 15, this.origine.getCoordonnees().y + 15, this.destination.getCoordonnees().x + 15, this.destination.getCoordonnees().y + 15);
        g2d.drawString(this.areteNom, (this.origine.getCoordonnees().x + this.destination.getCoordonnees().x) / 2, (this.origine.getCoordonnees().y + this.destination.getCoordonnees().y) / 2);
    }

    public LCGraphe.MaillonGraphe getOrigine() {
        return origine;
    }
    public LCGraphe.MaillonGraphe getDestination() {
        return destination;
    }
    public Color getCouleurActuelle() {
        return couleurActuelle;
    }

    public String getAreteNom() {
        return areteNom;
    }
    public void setCouleurActuelle(Color couleurActuelle) {
        this.couleurActuelle = couleurActuelle;
    }
}
