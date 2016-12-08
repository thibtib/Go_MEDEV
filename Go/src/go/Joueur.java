/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

/**
 *
 * @author Vincent
 */
public class Joueur {
    /*
    Le nombre de pierre que le joueur a capturé.
    */
    private int nbrPierreCapturees;
    
    /**
     * Initialise le nombre de pierres capturees à 0.
     */
    public Joueur()
    {
        nbrPierreCapturees = 0;
    }

    public int getNbrPierreCapturees() {
        return nbrPierreCapturees;
    }

    public void setNbrPierreCapturees(int nbrPierreCapturees) {
        this.nbrPierreCapturees = nbrPierreCapturees;
    }
    
    
}
