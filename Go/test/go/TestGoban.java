package go;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import go.Goban;
import go.Groupe;
import go.Pierre;
import go.Point2D;
import java.util.List;
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
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void testGetPierre() 
    {
        System.out.println("test : getPierre(int,int)");
        Pierre p = goban.getPierre(0, 0);
        System.out.println("goban.getPierre(0, 0) -> " + (p == null ? "null" : p.toString()));
        p = goban.getPierre(1, 1);
        System.out.println("goban.getPierre(1, 1) -> " + (p == null ? "null" : p.toString()));
        p = goban.getPierre(2, 2);
        System.out.println("goban.getPierre(2, 2) -> " + (p == null ? "null" : p.toString()));
        
    }


    @Test
    public void testGetGroupe() 
    {
        System.out.println("test : getGroupe(Pierre)");
        Groupe g = goban.getGroupe(goban.getPierre(0, 0));
        System.out.println(g.getPierres().toString());
        
    }
    
    @Test
    public void testGetGroups() 
    {
        System.out.println("test : getGroups()");
        long debut = System.nanoTime();
        List<Groupe> groupes = goban.getGroups();
        long fin = System.nanoTime();
        for(Groupe g : groupes)
        {
            System.out.println(g.getPierres().toString());
        }
        System.out.println("(took " + (fin-debut) + "ns)");
        
    }
    
    @Test
    public void testGetGroups2() 
    {
        System.out.println("test : getGroups2()");
        long debut = System.nanoTime();
        List<Groupe> groupes = goban.getGroups2();
        long fin = System.nanoTime();
        for(Groupe g : groupes)
        {
            System.out.println(g.getPierres().toString());
        }
        System.out.println("(took " + (fin-debut) + "ns)");
        
    }
    
    @Test
    public void testGetLiberte()
    {
        System.out.println("test getLiberte()");
        List<Groupe> groupes = goban.getGroups();
        for(Groupe g : groupes)
        {
            List<Point2D> lib = goban.getLiberte(g);
            System.out.println(g.getPierres().toString() + " -> " + lib.toString());
        }
    }
      
}
