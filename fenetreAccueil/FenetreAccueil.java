package gestionSuivi.fenetreAccueil;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gestionSuivi.data.DataIni;
import gestionSuivi.data.DataInterf;

public class FenetreAccueil extends JPanel {
	private JTable tableauRepartition;
	private JButton maJ = new JButton("M�J");
	
	public FenetreAccueil(){
		super();

		DataInterf dataInterf = DataIni.getInstance();
		
		// Chercher les donn�es pour l'accueil :
		Object[][] data = dataInterf.accueilData();
		
	    //Les titres des colonnes
	    String  title[] = {"Type", "Euros", "%"};
	    
	    ZModel2 model = new ZModel2(data, title);
	    this.tableauRepartition = new JTable(model);
	    this.tableauRepartition.setRowHeight(30);
	    
	    this.tableauRepartition.getColumn("Euros").setCellRenderer(new NumberRenderer());
	    this.tableauRepartition.getColumn("%").setCellRenderer(new PercentageRenderer());
	    
	    // Bouton et son listener
	    class MaJListener implements ActionListener{
			ZModel2 model;
			
			public MaJListener(ZModel2 model){
				super();
				this.model=model;
			}
			
			public void actionPerformed(ActionEvent event){
				// mise � jour du panneau synth�se (sur des donn�es � jour)
				model.setDataVector(dataInterf.accueilData(),title);
			}
		}
	    maJ.addActionListener(new MaJListener(model));
		
		// Mise en place finale
	    JPanel pan = new JPanel();
	    pan.setPreferredSize(new Dimension(350,100));
	    pan.add(new JScrollPane(tableauRepartition));
	    
		BorderLayout bl = new BorderLayout(); 
		this.setLayout(bl);

		this.add(new JLabel("R�partition des sommes :"), BorderLayout.NORTH);
		this.add(pan, BorderLayout.CENTER);
		this.add(maJ,BorderLayout.SOUTH);
		this.setVisible(true);
	}
}
