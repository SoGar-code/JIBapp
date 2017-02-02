package gestionSuivi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import gestionSuivi.compte.Compte;
import gestionSuivi.data.DataIni;
import gestionSuivi.data.DataInterf;

/*
 * Cr�ation des boutons pour s�lection des comptes et des alertes associ�es
 * 
 * Attention ! Pour �tre raccord avec DataIni, il faut initialiser les boutons � "rien n'est s�lectionn�"...
 */

public class BoutonCompte extends JCheckBox{
	
	class CompteListener implements ActionListener{
		private Compte compte;
		
		public CompteListener(Compte compte){
			super();
			this.compte = compte;
		}
		
		public void actionPerformed(ActionEvent e){
			DataInterf dataInterf = DataIni.getInstance();
			dataInterf.updateCompte(compte, ((JCheckBox)e.getSource()).isSelected());
		}
	}
	
	public BoutonCompte(Compte compte){
		super(compte.getName());
		this.addActionListener(new CompteListener(compte));
	}
}
