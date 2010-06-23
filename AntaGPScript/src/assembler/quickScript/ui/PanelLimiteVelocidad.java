package assembler.quickScript.ui;

import java.awt.Dimension;

import javax.swing.JLabel;

import assembler.model.Variables;
import assembler.ui.IntegerTextField;

public class PanelLimiteVelocidad extends PanelSubEvent{

	private static final long serialVersionUID = -2641950698128724437L;
	public JLabel lbl3 = new JLabel("GS#");
	public IntegerTextField txtID = new IntegerTextField();
	public JLabel lbl1 = new JLabel("Maximo");
	public IntegerTextField txtLimitVelocidad = new IntegerTextField();
	public JLabel lbl2 = new JLabel("Km/h");
	public PanelLimiteVelocidad() {
		super();
		lblTitulo.setText("LIMITE DE VELOCIAD");
		pnlContenido.add(lbl3);
		txtID.setPreferredSize(new Dimension(30,18));
		try {
			txtID.setText(Variables.ocupar(Variables.S).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlContenido.add(txtID);
		pnlContenido.add(lbl1);
		txtLimitVelocidad.setPreferredSize(new Dimension(30,18));
		pnlContenido.add(txtLimitVelocidad);
		pnlContenido.add(lbl2);
		activarOpciones();
	}
	
	@Override
	public void activarOpciones() {
			try {
				if(!chkActivar.isSelected() && !txtID.getText().equals(""))
					Variables.liberar(Variables.S, Integer.parseInt(txtID.getText()));
				else
					txtID.setText(Variables.ocupar(Variables.S).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		super.activarOpciones();
	}
}
