/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm;

import java.util.ArrayList;
import javax.swing.JFrame;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.DispoSortie;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.Hobby;
import m2105_ihm.nf.Mois;
import m2105_ihm.nf.NoyauFonctionnel;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Region;

import m2105_ihm.ui.CarnetUI;
import m2105_ihm.ui.FenetreUI;
import m2105_ihm.ui.PlanningUI;
import m2105_ihm.ui.BoiteDialogUI;

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
        int b = 0 ;	// temporaire à incrémenter dans la boucle
        
        GroupeContacts[] gtmp = new GroupeContacts[a];
        
        if(a > 0 && g.length!=0)
        {
	        
	        for( GroupeContacts i : g)
	        {
	        	for( GroupeContacts j : gc)
	        	{
	        		if(i!=j)
	        		{
	        			gtmp[b]=j;
	        			b++;
	        		}
	        	}
	        }
	
	        GroupeContacts gr = boiteDialogUI.afficherChoixMembreContact(fenetre," ", gtmp);
	        
	        if (gr!=null)
	        {
	        	gr.addContact(c);
	        }
        }
        else if(g.length==0)
        {
        	for( GroupeContacts j : gc)
        	{       			
        		gtmp[b]=j;
        		b++;        		
        	}
        	
        	GroupeContacts gr = boiteDialogUI.afficherChoixMembreContact(fenetre," ", gtmp);
	        
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
    public void creerEvenement()
    {
	Evenement e = new Evenement();
	planningUI.ajouterEvt(e);
	nf.addEvenement(e);
    }

    /**
     * Supprime un événement existant
     */
    public void supprimerEvenement() {
       
        Evenement e = planningUI.getSelectedEvt();
        BoiteDialogUI boiteDialogUI = new BoiteDialogUI();
        if( boiteDialogUI.afficherConfirmation(fenetre,e))
        {
            planningUI.retirerEvt(e);
            nf.removeEvenement(e);
        }
        
        
       
    }
    
    /**
     * Ajouter un participant à un événement
     */
    public void ajouterParticipantEvenement()
    {
    	Evenement e = planningUI.getSelectedEvt();
        BoiteDialogUI boiteDialogUI = new BoiteDialogUI();
        boolean bool = true;

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        ArrayList<Contact> contactsEvt = new ArrayList<Contact>();
        for(Contact c : nf.getContacts())
        {
            if(c.getNom()!=null)
            {
                contacts.add(c);
            }
        }
        
        for(Contact c : e.getParticipants())
        {
            if(c.getNom()!=null)
            {
                contactsEvt.add(c);
            }
        }
        
        int a = contacts.size()-contactsEvt.size();
        int b = 0 ;	// temporaire à incrémenter dans la boucle
        
        Contact[] gtmp = new Contact[a];
        
        if(a>0 && contactsEvt.size()!=0)
        {
	        
	        for(Contact i : contacts)
	        {
	        	
		    
		    
		    for(Contact j : contactsEvt)
	        	{
	        		if(j==i)
	        		{
	        			bool = false;
	        		}						
	        	}
		    if(bool)
		    {
		    	gtmp[b]=i;
		    	b++;
		    }
		    bool = true;
	        }
                

	        
	        Contact c = boiteDialogUI.afficherChoixMembreContact(fenetre," ", gtmp);
	        
	        if (c!=null)
	        {
	        	e.addParticipant(c);
	        }
        }
        else if(contactsEvt.size()==0)
        {
 
        	for( Contact j : contacts)
        	{       			
        		gtmp[b]=j;
        		b++;        		
        	}
        	

		Contact c = boiteDialogUI.afficherChoixMembreContact(fenetre," ", gtmp);
	        
	        if (c!=null)
	        {
	        	e.addParticipant(c);
	        }
        }    
    }
    
           
    

    /**
     * Retire un participant d'un événement
     */
    public void retirerParticipantEvenement() {
      Evenement e = planningUI.getSelectedEvt();
        BoiteDialogUI boiteDialogUI = new BoiteDialogUI();
        Contact [] g = e.getParticipants();
        Contact gr = boiteDialogUI.afficherChoixMembreContact(fenetre," ", g);
        e.removeParticipant(gr);
    
           
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

	public NoyauFonctionnel getNf()
	{
		return nf;
	}

	public void setNf(NoyauFonctionnel nf)
	{
		this.nf = nf;
	} 
    
    
}
