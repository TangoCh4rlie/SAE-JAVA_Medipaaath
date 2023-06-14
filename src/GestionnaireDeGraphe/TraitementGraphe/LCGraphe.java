package GestionnaireDeGraphe.TraitementGraphe;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import java.util.logging.*;

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
    }
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
            coordonnees = new Point ((int) (Math.random() * 500), (int) (Math.random() * 500));
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

        public void supprimer() {
            /*
             * @autor : Elouan
             * @description : supprime le sommet du graphe
             * @param : void
             * @return : void
             */
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
    
    public void addMain(String ori, TypeDispensaire t){
        /*
         * @autor : Haithem
         * @description : ajoute un sommet au graphe a partir de son nom et son type
         * a la liste chainee de sommets
         * @param : String ori, String t
         * @return : void
         */

        MaillonGraphe nouv = new MaillonGraphe(ori,t);
        nouv.suiv= this.premier;
        this.premier = nouv;
    }
    
    public void addEdge(String nom, String o, String d, double fiab, double dist, double dur){

        /*
         * @autor : Haithem
         * @description : ajoute une arete au graphe a partir de son origine, sa destination,
         * sa fiabilite, sa distance et sa duree
         * @param : String o, String d, double fiab, double dist, double dur
         * @return : void
         */
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
    }

    @Override
    public String toString() {
        /*
         * @autor : Haithem
         * @description : affiche le graphe
         * @param : void
         * @return : String
         */
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

    public String printBlock(){
        /*
         * @autor : Haithem
         * @description : affiche les sommets de type Block opératoire "B"
         * @param : void
         * @return : String
         */
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.equals("B")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    public String printMaternite(){
        /*
         * @autor : Haithem
         * @description : affiche les sommets de type Maternité "M"
         * @param : void
         * @return : String
         */
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.equals("M")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    public String printNutrition(){
        /*
         * @autor : Haithem
         * @description : affiche les sommets de type Nutrition "N"
         * @param : void
         * @return : String
         */
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.equals("N")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    public String oneDistNeighbors(String disp){
        /*
         * @autor : Haithem
         * @description : affiche les voisins a 1 distance d'un sommet
         * @param : String disp
         * @return : String
         */
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (!tmp.nom.equals(disp)) {
            tmp = tmp.suiv;
        }
        MaillonGrapheSec tmp2 = tmp.lVois;

        while (tmp2 != null) {
            if (tmp2.dist == 1){
                res += tmp2.dest + " - ";
            }
            tmp2 = tmp2.suiv;
        }
        return res;
    }

    public boolean VerifyTwoDistNeighborsVerify(String dep,String arv){
        /*
         * @autor : Haithem
         * @description : verifie si deux sommets sont voisins a 2 distance
         * @param : String dep, String arv
         * @return : boolean
         */
        MaillonGraphe tmp = this.premier;
        while (!tmp.nom.equals(dep)) {
            tmp = tmp.suiv;
        }
        MaillonGrapheSec tmp2 = tmp.lVois;
        while (tmp2 != null) {
            if (tmp2.dist == 1){
                MaillonGraphe tmp3 = this.premier;
                while (!tmp3.nom.equals(tmp2.dest)) {
                    tmp3 = tmp3.suiv;
                }
                MaillonGrapheSec tmp4 = tmp3.lVois;
                while (tmp4 != null) {
                    if (tmp4.dist == 1){
                        if (tmp4.dest.equals(arv)) return true;
                    }
                    tmp4 = tmp4.suiv;
                }
            }
            tmp2 = tmp2.suiv;
        }
        return false;
    }
    public ArrayList<String> ListingTwoDistNeighbors(String dep){
        /*
         * @autor : Haithem
         * @description : liste les voisins a 2 distance d'un sommet
         * @param : String dep
         * @return : List
         */
        ArrayList<String> res = new ArrayList<String>();
        MaillonGraphe tmp = this.premier;
        while (!tmp.nom.equals(dep)) {
            tmp = tmp.suiv;
        }
        MaillonGrapheSec tmp2 = tmp.lVois;
        while (tmp2 != null) {
            if (tmp2.dist == 1){
                MaillonGraphe tmp3 = this.premier;
                while (!tmp3.nom.equals(tmp2.dest)) {
                    tmp3 = tmp3.suiv;
                }
                MaillonGrapheSec tmp4 = tmp3.lVois;
                while (tmp4 != null) {
                    if (tmp4.dist == 1){
                        res.add(tmp4.dest);
                    }
                    tmp4 = tmp4.suiv;
                }
            }
            tmp2 = tmp2.suiv;
        }
        return res;
    }

    public int TypeTwoDistNeighbors(String dep,String type){
        /*
         * @autor : Haithem
         * @description : compte le nombre de voisins de type "type" a 2 distance d'un sommet
         * @param : String dep, String type
         * @return : int
         */
        int res = 0;
        List listing = ListingTwoDistNeighbors(dep);
        for (int i = 0; i < listing.size(); i++) {
            MaillonGraphe tmp = this.premier;
            while (!tmp.nom.equals(listing.get(i))) {
                tmp = tmp.suiv;
            }
            if (tmp.type.equals(type)){
                res++;
                }
        }
        return res;
    }

    public void CompareTwoDistNeighbors(String sommet1,String sommet2,String type){
        /*
         * @autor : Haithem
         * @description : compare le nombre de voisins de type "type" a 2 distance de deux sommets
         * @param : String sommet1, String sommet2, String type
         * @return : void
         */
        int res1 = TypeTwoDistNeighbors(sommet1,type);
        int res2 = TypeTwoDistNeighbors(sommet2,type);
        if (res1 > res2){
            System.out.println("Le sommet " + sommet1 + " a plus de voisins de type " + type + " que le sommet " + sommet2);
        }
        else if (res1 < res2){
            System.out.println("Le sommet " + sommet2 + " a plus de voisins de type " + type + " que le sommet " + sommet1);
        }
        else{
            System.out.println("Les deux sommets ont le meme nombre de voisins de type " + type);
        }
        LOGGER.log( Level.INFO, "Deux sommets on était comparé" );
    }

    public MaillonGraphe recherchenom(String nom){
        /*
         * @autor : Haithem
         * @description : recherche un sommet a partir de son nom
         * @param : String nom
         * @return : MaillonGraphe
         */
        MaillonGraphe tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(nom)) {
            //Parcours de la liste chainee de sommets
            tmp = tmp.suiv;
        }
        return tmp;
    }

    public void removeFirstEdges(){
        MaillonGraphe current = premier;
        while(current!= null){
            MaillonGrapheSec as = current.lVois;
            MaillonGrapheSec np = as.suiv;
            current.lVois = np;
            current = current.suiv;
        }
    }
    public void removeDuplicateEdges() {
        /*
         * @autor : Haithem
         * @description : supprime les arretes dupliquées dans le graphe lors du chargement
         * @param : void
         * @return : void
         */
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

    public LCGraphe charg() throws IOException{
        /*
         * @autor : Haithem
         * @description : charge le graphe a partir d'un fichier csv
         * @param : void
         * @return : LCGraphe
         */
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

    public void resetListed(){
         /* @autor : Haithem
         * @description : remet a false la valeur listed de tous les sommets
         * @param : void
         * @return : void
         */
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            tmp.listed = false;
            tmp = tmp.suiv;
        }
    }

    public TypeDispensaire attribueTypeDispensaire(String type){
        TypeDispensaire typeDispensaire = null;
        switch (type) {
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

    public List dijkstracourt(String start, String end) {
        /*
         * @autor : Haithem
         * @description : applique l'algorithme de dijkstra pour trouver le plus court chemin
         * entre deux sommets
         * @param : String start, String end
         * @return : List<String,String,Double>
         * @complexite : O(n^2)
         */
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

    public List dijkstrarapide(String start, String end) {
        /*
         * @autor : Haithem
         * @description : applique l'algorithme de Dijkstra pour trouver le chemin le plus rapide entre deux sommets
         * @param : String start, String end
         * @return : List<String,String,Double>
         *
         * @complexite : O(n^2)
         */
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
                        paths.put(next.nom, newPath);
                    }
                }

                edge = edge.suiv;
            }
        }

        List<String> path = paths.get(end);
        List<String> arc = new ArrayList<>(path); // Arcs = Chemin pour le chemin le plus rapide
        Collections.reverse(arc);

        if (path == null || durations.get(end) == Double.POSITIVE_INFINITY) {
            // Si le sommet n'est pas atteignable (graphe non connexe)
            System.out.println("No path found.");
            return null;
        }

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
        LOGGER.log( Level.INFO, "Le graphe a était parcouru - plus rapide" );
        return result;
    }

    public List dijkstrafiable(String start, String end) {
        /*
         * @autor : Haithem
         * @description : applique l'algorithme de Dijkstra pour trouver le chemin le plus fiable entre deux sommets
         * @param : String start, String end
         * @return : List<String,String,Double>
         * @complexite : O(n^2)
         */
        Map<String, Double> reliabilities = new HashMap<>(); // Remplace "distances"
        Map<String, List<String>> paths = new HashMap<>(); // Nouvelle variable pour stocker les chemins
        MaillonGraphe current = premier;

        while (current != null) {
            // Initialisation des fiabilités à 0.0 (représentant l'infini)
            reliabilities.put(current.nom, 0.0);
            current = current.suiv;
        }

        reliabilities.put(start, 1.0);
        PriorityQueue<MaillonGraphe> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> reliabilities.get(o.nom)));
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
                    double newRel = reliabilities.get(current.nom) * edge.fiab;

                    if (newRel > reliabilities.get(next.nom)) {
                        // Mise à jour de la fiabilité
                        reliabilities.put(next.nom, newRel);
                        queue.remove(next);
                        queue.add(next);

                        // Mise à jour du chemin vers le sommet suivant
                        List<String> newPath = new ArrayList<>(paths.get(current.nom)); // Copie du chemin actuel
                        newPath.add(edge.nomArete); // Ajout de l'arête au chemin
                        paths.put(next.nom, newPath);
                    }
                }

                edge = edge.suiv;
            }
        }

        List<String> path = paths.get(end);
        List<String> arc = new ArrayList<>(path); // Arcs = Chemin pour le chemin le plus fiable
        Collections.reverse(arc);

        if (path == null || reliabilities.get(end) == 0.0) {
            // Si le sommet n'est pas atteignable (graphe non connexe)
            System.out.println("No path found.");
            return null;
        }

        // Affichage du chemin
        System.out.print("Chemin le plus fiable: " + path.get(0));
        for (int i = 1; i < path.size(); i++) {
            System.out.print(" -> " + path.get(i));
        }
        System.out.println();
        System.out.println("Fiabilité totale : " + reliabilities.get(end));

        // Affichage des arêtes
        System.out.print("Arcs : " + arc.get(0));
        for (int i = 1; i < arc.size(); i++) {
            System.out.print(" -> " + arc.get(i));
        }

        List result = new ArrayList();
        this.resetListed();
        result.add(path);
        result.add(arc);
        result.add(reliabilities.get(end));
        LOGGER.log( Level.INFO, "Le graphe a était parcouru - plus fiable" );
        return result;
    }

    public void countEdges(){
        /*
         * @autor : Haithem
         * @description : compte le nombre d'arretes dans le graphe
         * @param : void
         * @return : void
         * @complexite : O(n^2)
         */
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
        System.out.println("Nb arretes = "+count);
    }

    public List<MaillonGraphe> getListSommet() {
/*
         * @autor : Elouan
         * @description : retourne la liste chainee de sommets
         * @param : void
         * @return : List<MaillonGraphe>
         */
        List<MaillonGraphe> listSommet = new ArrayList<>();
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            listSommet.add(tmp);
            tmp = tmp.suiv;
        }
        return listSommet;
    }

    public HashMap<String, MaillonGrapheSec> getListAretes() {
        /*
         * @autor : Elouan
         * @description : retourne la liste chainee d'arretes
         * @param : void
         * @return : List<MaillonGrapheSec>
         */
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

    public List<MaillonGraphe> getListSommetAdj(MaillonGraphe m) {
        /*
         * @autor : Elouan
         * @description : retourne la liste chainee de sommets adjacents a un sommet
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
                listSommet.add(tmp2);
            }
            tmp = tmp.suiv;
        }
        return listSommet;
    }

    public List<MaillonGrapheSec> getListAretesAdj(MaillonGraphe m) {
        /*
         * @autor : Elouan
         * @description : retourne la liste d'aretes adjacentes a un sommet
         * @param : MaillonGraphe m
         * @return : List<MaillonGrapheSec>
         */
        List<MaillonGrapheSec> listAretes = new ArrayList<>();
        MaillonGrapheSec tmp = m.lVois;
        while (tmp != null) {
            listAretes.add(tmp);
            tmp = tmp.suiv;
        }
        return listAretes;
    }

    public List<MaillonGraphe> getListSommet2Dist(MaillonGraphe m) {
        /*
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
        /*
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
        /*
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

    public void listedarrete(MaillonGrapheSec a){
        /*
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

    public MaillonGrapheSec recherchearrete(String nom){

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


    public static void main(String[] args) throws IOException{
        LCGraphe g = new LCGraphe("./common/liste-test.csv");
        g.charg();
        System.out.println(g.toString());
        System.out.println("////");
        System.out.println("////");
        System.out.println();
        g.dijkstrarapide("S2","S5");
        g.dijkstracourt("S2","S5");
    }
}