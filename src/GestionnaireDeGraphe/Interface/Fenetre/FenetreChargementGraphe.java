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
    private List<AreteGraphe> listearretegraphique;

//    Tout ce qui est relatif au menu
    private JMenu Traitement;
    private JMenuItem Distance1;
    private JMenuItem CheminCourt;

    public FenetreChargementGraphe(String filePath) throws IOException {
        super();
        this.graphe = new LCGraphe(filePath);
        this.graphe.charg();
        this.listeSommets = new ArrayList<>(this.graphe.getListSommet());
        this.listeArete = new HashMap<>(this.graphe.getListAretes());
        this.listearretegraphique = new ArrayList<>();
        initComponents();
        this.dessinerSommet();
        this.dessinerArc();
        initActionListener();
    }

    private void initComponents() {
        Traitement = new JMenu("Traitement");
        Distance1 = new JMenuItem("Distance 1");
        CheminCourt = new JMenuItem("Chemin le plus court");
        Traitement.add(Distance1);
        Traitement.add(CheminCourt);
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
        this.CheminCourt.addActionListener(e -> {
            for (AreteGraphe arete : this.listearretegraphique) {
                arete.setCouleurActuelle(Color.black);
            }
            JTextField ori = new JTextField(2);
            JTextField dest = new JTextField(2);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Origine:"));
            myPanel.add(ori);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Arrive:"));
            myPanel.add(dest);
            int result = JOptionPane.showConfirmDialog(null, myPanel,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                List a = this.graphe.dijkstra(ori.getText(),dest.getText());
                List arc = (List) a.get(1);
                for (Object obj : arc) {
                    //recuperer l'arc listearretegraphique
                    for (AreteGraphe arete : this.listearretegraphique) {
                        if (arete.getAreteNom().equals(obj)) {
                            arete.setCouleurActuelle(Color.red);
                        }
                    }
                }
                this.repaint();
            }
        });
    }
}
