/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends JPanel
{
	
	private Controleur controleur;
	
	
	private FicheEvtUI ficheEvtUI;
	
    /** 
     * Constructeur : initialise les composants de l'IHM pour les événements
     * @param une instance du controleur
     */
    public PlanningUI(Controleur ctrl)
    {
        super();
        this.controleur = controleur;
        
        initUIComponents(); 
        
    }
    
    

    private void initUIComponents()
	{
		
    	
    	ficheEvtUI = new FicheEvtUI(this);
    	
    	setLayout(new BorderLayout());
    	add(ficheEvtUI,BorderLayout.CENTER);
	}



	/*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt()
    {        
        return null;
    }

    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt)
    {
    	
        return true;
    }    
}
