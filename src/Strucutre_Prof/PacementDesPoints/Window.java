package Strucutre_Prof.PacementDesPoints;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {

//    Tout ce qui est relatif au menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
    private JMenuItem menuQuitter = new JMenuItem("Quitter");

//     Tout ce qui est relatif au contenu de la fenetre
    public JPanel content;

    public Window() {
        initComponents();
        initActionListener();
        this.pack();
    }

    private void initComponents() {
        this.setTitle("Placement des points");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,600));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.menu.add(menuOuvrir);
        this.menu.add(menuQuitter);
        this.menuBar.add(menu);
        this.setJMenuBar(this.menuBar);

        this.content = new JPanel();
        this.content.setLayout(null);
        this.content.setBorder(BorderFactory.createLineBorder(Color.red));

        this.setContentPane(this.content);
    }

    private void initActionListener() {
        this.menuQuitter.addActionListener(e -> {
            this.dispose();
        });
        this.menuOuvrir.addActionListener(e -> {
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showOpenDialog(this);
//            String path = fileChooser.getSelectedFile().getAbsolutePath();
//            TODO verifier que c'est bien un fichier .csv
//            /mnt/DA8682C68682A31D/Documents/IUT ECOLE SUP/TAFFFFFFFF/JAVA/SAE/liste-test.csv
            this.dispose();
            try {
                new WindowChargementGraphe("/mnt/DA8682C68682A31D/Documents/IUT ECOLE SUP/TAFFFFFFFF/JAVA/SAE/liste-test.csv");
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
}
