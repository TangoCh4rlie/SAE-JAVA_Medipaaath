import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class LCGraphe {

    public class MaillonGrapheSec {
        private double fiab;
        private double dist;
        private double dur;
        private String dest;
        private MaillonGrapheSec suiv;

        private MaillonGrapheSec(double f, double dt , double dr, String d) {
            fiab = f;
            dist = dt;
            dur = dr;
            dest = d;
            suiv = null;
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
        public String getDest(){
            return this.dest;
        }
        public MaillonGrapheSec getSuiv(){
            return this.suiv;
        }
    }
    class MaillonGraphe {
        private String nom;
        private String type;
        private MaillonGrapheSec lVois;
        private MaillonGraphe suiv;
        private boolean listed;
        private int x;
        private int y;

        MaillonGraphe(String n, String t) {
            nom = n;
            type = t;
            lVois = null;
            suiv = null;
            listed = false;
            x = ThreadLocalRandom.current().nextInt(0, 500);
            y = ThreadLocalRandom.current().nextInt(0,500);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getNom(){
            return this.nom;
        }
        public String getType(){
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
    }
    
    private MaillonGraphe premier;
    
    public LCGraphe(){
        premier = null;
    }
    
    public void addMain(String ori, String t){
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
    
    public void addEdge(String o, String d, double fiab, double dist, double dur){

        /*
         * @autor : Haithem
         * @description : ajoute une arete au graphe a partir de son origine, sa destination,
         * sa fiabilite, sa distance et sa duree
         * @param : String o, String d, double fiab, double dist, double dur
         * @return : void
         */
        MaillonGrapheSec nouv = new MaillonGrapheSec(fiab, dist, dur, d);
        MaillonGraphe tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(o)){
            tmp = tmp.suiv;
        }
        if (tmp != null) {
            nouv.suiv = tmp.lVois;
            tmp.lVois = nouv;
        }
        
        MaillonGrapheSec nouv2 = new MaillonGrapheSec(fiab, dist, dur, o);
        tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(d)){
            tmp = tmp.suiv;
        }
        if (tmp != null) {
            nouv2.suiv = tmp.lVois;
            tmp.lVois = nouv2;
        }
    }

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


    public MaillonGraphe recherchenom(String nom){
        /*
         * @autor : Haithem
         * @description : recherche un sommet a partir de son nom
         * @param : String nom
         * @return : MaillonGraphe
         */
        MaillonGraphe tmp = this.premier;
        while (tmp != null && tmp.nom.equals(nom)) {
            //Parcours de la liste chainee de sommets
            tmp = tmp.suiv;
        }
        return tmp;
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
            MaillonGrapheSec currentEdge = current.lVois;
            while (currentEdge != null) {
                MaillonGrapheSec previous = currentEdge;
                MaillonGrapheSec next = currentEdge.suiv;
                while (next != null) {
                    if (next.dest.equals(currentEdge.dest)) {
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
        File Fichier = new File("C:\\Users\\Haithem\\Desktop\\java-sae\\src\\liste-adjacence-jeuEssai.csv");
        Scanner Lecteur = new Scanner(Fichier);
        while (Lecteur.hasNext())
            {
                //Chargement des sommets
                String Ligne = Lecteur.nextLine();
                String[] decoupage = Ligne.split(";");
                String origine = decoupage[0];
                this.addMain(origine,decoupage[1]);
                int i = 2;
                while (i < (decoupage.length)-2) {
                    if (decoupage[i].equals("0")){
                        i++;
                    }
                    else{
                        String[] parts2 = decoupage[i].split(",");
                        //Chargement des arretes
                        double fiabilite = Double.parseDouble(parts2[0]);
                        double distance = Double.parseDouble(parts2[1]);
                        double duree = Double.parseDouble(parts2[2]);
                        Scanner Lecteur2 = new Scanner(Fichier);
                        while(Lecteur2.hasNext() ){
                            //Parcours du fichier pour trouver les sommets de destination
                            String Ligne2 = Lecteur2.nextLine();
                            String[] decoupage2 =  Ligne2.split(";");
                            if(!decoupage2[0].equals(origine)){
                                if(decoupage2[i].equals(decoupage[i])){
                                    String dest = decoupage2[0];
                                    //Ajout de l'arrete
                                    this.addEdge(origine,dest,fiabilite,distance,duree);
                                }
                            }
                        }
                        Lecteur2.close();
                        i++;
                    }
                }
        }
        Lecteur.close();
        //Suppression des arretes dupliquées
        this.removeDuplicateEdges();
        return this;
    }

    public void resetListed(){
        /*
         * @autor : Haithem
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

    public void dijkstra(String start, String end) {
        /*
         * @autor : Haithem
         * @description : applique l'algorithme de dijkstra pour trouver le plus court chemin
         * entre deux sommets
         * @param : String start, String end
         * @return : void
         */
        Map<String, Double> distances = new HashMap<>();
        MaillonGraphe Courant = premier;
        while (Courant != null) {
            //Initialisation des distances a l'infini
            distances.put(Courant.nom, Double.POSITIVE_INFINITY);
            Courant = Courant.suiv;
        }
        distances.put(start, 0.0);
        PriorityQueue<MaillonGraphe> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> distances.get(o.nom)));
        Courant = premier;
        while (Courant != null) {
            //Ajout des sommets a la file de priorite
            if (Courant.nom.equals(start)) {
                queue.add(Courant);
            }
            Courant = Courant.suiv;
        }

        while (!queue.isEmpty()) {
            //Relachement des arretes
            Courant = queue.poll();
            Courant.listed = true;
            MaillonGrapheSec edge = Courant.lVois;
            while (edge != null) {
                MaillonGraphe suiv = premier;
                while (suiv != null && !suiv.nom.equals(edge.dest)) {
                    //Parcours de la liste chainee de sommets
                    suiv = suiv.suiv;
                }
                if (suiv != null && !suiv.listed) {
                    //Si le sommet n'est pas encore visité
                    double newDist = distances.get(Courant.nom) + edge.dur;
                    if (newDist < distances.get(suiv.nom)) {
                        //Mise a jour de la distance
                        distances.put(suiv.nom, newDist);
                        queue.remove(suiv);
                        queue.add(suiv);
                    }
                }
                edge = edge.suiv;
            }
        }
        List<String> path = new ArrayList<>();
        Courant = premier;
        while (Courant != null && !Courant.nom.equals(end)) {
            //Parcours de la liste chainee de sommets
            Courant = Courant.suiv;
        }
        if (Courant == null || distances.get(Courant.nom) == Double.POSITIVE_INFINITY) {
            //Si le sommet n'est pas atteignable (graphe non connexe)
            System.out.println("No path found.");
            return;
        }
        path.add(Courant.nom);
        while (!Courant.nom.equals(start)) {
            //Parcours du chemin
            MaillonGrapheSec edge = Courant.lVois;
            while (edge != null) {
                MaillonGraphe next = premier;
                while (next != null && !next.nom.equals(edge.dest)) {
                    next = next.suiv;
                }
                if (next != null && distances.get(next.nom) + edge.dur == distances.get(Courant.nom)) {
                    //Si la distance est la bonne
                    path.add(next.nom);
                    Courant = next;
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
    }
    

    public void countEdges(){
        /*
         * @autor : Haithem
         * @description : compte le nombre d'arretes dans le graphe
         * @param : void
         * @return : void
         */
        MaillonGraphe tmp = this.premier;
        int count = 0;
        while (tmp != null) {
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
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


    public MaillonGraphe recherche_MaillonGraphe(String nom){
        MaillonGraphe tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(nom)) {
            //Parcours de la liste chainee de sommets
            tmp = tmp.suiv;
        }
        if(tmp.nom.equals(nom)){
            return tmp;
        }
        else{
            return null;
        }
    }

    public void ajout_arrete(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom du sommet d'origine : ");
        String origine = sc.nextLine();
        System.out.println("Veuillez saisir le nom du sommet de destination : ");
        String dest = sc.nextLine();
        System.out.println("Veuillez saisir la fiabilite de l'arrete : ");
        double fiab = sc.nextDouble();
        sc.nextLine();
        System.out.println("Veuillez saisir la distance de l'arrete : ");
        double dist = sc.nextDouble();
        sc.nextLine();
        System.out.println("Veuillez saisir la duree de l'arrete : ");
        double dur = sc.nextDouble();
        sc.nextLine();
        sc.close();
        this.addEdge(origine, dest, fiab, dist, dur);
        System.out.println("Arrete ajoutee avec succes");
    }

    public static void main(String[] args) throws IOException{
        LCGraphe g = new LCGraphe();
        g.charg();
        System.out.println(g.toString());
        System.out.println("////");
        System.out.println("////");
        System.out.println("////");
        g.countEdges();
        g.addMain("S60","M");
        g.addMain("S61", "M");
        g.ajout_arrete();
        System.out.println(g.toString());
    }
}