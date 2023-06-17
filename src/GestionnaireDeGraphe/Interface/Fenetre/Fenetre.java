package GestionnaireDeGraphe.Interface.Fenetre;

import GestionnaireDeGraphe.Interface.ElementDeStructure.AreteGraphe;
import GestionnaireDeGraphe.Interface.ElementDeStructure.SommetGraphe;
import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;
import GestionnaireDeGraphe.TraitementGraphe.TypeDispensaire;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fenetre extends JFrame {

    private LCGraphe graphe;
    private HashMap<String, LCGraphe.MaillonGrapheSec> listeAretes;
    private List<LCGraphe.MaillonGraphe> listeSommets;
    private List<AreteGraphe> listearretegraphique;
    private List<SommetGraphe> listesommetgraphique;

//    Tout ce qui est relatif au menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenu ajouter = new JMenu("Ajouter");
    private JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
    private JMenuItem menuQuitter = new JMenuItem("Quitter");
    private JMenuItem aj_sommet = new JMenuItem("Ajouter un sommet");
    private JMenuItem aj_arete = new JMenuItem("Ajouter une arete");

//     Tout ce qui est relatif au contenu de la fenetre
    public JPanel content;

    public Fenetre() {
        initComponents();
        initActionListener();
        this.pack();
    }

    private void initComponents() {
        this.setTitle("Placement des points");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,800));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Erreur lors du chargement la librairie graphique");
        }

        this.menu.add(menuOuvrir);
        this.menu.add(menuQuitter);
        this.ajouter.add(aj_sommet);
        this.ajouter.add(aj_arete);
        this.menuBar.add(menu);
        this.menuBar.add(ajouter);
        this.setJMenuBar(this.menuBar);

        this.content = (JPanel) getContentPane();
        this.content.setLayout(null);
        this.content.setBorder(BorderFactory.createLineBorder(Color.red));

        File newFile = new File("./common/grapheGenere.csv");
        newFile.delete();

        this.graphe = new LCGraphe("./common/grapheGenere.csv");
        this.listeAretes = this.graphe.getListAretes();
        this.listeSommets = this.graphe.getListSommet();
        this.listearretegraphique = new ArrayList<>();
        this.listesommetgraphique = new ArrayList<>();
    }

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
                TypeDispensaire val = TypeDispensaire.MATERNITE;
                if (type.getSelectedItem() == "Matérnité") {
                    val = TypeDispensaire.MATERNITE;
                } else if (type.getSelectedItem() == "Nutrition") {
                    val = TypeDispensaire.NUTRITION;
                } else if (type.getSelectedItem() == "Opératoire") {
                    val = TypeDispensaire.OPERATOIRE;
                }
                String nom = saisie_n.getText();
                if (nom.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vous devez entrer un nom", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean a = this.graphe.addMain(nom, val);
                if (a) {
                    JOptionPane.showMessageDialog(null, "Le sommet a été ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Le sommet n'a pas été ajouté", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
            //TODO AFFICHER LE GRAPHE AVEC SON NOUVEAUX POINTS
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
                String nom_edge = "a" + i;
                boolean a = this.graphe.addEdge(nom_edge,c1, c2, fiab, dist, temp);
                this.graphe.addEdge(nom_edge,c2, c1, fiab, dist, temp);
                if (a) {
                    JOptionPane.showMessageDialog(null, "L'arc a été ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "L'arc n'a pas été ajouté", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                //TODO Faire l'affichage de l'arrete
            }
        });
    }

    public void addJLabelToContent(JLabel label) {
        this.content.add(label);
        this.setContentPane(this.content);
    }
    public void addJPanelToContent(JPanel panel) {
        this.content.add(panel);
        this.setContentPane(this.content);
    }

    protected void addJMenuToMenuBar(JMenu traitement) {
        this.menuBar.add(traitement);
        this.setJMenuBar(this.menuBar);
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
