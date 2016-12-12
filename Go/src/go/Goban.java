/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * classe contenant tous les mécanismes de jeu
 * @author Vincent
 */
public class Goban {
    
    /**
     * Le plateau de jeu
     */
    private Pierre[][] goban;
    
    /**
     * Les joueurs
     */
    public Joueur joueur_b;
    public Joueur joueur_n;
    
    
    /**
     * Construit un plateau de jeu de la taille donnée
     * @param taille 
     */
    public Goban(int taille)
    {
        this.joueur_b = new Joueur();
        this.joueur_b.couleur = false;
        this.joueur_n = new Joueur();
        this.joueur_n.couleur = true;
        goban = new Pierre[taille][taille];
    }
    
    
    public void setPierre(int x, int y, boolean c)
    {
        goban[x][y] = new Pierre(x, y, c);
    }
    
    public Pierre getPierre(int x, int y)
    {
        return goban[x][y];
    }
    
    public Pierre takePierre(int x, int y)
    {
        Pierre p = goban[x][y];
        goban[x][y] = null;
        return p;
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
            System.out.print(" " + (x%10));
        }
        System.out.println("");
        
        for(int y = 0; y < getTaille(); y++)
        {
            // On affiche une indication du numéro de ligne
            System.out.print("" + (y%10));
            for(int x = 0; x < getTaille(); x++)
            {
                Pierre p = goban[x][y];
                System.out.print(" ");
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
    
    public ArrayList<Point2D> getLiberte(Groupe g){
        ArrayList<Pierre> l_pierre = g.getPierres();
        ArrayList<Point2D> libertes = new ArrayList<Point2D>();
        
        Pierre p;
        int x,y;
        Iterator<Pierre> it = l_pierre.iterator();
        while(it.hasNext()){
            p=it.next();
            x=p.getX();
            y=p.getY();
            if((x-1>0) && goban[x-1][y]==null) libertes.add(new Point2D(x-1,y));
            if((x+1<this.getTaille()) && goban[x+1][y]==null) libertes.add(new Point2D(x+1,y));
            if((y-1>0) && goban[x][y-1]==null) libertes.add(new Point2D(x,y-1));
            if((y+1<this.getTaille()) && goban[x][y+1]==null) libertes.add(new Point2D(x,y+1));
        }
        
        return libertes;
    }
    
    public boolean checkEmptySquare(int x,int y){
        if( this.goban[x][y]==null  ) return true;
        else return false;
    }
    
    public boolean checkNotOutofBounds(int x,int y){
        if( x < 0 || x >= this.getTaille() || y<0 || y>=this.getTaille() ) return false;
        else return true;
    }
    
    public boolean checkNotSuicide(int x, int y, boolean color){
        this.setPierre(x,y,color );
        Groupe g = this.getGroupe( this.getPierre(x, y) );
        ArrayList<Point2D> lib = this.getLiberte(g);
        if( lib.isEmpty() ){
            this.goban[x][y]=null;
            return false;
        }
        else return true;
    }
    
    
    
    public void tourDeJeu(Joueur joueur){
        System.out.println("Début du tour de jeu:");
        if( joueur.couleur) System.out.println("Joueur noir à toi de jouer!");
        else System.out.println("Joueur blanc à toi de jouer!");
        
        Point2D pos = new Point2D();
        boolean position_ok = false;
        while(!position_ok){
            this.afficheGoban();
            pos = joueur.askForPosition(); 
            // check if position is not of ouf bounds
            position_ok = this.checkNotOutofBounds(pos.getX(), pos.getY());
            if (  !position_ok  ){
                System.out.println("Entrée invalide --> recommencez!");
                continue;
            }
            // check if square if empty
            position_ok &= this.checkEmptySquare(pos.getX(), pos.getY());
            
            // check if not suicide
            position_ok &= this.checkNotSuicide(pos.getX(), pos.getY(), joueur.couleur);
            
            
            if(!position_ok){
                System.out.println("Entrée invalide --> recommencez!");
            }
        }

        this.setPierre(pos.getX(), pos.getY(),joueur.couleur );
        
    }
    
    public void main_loop(){
        while(true){
            this.tourDeJeu(joueur_n);
            this.tourDeJeu(joueur_b);
        }

    }
    
}
