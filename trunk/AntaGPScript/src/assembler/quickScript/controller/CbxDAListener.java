package assembler.quickScript.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;

import assembler.model.Variables;

public class CbxDAListener implements FocusListener {
	
	private JComboBox cbx;
	
	public CbxDAListener(JComboBox cbx) {
		this.cbx = cbx;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		cbx.removeAllItems();
		llenarCbxDA();
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}
	
	public void llenarCbxDA(){
		for(int i=0;i<Variables.DA.length;i++){
			if(Variables.DA[i]) cbx.addItem(i);
		}
	}
}
