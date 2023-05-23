package Strucutre_Prof;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IhmGraphe extends JFrame {
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listSommet;
    private JButton boutonQuitter;
    private JButton boutonRegenerer;
    private JPanel contentGraphe;

    public IhmGraphe(LCGraphe graphe) {
        this.graphe = graphe;
        this.listSommet = graphe.getListSommet();
        initComponents();
        initEventListener();
        this.setTitle("Graphe");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponents() {
//        content = new JPanel();
//        content.setLayout(new BorderLayout());
//        content.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        contentGraphe = new JPanel(null) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                initGrapheArete(g);
            }
        };
        contentGraphe.setLayout(new BorderLayout());
        contentGraphe.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        this.boutonQuitter = new JButton("Quitter");
        this.boutonRegenerer = new JButton("Regenerer");
        this.add(contentGraphe);
        this.add(boutonRegenerer, BorderLayout.NORTH);
        this.add(boutonQuitter, BorderLayout.SOUTH);
    }

    public void afficherGraphe() {
        initGrapheSommet();
    }

    private void initGrapheSommet() {
//        JPanel content = new JPanel();
//        content.setLayout(new BorderLayout());
        for (LCGraphe.MaillonGraphe m : this.listSommet) {
            JLabel sommet = new JLabel(new ImageIcon("img/sommet.png"));
            sommet.setIcon(new ImageIcon(((ImageIcon) sommet.getIcon()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            sommet.setBounds(m.getX(), m.getY(), 40, 40);
            contentGraphe.add(sommet);
        }
        this.add(contentGraphe);
    }
    private void initGrapheArete(Graphics g) {
//        JPanel content = new JPanel();
//        content.setLayout(new BorderLayout());
        for (LCGraphe.MaillonGraphe m : this.listSommet) {
            List<LCGraphe.MaillonGraphe> listSuccesseur = new ArrayList<>();
            listSuccesseur.addAll(this.graphe.getListSommetAdj(m));
            for (LCGraphe.MaillonGraphe m2 : listSuccesseur) {
                g.drawLine(m.getX() + 25, m.getY() + 25, m2.getX() + 25, m2.getY() + 25);
                contentGraphe.paintComponents(g);
            }
        }
    }
    private void initEventListener() {
        this.boutonQuitter.addActionListener(e -> {
            this.dispose();
        });
        this.boutonRegenerer.addActionListener(e -> {
            this.dispose();
            IhmGraphe ihmGraphe = new IhmGraphe(this.graphe);
            ihmGraphe.afficherGraphe();
        });
    }
}
