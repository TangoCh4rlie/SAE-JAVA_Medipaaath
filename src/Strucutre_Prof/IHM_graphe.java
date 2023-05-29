
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
//        content.add(graphe, BorderLayout.CENTER);
//        graphe.setLayout(null);
        for (LCGraphe.MaillonGraphe m : listSommet){
            List<LCGraphe.MaillonGraphe> listSuccesseur = new ArrayList<>();
            listSuccesseur.addAll(this.graphe.getListSommetAdj(m));
            JLabel sommet = new JLabel(m.getNom());
            sommet.setBounds(m.getX(), m.getY(), 50, 50);
            graphe.add(sommet);
            for (LCGraphe.MaillonGraphe m2 : listSuccesseur){
                JLabel sommet2 = new JLabel(m2.getNom());
                sommet2.setBounds(m2.getX(), m2.getY(), 50, 50);
                graphe.add(sommet2);
                Graphics2D g = (Graphics2D) graphe.getGraphics();
                g.drawLine(m.getX()+25, m.getY()+25, m2.getX()+25, m2.getY()+25);
                graphe.paintComponents(g);
            }
        }
    }
}
