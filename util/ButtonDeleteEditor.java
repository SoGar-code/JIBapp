package gestion.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonDeleteEditor extends DefaultCellEditor {
	    
	 protected JButton button;
	 private ButtonListener bListener = new ButtonListener();
	  
	 public ButtonDeleteEditor(JCheckBox checkBox) {
	    //Par d�faut, ce type d'objet travaille avec un JCheckBox
	    super(checkBox);
	     //On cr�e � nouveau notre bouton
	    button = new JButton();
	    button.setOpaque(true);
	    //On lui attribue un listener
	    button.addActionListener(bListener);
	 }
	
	 public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
	    //On d�finit le num�ro de ligne � notre listener
	    bListener.setRow(row);
	    //Idem pour le num�ro de colonne
	    bListener.setColumn(column);
	    //On passe aussi en param�tre le tableau pour des actions potentielles
	    bListener.setTable(table);
	    //On r�affecte le libell� au bouton
	    button.setText( (value ==null) ? "" : value.toString() );
	    //On renvoie le bouton
	     return button;
	 }
	  
	 public class ButtonListener implements ActionListener{
		private int row, column;
		private JTable table;
		private int nbre = 0;
		private JButton button;
		
		public void setColumn(int col){this.column=col;}
		public void setRow(int row){this.row=row;}
		public void setTable(JTable table){this.table=table;}
		
		public JButton getButton(){return this.button;}
		
		public void actionPerformed(ActionEvent event){
			((ZModel)table.getModel()).removeRow(row);
		}
	 }
}
