package m2105_ihm.ui;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.Hobby;
import m2105_ihm.nf.Symbole;


public class FicheGroupeUI extends javax.swing.JPanel
{
    private CarnetUI carnet;
    private ZoneDessinUI zoneDessin;
    private JTextField groupeName;
    JTable nomsTable;
    JList symbList;
    DefaultTableModel model;
    private JButton effacer = new JButton("effacer");
    private JButton valider = new JButton("valider");
    private JButton annuler = new JButton("annuler");
    
    private JPanel noms = new JPanel();
    private JPanel logo = new JPanel();
    private JPanel participants = new JPanel();
    private JPanel confirmation = new JPanel();
    
    private JPanel nom = new JPanel();
    private JPanel sym = new JPanel();
    
    private JPanel eff = new JPanel();
    private JPanel log = new JPanel();
    
    private JPanel val = new JPanel();
    private JPanel ann = new JPanel();
    
    
    
    private GridLayout grillePrincipale;
    private GridLayout grilleInfos;
    private GridLayout grilleLogo;
    private GridLayout grilleConfirmation;
    

    
    
    public FicheGroupeUI(CarnetUI carnet)
    { 
        super();
       
        this.carnet = carnet;
        
        initUIComponents();    
        initListeners();	
	
    }

    private void initUIComponents()
    {        
        //Boutons
	
	grillePrincipale = new GridLayout(2,2);
	grilleInfos = new GridLayout(2,1);
	grilleLogo = new GridLayout(2,1);
	grilleConfirmation = new GridLayout(2,1);
	
	this.setLayout(grillePrincipale);
	
	
	noms.setBorder(BorderFactory.createTitledBorder("Descriptifs"));
	logo.setBorder(BorderFactory.createTitledBorder("Logo"));
	participants.setBorder(BorderFactory.createTitledBorder("Participants"));
	confirmation.setBorder(BorderFactory.createTitledBorder("Confirmation"));
	
	
	
        
        noms.setLayout(grilleInfos);
	logo.setLayout(grilleLogo);
	confirmation.setLayout(grilleConfirmation);
	
	ann.add(annuler);
	val.add(valider);
	
	confirmation.add(val);
	confirmation.add(ann);

	
	//champs texte
	nom.add(new JLabel("Nom :"));
	groupeName = new JTextField(13);
        nom.add(groupeName);
	noms.add(nom);
	//tableau infos contacts
	String [] colonnes = {"Nom", "Prenom", "telephone"};
	model = new DefaultTableModel();
	model.setColumnIdentifiers(colonnes);
	nomsTable = new JTable(model);
	participants.add(nomsTable.getTableHeader());
	participants.add(nomsTable);
	
        sym.add(new JLabel("Symboles du groupe : "));
	
        symbList = new JList(Symbole.values());
        sym.add(symbList);
	
	noms.add(sym);
	
	//Zone dessin
        zoneDessin = new ZoneDessinUI();
	log.add(zoneDessin);
	eff.add(effacer);
	
        logo.add(log);
        logo.add(eff);
	
	
	
	this.add(noms);
	this.add(logo);
	this.add(participants);
	this.add(confirmation);
    }

    public boolean setValues(GroupeContacts groupe)
    {
        if (groupe == null)
	    return false;
	else
	{

	    model.setRowCount(0);
	    groupeName.setText(groupe.getNom());
	    
	    //Tableau noms
	    String [] obj = new String [3];
	    for(Contact tmp : groupe.getContacts())
	    {
		obj[0] = tmp.getNom();
		obj[1] = tmp.getPrenom();
		obj[2] = tmp.getNumeroTelephone();		
		model.addRow(obj);
		
		
	    }
            //afficher la liste des symboles associés au groupe
            int indice [] = new int[groupe.getSymboles().length];
	    for(int i = 0; i < groupe.getSymboles().length ; i++)
	    {
		indice[i] = groupe.getSymboles()[i].ordinal();
	    }
	    symbList.setSelectedIndices(indice);
	    zoneDessin.effacer();
            this.zoneDessin.setPoints(groupe.getPoints());
            return true;
        }
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche groupe de contacts
     * @return
     */    
    public boolean getValues(GroupeContacts groupe)
    {
        if (groupe == null)
            return false;
        
        groupe.setNom(groupeName.getText());
	for(Object o : symbList.getSelectedValues())
	{
		    groupe.addSymbole((Symbole)o);
	}
        
        groupe.setPoints(this.zoneDessin.getPoints());
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    public void initListeners()
    {        
      
        
        effacer.addActionListener(new ActionListener()
        {


            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.effacer();
            }
        });
        /*
         * Réagit aux évènements produits par le bouton effacer
         */
        zoneDessin.addMouseListener(new MouseListener()
        {
            //public void mouseClicked(MouseEvent e)()

           
            public void mouseClicked(MouseEvent e){}
            
            @Override
            public void mousePressed(MouseEvent e) 
            {
                //JFrame w = (JFrame) e.getComponent();
                Point p = new Point(e.getX(),e.getY());
                zoneDessin.addPoint(p);
            }

            public void mouseReleased(MouseEvent e) {}
           
            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}
        });
        /** TP 2 : à completer **/
        
        
               valider.addActionListener(new ActionListener()
        {


            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(true);
            }
        });
        
        annuler.addActionListener(new ActionListener()
        {


            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(false);
            }
        });
        
        
        
    }    
}
