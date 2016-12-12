/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

/**
 *
 * @author thibault
 */
public class Pierre {

    /**
     * abscisse de la pierre sur le goban
     */
    public int x;

    /**
     * ordonnée de la pierre sur le goban
     */
    public int y;
    
    /**
     * couleur de la pierre
     * true = Noir
     * false = Blanc
     */
    public boolean color;

    /**
     * constructeur avec tous les attributs en arguments
     * @author Vincent
     * @param x_ abscisse
     * @param y_ ordonnée
     * @param c couleur (true=noir et false=blanc)
     */
    public Pierre(int x_, int y_, boolean c)
    {
        x = x_;
        y = y_;
        color = c;
    }
    
    /**
     * getter abscisse
     * @return abscisse
     */
    public int getX() {
        return x;
    }

    /**
     * setter abscisse
     * @param x abscisse
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter ordonnée
     * @return ordonnée
     */
    public int getY() {
        return y;
    }

    /**
     * setter ordonnée
     * @param y ordonnée
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * setter couleur
     * @param color (true=noir et false=blanc)
     */
    public void setColor(boolean color) {
        this.color = color;
    }
    
    /**
     * getter couleur
     * @return color (true=noir et false=blanc)
     */
    public boolean getColor(){
        return this.color;
    }
    
    @Override
    public String toString()
    {
        return (color ? "Noir" : "Blanc") + "(" + x + "," + y +")";
    }

    /**
     * permet de déterminer si deux pierres ont la même position et la même couleur
     * @param p pierre à comparer
     * @return vrai si même pierre, faux sinon
     */
    public boolean equals(Pierre p){
        return (this.x==p.getX())&&(this.y==p.getY())&&(this.color==p.getColor());
    }

}