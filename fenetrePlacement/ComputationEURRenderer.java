package gestionSuivi.fenetrePlacement;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/*
 *  Classe responsable de la mise � jour des prix unitaires (calcul� � partir de l'augmentation de UC et augmentation �)
 *  Attention ! Il a l'air un peu fragile...
 */

public class ComputationEURRenderer extends JLabel implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row,
			int column) {
		try {
			float updatedVal= ((float)table.getModel().getValueAt(row, (column-2)))*((float)table.getModel().getValueAt(row, 1));
			table.setValueAt(updatedVal, row, column);
			this.setText(String.format("%.2f",updatedVal));
		} catch (ArithmeticException e){
			this.setText("Probl�me arithm�tique (?!?)");
		}
		return this;
	}
}
