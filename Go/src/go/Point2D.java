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
public class Point2D {
    /**
     * x position selon l'axe horizontal du point 2D
     */
    private int x;
    /**
     * y position selon l'axe vertical du point 2D
     */
    private int y;
    
    /**
     * Constructeur de Point2D
     * @param _x position selon l'axe horizontal du point 2D
     * @param _y position selon l'axe vertical du point 2D
     */
    public Point2D(int _x,int _y){
        this.x = _x;
        this.y = _y;
    }
    public Point2D( Point2D P){
        this.x = P.x;
        this.y = P.y;
    }
    public Point2D(){
        this.x = 0;
        this.y = 0;
    }
    
    // getters,setters
    public void setX(int _x){
        this.x = _x;
    }
    public int getX(){
        return this.x;
    }
    public void setY(int _y){
        this.y = _y;
    }
    public int getY(){
        return this.y;
    }
    public void setPosition(int _x,int _y){
        this.x = _x;
        this.y = _y;
    }
    public void translate(int _x,int _y){
        this.x += _x;
        this.y += _y;
    }
    public void affiche(){
        System.out.println("["+ this.x +";"+ this.y+"]");
    }

    
    public static double distance(Point2D pt1,Point2D pt2){
        return  Math.sqrt(  Math.pow(pt1.getX()-pt2.getX(),2) +Math.pow(pt1.getY()-pt2.getY(),2) );
    } 
}
