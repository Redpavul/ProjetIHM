/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

import java.util.ArrayList;

/**
 *
 * @author IUT2
 */

/**
 * 
 * @class Contact
 * Fiche d'un contact du carnet d'adresse
 */
public class Contact implements java.io.Serializable {
    /**
     * Attributs définissant un contact
     */
    private String nom;
    private String prenom;
    private int dateJour;
    private Mois dateMois;
    private int dateAnnee;
    private String numeroTelephone;
    private String email;
    private Region region;

    private DispoSortie disponibilite;
    private ArrayList<Hobby> hobbies;    
        
    /**
     * Constructeur
     */
    public Contact() {
        this.nom = "Nouveau contact";
        this.prenom = "Pr\u00E9nom";
        this.dateJour = 1;
        this.dateMois = Mois.JANVIER;
        this.dateAnnee = 1970;        
        this.numeroTelephone = "";
        this.email = "";
        this.region = Region.ALSACE;        
        this.disponibilite = DispoSortie.SOIR;
        this.hobbies = new ArrayList<Hobby>();
    }
    
    /**
     * Retourne le nom du contact
     * @return 
     */
    public String getNom() { return nom; }

    /**
     * Affecte le nom du contact
     * @param objet non nul
     * @return 
     */
    public boolean setNom(String nom) {
        boolean res = false;
        
        if (nom != null) { 
            this.nom = nom;
            res = true;
        }
        
        return res;
    }

    /**
     * Retourne le prénom du contact
     * @return 
     */
    public String getPrenom() { return prenom; }

    /**
     * Affecte le prénom du contact
     * @param objet non nul
     * @return 
     */
    public boolean setPrenom(String prenom) {
        boolean res = false;
        
        if (prenom != null) { 
            this.prenom = prenom;
            res = true;
        }
        
        return res;
    }
    
    /**
     * Retourne la date de naissance du contact
     * @return 
     */
    public int  getDateNaissanceJour()  { return dateJour; }
    public Mois getDateNaissanceMois()  { return dateMois; }
    public int  getDateNaissanceAnnee() { return dateAnnee; }
    
    /**
     * Affecte la date de naissance du contact
     * @param objet non nul
     * @return 
     */
    public boolean setDateNaissance(int jour, Mois mois, int annee) {        
        this.dateAnnee = annee;
        this.dateMois = mois;
        this.dateJour = jour;

        return true;
    }
    
    /**
     * Retourne le numéro de téléphone du contact
     * @return 
     */
    public String getNumeroTelephone() { return numeroTelephone; }

    /**
     * Affecte le numéro de téléphone
     * @param objet non nul
     * @return 
     */
    public boolean setNumeroTelephone(String numeroTelephone) {       
        boolean res = false;
        
        if (numeroTelephone != null) { 
            this.numeroTelephone = numeroTelephone;
            res = true;
        }
        
        return res;
    }

    /**
     * Retourne le mél du contact
     * @return 
     */
    public String getEmail() { return email; }

    /**
     * Affecte le mél du contact
     * @param objet non nul
     * @return 
     */
    public boolean setEmail(String email) {       
        boolean res = false;
        
        if (email != null) { 
            this.email = email;
            res = true;
        }
        
        return res;
    }

    public Region getRegion() { return region; }

    public boolean setRegion(Region region) {       
        boolean res = false;
        
        if (region != null) { 
            this.region = region;
            res = true;
        }
        
        return res;
    }
    
    public DispoSortie getDisponibilite() { return disponibilite; }

    public boolean setDisponibilite(DispoSortie disponibilite) {       
        boolean res = false;
        
        if (disponibilite != null) { 
            this.disponibilite = disponibilite;
            res = true;
        }
        
        return res;
    }

    public Hobby [] getHobbies() { 
        return hobbies.toArray(new Hobby[0]); 
    }

    public boolean addHobby(Hobby h) {
        boolean res = false;
        
        if (h != null) {
           if (hobbies.indexOf(h) == -1) {
               hobbies.add(h);
               res = true;
           }
        }
        
        return res;        
    }

    public boolean removeHobby(Hobby h) {
        boolean res = false;
        
        if (h != null) {
           int index = hobbies.indexOf(h);
           
           if (index >= 0) {
               hobbies.remove(index);
               res = true;
           }
        }
        
        return res;        
    }    
        
    public String toString() {
        return nom + " " + prenom;
    }
   
}
