package Strucutre_Prof.IHM;

import java.awt.Point;

public class AreteADessiner {
    private Point pointDepart;
    private Point pointArriver;
    public AreteADessiner(Point p1, Point p2){
        pointDepart = new Point(p1);
        pointArriver = new Point(p2);
    }
//    getters ans setters
    public Point getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(Point pointDepart) {
        this.pointDepart = pointDepart;
    }

    public Point getPointArriver() {
        return pointArriver;
    }

    public void setPointArriver(Point pointArriver) {
        this.pointArriver = pointArriver;
    }
}
