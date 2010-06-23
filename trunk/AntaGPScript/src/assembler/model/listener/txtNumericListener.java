package assembler.model.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class txtNumericListener implements KeyListener {
	
	JTextField txt;
	
	public txtNumericListener(JTextField txt) {
		this.txt = txt;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		JTextField t = (JTextField) arg0.getSource();
		String aux = t.getText();
		if(!isNumeric(t.getText())){
			//txt.setText(aux.substring(beginIndex));
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	private boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

}
