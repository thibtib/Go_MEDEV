/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent
 */
public class Goban {
    
    /**
     * La plateau de jeu
     */
    private Pierre[][] goban;
    
    /**
     * Construit un plateau de jeu de la taille donnée
     * @param taille 
     */
    Goban(int taille)
    {
        goban = new Pierre[taille][taille];
    }
    
    
    /**
     * Renvoie la taille du plateau
     * @return 
     */
    public int getTaille()
    {
        return goban.length;
    }
    
    /**
     * Renvoie la liste des groupes sur le goban
     * @return 
     */
    public List<Groupe> getGroups()
    {
        List<Groupe> ret = new ArrayList<Groupe>();
        
        for(int y = 0; y < getTaille(); y++)
        {
            for(int x = 0; x < getTaille(); x++)
            {
                Pierre p = goban[x][y];
                if(p == null) {
                    continue;
                }
                Groupe g = getGroupe(p);
                boolean ok = true;
                for(Groupe gg : ret) {
                    if(gg.equals(g)) {
                        ok = false;
                        break;
                    }
                }
                
                if(ok) {
                    ret.add(g);
                }
            }
        }
        
        return ret;
    }
    
    /**
     * Renvoie le groupe de la pierre.
     * @param p
     * @return 
     */
    public Groupe getGroupe(Pierre p)
    {
        ArrayList<Pierre> g = new ArrayList<Pierre>();
        g.add(p);
        
        // file des positions a explorer
        List<Pierre> list_exploration = new ArrayList<Pierre>();
        list_exploration.add(p);
        while(!list_exploration.isEmpty()) {
            Pierre pe = list_exploration.get(0);
            list_exploration.remove(0);
            
            List<Pierre> p_adjacentes = new ArrayList<Pierre>();
            if(pe.x > 0) { p_adjacentes.add(goban[pe.x-1][pe.y]); }
            if(pe.x < getTaille() - 1) { p_adjacentes.add(goban[pe.x+1][pe.y]); }
            if(pe.y > 0) { p_adjacentes.add(goban[pe.x][pe.y-1]); }
            if(pe.y < getTaille() - 1) { p_adjacentes.add(goban[pe.x][pe.y+1]); }
            
            for(Pierre pa : p_adjacentes)
            {
                if(pa == null) { continue; }
                if(pa.color == p.color) {
                    if(!g.contains(pa)) {
                        g.add(pa);
                        list_exploration.add(pa);
                    }
                }
            }
        }
        
        return new Groupe(g, p.color);
    }
    
    /**
     * Affiche le plateau de jeu
     */
    public void afficheGoban()
    {
        // on affiche une indication du numero de colone
        System.out.print(" ");
        for(int x = 0; x < getTaille(); x++)
        {
            System.out.print("" + (x%10));
        }
        System.out.println("");
        
        for(int y = 0; y < getTaille(); y++)
        {
            // On affiche une indication du numéro de ligne
            System.out.print("" + (y%10));
            for(int x = 0; x < getTaille(); x++)
            {
                Pierre p = goban[x][y];
                if(p == null) {
                    System.out.print(".");
                } else if(p.color) {
                    System.out.print("N");
                } else {
                    System.out.print("B");
                }
            }
            System.out.println("");
        }
    }
    
}
