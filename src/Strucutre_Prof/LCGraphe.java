import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

        MaillonGraphe(String n, String t) {
            nom = n;
            type = t;
            lVois = null;
            suiv = null;
            listed = false;
        }
    }
    
    private MaillonGraphe premier;
    
    public LCGraphe(){
        premier = null;
    }
    
    public void addMain(String ori, String t){
        MaillonGraphe nouv = new MaillonGraphe(ori,t);
        nouv.suiv= this.premier;
        this.premier = nouv;
    }
    
    public void addEdge(String o, String d, double fiab, double dist, double dur){
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
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.equals("B")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    public String printMaternite(){
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.equals("M")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    public String printNutrition(){
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            if (tmp.type.equals("N")) res+= tmp.nom + " - ";
            tmp = tmp.suiv;
        }
        return res;
    }

    public String printWays(){
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            res+= ("Trajet de "+tmp.nom + " a : ");
            MaillonGrapheSec tmp2 = tmp.lVois;
            while (tmp2 != null) {
                res += tmp2.dest + " (" + tmp2.fiab + ", " + tmp2.dist + ", " + tmp2.dur + ") -> ";
                tmp2 = tmp2.suiv;
            }
            res = res + '\n';
            tmp = tmp.suiv;
        }
        return res;
    }

    public String oneDistNeighbors(String disp){
        String res = "";
        MaillonGraphe tmp = this.premier;
        while (!tmp.nom.equals(disp)) {
            tmp = tmp.suiv;
        }
        MaillonGrapheSec tmp2 = tmp.lVois;

        while (tmp2 != null) {
            if (tmp2.dist == 1) res += tmp2.dest + " - ";
            tmp2 = tmp2.suiv;
        }
        return res;
    }


    public MaillonGraphe recherchenom(String nom){
        MaillonGraphe tmp = this.premier;
        while (tmp != null && !tmp.nom.equals(nom)) {
            tmp = tmp.suiv;
        }
        return tmp;
    }

    public void removeDuplicateEdges() {
        MaillonGraphe current = premier;
        while (current != null) {
            MaillonGrapheSec currentEdge = current.lVois;
            while (currentEdge != null) {
                MaillonGrapheSec previous = currentEdge;
                MaillonGrapheSec next = currentEdge.suiv;
                while (next != null) {
                    if (next.dest.equals(currentEdge.dest)) {
                        previous.suiv = next.suiv;
                        next = previous.suiv;
                    } else {
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
        File fr = new File("C:/Users/Haithem/Desktop/liste-successeurs.csv");
        Scanner sc = new Scanner(fr);
        int line = 0;
        while (sc.hasNext())
            {   
                String s = sc.nextLine();
                if (line == 0) {
                    s = s.substring(3);
                    line++;
                }
                String[] parts = s.split(";");
                String nom = parts[0];
                String type = null;

                this.addMain(nom,type);
                
                
        }
        sc.close();    
        File fr2 = new File("C:/Users/Haithem/Desktop/liste-adjacence-jeuEssai.csv");
        Scanner sc2 = new Scanner(fr2);
        while (sc2.hasNext())
            {
                String s = sc2.nextLine();
                String[] parts = s.split(";");
                String ori = parts[0];
                MaillonGraphe cible = recherchenom(ori);
                cible.type = parts[1];
                int i = 2;
                while (i < (parts.length)-2) {
                    if (parts[i].equals("0")){
                        i++;
                    }
                    else{
                        String[] parts2 = parts[i].split(",");
                        double fiab = Double.parseDouble(parts2[0]);
                        double dist = Double.parseDouble(parts2[1]);
                        double dur = Double.parseDouble(parts2[2]);

                        Scanner sc3 = new Scanner(fr2);
                        while(sc3.hasNext() ){
                            String s2 = sc3.nextLine();
                            String[] parts3 =  s2.split(";");
                            if(!parts3[0].equals(ori)){
                                if(parts3[i].equals(parts[i])){
                                    String dest = parts3[0];
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
        this.removeDuplicateEdges();
        return this;
    }


    
    public void resetListed(){
        MaillonGraphe tmp = this.premier;
        while (tmp != null) {
            tmp.listed = false;
            tmp = tmp.suiv;
        }
    }

    public void dijkstra(String start, String end) {
        Map<String, Double> distances = new HashMap<>();
        MaillonGraphe current = premier;
        while (current != null) {
            distances.put(current.nom, Double.POSITIVE_INFINITY);
            current = current.suiv;
        }
        distances.put(start, 0.0);
        PriorityQueue<MaillonGraphe> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> distances.get(o.nom)));
        current = premier;
        while (current != null) {
            if (current.nom.equals(start)) {
                queue.add(current);
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
                    double newDist = distances.get(current.nom) + edge.dur;
                    if (newDist < distances.get(next.nom)) {
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
            current = current.suiv;
        }
        if (current == null || distances.get(current.nom) == Double.POSITIVE_INFINITY) {
            System.out.println("No path found.");
            return;
        }
        path.add(current.nom);
        while (!current.nom.equals(start)) {
            MaillonGrapheSec edge = current.lVois;
            while (edge != null) {
                MaillonGraphe next = premier;
                while (next != null && !next.nom.equals(edge.dest)) {
                    next = next.suiv;
                }
                if (next != null && distances.get(next.nom) + edge.dur == distances.get(current.nom)) {
                    path.add(next.nom);
                    current = next;
                    break;
                }
                edge = edge.suiv;
            }
        }
        Collections.reverse(path);
        System.out.print("Plus cours chemin: " + path.get(0));
        for (int i = 1; i < path.size(); i++) {
            System.out.print(" -> " + path.get(i));
        }
        System.out.println();
        System.out.println("Distance total : " + distances.get(end));
    }
    
    public static void main(String[] args) throws IOException{
        LCGraphe g = new LCGraphe();
        g.charg();
        System.out.println(g.toString());
        System.out.println("////");
        System.out.println(g.printMaternite());
        System.out.println("////");
        g.dijkstra("S1", "S5");
    }
}