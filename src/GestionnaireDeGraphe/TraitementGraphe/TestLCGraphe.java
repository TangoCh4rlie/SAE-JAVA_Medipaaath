package GestionnaireDeGraphe.TraitementGraphe;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class TestLCGraphe {
        @Test
        void testage(){
            LCGraphe graphe = new LCGraphe("./common/liste-test.csv");
            try {
                graphe.charg();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals(graphe.countSommet(), 5);
            Assert.assertEquals(graphe.countEdges(), 11);
            Assert.assertEquals(graphe.VerifyTwoDistNeighbors("S1","S5"), true);
            Assert.assertEquals(graphe.VerifyTwoDistNeighbors("S1","S2"), false);
            Assert.assertEquals("S4 - S1 - ",graphe.oneDistNeighbors("S2"));
            Assert.assertEquals("S4 - S2 - S1 - ",graphe.printMaternite());
            Assert.assertEquals("S5 - ",graphe.printNutrition());
            Assert.assertEquals("S3 - ",graphe.printBlock());
            Assert.assertEquals("Les deux sommets ont le meme nombre de voisins de type Matérnité",graphe.CompareTwoDistNeighbors("S2","S5","Matérnité"));
        }

}
