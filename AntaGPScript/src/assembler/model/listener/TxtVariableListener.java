package assembler.model.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import javax.swing.JOptionPane;
import javax.swing.JTextField;

import assembler.model.Variables;



public class TxtVariableListener implements FocusListener {
	
	private JTextField txt;
	private boolean[] var;
	
	public TxtVariableListener(boolean[] var) {
		this.var = var;
	}

	@Override
	public void focusGained(FocusEvent e){
		txt = (JTextField) e.getSource();
		if(!txt.getText().equals("")) Variables.liberar(var, Integer.parseInt(txt.getText()));
	}

	@Override
	public void focusLost(FocusEvent e) {
		txt = (JTextField) e.getSource();
		try {
			if(!txt.getText().equals("")) Variables.ocupar(var, Integer.parseInt(txt.getText()));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(txt, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			txt.setText("");
			txt.requestFocusInWindow();
		}
	}
}
