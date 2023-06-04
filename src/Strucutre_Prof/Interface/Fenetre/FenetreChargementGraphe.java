package Strucutre_Prof.Interface.Fenetre;

import Strucutre_Prof.Interface.ElementDeStructure.AreteGraphe;
import Strucutre_Prof.Interface.ElementDeStructure.SommetGraphe;
import Strucutre_Prof.LCGraphe;

import javax.swing.BorderFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FenetreChargementGraphe extends Fenetre {
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listeSommets;
    private HashMap<String, LCGraphe.MaillonGrapheSec> listeArete;

    public FenetreChargementGraphe(String filePath) throws IOException {
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
//            TODO générer des vrai points
//            s.setBounds(0,0,getWidth(),getHeight());
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
            super.addJLabelToContent(a);
        }
    }
}
