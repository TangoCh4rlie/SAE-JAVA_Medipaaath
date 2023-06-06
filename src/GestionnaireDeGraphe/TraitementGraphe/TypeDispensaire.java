package GestionnaireDeGraphe.TraitementGraphe;

import java.awt.Color;

public enum TypeDispensaire {
    MATERNITE("Maternité", Color.PINK, Color.MAGENTA),
    NUTRITION("Nutrition", Color.ORANGE, Color.YELLOW),
    OPERATOIRE("Opératoire", Color.RED, Color.PINK);

    private String caption;
    private Color couleurNormal;
    private Color couleurHover;
    TypeDispensaire(String caption, Color couleurNormal, Color couleurHover) {
        this.caption = caption;
        this.couleurNormal = couleurNormal;
        this.couleurHover = couleurHover;
    }
//    getters et setters
    public String getCaption() { return this.caption; }
    public Color getColor() { return this.couleurNormal; }
    public Color getCouleurHover() {
        return couleurHover;
    }

    public void setCaption(String caption) { this.caption = caption; }
    public void setColor(Color color) { this.couleurNormal = color; }

    public void setCouleurHover(Color couleurHover) {
        this.couleurHover = couleurHover;
    }

    @Override
    public String toString() {
        return this.caption;
    }
}
