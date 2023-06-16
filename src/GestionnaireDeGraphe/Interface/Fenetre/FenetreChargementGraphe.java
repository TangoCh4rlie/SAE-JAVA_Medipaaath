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
    private List<LCGraphe.MaillonGraphe> listeSommets;
    private HashMap<String, LCGraphe.MaillonGrapheSec> listeArete;
    private List<AreteGraphe> listearretegraphique;
    private List<SommetGraphe> listesommetgraphique;

    private JMenu outils;
    private JMenuItem outilsItem;

    public FenetreChargementGraphe(String filePath) throws IOException {
        super();
        this.graphe = new LCGraphe(filePath);
        this.graphe.charg();
        this.listeSommets = new ArrayList<>(this.graphe.getListSommet());
        this.listeArete = new HashMap<>(this.graphe.getListAretes());
        this.listearretegraphique = new ArrayList<>();
        this.listesommetgraphique = new ArrayList<>();
        initComponents();
        this.dessinerSommet();
        this.dessinerArc();
        initActionListener();
        new FenetreOutils(this.graphe, this.listeSommets, this.listeArete, this.listearretegraphique, this.listesommetgraphique, this);
    }

    private void initComponents() {
        outils = new JMenu("Outils");
        outilsItem = new JMenuItem("Outils");
        outils.add(outilsItem);
        super.addJMenuToMenuBar(outils);
    }

    public void dessinerSommet() {
        for (LCGraphe.MaillonGraphe sommet : this.listeSommets) {
            SommetGraphe s = new SommetGraphe(sommet);
//            TODO générer des vrai points
            s.setBounds(sommet.getCoordonnees().x,sommet.getCoordonnees().y,35,35);
            this.listesommetgraphique.add(s);
            super.addJLabelToContent(s);
        }
    }
    public void dessinerArc() {
        for (LCGraphe.MaillonGrapheSec arete : this.listeArete.values()) {
            if(arete.getListed() == false){
                LCGraphe.MaillonGraphe origine = this.graphe.recherchenom(arete.getOrig());
                LCGraphe.MaillonGraphe destination = this.graphe.recherchenom(arete.getDest());
                AreteGraphe a = new AreteGraphe(arete.getNomArete(), origine, destination, arete);
                this.listearretegraphique.add(a);
                a.setBounds(0, 0, getWidth(), getHeight());
                super.addJLabelToContent(a);
                this.graphe.listedarrete(arete);
            }
        }
    }



    public Component getComponentnamed(String name) {
        for (Component c : this.getContentPane().getComponents()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public void initActionListener() {
    outilsItem.addActionListener(e -> {
        new FenetreOutils(this.graphe, this.listeSommets, this.listeArete, this.listearretegraphique, this.listesommetgraphique, this);
    });

}
}
