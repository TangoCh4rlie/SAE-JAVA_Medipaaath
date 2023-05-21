package Strucutre_Prof;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IHM_graphe extends javax.swing.JFrame{
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listSommet;

    private int sizeX;
    private int sizeY;
    public IHM_graphe(LCGraphe graphe, int sizeX, int sizeY){
        this.graphe = graphe;
        this.listSommet = graphe.getListSommet();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        initComponents();
        this.setTitle("Graphe");
        this.setSize(sizeX, sizeY);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //(int) (Math.random() * this.sizeX), (int) (Math.random() * this.sizeY)

    private void initComponents(){
        JPanel content = new JPanel();
        this.setContentPane(content);
        content.setLayout(new BorderLayout());
        JPanel graphe = new JPanel();
        content.add(graphe, BorderLayout.CENTER);
        graphe.setLayout(null);
        for (LCGraphe.MaillonGraphe m : listSommet){
            int xA = (int) (Math.random() * this.sizeX);
            int yA = (int) (Math.random() * this.sizeY);
            List<LCGraphe.MaillonGraphe> listSuccesseur = new ArrayList<>();
            listSuccesseur.addAll(this.graphe.getListSommetAdj(m));
            JLabel sommet = new JLabel(m.getNom());
            sommet.setBounds(xA, yA, 50, 50);
            graphe.add(sommet);
//          TODO Attribuer des coordonnés a chaque sommet dans la déclaration des sommet
            for (LCGraphe.MaillonGraphe m2 : listSuccesseur){
                JLabel sommet2 = new JLabel(m2.getSommet().getNom());
                sommet2.setBounds(m2.getSommet().getPosition().getX(), m2.getSommet().getPosition().getY(), 50, 50);
                graphe.add(sommet2);
                Graphics g = graphe.getGraphics();
                g.drawLine(m.getSommet().getPosition().getX()+25, m.getSommet().getPosition().getY()+25, m2.getSommet().getPosition().getX()+25, m2.getSommet().getPosition().getY()+25);
            }
        }
    }
}
