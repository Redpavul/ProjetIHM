/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package m2105_ihm.ui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.NoyauFonctionnel;

/**
 *
 * @author laurillau
 */
public class BoiteDialogUI {
    /**
     * Boîte de dialogue pour confirmer la suppression d'un contact
     * @param c un contact
     * @return vrai si confirmé
     */
	NoyauFonctionnel nf = ;
	
	
    public static boolean afficherConfirmation(JFrame fenetre, Contact c) {
        boolean res = false;

        if (c != null) {
            String [] choix = new String[] { "Supprimer", "Annuler" }; 
            
            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                  "Voulez-vous vraiment supprimer le contact : " 
                  + c.getPrenom() + " " + c.getNom() + " ?", 
                  "Suppression d'un contact",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.QUESTION_MESSAGE, 
                  null,
                  choix,
                  choix[1]);
            res = (((Integer) selectedValue) == 0);
        }
        
        return res;
    }

    /**
     * Boîte de dialogue pour confirmer la suppression d'un groupe de contacts
     * @param g un groupe de contacts
     * @return vrai si confirmé
     */    
    public static boolean afficherConfirmation(JFrame fenetre, GroupeContacts g) {
        boolean res = false;

                if (g != null) {
            String [] choix = new String[] { "Supprimer", "Annuler" }; 
            
            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                  "Voulez-vous vraiment supprimer le groupe : " 
                  + g.getNom() +" ?", 
                  "Suppression d'un contact",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.QUESTION_MESSAGE, 
                  null,
                  choix,
                  choix[1]);
            
            res = (((Integer) selectedValue) == 0);
        }
        
       
        return res;
    }

    /**
     * Boîte de dialogue choisir un groupe auquel ajouter un contact
     * @param titre titre de la fenêtre
     * @param groupes liste des groupes existants
     * @return groupe choisi sinon valeur null
     */    
    public static GroupeContacts afficherChoixMembreContact(JFrame fenetre, String titre, GroupeContacts [] groupes) {
        String [] test = new String [groupes.length];
    	
    	GroupeContacts res = null;
    	for ( int i=0; i<groupes.length;i++)
    	{
    		
    		test[i] = groupes[i].getNom();

    	}
    	
        if (titre == null) { titre = ""; }
                        if (groupes != null) {
           
            
            
             Object selectedValue =  JOptionPane.showInputDialog(fenetre, "Dans quel groupe voulez vous ajoutez le contact : ", " ", JOptionPane.QUESTION_MESSAGE, null  ,test, 1);
      
            res = ;
           
                      
        }
        
        return res;
    }
    
    public static Evenement afficherChoixEvenement(JFrame fenetre, String titre, Evenement [] e) {
        Evenement res = null;
        if (titre == null) { titre = ""; }
        JComboBox combo = new JComboBox(e);
                        if (e != null) {
           
            
            
             Object selectedValue =  JOptionPane.showInputDialog(fenetre, "Quel evenement voulez vous supprimer : ", " ", JOptionPane.QUESTION_MESSAGE, null , e, 1);
      
            
            res = ((Evenement) selectedValue);
           
                      
        }
        
        return res;
    }
    
}
