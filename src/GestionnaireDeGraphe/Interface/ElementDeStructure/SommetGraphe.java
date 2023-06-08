package GestionnaireDeGraphe.Interface.ElementDeStructure;

import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SommetGraphe extends JLabel implements MouseListener, MouseMotionListener {
    private LCGraphe.MaillonGraphe sommet;
    private int mouseX;
    private int mouseY;
    private Color couleurDuPoint;
    private JToolTip toolTip;
    public SommetGraphe(LCGraphe.MaillonGraphe sommet) {
        super();
        this.sommet = sommet;
        this.couleurDuPoint = sommet.getType().getColor();
        addMouseListener(this);
        addMouseMotionListener(this);
        initActionListener();
    }

    private void initActionListener() {

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.couleurDuPoint);
        g2d.fillOval(2, 2, 30, 30);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(2,2,30,30);
//            TODO faire un label pour le nom au lieux de faire un drawString
        g2d.drawString(sommet.getNom(), 5, 23);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        check if it's a right click
        if (SwingUtilities.isRightMouseButton(e)) {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem supprimer = new JMenuItem("Supprimer");
            popupMenu.add(supprimer);
            popupMenu.show(this, e.getX(), e.getY());
            supprimer.addActionListener(e1 -> {
                this.sommet.supprimer();
                getParent().repaint();
            });
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        this.couleurDuPoint = sommet.getType().getColor().darker();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.couleurDuPoint = sommet.getType().getColor();
        getParent().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getX() - mouseX;
        int deltaY = e.getY() - mouseY;
        int newX = this.getX() + deltaX;
        int newY = this.getY() + deltaY;
        this.setLocation(newX, newY);
        this.sommet.setCoordonnees(new Point(newX, newY));
        getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
