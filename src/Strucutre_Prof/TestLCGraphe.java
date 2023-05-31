package Strucutre_Prof;

public class TestLCGraphe {

    public static void main(String[] args) {
        LCGraphe g = new LCGraphe("sscs");
        g.addMain("Disp_Rilleux", "Maternité");
        g.addMain("Disp_SathonayVillage", "Bloc");
        g.addMain("Disp_SathonayCamp", "Nutrition");
        g.addMain("Disp_Lyon", "Bloc");
        System.out.println(g.printBlock());
        System.out.println(g.printMaternite());
        System.out.println(g.printNutrition());
        System.out.println(g.oneDistNeighbors("Disp_Rilleux"));
    }
    
}
