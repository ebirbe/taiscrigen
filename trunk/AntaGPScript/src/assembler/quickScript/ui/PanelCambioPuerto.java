package assembler.quickScript.ui;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import assembler.model.Configuration;

public class PanelCambioPuerto extends PanelSubEvent {

	private static final long serialVersionUID = -8807760863000822927L;
	public JComboBox cbxPuerto = new JComboBox();
	public JComboBox cbxCondicion = new JComboBox();
	public JLabel lblPuerto = new JLabel("Detectar Puerto:");
	public JLabel lblCondicion = new JLabel("cuando es");
	
	public PanelCambioPuerto() {
		super();
		super.lblTitulo.setText("DETECTAR CAMBIO DE PUERTO");
		pnlContenido.add(lblPuerto);
		pnlContenido.add(getCbxPuerto());
		pnlContenido.add(lblCondicion);
		pnlContenido.add(getCbxCondicion());
		activarOpciones();
	}
	public JComboBox getCbxPuerto(){
		for(Integer p : Configuration.input_port){
			cbxPuerto.addItem(p);
		}
		return cbxPuerto;
	}
	public JComboBox getCbxCondicion(){
		cbxCondicion.addItem("Desactivado");
		cbxCondicion.addItem("Activado");
		return cbxCondicion;
	}
}
