package essenger.ui;

import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Util {
	/**
	 * Autoscroll de un JtextArea en un Jsplitpane
	 * @param texto
	 * @param scrollpnl
	 */
	public static void AutoScroll(JTextArea texto, JScrollPane scrollpnl) {
		texto.getCaret().setDot(texto.getText().length() );
		scrollpnl.scrollRectToVisible(texto.getVisibleRect() );
	}
	
	public static String rellenarConCeroIzaquierda(int num, int cifras, String relleno){
		String sNumero = Integer.toString(num);
			while (sNumero.length() < cifras) {
				sNumero = relleno + sNumero;
			}
		return sNumero;
	}
	
	@SuppressWarnings("deprecation")
	public static String horaActual(){
		Date d = new Date();
		return
		Util.rellenarConCeroIzaquierda(d.getHours(), 2, "0")
		+":"
		+Util.rellenarConCeroIzaquierda(d.getMinutes(), 2, "0")
		+":"
		+Util.rellenarConCeroIzaquierda(d.getSeconds(), 2, "0")
		+"\t";
	}
}
