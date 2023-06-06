package GestionnaireDeGraphe.TraitementGraphe;

import java.awt.Color;

public enum TypeDispensaire {
    MATERNITE("Maternité", Color.PINK),
    NUTRITION("Nutrition", Color.ORANGE),
    OPERATOIRE("Opératoire", Color.RED);

    private String caption;
    private Color color;
    TypeDispensaire(String caption, Color color) {
        this.caption = caption;
        this.color = color;
    }
//    getters et setters
    public String getCaption() { return this.caption; }
    public Color getColor() { return this.color; }
    public void setCaption(String caption) { this.caption = caption; }
    public void setColor(Color color) { this.color = color; }

    @Override
    public String toString() {
        return this.caption;
    }
}
