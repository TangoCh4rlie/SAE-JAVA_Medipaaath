package GestionnaireDeGraphe.Interface.Fenetre;

import GestionnaireDeGraphe.Interface.ElementDeStructure.AreteGraphe;
import GestionnaireDeGraphe.Interface.ElementDeStructure.SommetGraphe;
import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;
import GestionnaireDeGraphe.TraitementGraphe.TypeDispensaire;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Elouan
 * @description Classe qui représente la fenêtre principale de l'application graphique.
 */
public class Fenetre extends JFrame {

    private LCGraphe graphe;
    private HashMap<String, LCGraphe.MaillonGrapheSec> listeAretes;
    private List<LCGraphe.MaillonGraphe> listeSommets;
    private List<AreteGraphe> listearretegraphique;
    private List<SommetGraphe> listesommetgraphique;

//    Tout ce qui est relatif au menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenu ajouter = new JMenu("Ajouter/Supprimer");
    private JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
    private JMenuItem menuSauvegarder = new JMenuItem("Sauvegarder");
    private JMenuItem menuQuitter = new JMenuItem("Quitter");
    private JMenuItem aj_sommet = new JMenuItem("Ajouter un sommet");
    private JMenuItem aj_arete = new JMenuItem("Ajouter une arete");
    private JMenuItem sup_sommet = new JMenuItem("Supprimer un sommet");
    private JMenuItem sup_arete = new JMenuItem("Supprimer une arete");

//     Tout ce qui est relatif au contenu de la fenetre
    public JPanel content;

    /**
     * @author Elouan
     * @description Constructeur de la classe Fenetre.
     * @param titre le titre de la fenêtre
     */
    public Fenetre() {
        initComponents();
        initActionListener();
        this.pack();
    }

    /**
     * @author Elouan
     * @description Initialise le menu de la fenêtre.
     */
    private void initComponents() {
        this.setTitle("Placement des points");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1520,1080));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Erreur lors du chargement la librairie graphique");
        }

        this.menu.add(menuOuvrir);
        this.menu.add(menuSauvegarder);
        this.menu.add(menuQuitter);
        this.ajouter.add(aj_sommet);
        this.ajouter.add(aj_arete);
        this.ajouter.add(sup_sommet);
        this.ajouter.add(sup_arete);
        this.menuBar.add(menu);
        this.menuBar.add(ajouter);
        this.setJMenuBar(this.menuBar);

        this.content = (JPanel) getContentPane();
        this.content.setLayout(null);
        this.content.setBorder(BorderFactory.createLineBorder(Color.red));

        File newFile = new File("./common/grapheGenere.csv");
        checkIfFileExist(newFile);


        this.graphe = new LCGraphe("./common/grapheGenere.csv");
        this.listeAretes = this.graphe.getListAretes();
        this.listeSommets = this.graphe.getListSommet();
        this.listearretegraphique = new ArrayList<>();
        this.listesommetgraphique = new ArrayList<>();
    }

    /**
     * @author Elouan
     * @description Vérifie si le fichier existe, si non il le crée.
     * @param file le fichier à vérifier
     */
    public void checkIfFileExist(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                file.delete();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @author Elouan
     * @description Initialise les action listener de la fenêtre.
     */
    private void initActionListener() {
        this.menuQuitter.addActionListener(e -> {
            this.dispose();
        });
        this.menuOuvrir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new java.io.File("./common"));
            fileChooser.showOpenDialog(this);
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (path == null) return;
            if (path.equals("")) return;
            if (!path.contains(".csv")) return;
            this.dispose();
            try {
                new FenetreChargementGraphe(path);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        this.aj_sommet.addActionListener(e -> {
            JComboBox<String> type = new JComboBox<>();
            type.addItem("Matérnité");
            type.addItem("Nutrition");
            type.addItem("Opératoire");
            JTextField saisie_n = new JTextField(5);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Nom:"));
            myPanel.add(saisie_n);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Type:"));
            myPanel.add(type);

            int result = JOptionPane.showConfirmDialog(null, myPanel, "Entrer vos sommet de départ", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                TypeDispensaire typeDisp = TypeDispensaire.MATERNITE;
                if (type.getSelectedItem() == "Matérnité") {
                    typeDisp = TypeDispensaire.MATERNITE;
                } else if (type.getSelectedItem() == "Nutrition") {
                    typeDisp = TypeDispensaire.NUTRITION;
                } else if (type.getSelectedItem() == "Opératoire") {
                    typeDisp = TypeDispensaire.OPERATOIRE;
                }
                String nom = saisie_n.getText();
                System.out.println(nom);
                if (nom.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vous devez entrer un nom", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
//                Verifie si le sommet existe déjà
                for (LCGraphe.MaillonGraphe sommet : this.listeSommets) {
                    if (sommet.getNom().equals(nom)) {
                        JOptionPane.showMessageDialog(null, "Le sommet existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                boolean a = this.graphe.addMain(nom, typeDisp);
                if (!a) {
                    JOptionPane.showMessageDialog(null, "Le sommet n'a pas été ajouté", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                this.listeSommets = this.graphe.getListSommet();
                LCGraphe.MaillonGraphe nouvSommet = this.listeSommets.get(0);
                nouvSommet.setCoordonnees(new Point(0, 0));

                SommetGraphe sommetGraphe = new SommetGraphe(nouvSommet);
                sommetGraphe.setBounds(nouvSommet.getCoordonnees().x, nouvSommet.getCoordonnees().y, 35, 35);
                this.listesommetgraphique.add(sommetGraphe);
                addJLabelToContent(sommetGraphe);

                JOptionPane.showMessageDialog(null, "Le sommet a été ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.aj_arete.addActionListener(e -> {
            JComboBox<String> cible1 = new JComboBox<>();
            for (LCGraphe.MaillonGraphe listeSommet : listeSommets) {
                cible1.addItem(listeSommet.getNom());
            }
            JComboBox<String> cible2 = new JComboBox<>();
            for (LCGraphe.MaillonGraphe listeSommet : listeSommets) {
                cible2.addItem(listeSommet.getNom());
            }
            JTextField fiabilite = new JTextField(5);
            JTextField distance = new JTextField(5);
            JTextField temps = new JTextField(5);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Cible 1:"));
            myPanel.add(cible1);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Cible 2:"));
            myPanel.add(cible2);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Fiabilité:"));
            myPanel.add(fiabilite);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Distance:"));
            myPanel.add(distance);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Temps:"));
            myPanel.add(temps);
            int result = JOptionPane.showConfirmDialog(null, myPanel, "Nouveaux sommet", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String c1 = cible1.getSelectedItem().toString();
                String c2 = cible2.getSelectedItem().toString();
                if (c1.equals(c2)) {
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas ajouter un arc sur le même sommet", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (fiabilite.getText().equals("") || distance.getText().equals("") || temps.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vous devez entrer une fiabilité, une distance et un temps", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double fiab = Double.parseDouble(fiabilite.getText());
                double dist = Double.parseDouble(distance.getText());
                double temp = Double.parseDouble(temps.getText());
                int i = this.graphe.countEdges() + 1;
                String nom_edge = "A" + i;
                boolean a = this.graphe.addEdge(nom_edge,c1, c2, fiab, dist, temp);
                this.graphe.addEdge(nom_edge,c2, c1, fiab, dist, temp);
                if (a) {
                    JOptionPane.showMessageDialog(null, "L'arc a été ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "L'arc n'a pas été ajouté", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                //TODO Faire l'affichage de l'arrete
                this.listeAretes = this.graphe.getListAretes();
                LCGraphe.MaillonGrapheSec nouvArete = this.listeAretes.get(nom_edge);

                AreteGraphe areteGraphe = new AreteGraphe(nom_edge, this.graphe.rechercheNomSommet(c1), this.graphe.rechercheNomSommet(c2),  nouvArete);
                this.addListeAretesGraphique(areteGraphe);
                areteGraphe.setBounds(0, 0, getWidth(), getHeight());
                this.addJLabelToContent(areteGraphe);
                this.graphe.listedArete(nouvArete);
            }
        });
        this.sup_sommet.addActionListener(e -> {
            JComboBox choixSommet = new JComboBox<>();
            for (LCGraphe.MaillonGraphe sommet : listeSommets) {
                choixSommet.addItem(sommet.getNom());
            }
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Choix Sommet :"));
            myPanel.add(choixSommet);
            int result = JOptionPane.showConfirmDialog(null, myPanel, "Supprimer un sommet", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String c1 = choixSommet.getSelectedItem().toString();
                LCGraphe.MaillonGraphe sommet = this.graphe.rechercheNomSommet(c1);
                sommet.supprimerSommet();
                this.listeSommets = this.graphe.getListSommet();
                for (SommetGraphe sommetASup : listesommetgraphique) {
                    if (sommetASup.getNomSommet().equals(c1)) {
                        this.listesommetgraphique.remove(sommetASup);
                        this.removeJLabelToContent(sommetASup);
                        break;
                    }
                }
//                supprimer les aretes liés a ce sommet
                List< LCGraphe.MaillonGrapheSec> listAreteASup = this.graphe.getListAretesAdj(sommet);
                for (LCGraphe.MaillonGrapheSec arete : listAreteASup) {
                    arete.supprimerArete();
                    for (AreteGraphe areteASup : listearretegraphique) {
                        if (areteASup.getAreteNom().equals(arete.getNomArete())) {
                            this.listearretegraphique.remove(areteASup);
                            this.removeJLabelToContent(areteASup);
                            break;
                        }
                    }
                }
                this.listeAretes = this.graphe.getListAretes();
            }
        });
        this.sup_arete.addActionListener(e -> {
            JComboBox choixArete = new JComboBox<>();
            for (AreteGraphe arete : listearretegraphique) {
                choixArete.addItem(arete.getAreteNom());
            }
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Arete:"));
            myPanel.add(choixArete);
            int result = JOptionPane.showConfirmDialog(null, myPanel, "Supprimer une arete", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String nomArete = choixArete.getSelectedItem().toString();
                LCGraphe.MaillonGrapheSec arete = this.graphe.rechercheNomArete(nomArete);
                arete.supprimerArete();
                this.listeAretes = this.graphe.getListAretes();
                for (AreteGraphe areteASup : listearretegraphique) {
                    if (areteASup.getAreteNom().equals(nomArete)) {
                        this.listearretegraphique.remove(areteASup);
                        this.removeJLabelToContent(areteASup);
                        break;
                    }
                }
            }
        });
        this.menuSauvegarder.addActionListener(e -> {
           JFileChooser choixFichier = new JFileChooser();
           choixFichier.setDialogTitle("Sauvegarder");
           choixFichier.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
           int result = choixFichier.showOpenDialog(this);
           if (result != JFileChooser.APPROVE_OPTION) {
               return;
           }
           String chemin = choixFichier.getCurrentDirectory().getAbsolutePath();

            String nom = JOptionPane.showInputDialog(this, "Entrez le nom du fichier", "Nom", JOptionPane.QUESTION_MESSAGE);
            if (nom == null) {
                return;
            }
            File fileToSave = new File("./common/" + nom + ".csv");
            fileToSave.delete();
            try {
                fileToSave.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(fileToSave);
            this.graphe.sauvegardeFichierCsv(fileToSave);
        });
    }

    /**
     * @author Elouan
     * @description Ajoute un JLabel au JPanel
     * @param label
     */
    public void addJLabelToContent(JLabel label) {
        this.content.add(label);
        this.setContentPane(this.content);
    }

    /**
     * @author Elouan
     * @description Supprime un JLabel du JPanel
     * @param label
     */
    public void removeJLabelToContent(JLabel label) {
        this.content.remove(label);
        this.setContentPane(this.content);
    }

    public void addJPanelToContent(JPanel panel) {
        this.content.add(panel);
        this.setContentPane(this.content);
    }

    /**
     * @author Elouan
     * @description Ajoute un JMenu au JMenuBar
     * @param traitement
     */
    protected void addJMenuToMenuBar(JMenu traitement) {
        this.menuBar.add(traitement);
        this.setJMenuBar(this.menuBar);
    }

    public void setGraphe(LCGraphe graphe) {
        this.graphe = graphe;
    }
    public void setListeAretes(HashMap<String, LCGraphe.MaillonGrapheSec> arete) {
        this.listeAretes.putAll(arete);
    }
    public void setListeSommets(List<LCGraphe.MaillonGraphe> sommet) {
        this.listeSommets.addAll(sommet);
    }
    public void setListeSommetsGraphique(List<SommetGraphe> sommet) {
        this.listesommetgraphique.addAll(sommet);
    }
    public void setListeAretesGraphique(List<AreteGraphe> arete) {
        this.listearretegraphique.addAll(arete);
    }
    public void addListeSommetsGraphique(SommetGraphe sommet) {
        this.listesommetgraphique.add(sommet);
    }
    public void addListeAretesGraphique(AreteGraphe arete) {
        this.listearretegraphique.add(arete);
    }

    public LCGraphe getGraphe() {
        return graphe;
    }
    public HashMap<String, LCGraphe.MaillonGrapheSec> getListeAretes() {
        return listeAretes;
    }
    public List<LCGraphe.MaillonGraphe> getListeSommets() {
        return listeSommets;
    }
    public List<SommetGraphe> getListesommetgraphique() {
        return listesommetgraphique;
    }
    public List<AreteGraphe> getListearretegraphique() {
        return listearretegraphique;
    }
}
