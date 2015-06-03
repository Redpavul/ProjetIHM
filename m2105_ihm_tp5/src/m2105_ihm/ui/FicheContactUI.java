/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JTextField champPrenom,champjour,champan;
    private JTextField champEmail;
    private JTextField champTel;
    private JComboBox listmois;
    
    private JButton valider = new JButton("Valider");
    private JButton annuler= new JButton("Annuler");
    
    Region [] r = Region.values();
    JComboBox sortie = new JComboBox(DispoSortie.values());
   
    JCheckBox sport = new JCheckBox("sport");
    JCheckBox lecture = new JCheckBox("lecture");
    JCheckBox musique = new JCheckBox("musique");
    JCheckBox cinema = new JCheckBox("cinema");
    
    /*JCheckBox soir = new JCheckBox("le soireeee");
    JCheckBox nuit = new JCheckBox("la nuit");
    JCheckBox we = new JCheckBox("le week-end");*/
    
    private CheckboxGroup cbg1 = new CheckboxGroup();
    private CheckboxGroup cbg2 = new CheckboxGroup();
    

    
    
    
    
    
    
    
    JComboBox regi = new JComboBox(r);
        
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
	
        this.add(sortie);
	

        
        /*this.add(soir,cbg2);
        this.add(nuit,cbg2);
	this.add(we,cbg2);*/
        
	this.add(new JLabel("Date de naissance : "));
        champjour = new JTextField(5);
        this.add(champjour);
        this.add(new JLabel("/"));
        Mois [] mois = Mois.values();
        listmois = new JComboBox(mois);
        this.add(listmois);
        this.add(new JLabel("/"));
        champan = new JTextField(5);
        this.add(champan);
	
	this.add(new JLabel("Loisirs : "));
	
	this.add(sport,cbg1);
        this.add(lecture,cbg1);
        this.add(musique,cbg1);
        this.add(cinema,cbg1);
        
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
	
	this.add(new JLabel("téléphone: "));
	

	this.add(regi);
	
	this.add(valider);
	
	this.add(annuler);
        
        
        /*this.add(champNom, BorderLayout.CENTER);
        this.add(valider, BorderLayout.PAGE_END);*/
        BorderLayout f = new BorderLayout();
      
         /*       GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(champNom)
                .addComponent(sortie)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(champDateNaissance)
                    .addComponent(champPrenom))
);
layout.setVerticalGroup(
   layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
           .addComponent(champNom)
           .addComponent(sortie)
           .addComponent(champDateNaissance))
      .addComponent(champPrenom)
);*/
    }
    
    /**
     * Affecte des valeurs au formulaire de fiche contact
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean setValues(Contact contact) {
        if (contact == null) { return false; }
        champNom.setText(contact.getNom());
	//champDateNaissance.setText(""+contact.getDateNaissanceJour());
	listmois.setSelectedItem(contact.getDateNaissanceMois());
        String an = ""+contact.getDateNaissanceJour();
        champjour.setText(an);
        String jour = ""+contact.getDateNaissanceAnnee();
        champan.setText(jour);
        champPrenom.setText(contact.getPrenom());
	champEmail.setText(contact.getEmail());
	champTel.setText(""+contact.getNumeroTelephone());
        
        Hobby [] h = contact.getHobbies();
        for(int i=0 ; i<contact.getHobbies().length;i++)
        {
            if(h[i]==Hobby.CINEMA)
            {
               cinema.setSelected(true);
            }
            else
            {
                cinema.setSelected(false);
            }
            
            if(h[i]==Hobby.SPORT)
            {
                sport.setSelected(true);
            }
            else
            {
                sport.setSelected(false);
            }
            
            if(h[i]==Hobby.LECTURE)
            {
                lecture.setSelected(true);
            }
            else
            {
                lecture.setSelected(false);
            }
            
            if(h[i]==Hobby.MUSIQUE)
            {
                musique.setSelected(true);
            }
            else
            {
                musique.setSelected(false);
            }
        }
        
        
        /*DispoSortie [] s = contact.getSorties();
        for(int i=0 ; i<contact.getSorties().length;i++)
        {
            if(s[i]==DispoSortie.NUIT)
            {
               nuit.setSelected(true);
            }
            else
            {
                nuit.setSelected(false);
            }
            
            if(s[i]==DispoSortie.SOIR)
            {
               soir.setSelected(true);
            }
            else
            {
                soir.setSelected(false);
            }
            
            if(s[i]==DispoSortie.WEEK_END)
            {
               we.setSelected(true);
            }
            else
            {
                we.setSelected(false);
            }
            

        }*/
	//check.setSelectedItem(contact.getHobbies());
	sortie.setSelectedItem(contact.getDisponibilite());
	regi.setSelectedItem(contact.getRegion());
        return true;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche contact
     * @param contact un contact à mettre à jour à partir de l'IHM
     * @return vrai si ok
     */
    public boolean getValues(Contact contact) {
        if (contact == null) { return false; }
        
        contact.setNom(champNom.getText());
	contact.setDateNaissance(Integer.parseInt(champan.getText()), (Mois)listmois.getSelectedItem(),Integer.parseInt(champjour.getText()) );
	contact.setPrenom(champPrenom.getText());
	contact.setEmail(champEmail.getText());
	contact.setNumeroTelephone(champTel.getText());
	//contact.setHobbies(check.getSelectedItem());
	sortie.setSelectedItem(contact.getDisponibilite());
	regi.setSelectedItem(contact.getRegion());

        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        
       valider.addActionListener(new ActionListener()
        {


            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(true);
            }
        });
        
        annuler.addActionListener(new ActionListener()
        {


            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(false);
            }
        });
        
    }    
}