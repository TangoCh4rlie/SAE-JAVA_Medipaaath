package GestionnaireDeGraphe.Interface.ElementDeStructure;

import GestionnaireDeGraphe.TraitementGraphe.LCGraphe;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * @author Elouan
 * @description Classe qui permet de dessiner un sommet dans une interface graphique.
 */
public class SommetGraphe extends JLabel implements MouseListener, MouseMotionListener {
    private LCGraphe.MaillonGraphe sommet;
    private String nomSommet;
    private int mouseX;
    private int mouseY;
    private Color couleurDuPoint;

    /**
     * @author Elouan
     * @description Constructeur de la classe SommetGraphe.
     * @param sommet le maillon du sommet à dessiner
     */
    public SommetGraphe(LCGraphe.MaillonGraphe sommet) {
        super();
        this.sommet = sommet;
        this.nomSommet = sommet.getNom();
        this.couleurDuPoint = sommet.getType().getColor();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * @author Elouan
     * @description Redéfinition de la méthode paintComponent pour dessiner le sommet.
     * @param g l'objet Graphics utilisé pour dessiner le sommet
     */
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
        g2d.drawString(sommet.getNom(), 5, 23);
    }

    public String getNomSommet() {
        return this.nomSommet;
    }
    public void setCouleurDuPoint(Color couleur) {
        this.couleurDuPoint = couleur;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * @author Elouan
     * @description Gestionnaire d'événements pour le pressage de souris.
     * @param e l'événement de souris
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        this.couleurDuPoint = sommet.getType().getColor().darker();
    }

    /**
     * @author Elouan
     * @description Gestionnaire d'événements pour le relâchement de souris.
     * @param e l'événement de souris
     */
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

    /**
     * @author Elouan
     * @description Gestionnaire d'événements pour le déplacement de souris.  
     * @param e l'événement de souris
     */   
    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getX() - mouseX;
        int deltaY = e.getY() - mouseY;
        int newX = this.getX() + deltaX;
        int newY = this.getY() + deltaY;
        if (newX < 0) {
        newX = 0;
        } else if (newX > getParent().getWidth() - getWidth()) {
            newX = getParent().getWidth() - getWidth();
        }
        if (newY < 0) {
            newY = 0;
        } else if (newY > getParent().getHeight() - getHeight()) {
            newY = getParent().getHeight() - getHeight();
        }
        this.setLocation(newX, newY);
        this.sommet.setCoordonnees(new Point(newX, newY));
        getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
