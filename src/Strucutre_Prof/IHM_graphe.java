
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IHM_graphe extends JFrame{
    private LCGraphe graphe;
    private List<LCGraphe.MaillonGraphe> listSommet;

    private int sizeX;
    private int sizeY;
    public IHM_graphe(LCGraphe graphe, int sizeX, int sizeY){
        this.graphe = graphe;
        this.listSommet = graphe.getListSommet();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.setTitle("Graphe");
        this.setSize(sizeX, sizeY);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        initComponents();
    }
    //(int) (Math.random() * this.sizeX), (int) (Math.random() * this.sizeY)

    private void initComponents(){
        JPanel content = new JPanel();
        this.setContentPane(content);
        content.setLayout(new BorderLayout());
        JPanel graphe = new JPanel();
        content.add(graphe, BorderLayout.CENTER);
        graphe.setLayout(null);
        Graphics g = content.getGraphics();
        for (LCGraphe.MaillonGraphe m : listSommet){
            List<LCGraphe.MaillonGraphe> listSuccesseur = new ArrayList<>(this.graphe.getListSommetAdj(m));
            JLabel sommet = new JLabel(new ImageIcon("/mnt/DA8682C68682A31D/Documents/IUT ECOLE SUP/TAFFFFFFFF/JAVA/SAE-apres-bug/java-sae/img/sommet.png"));
            sommet.setIcon(new ImageIcon(((ImageIcon) sommet.getIcon()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            sommet.setBounds(m.getX(), m.getY(), 50, 50);
            JLabel sommetNom = new JLabel(m.getNom());
            sommetNom.setBounds(m.getX()+10, m.getY()+10, 50, 50);
            graphe.add(sommet);
            for (LCGraphe.MaillonGraphe m2 : listSuccesseur) {
//                JLabel sommet2 = new JLabel(new ImageIcon("img/sommet.png"));
//                sommet2.setIcon(new ImageIcon(((ImageIcon) sommet.getIcon()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
//                sommet2.setBounds(m2.getX(), m2.getY(), 50, 50);
//                JLabel sommet2Nom = new JLabel(m2.getNom());
//                sommet2Nom.setBounds(m2.getX(), m.getY()+60, 50, 50);
//                graphe.add(sommet2);
                g.drawLine(m.getX()+25, m.getY()+25, m2.getX()+25, m2.getY()+25);
                content.paintComponents(g);
            }
        }
    }
}
