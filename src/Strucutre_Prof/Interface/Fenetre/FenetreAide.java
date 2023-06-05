package Strucutre_Prof.Interface.Fenetre;

import javax.swing.*;
import java.awt.*;

public class FenetreAide extends JFrame {
    public FenetreAide() {
        initComponent();
    }

    private void initComponent() {
        this.setTitle("Placement des points");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(250,400));
        this.pack();
        this.setVisible(true);
    }
}
