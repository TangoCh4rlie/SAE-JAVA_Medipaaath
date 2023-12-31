package GestionnaireDeGraphe.TraitementGraphe;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import java.util.logging.*;

/**
 * @autor : Elouan et Haithem
 * @description : Classe LCGraphe
 */
public class LCGraphe {
    private static final Logger LOGGER = Logger.getLogger( "logs" );
    static {
        try {
            FileHandler fileHandler = new FileHandler( "logs.txt" );
            LOGGER.addHandler( fileHandler );
        } catch( Exception exception ) {
            LOGGER.log( Level.SEVERE, "Cannot read configuration file", exception );
        }
    }



    public class MaillonGrapheSec {
        private String nomArete;
        private double fiab;
        private double dist;
        private double dur;
        private String orig;
        private String dest;
        private MaillonGrapheSec suiv;

//        relatif a l'ihm
        private Color couleur;

        private boolean listed;

        /**
         * @autor : Elouan
         * @description : constructeur de la classe MaillonGrapheSec
         * @param : String nom, double f, double dt , double dr,String o, String d
         * 
         */ 
        private MaillonGrapheSec(String nom, double f, double dt , double dr,String o, String d) {
            nomArete = nom;
            fiab = f;
            dist = dt;
            dur = dr;
            orig = o;
            dest = d;
            suiv = null;
            couleur = Color.BLACK;
            listed = false;
        }
        public boolean getListed(){
            return this.listed;
        }
        public void setListed(boolean b){
            this.listed = b;
        }
        public String getNomArete(){
            return this.nomArete;
        }
        public double getFiab(){
            return this.fiab;
        }
        public double getDist(){
            return this.dist;
        }
        public double getDur(){
            return this.dur;
        }
        public void setFiab(double f){
            this.fiab = f;
        }
        public void setDist(double d){
            this.dist = d;
        }
        public void setDur(double d){
            this.dur = d;
        }
        public String getOrig(){
            return this.orig;
        }
        public String getDest(){
            return this.dest;
        }
        public MaillonGrapheSec getSuiv(){
            return this.suiv;
        }
        public Color getCouleur() {
            return couleur;
        }
        public void setCouleur(Color couleur) {
            this.couleur = couleur;
        }
        public String toString(){
            return this.nomArete;
        }

        public void supprimerArete(){
            MaillonGraphe tmp = premier;
            while (tmp != null) {
                MaillonGrapheSec tmp2 = tmp.lVois;
                while (tmp2 != null) {
                    if (tmp2.dest.equals(this.dest)) {
                        tmp2 = tmp2.suiv;
                    } else {
                        tmp2 = tmp2.suiv;
                    }
                }
                tmp = tmp.suiv;
            }
        }


    }
        
    /**
     * @autor Haithem
     * @description : constructeur de la classe MaillonGraphe
     */
    public class MaillonGraphe {
        private String nom;
        private TypeDispensaire type;
        private MaillonGrapheSec lVois;
        private MaillonGraphe suiv;
        private boolean listed;
        private Point coordonnees;

        MaillonGraphe(String n, TypeDispensaire t) {
            nom = n;
            type = t;
            lVois = null;
            suiv = null;
            listed = false;
            coordonnees = new Point ((int) (Math.random() * 1360), (int) (Math.random() * 880));
        }

        public Point getCoordonnees() {
            return coordonnees;
        }
        public String getNom(){
            return this.nom;
        }
        public TypeDispensaire getType(){
            return this.type;
        }
        public MaillonGrapheSec getLVois(){
            return this.lVois;
        }
        public MaillonGraphe getSuiv(){
            return this.suiv;
        }
        public boolean getListed(){
            return this.listed;
        }
        public void setCoordonnees(Point p) {
            this.coordonnees = p;
        }
        public void setType(TypeDispensaire t){
            this.type = t;
        }
        public void setNom(String n){
            this.nom = n;
        }

        public String toString() {
            return nom;
        }

        /**
         * @autor : Elouan
         * @description : supprime le sommet du graphe
         * @param : void
         * @return : void
         */
        public void supprimerSommet() {

            MaillonGraphe tmp = premier;
            while (tmp != null) {
                MaillonGrapheSec tmp2 = tmp.lVois;
                while (tmp2 != null) {
                    if (tmp2.dest.equals(this.nom)) {
                        tmp2 = tmp2.suiv;
                    } else {
                        tmp2 = tmp2.suiv;
                    }
                }
                tmp = tmp.suiv;
            }
        }
    }
    
    private MaillonGraphe premier;
    private String pathFile;
    
    public LCGraphe(String pathFile){
        premier = null;
        this.pathFile = pathFile;
    }

    /**
     * @autor : Haithem
     * @description : ajoute un sommet au graphe a partir de son nom et son type
     * a la liste chainee de sommets
     * @param : String ori, String t
     * @return : void
     */
    public boolean addMain(String ori, TypeDispensaire t){
        MaillonGraphe nouv = new MaillonGraphe(ori,t);
        nouv.suiv= this.premier;
        this.premier = nouv;
        return true;
    }

    /**
     * @autor : Haithem
     * @description : ajoute une arete au graphe a partir de son origine, sa destination,
     * sa fiabilite, sa distance et sa duree
     * @param : String o, String d, double fiab, double dist, double dur
     * @return : boolean
     */
    public boolean addEdge(String nom, String o, String d, double fiab, double dist, double dur){
        MaillonGrapheSec nouv = new MaillonGrapheSec(nom, fiab, dist, dur, o, d);
        MaillonGraphe tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(o)){
            tmp = tmp.suiv;
        }
        if (tmp != null) {
            nouv.suiv = tmp.lVois;
            tmp.lVois = nouv;
        }
        
        MaillonGrapheSec nouv2 = new MaillonGrapheSec(nom, fiab, dist, dur, o, d);
        tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(d)){
            tmp = tmp.suiv;
        }
        if (tmp != null) {
            nouv2.suiv = tmp.lVois;
            tmp.lVois = nouv2;
        }
        return true;
    }

    /**
     * @autor : Haithem
     * @description : affiche le graphe
     * @param : void
     * @return : String
     */
    @Override
    public String toString() {

        String s = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            s += tmp.nom + " -> ";
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                s += tmp2.dest + " (" + tmp2.fiab + ", " + tmp2.dist + ", " + tmp2.dur + ") -> ";
                tmp2 = tmp2.suiv;
            }
            s = s + '\n';
            tmp = tmp.suiv;
        }
        return s;
    }

    /**
     * @autor : Haithem
     * @description : affiche les sommets de type Block opératoire "B"
     * @param : void
     * @return : String
     */
    public String printBlock(){

        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.getCaption().equals("Opératoire")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    /**
     * @autor : Haithem
     * @description : affiche les sommets de type Maternité "M"
     * @param : void
     * @return : String
     */
    public String printMaternite(){

        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.getCaption().equals("Maternité")){
                res+= tmp.nom + " - ";
            }
                tmp = tmp.suiv;
        }
        return res;
    }

    /**
     * @autor : Haithem
     * @description : affiche les sommets de type Nutrition "N"
     * @param : void
     * @return : String
     */
    public String printNutrition(){

        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.getCaption().equals("Nutrition")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    /**
     * @autor : Haithem
     * @description : affiche les voisins a 1 distance d'un sommet
     * @param : String disp
     * @return : String res
     */
    public String oneDistNeighbors(String disp){

        String res = "";
        MaillonGraphe tmp = this.premier;
        while (!tmp.nom.equals(disp)) {
            tmp = tmp.suiv;
        }
        MaillonGrapheSec tmp2 = tmp.lVois;

        while (tmp2 != null) {
                res += tmp2.dest + " - ";
            tmp2 = tmp2.suiv;
        }
        return res;
    }

    /**
     * @autor : Haithem
     * @description : verifie si deux sommets sont voisins avec 1 intermediaire
     * @param : String dep, String arv
     * @return : boolean
     */
    public boolean VerifyTwoDistNeighbors(String dep,String arv){

        MaillonGraphe tmp = this.premier;
        while (!tmp.nom.equals(dep)) {
            tmp = tmp.suiv;
        }
        MaillonGrapheSec tmp2 = tmp.lVois;
        while(tmp2 != null){
            MaillonGraphe tmp3 = rechercheNomSommet(tmp2.dest);
            MaillonGrapheSec tmp4 = tmp3.lVois;
            while (tmp4 != null) {
                if (tmp4.dest.equals(arv)) return true;
                tmp4 = tmp4.suiv;
            }
            tmp2 = tmp2.suiv;
        }
        return false;
    }

    /**
     * @autor : Haithem
     * @description : liste les voisins a 2 distance d'un sommet
     * @param : String dep
     * @return : List
     */
    public ArrayList<String> ListingTwoDistNeighbors(String dep) {
        ArrayList<String> listing = new ArrayList<String>();
        MaillonGraphe tmp = this.premier;
        // Recherche du sommet donné
        while (tmp != null && !tmp.nom.equals(dep)) {
            tmp = tmp.suiv;
        }

        String dist1 = this.oneDistNeighbors(dep);

        if (tmp != null) {
            // Parcourir les voisins du sommet donné
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                String voisin = tmp2.dest;

                // Rechercher le sommet voisin
                MaillonGraphe voisinSommet = rechercheNomSommet(voisin);
                if (voisinSommet != null) {
                    // Parcourir les voisins du sommet voisin
                    MaillonGrapheSec tmp3 = voisinSommet.lVois;
                    while (tmp3 != null) {
                        String voisinVoisin = tmp3.dest;

                        // Vérifier si le voisin des voisins est différent du sommet initial
                        if (!voisinVoisin.equals(dep) && !listing.contains(voisinVoisin) && !dist1.contains(voisinVoisin)) {
                            listing.add(voisinVoisin);
                        }

                        tmp3 = tmp3.suiv;
                    }
                }

                tmp2 = tmp2.suiv;
            }
        }

        return listing;
    }

    /**
     * @autor : Haithem
     * @description : compte le nombre de voisins de type "type" a 2 distance d'un sommet
     * @param : String dep, String type
     * @return : int
     */
    public int TypeTwoDistNeighbors(String dep,String type){

        int res = 0;
        List listing = ListingTwoDistNeighbors(dep);
        for (String s : (ArrayList<String>) listing) {
            MaillonGraphe tmp = rechercheNomSommet(s);
            if (tmp.type.getCaption().equals(type)) res++;
        }
        return res;
    }

    public void resetListedEdge(){
        MaillonGraphe tmp = premier;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                tmp2.setListed(false);
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
    }

    /**
     * @autor : Haithem
     * @description : compare le nombre de voisins de type "type" a 2 distance de deux sommets
     * @param : String sommet1, String sommet2, String type
     * @return : String
     */
    public String CompareTwoDistNeighbors(String sommet1,String sommet2,String type){

        int res1 = TypeTwoDistNeighbors(sommet1,type);
        int res2 = TypeTwoDistNeighbors(sommet2,type);
        String res = "";
        if (res1 > res2){
            res = "Le sommet " + sommet1 + " a plus de voisins \nde type " + type + " que le sommet " + sommet2;
        }
        else if (res1 < res2){
            res = "Le sommet " + sommet2 + " a plus de voisins \nde type " + type + " que le sommet " + sommet1;
        }
        else{
            res ="Les deux sommets ont le meme nombre de voisins de\ntype " + type;
        }
        LOGGER.log( Level.INFO, "Deux sommets on était comparé" );
        return res;
    }

    /**
     * @autor : Haithem
     * @description : recherche un sommet a partir de son nom
     * @param : String nom
     * @return : MaillonGraphe
     */
    public MaillonGraphe rechercheNomSommet(String nom){

        MaillonGraphe tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(nom)) {
            //Parcours de la liste chainee de sommets
            tmp = tmp.suiv;
        }
        return tmp;
    }

    public MaillonGrapheSec rechercheNomArete(String nom) {
        MaillonGraphe tmp1 = this.premier;
        while (tmp1 != null) {
            MaillonGrapheSec tmp2 = tmp1.lVois;
            while (tmp2 != null) {
                //Parcours de la liste chainee de sommets
                if (tmp2.nomArete.equals(nom)) {
                    return tmp2;
                }
                tmp2 = tmp2.suiv;

            }
            tmp1 = tmp1.suiv;
        }
        return null;
    }

    public String seuil(int seuil){
        //Renvoie le nom des aretes ayant une fiabilité infereure au seuil
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                if (tmp2.fiab <= seuil) {
                    if (!res.contains(tmp2.getNomArete())){
                        res += tmp2.getNomArete() + "\n";
                    }
                }
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
        return res;
    }

    /**
     * @autor : Haithem
     * @description : supprime les arretes 1er d'un sommet
     * @return : void
     */
    public void removeFirstEdges(){
        MaillonGraphe current = premier;
        while(current!= null){
            if (current != this.premier){
                MaillonGrapheSec as = current.lVois;
                MaillonGrapheSec np = as.suiv;
                current.lVois = np;
                current = current.suiv;
            }
            else{
                current = current.suiv;
            }
        }
    }

    /**
     * @autor : Haithem
     * @description : supprime les arretes dupliquées dans le graphe lors du chargement
     * @param : void
     * @return : void
     */
    public void removeDuplicateEdges() {
        MaillonGraphe current = premier;
        while (current != null) {
            String test = current.getNom();
            MaillonGrapheSec currentEdge = current.lVois;
            while (currentEdge != null) {
                MaillonGrapheSec previous = currentEdge;
                MaillonGrapheSec next = currentEdge.suiv;
                while (next != null) {
                    if (next.dest.equals(currentEdge.dest) || next.dest.equals(test)) {
                        // Suppression de l'arrete
                        previous.suiv = next.suiv;
                        next = previous.suiv;
                    } else {
                        // Passage a l'arrete suivante
                        previous = next;
                        next = next.suiv;
                    }
                }
                currentEdge = currentEdge.suiv;
            }
            current = current.suiv;
        }

    }

    /***
     * @autor : Haithem
     * @description : charge le graphe a partir d'un fichier csv
     * @param : void
     * @return : LCGraphe
     */
    public LCGraphe charg() throws IOException{

        File fr2 = new File(this.pathFile);
        Scanner sc2 = new Scanner(fr2);
        while (sc2.hasNext())
            {
                //Chargement des sommets
                String s = sc2.nextLine();
                String[] parts = s.split(";");
                String ori = parts[0];
                this.addMain(ori,attribueTypeDispensaire(parts[1]));
                int i = 2;
                while (i < (parts.length)) {
                    if(parts[i].equals("0")){
                        i++;
                    }
                    else{
                        String[] parts2 = parts[i].split(",");
                        //Chargement des arretes
                        double fiab = Double.parseDouble(parts2[0]);
                        double dist = Double.parseDouble(parts2[1]);
                        double dur = Double.parseDouble(parts2[2]);
                        Scanner sc3 = new Scanner(fr2);
                        while(sc3.hasNext() ){
                            //Parcours du fichier pour trouver les sommets de destination
                            String s2 = sc3.nextLine();
                            String[] parts3 =  s2.split(";");
                            if(parts3[0].equals(ori)) {
                                ;
                            }
                                else{
                                if(parts3[i].equals(parts[i])){
                                    String dest = parts3[0];
                                    //Ajout de l'arrete
                                    int j = i-1;
                                    this.addEdge("A"+j,ori,dest,fiab,dist,dur);
                                }
                            }
                        }
                        sc3.close();
                        i++;
                    }
                }
        }
        sc2.close();
        //Suppression des arretes dupliquée
        this.removeDuplicateEdges();
        this.removeFirstEdges();
        LOGGER.log( Level.INFO, "Le graphe a était chargé" );
        return this;
    }

    /** @autor : Haithem
     * @description : remet a false la valeur listed de tous les sommets
     * @param : void
     * @return : void
     */
    public void resetListed(){
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            tmp.listed = false;
            tmp = tmp.suiv;
        }
    }


    /**
     * @description attribue un type de dispensaire a partir d'un string
     * @autor : Haithem
     * @param type
     * @return  TypeDispensaire
     *
     */
    public TypeDispensaire attribueTypeDispensaire(String type){
        TypeDispensaire typeDispensaire = null;
        switch (type) {
            //Attribution du type de dispensaire
            case "N":
                typeDispensaire = TypeDispensaire.NUTRITION;
                break;
            case "M":
                typeDispensaire = TypeDispensaire.MATERNITE;
                break;
            case "O":
                typeDispensaire = TypeDispensaire.OPERATOIRE;
                break;
        }
        return typeDispensaire;
    }

    /**
     * @autor : Haithem
     * @description : applique l'algorithme de dijkstra pour trouver le plus court chemin entre deux sommets
     * @param : String start, String end
     * @return : List<String,String,Double>
     * @complexite : O(n^2)
     */
    public List dijkstracourt(String start, String end) {

        Map<String, Double> distances = new HashMap<>();
        MaillonGraphe current = premier;
        while (current != null) {
            //Initialisation des distances a l'infini
            distances.put(current.nom, Double.POSITIVE_INFINITY);
            current = current.suiv;
        }
        distances.put(start, 0.0);
        PriorityQueue<MaillonGraphe> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> distances.get(o.nom)));
        current = premier;
        while (current != null) {
            //Ajout des sommets a la file de priorite
            if (current.nom.equals(start)) {
                queue.add(current);
            }
            current = current.suiv;
        }

        while (!queue.isEmpty()) {
            //Relachement des arretes
            current = queue.poll();
            current.listed = true;
            MaillonGrapheSec edge = current.lVois;
            while (edge != null) {
                MaillonGraphe next = premier;
                while (next != null && !next.nom.equals(edge.dest)) {
                    //Parcours de la liste chainee de sommets
                    next = next.suiv;
                }
                if (next != null && !next.listed) {
                    //Si le sommet n'est pas encore visité
                    double newDist = distances.get(current.nom) + edge.dur;
                    if (newDist < distances.get(next.nom)) {
                        //Mise a jour de la distance
                        distances.put(next.nom, newDist);
                        queue.remove(next);
                        queue.add(next);
                    }
                }
                edge = edge.suiv;
            }
        }
        List<String> path = new ArrayList<>();
        List<String> arc = new ArrayList<>();
        current = premier;
        while (current != null && !current.nom.equals(end)) {
            //Parcours de la liste chainee de sommets
            current = current.suiv;
        }
        if (current == null || distances.get(current.nom) == Double.POSITIVE_INFINITY) {
            //Si le sommet n'est pas atteignable (graphe non connexe)
            System.out.println("No path found.");
            return null;
        }
        path.add(current.nom);
        while (!current.nom.equals(start)) {
            //Parcours du chemin
            MaillonGrapheSec edge = current.lVois;
            while (edge != null) {
                MaillonGraphe next = premier;
                while (next != null && !next.nom.equals(edge.dest)) {
                    next = next.suiv;
                }
                if (next != null && distances.get(next.nom) + edge.dur == distances.get(current.nom)) {
                    //Si la distance est la bonne
                    path.add(next.nom);
                    arc.add(edge.nomArete);
                    current = next;
                    break;
                }
                edge = edge.suiv;
            }
        }
        //Affichage du chemin
        Collections.reverse(path);
        System.out.print("Plus cours chemin: " + path.get(0));
        for (int i = 1; i < path.size(); i++) {
            System.out.print(" -> " + path.get(i));
        }
        System.out.println();
        System.out.println("Distance total : " + distances.get(end));
        //Affichage des arretes
        Collections.reverse(arc);
        System.out.print("Arcs : " + arc.get(0));
        for (int i = 1; i < arc.size(); i++) {
            System.out.print(" -> " + arc.get(i));
        }
        List result = new ArrayList();
        this.resetListed();
        result.add(path);
        result.add(arc);
        result.add(distances.get(end));
        LOGGER.log( Level.INFO, "Le graphe a était parcouru - plus court" );
        return result;

    }

    /**
     * @autor : Haithem
     * @description : applique l'algorithme de Dijkstra pour trouver le chemin le plus rapide entre deux sommets
     * @param : String start, String end
     * @return : List<String,String,Double>
     * @complexite : O(n^2)
     */
    public List dijkstrarapide(String start, String end) {

        Map<String, Double> durations = new HashMap<>(); // Remplace "distances"
        Map<String, List<String>> paths = new HashMap<>(); // Nouvelle variable pour stocker les chemins
        MaillonGraphe current = premier;

        while (current != null) {
            // Initialisation des durées à l'infini
            durations.put(current.nom, Double.POSITIVE_INFINITY);
            current = current.suiv;
        }

        durations.put(start, 0.0);
        PriorityQueue<MaillonGraphe> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> durations.get(o.nom)));
        current = premier;

        while (current != null) {
            // Ajout des sommets à la file de priorité
            if (current.nom.equals(start)) {
                queue.add(current);
                paths.put(current.nom, new ArrayList<>()); // Nouveau chemin vide pour le sommet de départ
            }
            current = current.suiv;
        }

        while (!queue.isEmpty()) {
            // Relâchement des arêtes
            current = queue.poll();
            current.listed = true;
            MaillonGrapheSec edge = current.lVois;

            while (edge != null) {
                MaillonGraphe next = premier;

                while (next != null && !next.nom.equals(edge.dest)) {
                    // Parcours de la liste chaînée de sommets
                    next = next.suiv;
                }

                if (next != null && !next.listed) {
                    // Si le sommet n'a pas encore été visité
                    double newDur = durations.get(current.nom) + edge.dur;

                    if (newDur < durations.get(next.nom)) {
                        // Mise à jour de la durée
                        durations.put(next.nom, newDur);
                        queue.remove(next);
                        queue.add(next);

                        // Mise à jour du chemin vers le sommet suivant
                        List<String> newPath = new ArrayList<>(paths.get(current.nom)); // Copie du chemin actuel
                        newPath.add(edge.nomArete); // Ajout de l'arête au chemin
                        newPath.add(next.nom);
                        paths.put(next.nom, newPath);
                    }
                }

                edge = edge.suiv;
            }
        }

        List<String> path = paths.get(end);
        List<String> arc = new ArrayList<>();
        arc.addAll(path); // Copie des éléments de path vers arc
        Collections.reverse(arc);

        if (path == null || durations.get(end) == Double.POSITIVE_INFINITY) {
            // Si le sommet n'est pas atteignable (graphe non connexe)
            System.out.println("No path found.");
            return null;
        }
        System.out.println();

        // Affichage du chemin
        System.out.print("Chemin le plus rapide: " + path.get(0));
        for (int i = 1; i < path.size(); i++) {
            System.out.print(" -> " + path.get(i));
        }
        System.out.println();
        System.out.println("Durée totale : " + durations.get(end));

        // Affichage des arêtes
        System.out.print("Arcs : " + arc.get(0));
        for (int i = 1; i < arc.size(); i++) {
            System.out.print(" -> " + arc.get(i));
        }

        List result = new ArrayList();
        this.resetListed();
        result.add(path);
        result.add(arc);
        result.add(durations.get(end));

        return result;
    }

    /**
     * @autor : Haithem
     * @description : applique l'algorithme de Dijkstra pour trouver le chemin le plus fiable entre deux sommets
     * @param : String start, String end
     * @return : List<String,String,Double>
     * @complexite : O(n^2)
     */
    public List dijkstrafiable(String start, String end) {
        Map<String, Double> reliabilities = new HashMap<>();
        Map<String, List<String>> paths = new HashMap<>();
        MaillonGraphe current = premier;

        while (current != null) {
            reliabilities.put(current.nom, 0.0);
            current = current.suiv;
        }

        reliabilities.put(start, 1.0);
        PriorityQueue<MaillonGraphe> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> reliabilities.get(o.nom)));
        current = premier;

        while (current != null) {
            if (current.nom.equals(start)) {
                queue.add(current);
                paths.put(current.nom, new ArrayList<>());
            }
            current = current.suiv;
        }

        while (!queue.isEmpty()) {
            current = queue.poll();
            current.listed = true;
            MaillonGrapheSec edge = current.lVois;

            while (edge != null) {
                MaillonGraphe next = premier;

                while (next != null && !next.nom.equals(edge.dest)) {
                    next = next.suiv;
                }

                if (next != null && !next.listed) {
                    double newRel = reliabilities.get(current.nom) * edge.fiab;

                    if (newRel > reliabilities.get(next.nom)) {
                        reliabilities.put(next.nom, newRel);
                        queue.remove(next);
                        queue.add(next);

                        List<String> newPath = new ArrayList<>(paths.get(current.nom));
                        newPath.add(next.nom); // Ajout du sommet suivant au chemin au lieu de l'arête
                        paths.put(next.nom, newPath);
                    }
                }

                edge = edge.suiv;
            }
        }

        List<String> path = paths.get(end);
        List<String> arc = new ArrayList<>(); // Liste d'arêtes vide car le chemin le plus fiable ne contient pas d'arêtes

        if (path == null || reliabilities.get(end) == 0.0) {
            System.out.println("No path found.");
            return null;
        }

        System.out.print("Chemin le plus fiable: " + path.get(0));
        for (int i = 1; i < path.size(); i++) {
            System.out.print(" -> " + path.get(i));
        }
        System.out.println();
        System.out.println("Fiabilité totale : " + reliabilities.get(end));

        System.out.println("Arcs : "); // Affichage des arêtes (vides pour le chemin le plus fiable)
        for(String s : arc) {
            System.out.println(s + " ->");
        }

        List result = new ArrayList();
        this.resetListed();
        result.add(path);
        result.add(arc);
        result.add(reliabilities.get(end));
        return result;
    }


    /**
     *@autor : Haithem
     * @description : compte le nombre de sommets dans le graphe
     * @param : void
     * @return : int
     */
    public int countSommet(){

        MaillonGraphe tmp = this.premier;
        int count = 0;
        while (tmp != null) {
            // ajout de 1 a chaque sommet
            count++;
            tmp = tmp.suiv;
        }
        return count;
    }

    /**
     * @autor : Haithem
     * @description : compte le nombre d'arretes dans le graphe
     * @param : void
     * @return : void
     * @complexite : O(n^2)
     */
    public int countEdges(){

        MaillonGraphe tmp = this.premier;
        int count = 0;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                // ajout de 1 a chaque arrete
                count++;
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
        return count;
    }

    /**
     * @autor : Haithem
     * @description : compte le nombre de maternité dans le graphe
     * @param : void
     * @return : void
     * @complexite : O(n^2)
     */
    public int countMaternite(){
            MaillonGraphe tmp = this.premier;
            int count = 0;
            while (tmp != null) {
                if(tmp.type.getCaption().equals("Maternité")){
                    count++;
                }
                tmp = tmp.suiv;
            }
            return count;
    }

    /**
     * @autor : Haithem
     * @description : compte le nombre de centre de nutrition dans le graphe
     * @param : void
     * @return : void
     * @complexite : O(n^2)
     */
    public int countNutrition(){
            MaillonGraphe tmp = this.premier;
            int count = 0;
            while (tmp != null) {
                if(tmp.type.getCaption().equals("Nutrition")){
                    count++;
                }
                tmp = tmp.suiv;
            }
            return count;
    }

    /**
     * @autor : Haithem
     * @description : compte le nombre de centre opératoires dans le graphe
     * @param : void
     * @return : void
     * @complexite : O(n^2)
     */
    public int countOperatoire(){
            MaillonGraphe tmp = this.premier;
            int count = 0;
            while (tmp != null) {
                if(tmp.type.getCaption().equals("Opératoire")){
                    count++;
                }
                tmp = tmp.suiv;
            }
            return count;
    }

    /**
     * @autor : Elouan
     * @description : retourne la liste chainee de sommets
     * @param : void
     * @return : List<MaillonGraphe>
     */
    public List<MaillonGraphe> getListSommet() {

        List<MaillonGraphe> listSommet = new ArrayList<>();
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            listSommet.add(tmp);
            tmp = tmp.suiv;
        }
        return listSommet;
    }

    /**
     * @autor : Elouan
     * @description : retourne la liste chainee d'arretes
     * @param : void
     * @return : List<MaillonGrapheSec>
     */
    public HashMap<String, MaillonGrapheSec> getListAretes() {

        HashMap<String, MaillonGrapheSec> listAretes = new HashMap<>();
        MaillonGraphe sommet1 = this.premier;
        while (sommet1 != null) {
            MaillonGrapheSec sommet2 = sommet1.lVois;
            while (sommet2 != null) {
                if (!listAretes.containsKey(sommet2.nomArete)) {
                    listAretes.put(sommet2.nomArete, sommet2);
                }
                sommet2 = sommet2.suiv;
            }
            sommet1 = sommet1.suiv;
        }
        return listAretes;
    }

    /**
     * @autor : Elouan
     * @description : retourne la liste chainee de sommets adjacents a un sommet
     * @param : MaillonGraphe m
     * @return : List<MaillonGraphe>
     */
    public List<MaillonGraphe> getListSommetAdj(MaillonGraphe m) {

        List<MaillonGraphe> listSommet = new ArrayList<>();
        MaillonGrapheSec tmp = m.lVois;
        while (tmp != null) {
            MaillonGraphe tmp2 = this.premier;
            while (tmp2 != null && !tmp2.nom.equals(tmp.dest)) {
                tmp2 = tmp2.suiv;
            }
            if (tmp2 != null) {
                listSommet.add(tmp2);
            }
            tmp = tmp.suiv;
        }
        return listSommet;
    }

    /**
     * @autor : Elouan
     * @description : retourne la liste d'aretes adjacentes a un sommet
     * @param : MaillonGraphe m
     * @return : List<MaillonGrapheSec>
     */
    public List<MaillonGrapheSec> getListAretesAdj(MaillonGraphe m) {

        List<MaillonGrapheSec> listAretes = new ArrayList<>();
        MaillonGrapheSec tmp = m.lVois;
        while (tmp != null) {
            listAretes.add(tmp);
            tmp = tmp.suiv;
        }
        return listAretes;
    }


    public List<MaillonGraphe> getListSommet2Dist(MaillonGraphe m) {
        /**
         * @autor : Elouan
         * @description : retourne la liste chainee de sommets a 2 de distance d'un sommet
         * @param : MaillonGraphe m
         * @return : List<MaillonGraphe>
         */
        List<MaillonGraphe> listSommet = new ArrayList<>();
        MaillonGrapheSec tmp = m.lVois;
        while (tmp != null) {
            MaillonGraphe tmp2 = this.premier;
            while (tmp2 != null && !tmp2.nom.equals(tmp.dest)) {
                tmp2 = tmp2.suiv;
            }
            if (tmp2 != null) {
                listSommet.addAll(this.getListSommetAdj(tmp2));
            }
            tmp = tmp.suiv;
        }
        return listSommet;
    }


    public List<MaillonGrapheSec> getListArete2Distance(MaillonGraphe m) {
        /**
         * @autor : Elouan
         * @description : retourne la liste chainee d'aretes a 2 de distance d'un sommet
         * @param : MaillonGraphe m
         * @return : List<MaillonGrapheSec>
         */
        List<MaillonGrapheSec> listAretes = new ArrayList<>();
        MaillonGrapheSec tmp = m.lVois;
        while (tmp != null) {
            MaillonGraphe tmp2 = this.premier;
            while (tmp2 != null && !tmp2.nom.equals(tmp.dest)) {
                tmp2 = tmp2.suiv;
            }
            if (tmp2 != null) {
                listAretes.addAll(this.getListAretesAdj(tmp2));
            }
            tmp = tmp.suiv;
        }
        return listAretes;
    }


    public void listedarretes(){
        /**
         * @autor : Haithem
         * @description : liste tout les arretes du graphe
         * @param : void
         * @return : void
         * @complexite : O(n^2)
         */
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                tmp2.listed = true;
                MaillonGraphe tmp3 = this.premier;
                while (tmp3 != null && !tmp3.nom.equals(tmp2.dest)) {
                    tmp3 = tmp3.suiv;
                }
                if (tmp3 != null) {
                    MaillonGrapheSec tmp4 = tmp3.lVois;
                    while (tmp4 != null && !tmp4.dest.equals(tmp.nom)) {
                        tmp4 = tmp4.suiv;
                    }
                    if (tmp4 != null) {
                        tmp4.listed = true;
                    }
                }
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
    }

    public void listedArete(MaillonGrapheSec a){
        /**
         * @autor : Haithem
         * @description : liste une arrete du graphe
         * @param : MaillonGrapheSec a
         * @return : void
         * @complexite : O(n^2)
         */
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                if (tmp2.nomArete.equals(a.nomArete)) {
                    tmp2.listed = true;
                    MaillonGraphe tmp3 = this.premier;
                    while (tmp3 != null && !tmp3.nom.equals(tmp2.dest)) {
                        tmp3 = tmp3.suiv;
                    }
                    if (tmp3 != null) {
                        MaillonGrapheSec tmp4 = tmp3.lVois;
                        while (tmp4 != null && !tmp4.dest.equals(tmp.nom)) {
                            tmp4 = tmp4.suiv;
                        }
                        if (tmp4 != null) {
                            tmp4.listed = true;
                        }
                    }
                }
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
    }

    public String printAllArete() {
        //print les arretes du graphe
        String s = "";
        MaillonGraphe tmp = this.premier;
        this.resetListedEdge();
        int i = 1;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                if (tmp2.listed == false) {
                    s += tmp2.nomArete+" ";
                    if(i%10 == 0){
                        s+="\n";
                    }
                    this.listedArete(tmp2);
                    i++;
                }
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
        return s;
    }


    public MaillonGrapheSec recherchearrete(String nom){
        /**
         * @autor : Haithem
         * @description : Renvoie l'adresse mémoire d'une arrete
         * @param : String nom
         * @return : MaillonGrapheSec
         */
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                if (tmp2.nomArete.equals(nom)) {
                    return tmp2;
                }
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
        return null;
    }

    /**
     * @autor Elouan
     * @description : sauvegarde le graphe dans un fichier csv
     * @param cheminFichier
     */
    public void sauvegardeFichierCsv(File cheminFichier) {
        try (PrintWriter writer = new PrintWriter(cheminFichier)) {
            MaillonGraphe sommet = this.premier;
            while (sommet != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(sommet.nom).append(";").append(sommet.type).append(";");

                MaillonGrapheSec arete = sommet.lVois;
                while (arete != null) {
                    stringBuilder.append(arete.dest).append(",");
                    arete = arete.suiv;
                }
                stringBuilder.append("0;");
                arete = sommet.lVois;
                while (arete != null) {
                    stringBuilder.append(arete.fiab).append(",");
                    arete = arete.suiv;
                }
                stringBuilder.append("0;");
                arete = sommet.lVois;
                while (arete != null) {
                    stringBuilder.append(arete.dur).append(",");
                    arete = arete.suiv;
                }
                stringBuilder.append("0\n");
                writer.write(stringBuilder.toString());
                sommet = sommet.suiv;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Haithem
     * @param nom
     * @return boolean
     */
    public boolean alreadyExist(String nom){
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.nom.equals(nom)) return true;
            tmp = tmp.suiv;
        }
        return false;
    }

    public String neigborsType(String nom,TypeDispensaire type) {
        //retourne les voisins d'un sommet de type type
        String s = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.nom.equals(nom)) {
                MaillonGrapheSec tmp2 = tmp.lVois;
                while (tmp2 != null) {
                    MaillonGraphe tmp3 = this.premier;
                    while (tmp3 != null && !tmp3.nom.equals(tmp2.dest)) {
                        tmp3 = tmp3.suiv;
                    }
                    if (tmp3 != null && tmp3.type.equals(type)) {
                        s += tmp3.nom + " ";
                    }
                    tmp2 = tmp2.suiv;
                }
            }
            tmp = tmp.suiv;
        }
        return s;
    }

}