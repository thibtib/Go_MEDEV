/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.util.Scanner;

/**
 *
 * @author Vincent
 */
public class Joueur {
    /*
    * couleur du joueur : true = Noir , false = blanc
    */
    public boolean couleur;
    
    /*
    Le nombre de pierre que le joueur a capturé.
    */
    private int nbrPierreCapturees;
    
    /**
     * Constructeur par défault
     * Initialise le nombre de pierres capturees à 0.
     */
    public Joueur(){    
        nbrPierreCapturees = 0;
    }

    /**
     * getter pour nbrPierreCapturees
     * @return nbrPierreCapturees
     */
    public int getNbrPierreCapturees() {
        return nbrPierreCapturees;
    }

    /**
     * setter pour nbrPierreCapturees
     * @param nbrPierreCapturees
     */
    public void setNbrPierreCapturees(int nbrPierreCapturees) {
        this.nbrPierreCapturees = nbrPierreCapturees;
    }
    
    /**
     * invite le joueur à rentrer une position pour son prochain coup
     * accès à l'aide via la commande 'h'
     * @return Point2D représentant la position rentrée
     */
    public Point2D askForPosition(){
        Point2D pos = new Point2D();
        Scanner user_input = new Scanner( System.in );
        System.out.print("Entrez la position(h pour l'aide): ");
        boolean flag = true;
        while(flag){
            String input = user_input.next();
            if( input.contains("h")){
                System.out.println("Entrez la position du point sous la forme: 'X:Y' avec X  la position sur "
                        + "l'axe horizontal et Y sur l'axe vertical   ");
            }
            else{
                try{                    
                    String[] numbers = input.split(":");
                    int x = Integer.parseInt(numbers[0]);
                    int y = Integer.parseInt(numbers[1]);
                    
                    pos.setX(x);
                    pos.setY(y);
                    flag = false;
                }
                catch(NumberFormatException e){
                    System.out.println("L'entrée est invalide");
                }                
            }
        }
        return pos;
    }
}
