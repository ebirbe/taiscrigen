package assembler.quickScript.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import assembler.quickScript.ui.PanelSubEvent;

public class ChkActivarListener implements ItemListener {

	PanelSubEvent pse;
	
	public ChkActivarListener(PanelSubEvent pse) {
		super();
		this.pse = pse;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		pse.activarOpciones();
	}

}
