package assembler.quickScript.ui;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import assembler.model.Variables;
import assembler.ui.IntegerTextField;

public class PanelBateriaBaja extends PanelSubEvent {
	
	private static final long serialVersionUID = 5519758255234182827L;
	public JLabel lbl2 = new JLabel("B#");
	public IntegerTextField txtID = new IntegerTextField("40");
	public JLabel lbl1 = new JLabel("Por debajo de");
	public IntegerTextField txtCantidad = new IntegerTextField("40");
	public JComboBox cbxUnidad = new JComboBox();
	public PanelBateriaBaja() {
		super();
		lblTitulo.setText("ALERTA DE BATERIA");
		pnlContenido.add(lbl2);
		txtID.setPreferredSize(new Dimension(38,18));
		try {
			txtID.setText(Variables.ocupar(Variables.B).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlContenido.add(txtID);
		pnlContenido.add(lbl1);
		txtCantidad.setPreferredSize(new Dimension(38,18));
		pnlContenido.add(txtCantidad);
		pnlContenido.add(getChkUnidad());
		activarOpciones();
	}
	private JComboBox getChkUnidad(){
		cbxUnidad.addItem("%");
		cbxUnidad.addItem("mV");
		return cbxUnidad;
	}
	
	@Override
	public void activarOpciones() {
			try {
				if(!chkActivar.isSelected() && !txtID.getText().equals(""))
					Variables.liberar(Variables.B, Integer.parseInt(txtID.getText()));
				else
					txtID.setText(Variables.ocupar(Variables.B).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		super.activarOpciones();
	}
}
