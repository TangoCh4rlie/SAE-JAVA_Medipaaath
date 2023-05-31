package Strucutre_Prof.PacementDesPoints;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WindowChargementGraphe extends Window{
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listeSommets;
    private HashMap<String, LCGraphe.MaillonGrapheSec> listeArete;

    public WindowChargementGraphe(String filePath) throws IOException {
        super();
        this.graphe = new LCGraphe(filePath);
        this.graphe.charg();
        this.listeSommets = new ArrayList<>(this.graphe.getListSommet());
        this.listeArete = new HashMap<>(this.graphe.getListAretes());
        this.dessinerSommet();
        this.dessinerArc();
    }

    private void dessinerSommet() {
        for (LCGraphe.MaillonGraphe sommet : this.listeSommets) {
            SommetGraphe s = new SommetGraphe(sommet);
            s.setBorder(BorderFactory.createLineBorder(java.awt.Color.green));
            s.setBounds(sommet.getCoordonnees().x, sommet.getCoordonnees().y, 30, 30);
            super.addJLabelToContent(s);
        }
    }
    private void dessinerArc() {
        for (LCGraphe.MaillonGrapheSec arete : this.listeArete.values()) {
            LCGraphe.MaillonGraphe origine = this.graphe.recherchenom(arete.getOrig());
            LCGraphe.MaillonGraphe destination = this.graphe.recherchenom(arete.getDest());
            AreteGraphe a = new AreteGraphe(arete.getNomArete(), origine, destination);
            a.setBounds(0, 0, getWidth(), getHeight());
            super.addJPanelToContent(a);
        }
        repaint();
    }
//        IhmArete dessinArete = new IhmArete(this.listeAreteADessiner);
//        this.add(dessinArete);
}
