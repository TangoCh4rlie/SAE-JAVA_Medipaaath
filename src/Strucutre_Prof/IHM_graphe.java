package Strucutre_Prof;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IHM_graphe extends JFrame{
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listSommet;
    private JPanel content;
    private JButton boutonAfficherGraphe;
    private int sizeX;
    private int sizeY;
    public IHM_graphe(LCGraphe graphe, int sizeX, int sizeY){
        this.graphe = graphe;
        this.listSommet = graphe.getListSommet();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        content = new JPanel();
        content.setLayout(new BorderLayout());
        initComponents();
        initGrapheSommet();
        initEventListener();
        this.setTitle("Graphe");
        this.setSize(sizeX, sizeY);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //(int) (Math.random() * this.sizeX), (int) (Math.random() * this.sizeY)

    private void initComponents(){
        this.boutonAfficherGraphe = new JButton("Afficher le graphe");
        this.content.add(boutonAfficherGraphe, BorderLayout.SOUTH);
        this.add(content);
    }

    private void initGrapheSommet() {
//        JPanel content = new JPanel();
//        content.setLayout(new BorderLayout());
        for (LCGraphe.MaillonGraphe m : this.listSommet) {
            JLabel sommet = new JLabel(new ImageIcon("img/sommet.png"));
            sommet.setIcon(new ImageIcon(((ImageIcon) sommet.getIcon()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            sommet.setBounds(m.getX(), m.getY(), 40, 40);
            content.add(sommet);
        }
        this.add(content);
    }
    private void initGrapheArete() {
//        JPanel content = new JPanel();
//        content.setLayout(new BorderLayout());
        for (LCGraphe.MaillonGraphe m : this.listSommet) {
            List<LCGraphe.MaillonGraphe> listSuccesseur = new ArrayList<>();
            listSuccesseur.addAll(this.graphe.getListSommetAdj(m));
            for (LCGraphe.MaillonGraphe m2 : listSuccesseur) {
                Graphics2D g = (Graphics2D) content.getGraphics();
                g.drawLine(m.getX() + 25, m.getY() + 25, m2.getX() + 25, m2.getY() + 25);
                content.paintComponents(g);
            }
        }
    }

    private void initEventListener() {
        this.boutonAfficherGraphe.addActionListener(e -> {
                initGrapheArete();
        });
    }
}
