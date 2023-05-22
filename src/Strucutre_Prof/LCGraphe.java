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
        File fr2 = new File("C:/Users/Haithem/Desktop/liste-adjacence-jeuEssai.csv");
        Scanner sc2 = new Scanner(fr2);
        while (sc2.hasNext())
            {
                //Chargement des sommets
                String s = sc2.nextLine();
                String[] parts = s.split(";");
                String ori = parts[0];
                this.addMain(ori,parts[1]);
                int i = 2;
                while (i < (parts.length)-2) {
                    if (parts[i].equals("0")){
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
                            if(!parts3[0].equals(ori)){
                                if(parts3[i].equals(parts[i])){
                                    String dest = parts3[0];
                                    //Ajout de l'arrete
                                    this.addEdge(ori,dest,fiab,dist,dur);
                                }
                            }
                        }
                        sc3.close();
                        i++;
                    }
                }
        }
        sc2.close();
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
        current = premier;
        while (current != null && !current.nom.equals(end)) {
            //Parcours de la liste chainee de sommets
            current = current.suiv;
        }
        if (current == null || distances.get(current.nom) == Double.POSITIVE_INFINITY) {
            //Si le sommet n'est pas atteignable (graphe non connexe)
            System.out.println("No path found.");
            return;
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
                // ajout de 1 a chaque arrete
                count++;
                tmp2 = tmp2.suiv;
            }
            tmp = tmp.suiv;
        }
        System.out.println("Nb arretes = "+count);
    }

    public void modifier(int key){
        switch (key) {
            case 1:
                //Ajout d'un sommet
                System.out.println("Veuillez saisir le nom du sommet a ajouter :");
                Scanner sc = new Scanner(System.in);
                String nom = sc.nextLine();
                System.out.println("Veuillez saisir le type du sommet a ajouter :");
                String type = sc.nextLine();
                this.addMain(nom, type);
                break;
            case 2:
                //Suppression d'un sommet
                System.out.println("Veuillez saisir le nom du sommet a supprimer :");
                Scanner sc2 = new Scanner(System.in);
                String nom2 = sc2.nextLine();
                MaillonGraphe tmp = this.premier;
                while (tmp != null) {
                    MaillonGrapheSec tmp2 = tmp.lVois;
                    while (tmp2 != null) {
                        if (tmp2.dest.equals(nom2)) {
                            //Suppression des arretes liees au sommet
                            tmp2.dest = null;
                        }
                        tmp2 = tmp2.suiv;
                    }
                    tmp = tmp.suiv;
                }
                break;
        }
    }
   
    public static void main(String[] args) throws IOException{
        LCGraphe g = new LCGraphe();
        g.charg();
        System.out.println(g.toString());
    }

}