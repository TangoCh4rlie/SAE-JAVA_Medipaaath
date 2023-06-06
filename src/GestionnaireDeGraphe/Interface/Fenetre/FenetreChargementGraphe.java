package GestionnaireDeGraphe.Interface.Fenetre;

import GestionnaireDeGraphe.Interface.ElementDeStructure.AreteGraphe;
import GestionnaireDeGraphe.Interface.ElementDeStructure.SommetGraphe;
import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FenetreChargementGraphe extends Fenetre {
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listeSommets;
    private HashMap<String, LCGraphe.MaillonGrapheSec> listeArete;

//    Tout ce qui est relatif au menu
    private JMenu Traitement;
    private JMenuItem Distance1;

    public FenetreChargementGraphe(String filePath) throws IOException {
        super();
        this.graphe = new LCGraphe(filePath);
        this.graphe.charg();
        this.listeSommets = new ArrayList<>(this.graphe.getListSommet());
        this.listeArete = new HashMap<>(this.graphe.getListAretes());
        initComponents();
        this.dessinerSommet();
        this.dessinerArc();
        initActionListener();
    }

    private void initComponents() {
        Traitement = new JMenu("Traitement");
        Distance1 = new JMenuItem("Distance 1");
        Traitement.add(Distance1);
        super.addJMenuToMenuBar(Traitement);
    }

    private void dessinerSommet() {
        for (LCGraphe.MaillonGraphe sommet : this.listeSommets) {
            SommetGraphe s = new SommetGraphe(sommet);
//            TODO générer des vrai points
            s.setBounds(sommet.getCoordonnees().x,sommet.getCoordonnees().y,35,35);
//            s.setBorder(BorderFactory.createLineBorder(java.awt.Color.green));
//            s.setBounds(sommet.getCoordonnees().x, sommet.getCoordonnees().y, 30, 30);
            super.addJLabelToContent(s);
        }
    }
    private void dessinerArc() {
        for (LCGraphe.MaillonGrapheSec arete : this.listeArete.values()) {
            LCGraphe.MaillonGraphe origine = this.graphe.recherchenom(arete.getOrig());
            LCGraphe.MaillonGraphe destination = this.graphe.recherchenom(arete.getDest());
            AreteGraphe a = new AreteGraphe(arete.getNomArete(), origine, destination, arete);
            a.setBounds(0, 0, getWidth(), getHeight());
            super.addJLabelToContent(a);
        }
    }

    private void initActionListener() {
        this.Distance1.addActionListener(e -> {
            String nomSommet = JOptionPane.showInputDialog(this, "Entrez le nom du sommet", "Distance 1", JOptionPane.QUESTION_MESSAGE);
            LCGraphe.MaillonGraphe sommetATraiter = this.graphe.recherchenom(nomSommet);
            List<LCGraphe.MaillonGrapheSec> aretesATraiter = this.graphe.getAretesAdj(sommetATraiter);
            for (LCGraphe.MaillonGrapheSec aretes : aretesATraiter) {
                aretes.setCouleur(Color.red);
            }
//            TODO ca fait des trucs louche la couleur est pas prise en compte
            this.listeArete = new HashMap<>(this.graphe.getListAretes());
            dessinerArc();
            this.repaint();
        });
    }
}
