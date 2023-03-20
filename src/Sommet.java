import java.util.ArrayList;

public class Sommet {
    private String nom;
    private String type;
    private ArrayList<Sommet> voisins;
    private ArrayList<Arete> Arete;

    public Sommet(String nom, String type) {
        this.nom = nom;
        this.type = type;
        this.voisins = new ArrayList<Sommet>();
        this.Arete = new ArrayList<Arete>();
    }

    public void ajout_voisin(Sommet sommet2) {
        this.voisins.add(sommet2);
        sommet2.voisins.add(this);
    }

    public void ajout_arrete(Sommet sommet2, int fiabilite, int distance, int temps) {
        Arete Arete = new Arete(this, sommet2, fiabilite, distance, temps);
        Arete Arete2 = new Arete(sommet2, this, fiabilite, distance, temps);
        this.Arete.add(Arete);
        sommet2.Arete.add(Arete2);
    }

    public void affiche_voisins() {
        int i = 0;
        int longueur = this.voisins.size();
        while (i < longueur) {
            System.out.print(this.voisins.get(i).nom + " ");
            i++;
        }
        System.out.println();
    }

    public String sommet_plus_proche() {
        Arete min = this.Arete.get(0);
        int i = 0;
        int longueur = this.Arete.size();
        while (i < longueur) {
            if (this.Arete.get(i).getDistance() < min.getDistance()) {
                min = this.Arete.get(i);
            }
            i++;
        }
        return min.getArrive().nom;
    }

    public String recherche_type(String type) {
        int i = 0;
        int longueur = this.voisins.size();
        while (i < longueur) {
            if (this.voisins.get(i).type.equals(type)) {
                return this.voisins.get(i).nom;
            }
            i++;
        }
        return "Aucun sommet de ce type";
    }

    public String chemin_plus_fiable() {
        int fiabilite = this.Arete.get(0).getFiabilite();
        Arete s_fiable = this.Arete.get(0);
        int i = 0;
        int longueur = this.Arete.size();
        while (i < longueur) {
            if (this.Arete.get(i).getFiabilite() > fiabilite) {
                fiabilite = this.Arete.get(i).getFiabilite();
                s_fiable = this.Arete.get(i);
            }
            i++;
        }
        return s_fiable.getArrive().nom;
    }

    public String chemin_plus_rapide() {
        int temps = this.Arete.get(0).getTemps();
        Arete s_rapide = this.Arete.get(0);
        int i = 0;
        int longueur = this.Arete.size();
        while (i < longueur) {
            if (this.Arete.get(i).getTemps() < temps) {
                temps = this.Arete.get(i).getTemps();
                s_rapide = this.Arete.get(i);
            }
            i++;
        }
        return s_rapide.getArrive().nom;
    }
}