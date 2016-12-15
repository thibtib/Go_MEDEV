/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import go.Goban;
import go.Territoire;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent
 */
public class TestTerritoire {
    
    static Goban goban;
    static Territoire territoire;

    public TestTerritoire() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        goban = new Goban(9);
        goban.setPierre(0, 0, true);
        goban.setPierre(0, 1, true);
        goban.setPierre(1, 0, true);
        goban.setPierre(0, 2, true);
        goban.setPierre(1, 1, false);
        
        goban.setPierre(4, 4, false);
        goban.setPierre(4, 5, false);
        goban.setPierre(5, 4, false);
        goban.setPierre(5, 5, false);
        
        goban.setPierre(0, 6, false);
        goban.setPierre(1, 6, false);
        goban.setPierre(2, 7, false);
        goban.setPierre(2, 8, false);
        
        goban.setPierre(5, 6, true);
        goban.setPierre(5, 7, true);
        goban.setPierre(5, 8, true);
        goban.setPierre(6, 8, true);
        goban.setPierre(7, 8, true);
        goban.setPierre(8, 8, true);
        goban.setPierre(8, 7, true);
        goban.setPierre(8, 6, true);
        goban.setPierre(7, 6, true);
        goban.setPierre(6, 6, true);
        
        goban.afficheGoban();
        
        territoire = new Territoire(goban);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void mainTest()
    {
        System.out.println("test Territoire");
        
        territoire.afficheTerritoire();
    }
    
    @Test
    public void testGetTailleTerritoire()
    {
        System.out.println("Test getTailleTerritoire[Noir|Blanc]()");
        System.out.println("Taille territoire blanc = " + territoire.getTailleTerritoireBlanc());
        System.out.println("Taille territoire noir = " + territoire.getTailleTerritoireNoir());
    }
}
