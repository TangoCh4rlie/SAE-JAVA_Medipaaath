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
    private List<SommetGraphe> listesommetgraphique;

//    Tout ce qui est relatif au menu
    private JMenu Traitement;
    private JMenuItem Distance1;
    private JMenuItem Parcours;
    private  JMenuItem Distance2;

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
    }

    private void initComponents() {
        Traitement = new JMenu("Traitement");
        Distance1 = new JMenuItem("Distance 1");
        Distance2 = new JMenuItem("Distance 2");
        Parcours = new JMenuItem("Parcours");

        Traitement.add(Distance1);
        Traitement.add(Distance2);
        Traitement.add(Parcours);
        super.addJMenuToMenuBar(Traitement);
    }

    private void dessinerSommet() {
        for (LCGraphe.MaillonGraphe sommet : this.listeSommets) {
            SommetGraphe s = new SommetGraphe(sommet);
//            TODO générer des vrai points
            s.setBounds(sommet.getCoordonnees().x,sommet.getCoordonnees().y,35,35);
            this.listesommetgraphique.add(s);
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

            List<LCGraphe.MaillonGrapheSec> lAretes = this.graphe.getListAretesAdj(graphe.recherchenom(nomSommet));
            for (LCGraphe.MaillonGrapheSec aretesADessiner : lAretes) {
                for (AreteGraphe areteGraphe : this.listearretegraphique) {
                    if (areteGraphe.getAreteNom().equals(aretesADessiner.getNomArete())) {
                        areteGraphe.setCouleurActuelle(Color.red);
                    }
                }
                this.repaint();
            }

            List<LCGraphe.MaillonGraphe> lSommets = this.graphe.getListSommetAdj(graphe.recherchenom(nomSommet));
            for (LCGraphe.MaillonGraphe sommetADessiner : lSommets) {
                for (SommetGraphe sommetGraphe : this.listesommetgraphique) {
                    if (sommetGraphe.getNomSommet().equals(sommetADessiner.getNom())) {
                        sommetGraphe.setCouleurDuPoint(Color.green);
//                        TODO fix le fait que lorsqu'on déplcale le point il rechange de couleur
                    }
                }
                this.repaint();
            }
        });
        this.Distance2.addActionListener(e -> {
            String nomSommet = JOptionPane.showInputDialog(this, "Entrez le nom du sommet", "Distance 2", JOptionPane.QUESTION_MESSAGE);

            List<LCGraphe.MaillonGraphe> lSommets = this.graphe.getListSommet2Dist(graphe.recherchenom(nomSommet));
            for (LCGraphe.MaillonGraphe sommetADessiner : lSommets) {
                for (SommetGraphe sommetGraphe : this.listesommetgraphique) {
                    if (sommetGraphe.getNomSommet().equals(sommetADessiner.getNom())) {
                        sommetGraphe.setCouleurDuPoint(Color.green);
//                        TODO fix le fait que lorsqu'on déplcale le point il rechange de couleur
                    }
                }
                this.repaint();
            }
        });
        this.Parcours.addActionListener(e -> {
            for (AreteGraphe arete : this.listearretegraphique) {
                arete.setCouleurActuelle(Color.black);
            }
            JTextField ori = new JTextField(2);
            JTextField dest = new JTextField(2);
            JComboBox<String> combo = new JComboBox<>();
            String choix1 = "Rapide";
            String choix2 = "Court";
            String choix3 = "Fiable";
            combo.addItem(choix1);
            combo.addItem(choix2);
            combo.addItem(choix3);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Origine:"));
            myPanel.add(ori);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Arrive:"));
            myPanel.add(dest);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Choix:"));
            myPanel.add(combo);
            int result = JOptionPane.showConfirmDialog(null, myPanel,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                switch (combo.getSelectedItem().toString()) {
                    case "Rapide":
                        List a1 = this.graphe.dijkstrarapide(ori.getText(),dest.getText());
                        List arc1 = (List) a1.get(1);
                        for (Object obj : arc1) {
                            //recuperer l'arc listearretegraphique
                            for (AreteGraphe arete : this.listearretegraphique) {
                                if (arete.getAreteNom().equals(obj)) {
                                    arete.setCouleurActuelle(Color.red);
                                }
                            }
                        }
                        this.repaint();
                        break;
                    case "Court":
                        List a2 = this.graphe.dijkstracourt(ori.getText(),dest.getText());
                        List arc2 = (List) a2.get(1);
                        for (Object obj : arc2) {
                            //recuperer l'arc listearretegraphique
                            for (AreteGraphe arete : this.listearretegraphique) {
                                if (arete.getAreteNom().equals(obj)) {
                                    arete.setCouleurActuelle(Color.red);
                                }
                            }
                        }
                        this.repaint();
                        break;
                    case "Fiable":
                        List a3 = this.graphe.dijkstrafiable(ori.getText(),dest.getText());
                        List arc3 = (List) a3.get(1);
                        for (Object obj : arc3) {
                            //recuperer l'arc listearretegraphique
                            for (AreteGraphe arete : this.listearretegraphique) {
                                if (arete.getAreteNom().equals(obj)) {
                                    arete.setCouleurActuelle(Color.red);
                                }
                            }
                        }
                        this.repaint();
                        break;
                }
                List a = this.graphe.dijkstracourt(ori.getText(),dest.getText());
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
