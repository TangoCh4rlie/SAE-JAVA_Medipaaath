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
    private List<AreteADessiner> listeAreteADessiner;
    private JButton boutonQuitter;
    private JButton boutonRegenerer;
    public IhmGrapheAvecTout(LCGraphe graphe) {
        this.graphe = graphe;
        this.listeSommet = graphe.getListSommet();
        initComponents();
        initEventListener();
        this.setTitle("Graphe");
        this.setPreferredSize(new Dimension(800, 800));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {
        this.setLayout(null);
        this.listeAreteADessiner = new ArrayList<>();
//        this.boutonQuitter = new JButton("Quitter");
//        this.boutonRegenerer = new JButton("Regenerer");
        dessinGraphe();
    }

    public void dessinGraphe() {
        for (LCGraphe.MaillonGraphe m : this.listeSommet) {
            IhmSommetV2 dessinSommet = new IhmSommetV2(m);
            dessinSommet.setLocation(m.getCoordonnees());
            this.add(dessinSommet);
        }
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
//        this.boutonQuitter.addActionListener(e -> {
//            this.dispose();
//        });
//        this.boutonRegenerer.addActionListener(e -> {
//            this.dispose();
//            changeCoordonnees();
//            IhmGrapheAvecTout ihmGraphe = new IhmGrapheAvecTout(this.graphe);
//            ihmGraphe.dessinGraphe();
//        });
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
