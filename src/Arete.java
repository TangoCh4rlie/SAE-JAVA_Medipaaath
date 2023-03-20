public class Arete{
    private String id;
    private Sommet depart;
    private Sommet arrive;
    private int distance;
    private int fiabilite;
    private int temps;
    public Object getArrive;

    public Arete(Sommet depart, Sommet arrive, int distance, int fiabilite, int temps){
        this.depart = depart;
        this.arrive = arrive;
        this.distance = distance;
        this.fiabilite = fiabilite;
        this.temps = temps;
    }

    public String getId(){
        return this.id;
    }

    public Sommet getDepart(){
        return this.depart;
    }

    public Sommet getArrive(){
        return this.arrive;
    }

    public int getDistance(){
        return this.distance;
    }

    public int getFiabilite(){
        return this.fiabilite;
    }

    public int getTemps(){
        return this.temps;
    }

    public void setDepart(Sommet depart){
        this.depart = depart;
    }

    public void setArrive(Sommet arrive){
        this.arrive = arrive;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public void setFiabilite(int fiabilite){
        this.fiabilite = fiabilite;
    }

    public void setTemps(int temps){
        this.temps = temps;
    }


}