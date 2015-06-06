/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import m2105_ihm.nf.*;

/**
 *
 * @author IUT2
 */
public class FicheContactUI extends JPanel
{

	private CarnetUI carnet;
	private JTextField champNom;
	private JTextField naissanceJour;
	private JTextField naissanceAnnee;
	private JTextField champPrenom;
	private JTextField champEmail;
	private JTextField champTel;
	private JButton valider = new JButton("Valider");
	private JButton annuler= new JButton("Annuler");
	private JComboBox sortieName = new JComboBox(DispoSortie.values());
	private JCheckBox check;
	private JComboBox regi = new JComboBox(Region.values());
	private JComboBox mois = new JComboBox(Mois.values());
	private HashMap<Hobby, JCheckBox> hobbyList = new HashMap();
	
	
	public FicheContactUI(CarnetUI carnet)
	{
		super();
		this.carnet = carnet;
		initUIComponents();
		initListeners();
	}

	private void initUIComponents()
	{      
		
		this.add(new JLabel("Nom :"));
		champNom = new JTextField(13);
		this.add(champNom);

		this.add(new JLabel("Préférences sorties : "));
		this.add(sortieName);

		this.add(new JLabel("Jour de naissance : "));
		naissanceJour = new JTextField(13);
		this.add(naissanceJour);

		this.add(new JLabel("Mois de naissance : "));
		this.add(mois);

		this.add(new JLabel("Année de naissance : "));
		naissanceAnnee = new JTextField(13);
		this.add(naissanceAnnee);

		this.add(new JLabel("Loisirs : "));
		//this.add(check);

		this.add(new JLabel("Prénom : "));
		champPrenom = new JTextField(13);
		this.add(champPrenom);

		this.add(new JLabel("email : "));
		champEmail = new JTextField(13);
		this.add(champEmail);

		this.add(new JLabel("téléphone: "));
		champTel = new JTextField(13);
		this.add(champTel);
		
		this.add(new JLabel("Loisirs : "));
		for(Hobby h : Hobby.values())
		{
		    check = new JCheckBox(""+h);
		    this.add(check);
		    hobbyList.put(h, check);
		}

		this.add(regi);

		this.add(valider);

		this.add(annuler);
	}

	/**
	 * Affecte des valeurs au formulaire de fiche contact
	 * @param contact un contact pour mettre à jour à l'IHM
	 * @return vrai si ok
	 */
	public boolean setValues(Contact contact)
	{
		if (contact == null) { return false; }
		champNom.setText(contact.getNom());
		naissanceJour.setText(""+contact.getDateNaissanceJour());
		mois.setSelectedItem(contact.getDateNaissanceMois());
		naissanceAnnee.setText(""+contact.getDateNaissanceAnnee());
		champPrenom.setText(contact.getPrenom());
		champEmail.setText(contact.getEmail());
		champTel.setText(""+contact.getNumeroTelephone());
		sortieName.setSelectedItem(contact.getDisponibilite());
		regi.setSelectedItem(contact.getRegion());
		for(JCheckBox j : hobbyList.values())
		{
		    j.setSelected(false);
		}
		
		for(Hobby h : contact.getHobbies())
		{
		    hobbyList.get(h).setSelected(true);
		}
		
		
		
		return true;
	}

	public boolean getValues(Contact contact) {
		if (contact == null) { return false; }
		contact.setNom(champNom.getText());
		int jour = Integer.parseInt(naissanceJour.getText());
		Mois moiss = (Mois)mois.getSelectedItem();
		int annee = Integer.parseInt(naissanceAnnee.getText());
		contact.setDateNaissance(jour, moiss, annee);
		
		for(Hobby h : Hobby.values())
		{
		    contact.removeHobby(h);
		}
		contact.setPrenom(champPrenom.getText());
		contact.setEmail(champEmail.getText());
		contact.setNumeroTelephone(champTel.getText());
		contact.setDisponibilite((DispoSortie)sortieName.getSelectedItem());
		contact.setRegion((Region)regi.getSelectedItem());
		
		for(Hobby h : hobbyList.keySet())
		{
		    if(hobbyList.get(h).isSelected())
			contact.addHobby(h);
		}

		return true;
	}

	/**
	 * Initialise la gestion des événements
	 */
	private void initListeners()
	{
	    annuler.addActionListener(new ActionListener()
	    {
		@Override
		public void actionPerformed(ActionEvent e)
		{
		    carnet.setContactModified(false);
		}
	    });
	    
	    valider.addActionListener(new ActionListener()
	    {
		@Override
		public void actionPerformed(ActionEvent e)
		{
		     carnet.setContactModified(true);
		}
	    });
	}
	
}
