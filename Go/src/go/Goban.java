/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        // écrase le fichier backup s'il existe
        File file= new File("backup.txt");
        if(file.exists()) file.delete();
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
     * Renvoie la liste des groupes sur le goban.
     * <p>
     * Implémentation alternative
     * </p>
     * @return 
     */
    public List<Groupe> getGroups2()
    {
        List<Groupe> resultat = new ArrayList<Groupe>();
        
        // tableau indiquant les cases deja visitees
        boolean[][] visite = new boolean[getTaille()][getTaille()];
        for(int y = 0; y < getTaille(); y++) {
            for(int x = 0; x < getTaille(); x++) {
                visite[x][y] = false;
            }
        }
        
        for(int y = 0; y < getTaille(); y++)
        {
            for(int x = 0; x < getTaille(); x++)
            {
                // on vérifie s'il y a une pierre a cet endroit
                if(goban[x][y] == null) {
                    continue;
                }
                
                // on regarde si la pierre n'appartient pas deja a un groupe
                if(visite[x][y]) {
                    continue;
                }
                
                // on commence un nouveau groupe
                ArrayList<Pierre> groupe = new ArrayList<Pierre>();
                Pierre p = goban[x][y];
                groupe.add(p);
                
                // on explore à partir des pierres du groupe les pierres adjacentes 
                // pour ompléter le groupe
                List<Pierre> exploration = new ArrayList<Pierre>();
                exploration.add(p);
                while(!exploration.isEmpty()) 
                {
                    Pierre pe = exploration.get(0);
                    exploration.remove(0);

                    // on regarde les pierres adjacentes à la pierre courante
                    List<Pierre> p_adjacentes = new ArrayList<Pierre>();
                    if(pe.x > 0) { p_adjacentes.add(goban[pe.x-1][pe.y]); }
                    if(pe.x < getTaille() - 1) { p_adjacentes.add(goban[pe.x+1][pe.y]); }
                    if(pe.y > 0) { p_adjacentes.add(goban[pe.x][pe.y-1]); }
                    if(pe.y < getTaille() - 1) { p_adjacentes.add(goban[pe.x][pe.y+1]); }

                    // pour chaque pierre, on regarde si elle est de la bonne 
                    // couleur et on l'ajoute au groupe si elle n'en fait pas 
                    // partie ; cette pierre devient ensuite une pierre à explorer.
                    for(Pierre pa : p_adjacentes)
                    {
                        if(pa == null) { continue; }
                        if(pa.color == p.color) {
                            if(!groupe.contains(pa)) {
                                groupe.add(pa);
                                visite[pa.x][pa.y] = true;
                                exploration.add(pa);
                            }
                        }
                    }
                }
                
                // on ajoute le groupe à la liste des groupes
                resultat.add(new Groupe(groupe, p.getColor()));
                
            }
        }
        
        return resultat;
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
    
    public void captureGroup(){
        List<Groupe> groups = this.getGroups();
        for(Groupe g : groups){
            ArrayList<Point2D> lib = this.getLiberte(g);
            // if Groupe wihtout liberté --> remove it and add it to pierrecapture of the other player
            if( lib.isEmpty()){
                for(Pierre p : g.getPierres() ){
                    int x = p.x;
                    int y = p.y;
                    this.goban[x][y] = null;
                    if (p.color ) this.joueur_n.incr_nbrPierreCapturees();
                    else this.joueur_b.incr_nbrPierreCapturees();
                }
            }
        }
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
    
    public boolean checkNotKo(int x,int y, Joueur joueur){
        
        // c'est pas beau je sais mais bon
        if( joueur.couleur ){
            // if other player's last move is pass, the move is ok
            if( this.joueur_b.last_move == null ) return true;
            // else we check for ko
            else{
                Point2D last = joueur_b.last_move;
                Pierre p = this.goban[last.getX()][last.getY()];
                Groupe g = this.getGroupe(p);
                ArrayList<Point2D> libs = this.getLiberte(g);
                // if the stone from last move has not one liberty, the move is ok
                if( libs.size()!= 1 ) return true;
                // we have to check if this move is gonna capture the stone which did the last move
                else {
                    this.setPierre(x, y, joueur.couleur);
                    g = this.getGroupe(p);
                    libs = this.getLiberte(g);
                    if( libs.isEmpty() ){
                        this.goban[x][y] = null;
                        return false;
                    }
                    else return true;
                }
            }
        }
        else{
            // if other player's last move is pass, the move is ok
            if( this.joueur_n.last_move == null ) return true;
            // else we check for ko
            else{
                Point2D last = joueur_n.last_move;
                Pierre p = this.goban[last.getX()][last.getY()];
                Groupe g = this.getGroupe(p);
                ArrayList<Point2D> libs = this.getLiberte(g);
                // if the stone from last move has not one liberty, the move is ok
                if( libs.size()!= 1 ) return true;
                // we have to check if this move is gonna capture the stone which did the last move
                else {
                    this.setPierre(x, y, joueur.couleur);
                    g = this.getGroupe(p);
                    libs = this.getLiberte(g);
                    if( libs.isEmpty() ){
                        this.goban[x][y] = null;
                        return false;
                    }
                    else return true;
                }
            }
        }
    }
    
    public boolean tourDeJeu(Joueur joueur){
        System.out.println("Début du tour de jeu:");
        if( joueur.couleur) System.out.println("Joueur noir à toi de jouer!");
        else System.out.println("Joueur blanc à toi de jouer!");
        
        Point2D pos = new Point2D();
        boolean position_ok = false;
        while(!position_ok){
            this.afficheGoban();
            pos = joueur.askForPosition();
            if ( pos == null ){
                System.out.println("Tu passes ton tour");
                this.writeBackup(false,0,0,false);
                joueur.last_move = null;
                return false;
            }
            // check if position is not of ouf bounds
            position_ok = this.checkNotOutofBounds(pos.getX(), pos.getY());
            if (  !position_ok  ){
                System.out.println("Position hors du goban --> recommencez!");
                continue;
            }
            // check if square if empty
            position_ok &= this.checkEmptySquare(pos.getX(), pos.getY());
            if (  !position_ok  ){
                System.out.println("Position déjà occupée --> recommencez!");
                continue;
            }
            
            //check if not ko
            position_ok &= this.checkNotKo(pos.getX(), pos.getY(), joueur);
            if (  !position_ok  ){
                System.out.println("Y'a ko là --> recommencez!");
                continue;
            }
            // check if not suicide
            position_ok &= this.checkNotSuicide(pos.getX(), pos.getY(), joueur.couleur);
            if (  !position_ok  ){
                System.out.println("Suicide!  --> recommencez!");
                continue;
            }
            if(!position_ok){
                System.out.println("Entrée invalide --> recommencez!");
            }
        }

        this.setPierre(pos.getX(), pos.getY(),joueur.couleur );
        this.writeBackup(true, pos.getX(), pos.getY(), joueur.couleur);
        joueur.last_move = pos;
        return true;
        
    }
    
    public void main_loop(){
        boolean noir = true;
        boolean blanc = true;
        while(true){
            noir = this.tourDeJeu(joueur_n);
            this.captureGroup();
            if ( !noir && !blanc ) break;
            blanc = this.tourDeJeu(joueur_b);
            this.captureGroup();
            if ( !noir && !blanc ) break;
        }
        System.out.println("Fin du jeu!");
        
        // on récupère les pierres mortes pour les comptabiliser
        List<Point2D> pierres_mortes = joueur_n.askForDeadStones();
        for(Point2D pos : pierres_mortes) 
        {
            int x = pos.getX();
            int y = pos.getY();
            
            if(x >= getTaille() || y >= getTaille()) {
                continue;
            }
            
            Pierre lala = goban[x][y];
            if(lala == null) {
                continue;
            }
            
            if(lala.color) {
                joueur_b.incr_nbrPierreCapturees();
            } else {
                joueur_n.incr_nbrPierreCapturees();
            }
            
            goban[x][y] = null;
        }
        
        // Affichage du score 
        System.out.println("Joueur N : " + joueur_n.getNbrPierreCapturees() + " pierre(s) capturée(s)");
        System.out.println("Joueur B : " + joueur_b.getNbrPierreCapturees() + " pierre(s) capturée(s)");

    }
    
    
    public void writeBackup(boolean play, int x, int y, boolean color){
        BufferedWriter buffWrt = null;
        try{
            buffWrt= new BufferedWriter(new FileWriter("backup.txt",true));
            if(play){
                String couleur = new String();
                if(color) couleur="N";
                else couleur="B";
                buffWrt.write("play-"+couleur+":"+x+","+y);
            }
            else{
                buffWrt.write("pass-");
            }
            buffWrt.newLine();
        }
        catch(FileNotFoundException fe){
            System.out.println("impossible d'ouvrir le fichier de backup");
        }
        catch(IOException ioe){
            System.out.println("impossible d'écrire dans le fichier de backup");
        }
        // on ferme le fichier
        finally {
            try {
                if (buffWrt != null) {
                    // je force l'écriture dans le fichier
                    buffWrt.flush();
                    // puis je le ferme
                    buffWrt.close();
                }
            }
            // on attrape l'exception potentielle 
            catch (IOException ex) {
                System.out.println("impossible de flush le BufferedWriter");
            }
        }
    }
    
    
}
