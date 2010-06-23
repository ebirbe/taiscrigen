package assembler.quickScript.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import assembler.model.Variables;
import assembler.quickScript.controller.CbxDAListener;
import assembler.quickScript.controller.ChkActivarListener;
import assembler.ui.IntegerTextField;

public class PanelSubEvent extends JPanel {
	private static final long serialVersionUID = 7368561064196294859L;
	public JCheckBox chkActivar = new JCheckBox();
	public JPanel pnlTitulo = new JPanel();
	public JLabel lblTitulo = new JLabel();
	public JPanel pnlContenido = new JPanel();
	public JLabel lbl0= new JLabel("Evento #");
	public IntegerTextField txtEvtID = new IntegerTextField(); 
	public JLabel lblDA= new JLabel("Destino #");
	public JComboBox cbxDA = new JComboBox();

	public PanelSubEvent() {

		add(getPnlTitulo());
		add(getPnlContenido());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	public JPanel getPnlTitulo(){
		pnlTitulo.add(lblTitulo);
		pnlTitulo.setLayout(new FlowLayout(FlowLayout.LEFT));
		return pnlTitulo;
	}
	public JPanel getPnlContenido(){
		pnlContenido.add(getChkActivar());
		pnlContenido.add(lbl0);
		pnlContenido.add(getTxtEvtID());
		pnlContenido.add(lblDA);
		pnlContenido.add(getCbxDA());
		pnlContenido.setLayout(new FlowLayout(FlowLayout.LEFT));
		return pnlContenido;
	}

	private JCheckBox getChkActivar(){
		chkActivar.addItemListener(new ChkActivarListener(this));
		return chkActivar;
	}

	private JTextField getTxtEvtID(){
		try {
			txtEvtID.setText(Variables.ocupar(Variables.ED).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtEvtID.setPreferredSize(new Dimension(30, 18));
		return txtEvtID;
	}

	public JComboBox getCbxDA(){
		cbxDA.addItem("");
		cbxDA.addItem("");
		cbxDA.addItem("");
		cbxDA.addFocusListener(new CbxDAListener(cbxDA));
		return cbxDA;
	}

	public void activarOpciones(){

		boolean seleccionado = chkActivar.isSelected();

		for(int i=0;i<pnlContenido.getComponentCount();i++){
			pnlContenido.getComponent(i).setEnabled(seleccionado);
		}

		try {
			if(!seleccionado) 	Variables.liberar(Variables.ED, Integer.parseInt(txtEvtID.getText()));
			else
				txtEvtID.setText(Variables.ocupar(Variables.ED).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chkActivar.setEnabled(true);
	}
}