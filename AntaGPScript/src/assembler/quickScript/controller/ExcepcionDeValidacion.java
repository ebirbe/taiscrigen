package assembler.quickScript.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ExcepcionDeValidacion extends Exception {

	private static final long serialVersionUID = 3269195535055001413L;
	private String texto;
	private Component comp;
	private Color colorAnt;
	
	public ExcepcionDeValidacion(String string, Component comp) {
		this.texto = string;
		this.comp = comp;
		this.colorAnt = comp.getBackground();
		this.comp.setBackground(Color.RED);
		this.comp.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) {
				ExcepcionDeValidacion.this.comp.setBackground(colorAnt);
			}
		});
	}
	
	public String getTexto(){
		return texto;
	}

}
