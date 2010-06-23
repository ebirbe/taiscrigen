package assembler.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class OkListener implements ActionListener{
	private Container ui;
	public OkListener(Container ui) {
		this.ui = ui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.ClosePanel();
	}
	
	public void ClosePanel(){
		this.ui.setVisible(false);
	}
}
