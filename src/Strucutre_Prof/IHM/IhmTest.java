package Strucutre_Prof.IHM;

import Strucutre_Prof.LCGraphe;

import java.io.IOException;

public class IhmTest {
    public static void main(String[] args) throws IOException {
        LCGraphe graphe = new LCGraphe();
        graphe.charg();
        IhmGrapheAvecTout ihmGraphe = new IhmGrapheAvecTout(graphe);
    }
}
