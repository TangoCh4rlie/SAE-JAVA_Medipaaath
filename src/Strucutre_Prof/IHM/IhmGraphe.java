package Strucutre_Prof.IHM;

import Strucutre_Prof.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class IhmGraphe extends JFrame{
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listeSommet;
    private List<Point> listeSommetADessiner;
    private JButton boutonQuitter;
    private JButton boutonRegenerer;
    private JPanel contentGraphe;

    public IhmGraphe(LCGraphe graphe) {
        this.graphe = graphe;
        this.listeSommet = graphe.getListSommet();
        initComponents();
        dessineSommets();
        initEventListener();
        this.setTitle("Graphe");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponents() {
        this.listeSommetADessiner = new ArrayList<>();
        for (LCGraphe.MaillonGraphe maillonGraphe : listeSommet) {
            listeSommetADessiner.add(new Point(maillonGraphe.getX(), maillonGraphe.getY()));
        }
        this.boutonQuitter = new JButton("Quitter");
        this.boutonRegenerer = new JButton("Regenerer");
        this.add(boutonRegenerer, BorderLayout.NORTH);
        this.add(boutonQuitter, BorderLayout.SOUTH);
    }

    public void dessineSommets() {
        IhmSommetV2 dessinSommet = new IhmSommetV2(listeSommetADessiner, listeSommet);
//        contentGraphe = new JPanel();
//        contentGraphe.setLayout(null);
//        contentGraphe.add(dessinSommet);
        this.add(dessinSommet);

//        JPanel contentsommet = new JPanel();
//        contentsommet.setLayout(null);
//        IhmSommet sommet1 = new IhmSommet(this.listeSommet.get(0), this.listeSommetADessiner, this.listeSommet.get(0).getNom());
//        this.listeSommetADessiner.add(sommet1);
//        IhmSommet sommet2 = new IhmSommet(this.listeSommet.get(1), this.listeSommetADessiner, this.listeSommet.get(1).getNom());
//        this.listeSommetADessiner.add(sommet2);
//        IhmSommet sommet3 = new IhmSommet(this.listeSommet.get(2), this.listeSommetADessiner);
//        this.listeSommetADessiner.add(sommet3);
//        IhmSommet sommet4 = new IhmSommet(this.listeSommet.get(3), this.listeSommetADessiner);
//        this.listeSommetADessiner.add(sommet4);
//        IhmSommet sommet5 = new IhmSommet(this.listeSommet.get(4), this.listeSommetADessiner);
//        this.listeSommetADessiner.add(sommet5);
//        sommet1.setLocation(this.listeSommet.get(0).getX(),this.listeSommet.get(0).getY());
//        contentsommet.add(sommet1);
//        sommet1.setLocation(this.listeSommet.get(1).getX(),this.listeSommet.get(1).getY());
//        contentsommet.add(sommet2);
//        this.add(contentsommet, BorderLayout.CENTER);
//        this.add(sommet3);
//        this.add(sommet4);
//        this.add(sommet5);

//        for (LCGraphe.MaillonGraphe m : this.listeSommet) {
//            IhmSommet sommet = new IhmSommet(m);
//            this.listeSommetADessiner.add(sommet);
//            this.add(sommet);
//        }
    }
    private void initGrapheArete(Graphics g) {
//        JPanel content = new JPanel();
//        content.setLayout(new BorderLayout());
        for (LCGraphe.MaillonGraphe m : this.listeSommet) {
            List<LCGraphe.MaillonGraphe> listSuccesseur = new ArrayList<>();
            listSuccesseur.addAll(this.graphe.getListSommetAdj(m));
            for (LCGraphe.MaillonGraphe m2 : listSuccesseur) {
                g.drawLine(m.getX() + 25, m.getY() + 25, m2.getX() + 25, m2.getY() + 25);
                contentGraphe.paintComponents(g);
            }
        }
    }
    public void changeCoordonnees() {
        for (LCGraphe.MaillonGraphe m : this.listeSommet) {
            m.setX((int) (Math.random() * 400));
            m.setY((int) (Math.random() * 400));
        }
    }
    private void initEventListener() {
        this.boutonQuitter.addActionListener(e -> {
            this.dispose();
        });
        this.boutonRegenerer.addActionListener(e -> {
            this.dispose();
            changeCoordonnees();
            IhmGraphe ihmGraphe = new IhmGraphe(this.graphe);
            ihmGraphe.dessineSommets();
        });
        Point p = new Point();
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
