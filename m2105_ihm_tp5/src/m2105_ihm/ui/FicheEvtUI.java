/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
public class FicheEvtUI extends javax.swing.JPanel {

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
        this.add(new JLabel("Nom de l'évenement : "));
        champNom = new JTextField(13);
        this.add(champNom);
        this.add(new JLabel("Date de naissance : "));
        champjour = new JTextField(5);
        this.add(champjour);
        this.add(new JLabel("/"));
        Mois[] mois = Mois.values();
        listmois = new JComboBox(mois);
        this.add(listmois);
        this.add(new JLabel("/"));
        champan = new JTextField(5);
        this.add(champan);
        this.add(annuler);
        this.add(valider);
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
        if (event == null) { return false; }
		champNom.setText(event.getIntitule());
		champjour.setText(""+event.getDateJour());
		listmois.setSelectedItem(event.getDateMois());
		champan.setText(""+event.getDateAnnee());		
        return true;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     *
     * @param Evenement événement à affecter
     * @return
     */
    public boolean getValues(Evenement event) {
        if (event == null) {return false;}

            event.setIntitule(champNom.getText());
            int jour = Integer.parseInt(champjour.getText());
            Mois moiss = (Mois) listmois.getSelectedItem();
            int annee = Integer.parseInt(champan.getText());
            event.setDate(jour, moiss, annee);
            return true;

    }
}
