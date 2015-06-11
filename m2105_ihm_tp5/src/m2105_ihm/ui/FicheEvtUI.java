/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import m2105_ihm.nf.Mois;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;

public class FicheEvtUI extends JPanel {

    /*
     * Attributs
     */
    private PlanningUI planning;
    private JTextField champNom;
    private JTextField champjour, champan;
    private JComboBox listmois;
    private JButton valider = new JButton("Valider");
    private JButton annuler = new JButton("Annuler");
    JTable nomsTable;
    JList symbList;
    DefaultTableModel model;
    private JPanel description = new JPanel();
    private JPanel participants = new JPanel();
    
        
    private JPanel nom = new JPanel();
    private JPanel date= new JPanel();
    private JPanel bouton= new JPanel();
    
    
    private GridLayout grilleinfos;
    private GridLayout grillePrincipale;
    private GridLayout grilleParticipants;

    /**
     * Creates new form CarnetUI
     */
    public FicheEvtUI(PlanningUI planning) {
        super();
        this.planning = planning;
        initUIComponents();
        initListeners();
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {
        //this.add(new JLabel("Piou"));
        
	grilleinfos = new GridLayout(3,1);
	grillePrincipale = new GridLayout(2,1);
	grilleParticipants = new GridLayout(2,1);

	this.setLayout(grillePrincipale);
        description.setBorder(BorderFactory.createTitledBorder("Descriptif"));
        participants.setBorder(BorderFactory.createTitledBorder("Participants"));
	description.setLayout(grilleinfos);
	participants.setLayout(grilleParticipants);
        this.add(description);
        this.add(participants);
	
        nom.add(new JLabel("Nom de l'événement : "));
        champNom = new JTextField(13);
        nom.add(champNom);
        date.add(new JLabel("Date de l'événement : "));
        champjour = new JTextField(5);
        date.add(champjour);
        date.add(new JLabel("/"));
        Mois[] mois = Mois.values();
        listmois = new JComboBox(mois);
        date.add(listmois);
        date.add(new JLabel("/"));
        champan = new JTextField(5);
        date.add(champan);
        String[] colonnes = {"Nom", "Prenom", "telephone"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colonnes);
        nomsTable = new JTable(model);
        participants.add(nomsTable.getTableHeader());
        participants.add(nomsTable);
        bouton.add(annuler);
        bouton.add(valider);
	
	
	
		
	description.add(nom);
	description.add(date);
	description.add(bouton);
	
        add(description);
        add(participants);
    }

    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planning.setEventModified(false);
            }
        });

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planning.setEventModified(true);
            }
        });
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     *
     * @param Evenement un événement
     * @return
     */
    public boolean setValues(Evenement event) {
        if (event == null) {
            return false;
        }
        if (event == null) {
            return false;
        }
        champNom.setText(event.getIntitule());
        champjour.setText("" + event.getDateJour());
        listmois.setSelectedItem(event.getDateMois());
        champan.setText("" + event.getDateAnnee());

        String[] obj = new String[3];

	model.setRowCount(0);
	
        for (Contact tmp : event.getParticipants()) {
            obj[0] = tmp.getNom();
            obj[1] = tmp.getPrenom();
            obj[2] = tmp.getNumeroTelephone();
            model.addRow(obj);

        }
        return true;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     *
     * @param Evenement événement à affecter
     * @return
     */
    public boolean getValues(Evenement event) {
        if (event == null) {
            return false;
        }

        event.setIntitule(champNom.getText());
        int jour = Integer.parseInt(champjour.getText());
        Mois moiss = (Mois) listmois.getSelectedItem();
        int annee = Integer.parseInt(champan.getText());
        event.setDate(jour, moiss, annee);
        return true;

    }
}
