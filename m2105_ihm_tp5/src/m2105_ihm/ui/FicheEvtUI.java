/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
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

/**
 *
 * @author IUT2
 */
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
        
        description.setBorder(BorderFactory.createTitledBorder("Descriptif"));
        participants.setBorder(BorderFactory.createTitledBorder("Participants"));
        this.add(description);
        this.add(participants);
        description.add(new JLabel("Nom de l'événement : "));
        champNom = new JTextField(13);
        description.add(champNom);
        description.add(new JLabel("Date de l'événement : "));
        champjour = new JTextField(5);
        description.add(champjour);
        description.add(new JLabel("/"));
        Mois[] mois = Mois.values();
        listmois = new JComboBox(mois);
        description.add(listmois);
        description.add(new JLabel("/"));
        champan = new JTextField(5);
        description.add(champan);
        String[] colonnes = {"Nom", "Prenom", "telephone"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colonnes);
        nomsTable = new JTable(model);
        participants.add(nomsTable.getTableHeader());
        participants.add(nomsTable);
        this.add(annuler);
        this.add(valider);
        
        add(description,BorderLayout.NORTH);
        add(participants, BorderLayout.EAST);
        add(annuler,BorderLayout.SOUTH);
        add(valider,BorderLayout.SOUTH);
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
        for( int i=0 ; i<model.getRowCount();i++)
		{
			model.removeRow(i);
		}
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
