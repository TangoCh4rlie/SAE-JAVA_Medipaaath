package Strucutre_Prof;

public class TestLCGraphe {

    public static void main(String[] args) {
        LCGraphe g = new LCGraphe();
        g.addMain("Disp_Rilleux", "Maternité");
        g.addMain("Disp_SathonayVillage", "Bloc");
        g.addMain("Disp_SathonayCamp", "Nutrition");
        g.addMain("Disp_Lyon", "Bloc");
        g.addEdge("Disp_Rilleux", "Disp_SathonayCamp", 0.5, 10, 10);
        g.addEdge("Disp_Rilleux", "Disp_SathonayVillage", 0.5, 10, 10);
        g.addEdge("Disp_SathonayVillage", "Disp_SathonayCamp", 0.5, 10, 10);
        g.addEdge("Disp_SathonayCamp", "Disp_Lyon", 0.5, 10, 10);

        System.out.println(g.printBlock());
        System.out.println(g.printMaternite());
        System.out.println(g.printNutrition());
        System.out.println(g.oneDistNeighbors("Disp_Rilleux"));
    }
    
}
