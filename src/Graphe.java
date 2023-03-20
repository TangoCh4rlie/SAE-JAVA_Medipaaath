public class Graphe {
    
    // Instanciation des Sommets
    private static Sommet A = new Sommet("A", "M");
    private static Sommet B = new Sommet("B", "O");
    private static Sommet C = new Sommet("C", "D");

    // Ajout des voisins
    public static void main(String[] args) {

        A.ajout_voisin(B);
        A.ajout_voisin(C);
        B.ajout_voisin(C);
    

    // Ajout des arêtes
        A.ajout_arrete(B, 50, 30, 40);
        A.ajout_arrete(C, 90, 134, 145);
        B.ajout_arrete(C, 10, 13, 40);

    // Affichage des voisins

        A.affiche_voisins();
        B.affiche_voisins();
        C.affiche_voisins();

    // Affichage du sommet le plus proche

    System.out.println(A.sommet_plus_proche());
    System.out.println(B.sommet_plus_proche());
    System.out.println(C.sommet_plus_proche());  
    
    // Affichage du sommet de type O le plus proche

    System.out.println(A.recherche_type("O"));

}
}
