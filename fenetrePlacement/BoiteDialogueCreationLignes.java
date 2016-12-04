package gestionSuivi.fenetrePlacement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;

import gestionSuivi.compte.Compte;
import gestionSuivi.placement.Placement;


/**
 * 
 * @author Trivy
 * Classe pour la cr�ation des nouvelles lignes (sur r�partition pr�d�finie).
 */

public class BoiteDialogueCreationLignes extends JDialog{
	String[] optionsDispo={Placement.LyxorETFEuro.getName(),Placement.LyxorETFDJ.getName()};
	private TableauTransaction tabTrans;
	
	public BoiteDialogueCreationLignes(TableauTransaction tabTrans){
		super();
		this.tabTrans=tabTrans;
		this.setTitle("Choix placement");
		this.setModal(false);
		this.setSize(400,250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JPanel panChoix = new JPanel();
		JPanel panControl = new JPanel();
		
		/** n�cessaire pour remplir nouvelle ligne :
		 * date
		 * compte concern� (d�fini par d�faut)
		 * cours
		 * UC ajout�es (d�fini � partir de UC tot)
		 * UC retir�es (0)
		 * � ajout�s (calcul � partir des valeurs obtenues)
		 * � retir�s (0)
		 */
		
		// date :
		JLabel dateLabel = new JLabel("Date ? (format JJ/MM/AAAA)");
		JTextField jtf = new JTextField("03/12/2016"); // Pourrait �tre remplac� par un contr�le du format des dates.

		// entr�e placement
		JLabel placementLabel = new JLabel("Choix du placement :");
		JComboBox choixPlacement = new JComboBox(optionsDispo);
		
		// entr�e cours 
		JLabel coursLabel = new JLabel("Cours :");
		JFormattedTextField jftfCours = new JFormattedTextField(NumberFormat.getNumberInstance());
		
		// entr�e nbr UC
		// + formatage des nombres � 4 d�cimales...
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumFractionDigits(4);
		JLabel totUCLabel = new JLabel("Nbr tot. UC :");
		JFormattedTextField jftfUC = new JFormattedTextField(formatter);
		
		panChoix.setLayout(new GridLayout(4,2));
		panChoix.add(dateLabel);
		panChoix.add(jtf);
		panChoix.add(placementLabel);
		panChoix.add(choixPlacement);
		panChoix.add(coursLabel);
		panChoix.add(jftfCours);
		panChoix.add(totUCLabel);
		panChoix.add(jftfUC);	
		
	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
	    
	    JButton okBouton = new JButton("OK");
	    okBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0){
			Compte[] listeCompte = Compte.values();
	    	JTable tableau = tabTrans.getTableau();
	    	float[] nbr = new float[2];
	    	float cours = 0f;
	    	if (choixPlacement.getSelectedIndex()==0){
	    		// la valeur 20/78f correspond � la r�partition "ordinaire"/"retraite" dans Epargnissimo (pour Lyxor Euro)
	    		nbr[0]=(20/78f)*((Number)jftfUC.getValue()).floatValue();
	    		nbr[1]=(58/78f)*((Number)jftfUC.getValue()).floatValue();	    		
	    	}
	    	else if (choixPlacement.getSelectedIndex()==1){
	    		// la valeur 20/48f correspond � la r�partition "ordinaire"/"retraite" dans Epargnissimo (pour Lyxor DJ)
	    		nbr[0]=(20/48f)*((Number)jftfUC.getValue()).floatValue();
	    		nbr[1]=(28/48f)*((Number)jftfUC.getValue()).floatValue();	    		
	    	} else {
	    		nbr[0]=0f;
	    		nbr[1]=0f;
	    	}
	    	cours =((Number)jftfCours.getValue()).floatValue();
	    	
	    	// ajoute la premi�re ligne au tableau
	    	Object[] ligne0 = {jtf.getText(), listeCompte[0], cours, nbr[0], new Float(0), nbr[0]*cours, new Float(0), "-"};
	    	((ZModel)tableau.getModel()).addRow(ligne0);

	    	// ajoute la seconde ligne au tableau
	    	Object[] ligne1 = {jtf.getText(), listeCompte[1], cours, nbr[1], new Float(0), nbr[1]*cours, new Float(0), "-"};
	    	((ZModel)tableau.getModel()).addRow(ligne1);
	    	
	    	// termine le dialogue en rendant la boite invisible
	        setVisible(false);
	      }
	    });
	    
	    panControl.add(cancelBouton);
	    panControl.add(okBouton);
	    
	    this.getContentPane().add(panChoix, BorderLayout.CENTER);
	    this.getContentPane().add(panControl, BorderLayout.SOUTH);
	}
}
