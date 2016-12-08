/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import go.Goban;
import go.Groupe;
import go.Pierre;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent
 */
public class TestGoban {
    
    static Goban goban;
    
    public TestGoban() {
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
        goban.afficheGoban();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    @Test
    public void testGetGroupe() 
    {
        System.out.println("testGetGroupe(Pierre)");
        Groupe g = goban.getGroupe(goban.getPierre(0, 0));
        System.out.println(g.getPierres().toString());
        
    }
}
