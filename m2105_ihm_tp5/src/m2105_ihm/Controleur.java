/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm;

import javax.swing.JFrame;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.NoyauFonctionnel;
import m2105_ihm.nf.Evenement;

import m2105_ihm.ui.CarnetUI;
import m2105_ihm.ui.FenetreUI;
import m2105_ihm.ui.PlanningUI;
import m2105_ihm.ui.BoiteDialogUI;

/**
 *
 * @author IUT2
 */
public class Controleur {
    
    /*
     * Noyau Fonctionnel
     */    
    NoyauFonctionnel nf;
            
    /*
     * Composants
     */
    private CarnetUI carnetUI;
    private FenetreUI fenetre;
    private PlanningUI planningUI;

    /**
     * Constructeur de la fenêtre principale
     */
    public Controleur() {
        initUI();
        initContent();
    }
    
    /**
     * Action créer un nouveau contact
     */
    public void creerContact() {
        
      
      Contact c = new Contact();
      carnetUI.ajouterContact(c);
      nf.addContact(c);
      
    }

    /**
     * Action supprimer contact
     */
    public void supprimerContact() {
        
        Contact c = carnetUI.getSelectedContact();
        BoiteDialogUI boiteDialogUI = new BoiteDialogUI();
        if( boiteDialogUI.afficherConfirmation(fenetre,c)==true){
            carnetUI.retirerContact(c);
            nf.removeContact(c);
        }
        
        
        
    }
    
    /**
     * Action créer un groupe de contacts
     */
    public void creerGroupe() {
        
       GroupeContacts g = new GroupeContacts();
       carnetUI.ajouterGroupe(g);
       nf.addGroupe(g);
              
    }

    /**
     * Action supprimer un groupe de contacts
     */
    public void supprimerGroupe() {
        
        GroupeContacts g = carnetUI.getSelectedGroupe();
        BoiteDialogUI boiteDialogUI = new BoiteDialogUI();
        if( boiteDialogUI.afficherConfirmation(fenetre,g))
        {
            carnetUI.retirerGroupe(g);
            nf.removeGroupe(g);
        }
        
        
    }
    
    
    
    public void ajouterContactGroupe() 
    {
        Contact c = carnetUI.getSelectedContact();
        BoiteDialogUI boiteDialogUI = new BoiteDialogUI();
        
        GroupeContacts[] g = nf.getGroupesContact(c);
        GroupeContacts[] gc = nf.getGroupes();
        int a = gc.length-g.length;
        if(a>0)
        {
	        GroupeContacts[] gt = new GroupeContacts[a];
	        int b = 0 ;
	        for( GroupeContacts i : g)
	        {
	        	for( GroupeContacts j : gc)
	        	{
	        		if(i!=j)
	        		{
	        			gt[b]=j;
	        			b++;
	        		}
	        	}
	        }
	        GroupeContacts gr = boiteDialogUI.afficherChoixMembreContact(fenetre," ", gt);
	        
	        if (gr!=null)
	        {
	        	gr.addContact(c);
	        }
        }
        else
        {
        	System.out.println("Cette personne appartient déjà tout les groupes");
        }
    
    
    }
    
    public void retirerContactGroupe() 
    {
        Contact c = carnetUI.getSelectedContact();
        BoiteDialogUI boiteDialogUI = new BoiteDialogUI();
        GroupeContacts[] g = nf.getGroupesContact(c);
        GroupeContacts gr = boiteDialogUI.afficherChoixMembreContact(fenetre," ", g);
        gr.removeContact(c);
    	
    	
    }
    
    /**
     * Crée un nouvel événement
     */
    public void creerEvenement() {
    
       /** Projet **/
       
    }

    /**
     * Supprime un événement existant
     */
    public void supprimerEvenement() {
       
       /** Projet **/
       
    }
    
    /**
     * Ajouter un participant à un événement
     */
    public void ajouterParticipantEvenement() {
    
       /** Projet **/
           
    }

    /**
     * Retire un participant d'un événement
     */
    public void retirerParticipantEvenement() {
    
       /** Projet **/
           
    }

    /**
     * Met à jour la base de données
     */
    public void enregistrer() {
        nf.updateDB();
    }    
        
    /**
     * Quitter l'application sans enregistrer les modifications
     */
    public void quitter() {
        System.exit(0);
    }

    /**
     * Création des composants constituant la fenêtre principale
     */
    private void initUI() {
        /* Onglets */
        carnetUI = new CarnetUI(this);
        planningUI = new PlanningUI(this);

        /* Fenêtre principale */
        fenetre = new FenetreUI(this);
        fenetre.addTab(carnetUI, "Carnet");     // onglet carnet
        fenetre.addTab(planningUI, "Planning"); // onglet planning
        fenetre.afficher();
    }
        
    /**
     * Alimente la liste avec la liste des contacts existants
     */
    private void initContent() {
        nf = new NoyauFonctionnel();
                
        for(Contact c : nf.getContacts()) {
            carnetUI.ajouterContact(c);
        }
        
        for(GroupeContacts g : nf.getGroupes()) {
            carnetUI.ajouterGroupe(g);
        }
        
        for(Evenement e : nf.getEvenements()) {
            planningUI.ajouterEvt(e);
        }
        
        carnetUI.show();
    }
    
    public void setContactSelected(boolean selected) {
        fenetre.setMenuContactSelected(selected);
        
    }
    
    public void setEvtSelected(boolean selected) {
        fenetre.setMenuEvenementSelected(selected);
    }    
}