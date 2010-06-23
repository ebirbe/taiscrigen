package assembler.quickScript.controller;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChkIMEIListener implements ItemListener{
	
	Component comp;
	
	public ChkIMEIListener(Component comp) {
		this.comp = comp;
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getStateChange() == ItemEvent.SELECTED)		comp.setEnabled(false);
		else												comp.setEnabled(true);
	}

}
