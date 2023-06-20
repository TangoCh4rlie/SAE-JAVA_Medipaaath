package GestionnaireDeGraphe.Interface.Fenetre;

import GestionnaireDeGraphe.Interface.ElementDeStructure.AreteGraphe;
import GestionnaireDeGraphe.Interface.ElementDeStructure.SommetGraphe;
import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;
import GestionnaireDeGraphe.TraitementGraphe.TypeDispensaire;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

/**
 * @author Elouan
 * @description Classe qui permet de créer la fenêtre d'outils où se trouve les différents outils de traitement du graphe
 */
public class FenetreOutils extends JFrame {

    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listeSommets;
    private HashMap<String, LCGraphe.MaillonGrapheSec> listeArete;
    private List<AreteGraphe> listearretegraphique;
    private List<SommetGraphe> listesommetgraphique;
    private FenetreChargementGraphe fenetreChargementGraphe;

    //    Tout ce qui est relatif au menU
    private JMenuBar menu;
    private JMenu traitement;
    private JMenu modification;
    private JMenu affichage;
    private JMenu analyse;
    private JMenuItem disp_comp;
    private JMenuItem disp_type;
    private JMenuItem disp_dec;
    private JMenuItem disp_risque;
    private JMenuItem modifie_arete;
    private JMenuItem modifie_sommet;
    private JMenuItem reinit;
    private JMenuItem distance1;
    private JMenuItem parcours;
    private JMenuItem distance2;
    private JMenuItem afficheDataArete;
    private JMenuItem afficheDataSommet;
    private JMenuItem afficheVoisinsTypeDonne;
    private JMenuItem afficherToutesLesAretes;


//    Tout ce qui est relatif au contenu de la fenetre
    private JTextArea textArea;
    private JPanel content;

    /**
     * @autor Elouan
     * @description Constructeur de la fenêtre d'outils
     * @param graphe
     * @param listeSommets
     * @param listeArete
     * @param listearretegraphique
     * @param listesommetgraphique
     * @param fenetreChargementGraphe
     */
    public FenetreOutils(LCGraphe graphe, List<LCGraphe.MaillonGraphe> listeSommets, HashMap<String, LCGraphe.MaillonGrapheSec> listeArete, List<AreteGraphe> listearretegraphique, List<SommetGraphe> listesommetgraphique, FenetreChargementGraphe fenetreChargementGraphe) {
        this.graphe = graphe;
        this.listearretegraphique = listearretegraphique;
        this.listesommetgraphique = listesommetgraphique;
        this.fenetreChargementGraphe = fenetreChargementGraphe;
        this.listeSommets = listeSommets;
        this.listeArete = listeArete;
        initComponents();
        initActionListener();
        this.pack();
    }

    /**
     * @autor Elouan
     * @description Méthode qui permet d'initialiser les composants de la fenêtre
     */
    private void initComponents() {
        this.setTitle("Outils");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,500));
        this.setVisible(true);

        this.menu = new JMenuBar();
        traitement = new JMenu("Traitement");
        modification = new JMenu("Modification");
        affichage = new JMenu("Affichage");
        analyse = new JMenu("Analyse");

        reinit = new JMenuItem("Reinitialiser");
        distance1 = new JMenuItem("Distance 1");
        distance2 = new JMenuItem("Distance 2");
        parcours = new JMenuItem("Parcours");

        modifie_arete = new JMenuItem("Modifier une arete");
        modifie_sommet = new JMenuItem("Modifier un sommet");

        afficheDataArete = new JMenuItem("Afficher les données d'une arete");
        afficheDataSommet = new JMenuItem("Afficher les données d'un sommet");
        afficherToutesLesAretes = new JMenuItem("Afficher toutes les aretes");
        afficheVoisinsTypeDonne = new JMenuItem("Afficher les voisins d'un type donné");

        disp_type = new JMenuItem("Afficher les sommets d'un type");
        disp_dec = new JMenuItem("Décompte des types");
        disp_risque = new JMenuItem("Afficher chemin le plus risqué");


        disp_comp = new JMenuItem("Comparer deux sommets");

        this.traitement.add(reinit);
        this.traitement.add(distance1);
        this.traitement.add(distance2);
        this.traitement.add(parcours);

        this.modification.add(modifie_arete);
        this.modification.add(modifie_sommet);

        this.affichage.add(afficheDataArete);
        this.affichage.add(afficheDataSommet);
        this.affichage.add(disp_type);
        this.affichage.add(disp_dec);
        this.affichage.add(disp_risque);
        this.affichage.add(afficherToutesLesAretes);
        this.affichage.add(afficheVoisinsTypeDonne);

        this.analyse.add(disp_comp);

        this.menu.add(traitement);
        this.menu.add(modification);
        this.menu.add(affichage);
        this.menu.add(analyse);
        this.setJMenuBar(this.menu);

        this.textArea = new JTextArea();
        this.textArea.setPreferredSize(new Dimension(360, 400));
        this.textArea.setEditable(false);
        this.textArea.setBorder(BorderFactory.createLineBorder(Color.black));
        this.textArea.setText("Effectuer un traitement\nsur le graphe");

        this.content = new JPanel();
        this.content.setBorder(BorderFactory.createLineBorder(Color.red));
        this.content.add(this.textArea);

//        this.add(this.titrePanel);
        this.add(this.content);
    }

    /**
     * @autor Elouan
     * @description Méthode qui permet de recuperer le nom d'une arete
     * @param nomArete
     */
    public LCGraphe.MaillonGrapheSec getArete(String nomArete){
        return this.listeArete.get(nomArete);
    }

    /**
     * @autor Elouan
     * @description Méthode qui permet de recuperer un sommet
     * @param nomSommet
     */
    public LCGraphe.MaillonGraphe getSommet(String nomSommet){
        for (LCGraphe.MaillonGraphe listeSommet : listeSommets) {
            if (listeSommet.getNom().equals(nomSommet))
                return listeSommet;
        }
        return null;
    }

    /**
     * @autor Elouan
     * @description Méthode qui permet de reinitaliser la couleur d'un sommet
     */
    public void reinitCouleurSommet(){
        for (LCGraphe.MaillonGraphe listeSommet : listeSommets) {
            for (SommetGraphe sommetGraphe : this.listesommetgraphique) {
                if (sommetGraphe.getNomSommet().equals(listeSommet.getNom()))
                    sommetGraphe.setCouleurDuPoint(listeSommet.getType().getColor());
            }
        }
    }

    /**
     * @autor Elouan
     * @description Méthode qui permet de reinitaliser la couleur d'une arete
     */
    public void reinitCouleurArete(){
        for (AreteGraphe areteGraphe : this.listearretegraphique) {
            areteGraphe.setCouleurActuelle(Color.black);
        }
    }

    /**
     * @autor Elouan
     * @description Méthode qui permet d'initialiser les actionListener
     */
    private void initActionListener() {
        this.reinit.addActionListener(e -> {
            reinitCouleurArete();
            reinitCouleurSommet();
            fenetreChargementGraphe.repaint();
        });
        this.distance1.addActionListener(e -> {
            reinitCouleurArete();
            reinitCouleurSommet();

            JComboBox choixSommet = new JComboBox<>();
            for (LCGraphe.MaillonGraphe sommet : listeSommets) {
                choixSommet.addItem(sommet.getNom());
            }
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Sommet:"));
            myPanel.add(choixSommet);


            int result = JOptionPane.showConfirmDialog(null, myPanel, "Supprimer un sommet", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                List<LCGraphe.MaillonGrapheSec> lAretes = this.graphe.getListAretesAdj(graphe.rechercheNomSommet(choixSommet.getSelectedItem().toString()));
                for (LCGraphe.MaillonGrapheSec aretesADessiner : lAretes) {
                    for (AreteGraphe areteGraphe : this.listearretegraphique) {
                        if (areteGraphe.getAreteNom().equals(aretesADessiner.getNomArete())) {
                            areteGraphe.setCouleurActuelle(Color.red);
                        }
                    }
                    fenetreChargementGraphe.repaint();
                }

                List<LCGraphe.MaillonGraphe> lSommets = this.graphe.getListSommetAdj(graphe.rechercheNomSommet(choixSommet.getSelectedItem().toString()));
                for (LCGraphe.MaillonGraphe sommetADessiner : lSommets) {
                    for (SommetGraphe sommetGraphe : this.listesommetgraphique) {
                        if (sommetGraphe.getNomSommet().equals(sommetADessiner.getNom())) {
                            sommetGraphe.setCouleurDuPoint(Color.green);
//                        TODO fix le fait que lorsqu'on déplcale le point il rechange de couleur
                        }
                    }
                    fenetreChargementGraphe.repaint();
                }
                this.textArea.setText("Sommet adjacent : " + lSommets.toString() + "\n" + "Arete adjacent : " + lAretes.toString());
            }
        });
        this.distance2.addActionListener(e -> {
            reinitCouleurArete();
            reinitCouleurSommet();

            JComboBox choixSommet = new JComboBox<>();
            for (LCGraphe.MaillonGraphe sommet : listeSommets) {
                choixSommet.addItem(sommet.getNom());
            }
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Sommet:"));
            myPanel.add(choixSommet);

            int result = JOptionPane.showConfirmDialog(null, myPanel, "Supprimer une arete", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                List<LCGraphe.MaillonGrapheSec> lAretes = this.graphe.getListArete2Distance(graphe.rechercheNomSommet(choixSommet.getSelectedItem().toString()));
                for (LCGraphe.MaillonGrapheSec aretesADessiner : lAretes) {
                    for (AreteGraphe areteGraphe : this.listearretegraphique) {
                        if (areteGraphe.getAreteNom().equals(aretesADessiner.getNomArete())) {
                            areteGraphe.setCouleurActuelle(Color.red);
                        }
                    }
                    fenetreChargementGraphe.repaint();
                }

                List<LCGraphe.MaillonGraphe> lSommets = this.graphe.getListSommet2Dist(graphe.rechercheNomSommet(choixSommet.getSelectedItem().toString()));
                for (LCGraphe.MaillonGraphe sommetADessiner : lSommets) {
                    for (SommetGraphe sommetGraphe : this.listesommetgraphique) {
                        if (sommetGraphe.getNomSommet().equals(sommetADessiner.getNom())) {
                            sommetGraphe.setCouleurDuPoint(Color.green);
//                        TODO fix le fait que lorsqu'on déplcale le point il rechange de couleur
                        }
                    }
                    fenetreChargementGraphe.repaint();
                }
                this.textArea.setText("Sommet adjacent : " + lSommets.toString() + "\n" + "Arete adjacent : " + lAretes.toString());

            }
        });
        this.parcours.addActionListener(e -> {
            reinitCouleurArete();
            reinitCouleurSommet();
            JComboBox<String> ori = new JComboBox<>();
            JComboBox<String> dest = new JComboBox<>();
            for (LCGraphe.MaillonGraphe listeSommet : listeSommets) {
                ori.addItem(listeSommet.getNom());
                dest.addItem(listeSommet.getNom());
            }
            JComboBox<String> combo = new JComboBox<>();
            String choix1 = "Rapide";
            String choix2 = "Court";
            String choix3 = "Fiable";
            combo.addItem(choix1);
            combo.addItem(choix2);
            combo.addItem(choix3);

            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Origine:"));
            panelPopup.add(ori);
            panelPopup.add(Box.createHorizontalStrut(15)); // a spacer
            panelPopup.add(new JLabel("Arrive:"));
            panelPopup.add(dest);
            panelPopup.add(Box.createHorizontalStrut(15)); // a spacer
            panelPopup.add(new JLabel("Choix:"));
            panelPopup.add(combo);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (ori.getSelectedItem().equals(dest.getSelectedItem())){
                    JOptionPane.showMessageDialog(null, "Les sommets de départ et d'arrivé doivent être différents", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                switch (combo.getSelectedItem().toString()) {
                    case "Rapide":
                        List a1 = this.graphe.dijkstrarapide((String) ori.getSelectedItem(), (String) dest.getSelectedItem());
                        List arc1 = (List) a1.get(1);
                        for (Object obj : arc1) {
                            //recuperer l'arc listearretegraphique
                            for (AreteGraphe arete : this.listearretegraphique) {
                                if (arete.getAreteNom().equals(obj)) {
                                    arete.setCouleurActuelle(Color.red);
                                }
                            }
                        }
                        fenetreChargementGraphe.repaint();
                        this.textArea.setText("Les sommets empruntés sont : " + a1.get(0));
                        break;
                    case "Court":
                        List a2 = this.graphe.dijkstracourt((String) ori.getSelectedItem(), (String) dest.getSelectedItem());
                        List arc2 = (List) a2.get(1);
                        for (Object obj : arc2) {
                            //recuperer l'arc listearretegraphique
                            for (AreteGraphe arete : this.listearretegraphique) {
                                if (arete.getAreteNom().equals(obj)) {
                                    arete.setCouleurActuelle(Color.red);
                                }
                            }
                        }
                        fenetreChargementGraphe.repaint();
                        this.textArea.setText("Les sommets empruntés sont : " + a2.get(0));
                        break;
                    case "Fiable":
                        List a3 = this.graphe.dijkstrafiable((String) ori.getSelectedItem(), (String) dest.getSelectedItem());
                        List arc3 = (List) a3.get(1);
                        for (Object obj : arc3) {
                            //recuperer l'arc listearretegraphique
                            for (AreteGraphe arete : this.listearretegraphique) {
                                if (arete.getAreteNom().equals(obj)) {
                                    arete.setCouleurActuelle(Color.red);
                                }
                            }
                        }
                        fenetreChargementGraphe.repaint();
                        this.textArea.setText("Les sommets empruntés sont : " + a3.get(0));
                        break;
                }
                List a = this.graphe.dijkstracourt((String) ori.getSelectedItem(), (String) dest.getSelectedItem());
                List arc = (List) a.get(1);
                for (Object obj : arc) {
                    //recuperer l'arc listearretegraphique
                    for (AreteGraphe arete : this.listearretegraphique) {
                        if (arete.getAreteNom().equals(obj)) {
                            arete.setCouleurActuelle(Color.red);
                        }
                    }
                }
                fenetreChargementGraphe.repaint();
            }
        });
        this.modifie_arete.addActionListener(e -> {
            JComboBox<String> selected_arrete = new JComboBox<>();
            for (AreteGraphe listeArete : listearretegraphique)
                selected_arrete.addItem(listeArete.getAreteNom());
            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Arete:"));
            panelPopup.add(selected_arrete);
            JTextField fiab = new JTextField(2);
            JTextField duree = new JTextField(2);
            JTextField dist = new JTextField(2);
            panelPopup.add(Box.createHorizontalStrut(15)); // a spacer
            panelPopup.add(new JLabel("Fiabilité:"));
            panelPopup.add(fiab);
            panelPopup.add(Box.createHorizontalStrut(15)); // a spacer
            panelPopup.add(new JLabel("Durée:"));
            panelPopup.add(duree);
            panelPopup.add(Box.createHorizontalStrut(15)); // a spacer
            panelPopup.add(new JLabel("Distance:"));
            panelPopup.add(dist);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                    LCGraphe.MaillonGrapheSec arrete_a_modifier = this.getArete((String) selected_arrete.getSelectedItem());
                    if (fiab.getText().equals("") || duree.getText().equals("") || dist.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Vous devez modifier au moins un paramètre", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (Integer.parseInt(fiab.getText()) < 0 || Integer.parseInt(fiab.getText()) > 100) {
                        JOptionPane.showMessageDialog(null, "La fiabilité doit être comprise entre 0 et 100", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (Integer.parseInt(duree.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "La durée doit être supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (Integer.parseInt(dist.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "La distance doit être supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    arrete_a_modifier.setFiab(Integer.parseInt(fiab.getText()));
                    arrete_a_modifier.setDur(Integer.parseInt(duree.getText()));
                    arrete_a_modifier.setDist(Integer.parseInt(dist.getText()));
                }
        });
        this.modifie_sommet.addActionListener(e -> {
            JComboBox<String> selected_sommet = new JComboBox<>();
            for (SommetGraphe listeSommet : listesommetgraphique) {
                selected_sommet.addItem(listeSommet.getNomSommet());
            }
            JComboBox<String> selected_type = new JComboBox<>();
            for (TypeDispensaire typeDispensaire : TypeDispensaire.values()) {
                selected_type.addItem(typeDispensaire.toString());
            }
            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Sommet:"));
            panelPopup.add(selected_sommet);
            JTextField nom = new JTextField(2);
            panelPopup.add(Box.createHorizontalStrut(15));
            panelPopup.add(new JLabel("Nom:"));
            panelPopup.add(nom);
            panelPopup.add(Box.createHorizontalStrut(15));
            panelPopup.add(new JLabel("Type:"));
            panelPopup.add(selected_type);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Modifie un sommet", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                    LCGraphe.MaillonGraphe sommet_a_modifier = this.getSommet((String) selected_sommet.getSelectedItem());
                    if (nom.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Vous devez rentrer un nom", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else if (graphe.alreadyExist(nom.getText())) {
                        JOptionPane.showMessageDialog(null, "Le nom est déjà utilisé", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        sommet_a_modifier.setNom(nom.getText());
                        if (selected_type.getSelectedItem().equals("Maternité")) {
                            sommet_a_modifier.setType(TypeDispensaire.MATERNITE);
                        } else if (selected_type.getSelectedItem().equals("Nutrition")) {
                            sommet_a_modifier.setType(TypeDispensaire.NUTRITION);
                        } else if (selected_type.getSelectedItem().equals("Opératoire")) {
                            sommet_a_modifier.setType(TypeDispensaire.OPERATOIRE);
                        }
                    }
               }
               fenetreChargementGraphe.repaint();
        });
        this.afficheDataArete.addActionListener(e -> {
            JComboBox<String> selectedArrete = new JComboBox<>();
            for (AreteGraphe listeArete : listearretegraphique) {
                selectedArrete.addItem(listeArete.getAreteNom());
            }
            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Arete:"));
            panelPopup.add(selectedArrete);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                    LCGraphe.MaillonGrapheSec arrete_a_afficher = this.getArete((String) selectedArrete.getSelectedItem());
                    this.textArea.setText("La fiabilité est de : " + arrete_a_afficher.getFiab() + "\n" + "La durée est de : " + arrete_a_afficher.getDur() + "\n" + "La distance est de : " + arrete_a_afficher.getDist() + "\n" + arrete_a_afficher.getOrig() + " <-> " + arrete_a_afficher.getDest());
                }
        });
        this.afficheDataSommet.addActionListener(e -> {
            JComboBox<String> selectedSommet = new JComboBox<>();
            for (SommetGraphe listeSommet : listesommetgraphique)
                selectedSommet.addItem(listeSommet.getNomSommet());
            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Sommet:"));
            panelPopup.add(selectedSommet);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                    LCGraphe.MaillonGraphe sommet_a_afficher = this.getSommet((String) selectedSommet.getSelectedItem());
                    this.textArea.setText("Le nom est : " + sommet_a_afficher.getNom() + "\n" + "Le type est : " + sommet_a_afficher.getType());
               }
        });
        this.disp_type.addActionListener(e -> {
            JComboBox<String> type = new JComboBox<>();
            type.addItem("Matérnité");
            type.addItem("Nutrition");
            type.addItem("Opératoire");

            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Type:"));
            panelPopup.add(type);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                   switch (type.getSelectedItem().toString()) {
                       case "Matérnité":
                           this.textArea.setText("Les matérnité sont : " + this.graphe.printMaternite());
                           break;
                       case "Nutrition":
                           this.textArea.setText("Les nutrition sont : " + this.graphe.printNutrition());
                           break;
                       case "Opératoire":
                           this.textArea.setText("Les opératoire sont : " + this.graphe.printBlock());
                           break;
                   }
                }
        });
        this.afficherToutesLesAretes.addActionListener(e -> {
            this.textArea.setText(this.graphe.printAllArete() + "\n" + "Il y'a " + this.graphe.countEdges() + " Arêtes");
        });
        this.afficheVoisinsTypeDonne.addActionListener(e -> {
            JComboBox<String> type = new JComboBox<>();
            for (TypeDispensaire value : TypeDispensaire.values()) {
                type.addItem(value.toString());
            }
            JComboBox<String> Sommet = new JComboBox<>();
            for (SommetGraphe listeSommet : listesommetgraphique)
                Sommet.addItem(listeSommet.getNomSommet());

            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Sommet:"));
            panelPopup.add(Sommet);
            panelPopup.add(new JLabel("Type:"));
            panelPopup.add(type);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                   TypeDispensaire ty = TypeDispensaire.MATERNITE;
                   if (type.getSelectedItem().equals("Maternité")) {
                       ty = TypeDispensaire.MATERNITE;
                   } else if (type.getSelectedItem().equals("Nutrition")) {
                       ty = TypeDispensaire.NUTRITION;
                   } else if (type.getSelectedItem().equals("Opératoire")) {
                       ty = TypeDispensaire.OPERATOIRE;
                   }

                   this.textArea.setText(this.graphe.neigborsType((String) Sommet.getSelectedItem(), ty));
               }
        });
        this.disp_dec.addActionListener(e -> {
            this.textArea.setText("Il y'a " + this.graphe.countMaternite() + " Matérnité" + "\n" + "Il y'a " + this.graphe.countNutrition() + " Centre de Nutrition" + "\n" + "Il y'a " + this.graphe.countOperatoire() + " Bloc Opératoire");
        });
        this.disp_risque.addActionListener(e ->{
            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Seuil:"));
            JTextField seuil = new JTextField(2);
            panelPopup.add(seuil);
            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ et d'arrivé", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                   if (seuil.getText().equals("")) {
                       JOptionPane.showMessageDialog(null, "Vous devez entrer un seuil", "Erreur", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
                   if (Integer.parseInt(seuil.getText()) < 0 || Integer.parseInt(seuil.getText()) > 100) {
                       JOptionPane.showMessageDialog(null, "Le seuil doit être compris entre 0 et 100", "Erreur", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
                   this.textArea.setText("Les arrêtes à risque sont : " + this.graphe.seuil(Integer.parseInt(seuil.getText())));
                }
        });
        this.disp_comp.addActionListener(e -> {
            JComboBox<String> cible1 = new JComboBox<>();
            for (LCGraphe.MaillonGraphe listeSommet : listeSommets) {
                cible1.addItem(listeSommet.getNom());
            }
            JComboBox<String> cible2 = new JComboBox<>();
            for (LCGraphe.MaillonGraphe listeSommet : listeSommets) {
                cible2.addItem(listeSommet.getNom());
            }
            JComboBox<String> type = new JComboBox<>();
            type.addItem("Matérnité");
            type.addItem("Nutrition");
            type.addItem("Opératoire");
            JPanel panelPopup = new JPanel();
            panelPopup.add(new JLabel("Cible 1:"));
            panelPopup.add(cible1);
            panelPopup.add(Box.createHorizontalStrut(15)); // a spacer
            panelPopup.add(new JLabel("Cible 2:"));
            panelPopup.add(cible2);
            panelPopup.add(Box.createHorizontalStrut(15)); // a spacer
            panelPopup.add(new JLabel("Type:"));
            panelPopup.add(type);

            int result = JOptionPane.showConfirmDialog(null, panelPopup,"Entrer vos sommet de départ", JOptionPane.OK_CANCEL_OPTION);
               if (result == JOptionPane.OK_OPTION) {
                      String res = this.graphe.CompareTwoDistNeighbors(cible1.getSelectedItem().toString(), cible2.getSelectedItem().toString(), type.getSelectedItem().toString());
                      this.textArea.setText(res);
                }
        });
    }
}
