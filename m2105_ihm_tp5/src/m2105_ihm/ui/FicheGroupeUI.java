package m2105_ihm.ui;

import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
	this.add(new javax.swing.JButton("Hello !"));
	this.add(annuler);
	this.add(valider);
        
        
	
	//champs texte
	this.add(new javax.swing.JLabel("Fiche groupe"));
	this.add(new JLabel("Nom :"));
	groupeName = new JTextField(13);
        this.add(groupeName);
	
	//tableau infos contacts
	String [] colonnes = {"Nom", "Prenom", "telephone"};
	model = new DefaultTableModel();
	model.setColumnIdentifiers(colonnes);
	nomsTable = new JTable(model);
	this.add(nomsTable.getTableHeader());
	this.add(nomsTable);
	
        this.add(new JLabel("Symboles du groupe : "));
	
        symbList = new JList(Symbole.values());
        this.add(symbList);
	
	
	
	//Zone dessin
        zoneDessin = new ZoneDessinUI();
        this.add(zoneDessin);
        this.add(effacer);
    }

    public boolean setValues(GroupeContacts groupe)
    {
        if (groupe == null)
	    return false;
	else
	{
		for( int i=0 ; i<model.getRowCount();i++)
		{
			model.removeRow(i);
		}
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
