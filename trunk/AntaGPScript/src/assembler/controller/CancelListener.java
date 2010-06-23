package assembler.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelListener implements ActionListener{
	private Container ui;
	public CancelListener(Container ui) {
		this.ui = ui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.ui.setVisible(false);
	}
}
