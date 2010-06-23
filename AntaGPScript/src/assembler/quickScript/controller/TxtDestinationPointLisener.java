package assembler.quickScript.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import assembler.quickScript.ui.MainUI;

public class TxtDestinationPointLisener implements KeyListener {

	public MainUI ui;

	public TxtDestinationPointLisener(MainUI ui) {
		this.ui = ui;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ui.pnlDA1.nameCHKS();
		ui.pnlDA2.nameCHKS();
		ui.pnlDA3.nameCHKS();
		ui.pnlDA4.nameCHKS();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
