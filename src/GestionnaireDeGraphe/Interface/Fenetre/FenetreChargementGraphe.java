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
import java.util.concurrent.TimeUnit;

public class FenetreChargementGraphe extends Fenetre {
    private LCGraphe graphe;
    private JMenu outils;
    private JMenuItem outilsItem;

    public FenetreChargementGraphe(String filePath) throws IOException {
        super();
        this.graphe = new LCGraphe(filePath);
        this.graphe.charg();
        this.setListeSommets(this.graphe.getListSommet());
        this.setListeAretes(this.graphe.getListAretes());
        this.setListeSommetsGraphique(new ArrayList<>());
        this.setListeAretesGraphique(new ArrayList<>());

        initComponents();
        this.dessinerSommet();
        this.dessinerArc();
        initActionListener();
        new FenetreOutils(this.graphe, this.getListeSommets(), this.getListeAretes(), this.getListearretegraphique(), this.getListesommetgraphique(), this);
    }

    private void initComponents() {
        outils = new JMenu("Outils");
        outilsItem = new JMenuItem("Outils");
        outils.add(outilsItem);
        super.addJMenuToMenuBar(outils);
    }

    public void dessinerSommet() {
        for (LCGraphe.MaillonGraphe sommet : this.getListeSommets()) {
            SommetGraphe s = new SommetGraphe(sommet);
//            TODO générer des vrai points
            s.setBounds(sommet.getCoordonnees().x,sommet.getCoordonnees().y,35,35);
            this.addListeSommetsGraphique(s);
            super.addJLabelToContent(s);
        }
    }
    public void dessinerArc() {
        for (LCGraphe.MaillonGrapheSec arete : this.getListeAretes().values()) {
            if(arete.getListed() == false){
                LCGraphe.MaillonGraphe origine = this.graphe.recherchenom(arete.getOrig());
                LCGraphe.MaillonGraphe destination = this.graphe.recherchenom(arete.getDest());
                AreteGraphe a = new AreteGraphe(arete.getNomArete(), origine, destination, arete);
                this.addListeAretesGraphique(a);
                a.setBounds(0, 0, getWidth(), getHeight());
                super.addJLabelToContent(a);
                this.graphe.listedarrete(arete);
            }
        }
    }

    public void initActionListener() {
    outilsItem.addActionListener(e -> {
        new FenetreOutils(this.graphe, this.getListeSommets(), this.getListeAretes(), this.getListearretegraphique(), this.getListesommetgraphique(), this);
    });

}
}
