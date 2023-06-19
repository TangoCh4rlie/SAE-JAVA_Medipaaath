package GestionnaireDeGraphe.Interface.Fenetre;

import GestionnaireDeGraphe.Interface.ElementDeStructure.AreteGraphe;
import GestionnaireDeGraphe.Interface.ElementDeStructure.SommetGraphe;
import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Elouan
 * @description Classe FenetreChargementGraphe
 */
public class FenetreChargementGraphe extends Fenetre {
    private JMenu outils;
    private JMenuItem outilsItem;
    
    /**
     * @autor Elouan
     * @param filePath
     * @throws IOException
     * @description Constructeur de la classe FenetreChargementGraphe
     */
    public FenetreChargementGraphe(String filePath) throws IOException {
        super();
        this.setGraphe(new LCGraphe(filePath));
        this.getGraphe().charg();
        this.setListeSommets(this.getGraphe().getListSommet());
        this.setListeAretes(this.getGraphe().getListAretes());
        this.setListeSommetsGraphique(new ArrayList<>());
        this.setListeAretesGraphique(new ArrayList<>());

        initComponents();
        this.dessinerSommet();
        this.dessinerArc();
        initActionListener();
        new FenetreOutils(this.getGraphe(), this.getListeSommets(), this.getListeAretes(), this.getListearretegraphique(), this.getListesommetgraphique(), this);
    }

    /**
     * @autor Elouan
     * @description Méthode qui initialise les composants de la fenêtre
     */
    private void initComponents() {
        outils = new JMenu("Outils");
        outilsItem = new JMenuItem("Outils");
        outils.add(outilsItem);
        super.addJMenuToMenuBar(outils);
    }

    /**
     * @autor Elouan
     * @description Méthode qui dessine les sommets
     */
    public void dessinerSommet() {
        for (LCGraphe.MaillonGraphe sommet : this.getListeSommets()) {
            SommetGraphe s = new SommetGraphe(sommet);
            s.setBounds(sommet.getCoordonnees().x,sommet.getCoordonnees().y,35,35);
            this.addListeSommetsGraphique(s);
            super.addJLabelToContent(s);
        }
    }

    /**
     * @autor Elouan
     * @description Méthode qui dessine les arcs
     */
    public void dessinerArc() {
        for (LCGraphe.MaillonGrapheSec arete : this.getListeAretes().values()) {
            if(arete.getListed() == false){
                LCGraphe.MaillonGraphe origine = this.getGraphe().rechercheNomSommet(arete.getOrig());
                LCGraphe.MaillonGraphe destination = this.getGraphe().rechercheNomSommet(arete.getDest());
                AreteGraphe a = new AreteGraphe(arete.getNomArete(), origine, destination, arete);
                this.addListeAretesGraphique(a);
                a.setBounds(0, 0, getWidth(), getHeight());
                super.addJLabelToContent(a);
                this.getGraphe().listedArete(arete);
            }
        }
    }

    /**
     * @autor Elouan
     * @description Méthode qui initialise les actionListener
     */
    public void initActionListener() {
    outilsItem.addActionListener(e -> {
        new FenetreOutils(this.getGraphe(), this.getListeSommets(), this.getListeAretes(), this.getListearretegraphique(), this.getListesommetgraphique(), this);
    });

}
}
