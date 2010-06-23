package assembler.quickScript.ui;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import assembler.model.Variables;
import assembler.model.listener.TxtVariableListener;
import assembler.ui.IntegerTextField;

public class PanelDestinationAdress extends JPanel{
	private static final long serialVersionUID = -7982326798443355849L;
	MainUI ui;
	public JLabel lbl1;
	public IntegerTextField txtID;
	public JLabel lbl2;
	public JPanel pnlChkBoxes;
	public JCheckBox[] chk = new JCheckBox[8];
	public boolean activo;

	public PanelDestinationAdress(MainUI ui) {
		for(int i = 0; i<chk.length; i++){
			chk[i]=new JCheckBox();
		}
		this.ui = ui;
		lbl1 = new JLabel("Destino");
		add(lbl1);
		txtID = new IntegerTextField();
		try {
			txtID.setText(Variables.ocupar(Variables.DA).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtID.setPreferredSize(new Dimension(30,18));
		txtID.addFocusListener(new TxtVariableListener(Variables.DA));
		add(txtID);
		lbl2  = new JLabel("Reportar a");
		add(lbl2);
		nameCHKS();
		add(getPnlCheckBoxes());
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public JPanel getPnlCheckBoxes(){
		pnlChkBoxes = new JPanel();
		pnlChkBoxes.setLayout(new BoxLayout(pnlChkBoxes, BoxLayout.Y_AXIS));

		for(int i = 0; i<chk.length; i++){
			chk[i].addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					activo = false;
					for (JCheckBox c : chk) {
						if(c.isSelected()) activo = true;
					}
				}
			});
			pnlChkBoxes.add(chk[i]);
		}
		return pnlChkBoxes;
	}
	
	public void nameCHKS(){
		if(ui.txtDPi1.getText().equals("") || ui.txtDPinternet1.getText().equals("")) 
			chk[0].setVisible(false);
		else	chk[0].setVisible(true);
		if(ui.txtDPi2.getText().equals("") || ui.txtDPinternet2.getText().equals("")) 
			chk[1].setVisible(false);
		else	chk[1].setVisible(true);
		if(ui.txtDPi3.getText().equals("") || ui.txtDPinternet3.getText().equals("")) 
			chk[2].setVisible(false);
		else	chk[2].setVisible(true);
		if(ui.txtDPp1.getText().equals("") || ui.txtDPphone1.getText().equals("")) 
			chk[3].setVisible(false);
		else	chk[3].setVisible(true);
		if(ui.txtDPp2.getText().equals("") || ui.txtDPphone2.getText().equals("")) 
			chk[4].setVisible(false);
		else	chk[4].setVisible(true);
		if(ui.txtDPp3.getText().equals("") || ui.txtDPphone3.getText().equals("")) 
			chk[5].setVisible(false);
		else	chk[5].setVisible(true);
		if(ui.txtDPp4.getText().equals("") || ui.txtDPphone4.getText().equals("")) 
			chk[6].setVisible(false);
		else	chk[6].setVisible(true);
		if(ui.txtDPp5.getText().equals("") || ui.txtDPphone5.getText().equals("")) 
			chk[7].setVisible(false);
		else	chk[7].setVisible(true);

		chk[0].setText(ui.txtDPi1.getText()+" "+ui.txtDPinternet1.getText()+":"+ui.txtDPport1.getText());
		chk[1].setText(ui.txtDPi2.getText()+" "+ui.txtDPinternet2.getText()+":"+ui.txtDPport2.getText());
		chk[2].setText(ui.txtDPi3.getText()+" "+ui.txtDPinternet3.getText()+":"+ui.txtDPport3.getText());
		chk[3].setText(ui.txtDPp1.getText()+" "+ui.txtDPphone1.getText());
		chk[4].setText(ui.txtDPp2.getText()+" "+ui.txtDPphone2.getText());
		chk[5].setText(ui.txtDPp3.getText()+" "+ui.txtDPphone3.getText());
		chk[6].setText(ui.txtDPp4.getText()+" "+ui.txtDPphone4.getText());
		chk[7].setText(ui.txtDPp5.getText()+" "+ui.txtDPphone5.getText());
	}
}
