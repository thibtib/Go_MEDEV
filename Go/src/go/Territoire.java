/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de calculer les territoires des deux joueurs.
 * <p>
 * La classe créer un tableau de la taille du goban + 2 (pour prendre en compte 
 * la frontière), et attribue à chaque case une couleur parmi Frontiere, 
 * PierreNoire, PierreBlanche et Vide. <br/>
 * Elle colorie ensuite les cases vides adjacentes jusqu'à former un territoire 
 * et trouver son proprietaire s'il y en a un. Elle continue jusqu'a ce que 
 * toute les cases est un proprietaire ou soit marquée comme n'ayant pas de 
 * propriétaire.
 * </p>
 * @author Vincent
 */
public class Territoire {
    
    /**
     * Tableau d'entier permettant le caclul des territoires
     */
    private int[][] tab;
    
    /**
     * Réference vers le goban.
     */
    private Goban goban;
        
    /**
     * Constantes décrivant les cases
     */
    static public final int PierreNoire = 0;
    static public final int PierreBlanche = 1;
    static public final int TerritoireNoir = 2;
    static public final int TerritoireBlanc = 3;
    static public final int Vide = 4;
    static public final int Frontiere = 5;
    static public final int SansProprietaire = -1;
    static public final int Coloration = 6;
    
    public Territoire(Goban g)
    {
        goban = g;
        
        initialiseTableau();
        calculTerritoire();
    }
    
    private void initialiseTableau()
    {
        // on construit un tableau plus grand que le plateau pour inclure la
        // notion de frontière (les bords du plateau)
        final int taille_tab = goban.getTaille() + 2;
        tab = new int[goban.getTaille() + 2][goban.getTaille() + 2];
        
        // on colorie les frontieres
        for(int y = 0; y < taille_tab; ++y){
            tab[0][y] = Frontiere;
            tab[taille_tab-1][y] = Frontiere;
        }
        for(int x = 0; x < taille_tab; ++x){
            tab[x][0] = Frontiere;
            tab[x][taille_tab-1] = Frontiere;
        }
        
        // on colorie les pierres et les cases vides
        for(int y = 0; y < goban.getTaille(); ++y){
            for(int x = 0; x < goban.getTaille(); ++x) {
                if(goban.getPierre(x, y) != null) {
                    if(goban.getPierre(x, y).color) {
                        tab[x+1][y+1] = PierreNoire;
                    } else {
                        tab[x+1][y+1] = PierreBlanche;
                    }
                } else {
                    tab[x+1][y+1] = Vide;
                }
            }
        }
    }
    
    
    /**
     * Recalcule le propriétaire du territoire.
     * <p>
     * Si actuel vaut vide (i.e. il n'y a pas encore de propriétaire connue),
     * la fonction renvoie nouveau (sauf si nouveau vaut Frontiere) <br/>
     * Si actuel vaut SansProprietaire (i.e. le territoire est délimité par des 
     * pierres des deux couleurs), la fonction renvoie SansProprietaire <br/>
     * Si actuel vaut PierreBlanche (resp. PierreNoire), la fonction renvoie 
     * actuel sauf si nouveau est de la couleur opposé, auquel cas elle renvoie 
     * SansProprietaire
     * </p>
     * @param actuel
     * @param nouveau
     * @return 
     */
    private int updateProprietaire(int actuel, int nouveau)
    {
        if(actuel == Vide) {
            if(nouveau == Frontiere) {
                return Vide;
            }
            return nouveau;
        }
        
        if(actuel == SansProprietaire) {
            return SansProprietaire;
        } 
        
        if((actuel == PierreBlanche && nouveau == PierreNoire) || 
                (actuel == PierreNoire && nouveau == PierreBlanche)) {
            return SansProprietaire;
        }
        
        return actuel;
    }
    
    /**
     * Calcul les territoires. La fonction colorie des cases vides adjacentes 
     * jusqu'a arriver sur une case marquée PierreBlanche ou une case 
     * PierreNoire auquel cas elle peut en déduire un propriétaire (ou l'absence 
     * de propriétaire si une case vide est à côté d'une pierre blanche et 
     * d'une pierre noire).
     */
    private void calculTerritoire()
    {
        final int taille_tab = tab.length;
        
        for(int y = 1; y < taille_tab - 1; ++y)
        {
            for(int x = 1; x < taille_tab - 1; ++x)
            {
                int c = tab[x][y];
                if(c != Vide) {
                    continue;
                }
                
                tab[x][y] = Coloration;
                
                // ter : territoire en cours de construction
                List<Point2D> ter = new ArrayList<Point2D>();
                ter.add(new Point2D(x, y));
                // le propriétaire du territoire
                int Proprietaire = Vide;
                // explor : les points du territoire dont on doit encore exploré les voisins
                List<Point2D> explor = new ArrayList<Point2D>();
                explor.add(new Point2D(x, y));
                while(!explor.isEmpty())
                {
                    Point2D pos = explor.get(0);
                    explor.remove(0);
                    
                    List<Point2D> pos_adjacentes = pos.getPosAdjacentes();
                    for(Point2D pos_adj : pos_adjacentes)
                    {
                        Proprietaire = updateProprietaire(Proprietaire, tab[pos_adj.getX()][pos_adj.getY()]);
                        
                        if(tab[pos_adj.getX()][pos_adj.getY()] == Vide) {
                            // on colorie la case pour ne pas retomber dessus
                            tab[pos_adj.getX()][pos_adj.getY()] = Coloration;
                            ter.add(pos_adj);
                            explor.add(pos_adj);
                        }
                    }
                }
                
                
                // on n'a plus qu'a recolorier correctement les cases.
                int couleur = (Proprietaire == PierreBlanche ? TerritoireBlanc : (Proprietaire == PierreNoire ? TerritoireNoir : SansProprietaire));
                for(Point2D pos : ter)
                {
                    tab[pos.getX()][pos.getY()] = couleur;
                }
                                
            }
        }
        
        // décolorie les cases coloriés avec la couleur SansProprietaire
        enleveColoration();
    }
    
    /**
     * Recolorie avec la couleur Vide les cases sans propriétaire.
     */
    private void enleveColoration()
    {
        final int taille_tab = tab.length;

        // on recolorie en vide les cases sans proprietaire
        for(int y = 1; y < taille_tab - 1; ++y)
        {
            for(int x = 1; x < taille_tab - 1; ++x)
            {
                if(tab[x][y] == SansProprietaire) {
                    tab[x][y] = Vide;
                }
            }
        }
    }
    
    /**
     * Affiche le tableau utilisé pour calculer le territoire.
     * <p>
     * Légende : <br/>
     * @ : Frontière <br/>
     * + : Territoire noir <br/>
     * - : Territoire blanc <br/>
     * </p>
     * @return 
     */
    public void afficheTerritoire()
    {
        for(int y = 0; y < tab.length; y++)
        {
            for(int x = 0; x < tab.length; ++x)
            {
                if(tab[x][y] == PierreNoire) {
                    System.out.print(" N");
                } else if(tab[x][y] == PierreBlanche) {
                    System.out.print(" B");
                } else if(tab[x][y] == TerritoireNoir) {
                    System.out.print(" +");
                } else if(tab[x][y] == TerritoireBlanc) {
                    System.out.print(" -");
                } else if(tab[x][y] == Vide) {
                    System.out.print(" .");
                } else if(tab[x][y] == Frontiere) {
                    System.out.print(" @");
                }
            }
            System.out.println("");
        }
    }
    
    /**
     * Compte le nombre de cases ayant une couleur donnée dans le tableau
     * @param color
     * @return 
     */
    public int count(int color)
    {
        int ret = 0;
        for(int y = 0; y < tab.length; y++)
        {
            for(int x = 0; x < tab.length; ++x)
            {
                if(tab[x][y] == color) {
                    ret += 1;
                }
            }
        }
        return ret;
    }
    
    public int getTailleTerritoireBlanc()
    {
        return count(TerritoireBlanc);
    }
    
    public int getTailleTerritoireNoir()
    {
        return count(TerritoireNoir);
    }
    
    
}
