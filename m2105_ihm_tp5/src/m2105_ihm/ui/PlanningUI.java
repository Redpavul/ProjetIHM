/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.GroupeContacts;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends JPanel
{
	
	private Controleur controleur;
	
	private ListeEvenement listeEvenement ;
	
	
	private FicheEvtUI ficheEvtUI;
	
    /** 
     * Constructeur : initialise les composants de l'IHM pour les événements
     * @param une instance du controleur
     */
    public PlanningUI(Controleur ctrl)
    {
        super();
        this.controleur = ctrl;
        
        initUIComponents(); 
        
    }
    
    

    private void initUIComponents()
	{
		
    	listeEvenement = new ListeEvenement(this);
        listeEvenement.setBorder(BorderFactory.createTitledBorder("Evenement"));
    	ficheEvtUI = new FicheEvtUI(this);
    	
    	setLayout(new BorderLayout());
    	add(listeEvenement, BorderLayout.WEST);
    	add(ficheEvtUI,BorderLayout.CENTER);
	}



	/*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt()
    {        
        return listeEvenement.getSelectedEvenement();
    }

    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt)
    {
    	
        return listeEvenement.ajouterEvenement(evt);
    }
    
    public boolean retirerEvt(Evenement evt)
    {
    	
        return listeEvenement.retirerEvenement(evt);
    }
    
    
    public void setEventModified(boolean modified) {
        Evenement e = listeEvenement.getSelectedEvenement();

        if (modified) {
            ficheEvtUI.getValues(e);
            listeEvenement.updateEntry(e);
        } else {
            ficheEvtUI.setValues(e);
        } 
    }
    public void setSelectedItem(Object item) {
        if (item == null) {} 
	else {
            if (item instanceof Evenement) {
                controleur.setEvtSelected(true);
                ficheEvtUI.setValues((Evenement) item); // affiche les données du contact                                
            } 
        }
    }
}
