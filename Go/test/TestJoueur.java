/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import go.Joueur;
import go.Point2D;
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
        
        Point2D pos = joueur.askForPosition();
        System.out.println("Joueur.askForPosition() -> " + pos.toString());
    }
}
