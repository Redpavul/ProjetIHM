/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
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
public class FicheContactUI extends JPanel {

    private CarnetUI carnet;
    private JTextField champNom;
    private JTextField naissanceJour;
    private JTextField naissanceAnnee;
    private JTextField champPrenom;
    private JTextField champEmail;
    private JTextField champTel;
    private JButton valider = new JButton("Valider");
    private JButton annuler = new JButton("Annuler");
    private JComboBox sortieName = new JComboBox(DispoSortie.values());
    private JCheckBox check;
    private JComboBox regi = new JComboBox(Region.values());
    private JComboBox mois = new JComboBox(Mois.values());
    private HashMap<Hobby, JCheckBox> hobbyList = new HashMap();
    
    private JPanel infos = new JPanel();
    private JPanel coordonnees = new JPanel();
    private JPanel preferences = new JPanel();
    private JPanel validation = new JPanel();
    
    private JPanel datedenaissance = new JPanel();
    private JPanel nomprenom = new JPanel();
    
    private JPanel telmail= new JPanel();
    private JPanel region = new JPanel();
    
    private JPanel lois = new JPanel();
    private JPanel sortie = new JPanel();
	    
    private GridLayout grillePrincipale;
    private GridLayout grilleInfo;
    private GridLayout grilleCoordonnees;
    private GridLayout grillePreferences;

    public FicheContactUI(CarnetUI carnet) {
	super();
	this.carnet = carnet;
	initUIComponents();
	initListeners();
    }

    private void initUIComponents() {
	
	
	grillePrincipale = new GridLayout(4,1);
	grilleInfo = new GridLayout(2,1);
	grilleCoordonnees = new GridLayout(3,1);
	grillePreferences = new GridLayout(2,1);
	
	this.setLayout(grillePrincipale);
	infos.setBorder(BorderFactory.createTitledBorder("Informations générales"));
	coordonnees.setBorder(BorderFactory.createTitledBorder("Coordonnées"));
	preferences.setBorder(BorderFactory.createTitledBorder("Préférence"));
	validation.setBorder(BorderFactory.createTitledBorder("Confirmation"));
	infos.setLayout(grilleInfo);
	coordonnees.setLayout(grilleCoordonnees);
	preferences.setLayout(grillePreferences);
	
	nomprenom.add(new JLabel("Nom :"));
	champNom = new JTextField(13);
	nomprenom.add(champNom);
	
	nomprenom.add(new JLabel("Prénom : "));
	champPrenom = new JTextField(13);
	nomprenom.add(champPrenom);

	sortie.add(new JLabel("Préférences sorties : "));
	sortie.add(sortieName);

	datedenaissance.add(new JLabel("Date de naissance : "));
	naissanceJour = new JTextField(5);
	datedenaissance.add(naissanceJour);

	datedenaissance.add(new JLabel("/"));
	datedenaissance.add(mois);

	datedenaissance.add(new JLabel("/"));
	naissanceAnnee = new JTextField(5);
	datedenaissance.add(naissanceAnnee);

	telmail.add(new JLabel("Email : "));
	champEmail = new JTextField(16);
	telmail.add(champEmail);

	telmail.add(new JLabel("Téléphone: "));
	champTel = new JTextField(13);
	telmail.add(champTel);

	lois.add(new JLabel("Loisirs : "));
	for (Hobby h : Hobby.values()) {
	    check = new JCheckBox("" + h);
	    lois.add(check);
	    hobbyList.put(h, check);
	}
	region.add(new JLabel("                 Région : "));
	region.add(regi);

	validation.add(valider);
	validation.add(annuler);

	
	coordonnees.add(telmail);
	coordonnees.add(region);
	infos.add(nomprenom);
	infos.add(datedenaissance);
	preferences.add(lois);
	preferences.add(sortie);
	
	this.add(infos);
	this.add(coordonnees);
	this.add(preferences);
	this.add(validation);
    }

    /**
     * Affecte des valeurs au formulaire de fiche contact
     *
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean setValues(Contact contact) {
	if (contact == null) {
	    return false;
	}
	champNom.setText(contact.getNom());
	naissanceJour.setText("" + contact.getDateNaissanceJour());
	mois.setSelectedItem(contact.getDateNaissanceMois());
	naissanceAnnee.setText("" + contact.getDateNaissanceAnnee());
	champPrenom.setText(contact.getPrenom());
	champEmail.setText(contact.getEmail());
	champTel.setText("" + contact.getNumeroTelephone());
	sortieName.setSelectedItem(contact.getDisponibilite());
	regi.setSelectedItem(contact.getRegion());
	for (JCheckBox j : hobbyList.values()) {
	    j.setSelected(false);
	}

	for (Hobby h : contact.getHobbies()) {
	    hobbyList.get(h).setSelected(true);
	}

	return true;
    }

    public boolean getValues(Contact contact) {
	if (contact == null) {
	    return false;
	}
	contact.setNom(champNom.getText());
	int jour = Integer.parseInt(naissanceJour.getText());
	Mois moiss = (Mois) mois.getSelectedItem();
	int annee = Integer.parseInt(naissanceAnnee.getText());
	contact.setDateNaissance(jour, moiss, annee);

	for (Hobby h : Hobby.values()) {
	    contact.removeHobby(h);
	}
	contact.setPrenom(champPrenom.getText());
	contact.setEmail(champEmail.getText());
	contact.setNumeroTelephone(champTel.getText());
	contact.setDisponibilite((DispoSortie) sortieName.getSelectedItem());
	contact.setRegion((Region) regi.getSelectedItem());

	for (Hobby h : hobbyList.keySet()) {
	    if (hobbyList.get(h).isSelected()) {
		contact.addHobby(h);
	    }
	}

	return true;
    }

    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
	annuler.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		carnet.setContactModified(false);
	    }
	});

	valider.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		carnet.setContactModified(true);
	    }
	});
    }

}
