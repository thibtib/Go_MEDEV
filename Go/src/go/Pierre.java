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
    public int x;
    public int y;
    /**
     * true = Noir
     * false = Blanc
     */
    public boolean color;

    /**
     * @author Vincent
     * @param x_
     * @param y_
     * @param c 
     */
    public Pierre(int x_, int y_, boolean c)
    {
        x = x_;
        y = y_;
        color = c;
    }
    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
    
    public boolean getColor(){
        return this.color;
    }
    
    @Override
    public String toString()
    {
        return (color ? "Noir" : "Blanc") + "(" + x + "," + y +")";
    }

    public boolean equals(Pierre p){
        return (this.x==p.getX())&&(this.y==p.getY())&&(this.color==p.getColor());
    }

}