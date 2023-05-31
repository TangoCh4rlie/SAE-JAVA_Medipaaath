package Strucutre_Prof.IHM;

import Strucutre_Prof.LCGraphe;

import java.io.IOException;

public class IhmTest {
    public static void main(String[] args) throws IOException {
        LCGraphe graphe = new LCGraphe("/mnt/DA8682C68682A31D/Documents/IUT ECOLE SUP/TAFFFFFFFF/JAVA/SAE/liste-test.csv");
        graphe.charg();
        IhmGrapheAvecTout ihmGraphe = new IhmGrapheAvecTout(graphe);
    }
}
