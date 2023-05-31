package Strucutre_Prof.PacementDesPoints;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
    private JMenuItem menuQuitter = new JMenuItem("Quitter");
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

        this.menu.add(menuQuitter);
        this.menuBar.add(menu);
        this.setJMenuBar(this.menuBar);
    }

    private void initActionListener() {
        this.menuQuitter.addActionListener(e -> {
            this.dispose();
        });
        this.menuOuvrir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(this);
        });
    }
}
