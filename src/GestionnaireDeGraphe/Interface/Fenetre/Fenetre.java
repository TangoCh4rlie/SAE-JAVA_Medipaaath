package GestionnaireDeGraphe.Interface.Fenetre;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.io.IOException;

public class Fenetre extends JFrame {

//    Tout ce qui est relatif au menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
    private JMenuItem menuQuitter = new JMenuItem("Quitter");

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
        this.menuBar.add(menu);
        this.setJMenuBar(this.menuBar);

        this.content = (JPanel) getContentPane();
        this.content.setLayout(null);
        this.content.setBorder(BorderFactory.createLineBorder(Color.red));
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
}
