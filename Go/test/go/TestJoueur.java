package go;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import go.Joueur;
import go.Point2D;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent
 */
public class TestJoueur {
    
    static Joueur joueur;
    
    public TestJoueur() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        joueur = new Joueur();

    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testAskForPosition() 
    {
        System.out.println("test Joueur.askForPosition()");
        
        String[] inputs = {"12:13", "lala:0\n1:2", "Bob l'éponge 1:2", "-5:2", "0:1", "p"};
        for(String in : inputs)
        {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(in.getBytes());
            System.setIn(inputStream);
           
            Point2D pos = joueur.askForPosition();
            System.out.println("Joueur.askForPosition() -> " + (pos == null ? "null" : pos.toString()));
            
            System.setIn(System.in);

        }
    }
    
    @Test
    public void testAskForDeadStones() 
    {
        System.out.println("test Joueur.askForDeadStones()");
        
        String[] inputs = {"12:13\n14:18\n-1:0\nq", "lala:0\n1:2\nq"};
        for(String in : inputs)
        {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(in.getBytes());
            System.setIn(inputStream);

            System.out.println(in);
            List<Point2D> list_pos = joueur.askForDeadStones();
            System.out.println("Joueur.askForDeadStones() -> " + list_pos.toString());
            
            System.setIn(System.in);

        }
    
    }
}
