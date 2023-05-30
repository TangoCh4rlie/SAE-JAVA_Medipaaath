package Strucutre_Prof.IHM;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IhmArete extends JLabel {
    private List<AreteADessiner> aretes;
    public IhmArete(List<AreteADessiner> aretes){
        this.aretes = aretes;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D ) g;
        for (AreteADessiner arete : aretes) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine((int) arete.getPointDepart().getX()+15, (int) arete.getPointDepart().getY()+15, (int) arete.getPointArriver().getX()+15, (int) arete.getPointArriver().getY()+15);
        }
    }
}
