package assembler.quickScript.ui;

import java.awt.Dimension;

import javax.swing.JLabel;

import assembler.model.Variables;
import assembler.ui.IntegerTextField;

public class PanelCambioDireccion extends PanelSubEvent {

	private static final long serialVersionUID = -3511812970594614813L;
	public JLabel lbl2 = new JLabel("J#");
	public IntegerTextField txtID = new IntegerTextField();
	public JLabel lbl1 = new JLabel("Grados");
	public IntegerTextField txtGrados = new IntegerTextField("25");
	public PanelCambioDireccion() {
		super();
		lblTitulo.setText("CAMBIO DE DIRECCION");
		pnlContenido.add(lbl2);
		txtID.setPreferredSize(new Dimension(38,18));
		try {
			txtID.setText(Variables.ocupar(Variables.J).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlContenido.add(txtID);
		pnlContenido.add(lbl1);
		txtGrados.setPreferredSize(new Dimension(38,18));
		pnlContenido.add(txtGrados);
		activarOpciones();
	}
	
	@Override
	public void activarOpciones() {
			try {
				if(!chkActivar.isSelected() && !txtID.getText().equals(""))
					Variables.liberar(Variables.J, Integer.parseInt(txtID.getText()));
				else
					txtID.setText(Variables.ocupar(Variables.J).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		super.activarOpciones();
	}
	
}
