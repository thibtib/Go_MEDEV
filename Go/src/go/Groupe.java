/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.util.ArrayList;

/**
 *
 * @author fasol
 */
public class Groupe {
    private ArrayList<Pierre> pierres;
    private boolean couleur; // true si noir, false si blanc

    // getters
    public ArrayList<Pierre> getPierres() {
        return pierres;
    }

    public boolean getCouleur() {
        return couleur;
    }

    // setters
    public void setPierres(ArrayList<Pierre> pierres) {
        this.pierres = pierres;
    }

    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }
    
    /**
     * vérifie l'égalité de deux groupes de pierres
     * @param g groupe à comparer
     * @return vrai si les groupes sont égaux, faux sinon
     */
    boolean equals(Groupe g){
        return (this.pierres.equals(g.getPierres()))&&(this.couleur==g.getCouleur());
    }
    
}
