package essenger.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
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
	
	public static void centrarVentana(Component c){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = c.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		// Center the window
		c.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
	}
	
	public static void pantallaInferiorDerecha(Component c){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.height -= 52;
		screenSize.width -= 52;
		Dimension frameSize = c.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		c.setLocation((screenSize.width - frameSize.width), (screenSize.height - frameSize.height));
	}
	
}
