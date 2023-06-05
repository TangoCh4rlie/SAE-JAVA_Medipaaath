package Strucutre_Prof;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IhmMenu extends JFrame{
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listSommet;
    private JPanel contentMenu;
    private JPanel contentGraphe;
    private JButton boutonAfficherGraphe;
    public IhmMenu(LCGraphe graphe){
        this.graphe = graphe;
        this.listSommet = graphe.getListSommet();
        initComponents();
        initEventListener();
        this.setTitle("Menu");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //(int) (Math.random() * this.sizeX), (int) (Math.random() * this.sizeY)

    private void initComponents(){
        contentMenu = new JPanel();
        contentMenu.setLayout(new BorderLayout());
        this.boutonAfficherGraphe = new JButton("Afficher le graphe");
        this.contentMenu.add(boutonAfficherGraphe, BorderLayout.CENTER);
        this.add(contentMenu);
    }
    private void initEventListener() {
        this.boutonAfficherGraphe.addActionListener(e -> {
            IhmGrapheOld ihmGraphe = new IhmGrapheOld(this.graphe);
            ihmGraphe.afficherGraphe();
        });
    }
}
