/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.util.List;

/**
 * objet représentant les groupes de pierres liées sur le goban
 * @author fasol
 */
public class Groupe {
    private List<Pierre> pierres;
    private boolean couleur; // true si noir, false si blanc

    /**
     * constructeur avec tous les attributs en paramètre
     * @param ps liste des pierres du groupe
     * @param c couleur des pierres du groupe
     */
    public Groupe(List<Pierre> ps, boolean c)
    {
        pierres = ps;
        couleur = c;
    }
    
    // getters

    /**
     * getter liste des pierres
     * @return liste des pierres
     */
    public List<Pierre> getPierres() {
        return pierres;
    }

    /**
     * getter couleur des pierres
     * @return couleur des pierres
     */
    public boolean getCouleur() {
        return couleur;
    }

    // setters

    /**
     * setter liste des pierres
     * @param pierres liste des pierres
     */
    public void setPierres(List<Pierre> pierres) {
        this.pierres = pierres;
    }

    /**
     * setter couleur des pierres
     * @param couleur couleur des pierres
     */
    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }
    
    /**
     * vérifie l'égalité de deux groupes de pierres
     * @param g groupe à comparer
     * @return vrai si les groupes sont égaux, faux sinon
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null) {
            return false;
        }
        
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        
        Groupe g = (Groupe) obj;
        
        return (this.pierres.containsAll(g.getPierres()))&&(g.getPierres().containsAll(this.pierres))&&(this.couleur==g.getCouleur());
    }
    
}
