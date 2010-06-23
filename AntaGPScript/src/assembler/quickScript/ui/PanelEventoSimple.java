package assembler.quickScript.ui;

import javax.swing.JLabel;

public class PanelEventoSimple extends PanelSubEvent{

	private static final long serialVersionUID = -2721524960841795743L;
	public JLabel lblOpcion = new JLabel();
	
	public PanelEventoSimple(String strLabel) {
		lblOpcion.setText(strLabel);
		pnlContenido.add(lblOpcion);
		activarOpciones();
	}
}
