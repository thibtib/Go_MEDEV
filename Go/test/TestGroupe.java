/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import go.Groupe;
import go.Pierre;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fasol
 */
public class TestGroupe {
    
    public TestGroupe() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetPierres(){
        ArrayList<Pierre> l_p = new ArrayList<Pierre>();
        Groupe g = new Groupe(l_p,true);
        assertEquals(l_p,g.getPierres());
        
        l_p.add(new Pierre(0,0,true));
        g = new Groupe(l_p, true);
        assertEquals(l_p,g.getPierres());
    }
    
    @Test
    public void testGetCouleur(){
        ArrayList<Pierre> l_p = new ArrayList<Pierre>();
        l_p.add(new Pierre(0,0,true));
        
        Groupe g = new Groupe(l_p,true);
        assertEquals(true,g.getCouleur());
        
        g = new Groupe(l_p, false);
        assertEquals(l_p,g.getPierres());
    }
}
