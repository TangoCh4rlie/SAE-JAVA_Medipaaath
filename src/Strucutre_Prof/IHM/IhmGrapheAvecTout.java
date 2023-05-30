package Strucutre_Prof.IHM;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IhmGrapheAvecTout extends JFrame{
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listeSommet;
    private List<Point> listeSommetADessiner;
    private JButton boutonQuitter;
    private JButton boutonRegenerer;
    private List<AreteADessiner> listeAreteADessiner;
    //private hashmap with the name of each peak name
    private HashMap<String, Point> peakName;
    public IhmGrapheAvecTout(LCGraphe graphe) {
        this.graphe = graphe;
        this.listeSommet = graphe.getListSommet();
        initComponents();
        dessineSommets();
        dessineAretes();
        this.repaint();
        initEventListener();
        this.setTitle("Graphe");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponents() {
        this.listeSommetADessiner = new ArrayList<>();
        this.peakName = new HashMap<>();
        this.listeAreteADessiner = new ArrayList<>();
        for (LCGraphe.MaillonGraphe maillonGraphe : listeSommet) {
            listeSommetADessiner.add(new Point((int) maillonGraphe.getCoordonnees().getX(), (int) maillonGraphe.getCoordonnees().getY()));
        }
        this.boutonQuitter = new JButton("Quitter");
        this.boutonRegenerer = new JButton("Regenerer");
        this.add(boutonRegenerer, BorderLayout.NORTH);
        this.add(boutonQuitter, BorderLayout.SOUTH);
    }

    public void dessineSommets() {
        IhmSommetV2 dessinSommet = new IhmSommetV2(listeSommetADessiner, listeSommet);
        this.add(dessinSommet);
    }
    private void dessineAretes() {
        for (LCGraphe.MaillonGraphe m : this.listeSommet) {
            List<LCGraphe.MaillonGraphe> listSuccesseur = new ArrayList<>();
            listSuccesseur.addAll(this.graphe.getListSommetAdj(m));
            for (LCGraphe.MaillonGraphe m2 : listSuccesseur) {
                this.listeAreteADessiner.add(new AreteADessiner(m.getCoordonnees(), m2.getCoordonnees()));
            }
        }
        IhmArete dessinArete = new IhmArete(this.listeAreteADessiner);
        this.add(dessinArete);
    }
    public void changeCoordonnees() {
        for (LCGraphe.MaillonGraphe m : this.listeSommet) {
            m.setCoordonnees(new Point((int) (Math.random() * 600), (int) (Math.random() * 600)));
        }
    }
    private void initEventListener() {
        this.boutonQuitter.addActionListener(e -> {
            this.dispose();
        });
        this.boutonRegenerer.addActionListener(e -> {
            this.dispose();
            changeCoordonnees();
            IhmGrapheAvecTout ihmGraphe = new IhmGrapheAvecTout(this.graphe);
            ihmGraphe.dessineSommets();
        });
//        Point p = new Point();
//        this.listSommet.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                p.x = e.getX();
//                p.y = e.getY();
//            }
//        });
//        this.listSommet.addMouseMotionListener(new MouseAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                int dx = e.getX() - p.x;
//                int dy = e.getY() - p.y;
//                sommet.setLocation(sommet.getX() + dx, sommet.getY() + dy);
//            }
//        });
    }
}
