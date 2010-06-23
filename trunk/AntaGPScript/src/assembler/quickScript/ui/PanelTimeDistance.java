package assembler.quickScript.ui;

import java.awt.Dimension;

import javax.swing.JLabel;

import assembler.model.Variables;
import assembler.model.listener.TxtVariableListener;
import assembler.ui.IntegerTextField;

public class PanelTimeDistance extends PanelSubEvent{

	private static final long serialVersionUID = -4693152903405977344L;
	public JLabel lblTitulo = new JLabel();
	public JLabel lbl1= new JLabel("TD#");
	public IntegerTextField txtID = new IntegerTextField();
	public JLabel lbl2= new JLabel("T. Minimo:");
	public IntegerTextField txtMinTime = new IntegerTextField("1");
	public JLabel lbl3= new JLabel("T. Maximo:");
	public IntegerTextField txtMaxTime = new IntegerTextField("60");
	public JLabel lbl4= new JLabel("Distancia:");
	public IntegerTextField txtDistance = new IntegerTextField("10");
	
	public PanelTimeDistance() {
		super();
		super.lblTitulo.setText("TIEMPO Y DISTANCIA");
		pnlContenido.add(lbl1);
		txtID.setPreferredSize(new Dimension(30,18));
		try {
			txtID.setText(Variables.ocupar(Variables.TD).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtID.addFocusListener(new TxtVariableListener(Variables.TD));
		pnlContenido.add(txtID);
		pnlContenido.add(lbl2);
		txtMinTime.setPreferredSize(new Dimension(50,18));
		pnlContenido.add(txtMinTime);
		pnlContenido.add(lbl3);
		txtMaxTime.setPreferredSize(new Dimension(50,18));
		pnlContenido.add(txtMaxTime);
		pnlContenido.add(lbl4);
		txtDistance.setPreferredSize(new Dimension(55,18));
		pnlContenido.add(txtDistance);
		activarOpciones();
	}
	
	@Override
	public void activarOpciones() {
			try {
				if(!chkActivar.isSelected() && !txtID.getText().equals(""))
					Variables.liberar(Variables.TD, Integer.parseInt(txtID.getText()));
				else
					txtID.setText(Variables.ocupar(Variables.TD).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		super.activarOpciones();
	}
}
