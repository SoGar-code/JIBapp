package gestion.data.quotation;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Mano extends Transfer {
	
	// Une seule instance attendue (mais un singleton paraissait �tre de la sur-qualit�)
	// En outre, les "Transfer" sont appel�s via Transfer.values()
	public Mano() {
		super("� la mano ! (pas de M�J)");
	}
	
	// Identifiant de cette classe :
	public int getIdTransf(){
		return 4;
	}

	@Override
	public Date getDate(String text) throws QuotationException{
		throw new QuotationException();
	}

	@Override
	public float getPrice(String text) throws QuotationException{
		throw new QuotationException();
	}
}
