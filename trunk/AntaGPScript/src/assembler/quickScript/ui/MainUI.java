package assembler.quickScript.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import assembler.controller.CancelListener;
import assembler.model.Variables;
import assembler.model.listener.TxtVariableListener;
import assembler.model.listener.txtNumericListener;
import assembler.quickScript.controller.BtnOkListener;
import assembler.quickScript.controller.ChkIMEIListener;
import assembler.quickScript.controller.TxtDestinationPointLisener;
import assembler.ui.IntegerTextField;

import java.awt.Rectangle;

public class MainUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JPanel jPanel = null;
	public JButton btnOK = null;
	public JButton btnCancel = null;
	public JPanel jPanel1 = null;
	public JPanel jPanel2 = null;
	public JCheckBox chkResetConfig = null;
	public JPanel jPanel3 = null;
	public JLabel jLabel1 = null;
	public JTextField txtID = null;
	public IntegerTextField txtPIN = null;
	public JPanel jPanel4 = null;
	public JScrollPane jScrollPane = null;
	public JCheckBox chkIMEIasID = null;
	public JPanel jPanel511 = null;
	public JLabel jLabel311 = null;
	public JTextField txtApn = null;
	public JLabel jLabel = null;
	public JPanel jPanel52 = null;
	public JLabel jLabel32 = null;
	public JTextField txtDPinternet1 = null;
	public JLabel jLabel41 = null;
	public IntegerTextField txtDPport1 = null;
	public JLabel jLabel21 = null;
	public IntegerTextField txtDPi1 = null;
	public JPanel jPanel53 = null;
	public JLabel jLabel33 = null;
	public JTextField txtDPinternet2 = null;
	public JLabel jLabel42 = null;
	public IntegerTextField txtDPport2 = null;
	public JLabel jLabel22 = null;
	public IntegerTextField txtDPi2 = null;
	public JPanel jPanel54 = null;
	public JLabel jLabel34 = null;
	public JTextField txtDPinternet3 = null;
	public JLabel jLabel43 = null;
	public IntegerTextField txtDPport3 = null;
	public JLabel jLabel23 = null;
	public IntegerTextField txtDPi3 = null;
	public JPanel jPanel512 = null;
	public JLabel jLabel312 = null;
	public JTextField txtDPphone1 = null;
	public IntegerTextField txtDPp1 = null;
	public JPanel jPanel513 = null;
	public JLabel jLabel313 = null;
	public JTextField txtDPphone2 = null;
	public IntegerTextField txtDPp2 = null;
	public JPanel jPanel514 = null;
	public JLabel jLabel314 = null;
	public JTextField txtDPphone3 = null;
	public IntegerTextField txtDPp3 = null;
	public JPanel jPanel5141 = null;
	public JLabel jLabel3141 = null;
	public JLabel jLabel231 = null;
	public JLabel jLabel232 = null;
	public JLabel jLabel233 = null;
	public JPanel jPanel5142 = null;
	public JLabel jLabel2331 = null;
	public IntegerTextField txtDPp4 = null;
	public JLabel jLabel3142 = null;
	public JTextField txtDPphone4 = null;
	public JPanel jPanel5143 = null;
	public JLabel jLabel2332 = null;
	public IntegerTextField txtDPp5 = null;
	public JLabel jLabel3143 = null;
	public JTextField txtDPphone5 = null;
	public JPanel jPanel51431 = null;
	public JLabel jLabel23321 = null;
	public JPanel pnl = new JPanel(new GridLayout(2,2));
	public PanelDestinationAdress pnlDA1;
	public PanelDestinationAdress pnlDA2;
	public PanelDestinationAdress pnlDA3;
	public PanelDestinationAdress pnlDA4;
	public PanelEvents pnlEvt;
	private JPanel jPanelTituloReportes = null;
	private JLabel jLabel31411 = null;
	/**
	 * This is the default constructor
	 */
	public MainUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(0, 0, 800, 600));
		this.add(getJScrollPane(), BorderLayout.CENTER);
		this.add(getJPanel1(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(getJPanel3(), null);
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJPanel511(), null);
			jPanel.add(getJPanel4(), null);
			jPanel.add(getJPanel5141(), null);
			jPanel.add(getJPanel52(), null);
			jPanel.add(getJPanel53(), null);
			jPanel.add(getJPanel54(), null);
			jPanel.add(getJPanel512(), null);
			jPanel.add(getJPanel513(), null);
			jPanel.add(getJPanel514(), null);
			jPanel.add(getJPanel5142(), null);
			jPanel.add(getJPanel5143(), null);
			jPanel.add(getJPanel51431(), null);
			pnlDA1 = new PanelDestinationAdress(this);
			pnlDA2 = new PanelDestinationAdress(this);
			pnlDA3 = new PanelDestinationAdress(this);
			pnlDA4 = new PanelDestinationAdress(this);
			pnl.add(pnlDA1);
			pnl.add(pnlDA2);
			pnl.add(pnlDA3);
			pnl.add(pnlDA4);
			jPanel.add(pnl);
			jPanel.add(getJPanelTituloReportes(), null);
			pnlEvt = new PanelEvents();
			jPanel.add(pnlEvt);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes btnOK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton();
			btnOK.setText("Aceptar");
			btnOK.addActionListener(new BtnOkListener(this));
		}
		return btnOK;
	}

	/**
	 * This method initializes btnCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton();
			btnCancel.setText("Cancelar");
			btnCancel.addActionListener(new CancelListener(this));
		}
		return btnCancel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new FlowLayout());
			jPanel1.add(getBtnOK(), null);
			jPanel1.add(getBtnCancel(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(flowLayout);
			jPanel2.add(getChkResetConfig(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes chkResetConfig	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkResetConfig() {
		if (chkResetConfig == null) {
			chkResetConfig = new JCheckBox();
			chkResetConfig.setSelected(true);
			chkResetConfig.setText("Limpiar configuracion anterior");
		}
		return chkResetConfig;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(java.awt.FlowLayout.LEFT);
			jLabel1 = new JLabel();
			jLabel1.setText("ID");
			jPanel3 = new JPanel();
			jPanel3.setLayout(flowLayout1);
			jPanel3.add(jLabel1, null);
			jPanel3.add(getTxtID(), null);
			jPanel3.add(getChkIMEIasID(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes txtID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtID() {
		if (txtID == null) {
			txtID = new JTextField();
			txtID.setPreferredSize(new Dimension(100, 18));
		}
		return txtID;
	}

	/**
	 * This method initializes txtPIN	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPIN() {
		if (txtPIN == null) {
			txtPIN = new IntegerTextField();
			txtPIN.setPreferredSize(new Dimension(100, 18));
			txtPIN.setText("0000");
		}
		return txtPIN;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel = new JLabel();
			jLabel.setText("PIN de la Tarjeta SIM");
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setAlignment(java.awt.FlowLayout.LEFT);
			jPanel4 = new JPanel();
			jPanel4.setLayout(flowLayout2);
			jPanel4.add(jLabel, null);
			jPanel4.add(getTxtPIN(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			JScrollBar hBar, vBar;
			hBar = jScrollPane.getHorizontalScrollBar();
			vBar = jScrollPane.getVerticalScrollBar();
			hBar.setUnitIncrement(20);
			vBar.setUnitIncrement(40);
			jScrollPane.setHorizontalScrollBar(hBar);
			jScrollPane.setVerticalScrollBar(vBar);
			jScrollPane.setViewportView(getJPanel());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes chkIMEIasID	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkIMEIasID() {
		if (chkIMEIasID == null) {
			chkIMEIasID = new JCheckBox();
			chkIMEIasID.setText("Usar el IMEI como ID");
			chkIMEIasID.addItemListener(new ChkIMEIListener(txtID));
		}
		return chkIMEIasID;
	}

	/**
	 * This method initializes jPanel511	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel511() {
		if (jPanel511 == null) {
			jLabel311 = new JLabel();
			jLabel311.setText("Punto de Acceso GPRS (APN)");
			FlowLayout flowLayout311 = new FlowLayout();
			flowLayout311.setAlignment(FlowLayout.LEFT);
			jPanel511 = new JPanel();
			jPanel511.setLayout(flowLayout311);
			jPanel511.add(jLabel311, null);
			jPanel511.add(getTxtApn(), null);
		}
		return jPanel511;
	}

	/**
	 * This method initializes txtApn	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtApn() {
		if (txtApn == null) {
			txtApn = new JTextField();
			txtApn.setPreferredSize(new Dimension(200, 18));
		}
		return txtApn;
	}

	/**
	 * This method initializes jPanel52	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel52() {
		if (jPanel52 == null) {
			jLabel21 = new JLabel();
			jLabel21.setText("Punto");
			jLabel41 = new JLabel();
			jLabel41.setText("Puerto");
			jLabel32 = new JLabel();
			jLabel32.setText("Servidor");
			FlowLayout flowLayout32 = new FlowLayout();
			flowLayout32.setAlignment(FlowLayout.LEFT);
			jPanel52 = new JPanel();
			jPanel52.setLayout(flowLayout32);
			jPanel52.add(jLabel21, null);
			jPanel52.add(getTxtDPi1(), null);
			jPanel52.add(jLabel32, null);
			jPanel52.add(getTxtDPinternet1(), null);
			jPanel52.add(jLabel41, null);
			jPanel52.add(getTxtDPport1(), null);
		}
		return jPanel52;
	}

	/**
	 * This method initializes txtDPinternet1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPinternet1() {
		if (txtDPinternet1 == null) {
			txtDPinternet1 = new JTextField();
			txtDPinternet1.setPreferredSize(new Dimension(200, 18));
			txtDPinternet1.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPinternet1;
	}

	/**
	 * This method initializes txtDPport1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPport1() {
		if (txtDPport1 == null) {
			txtDPport1 = new IntegerTextField();
			txtDPport1.setPreferredSize(new Dimension(100, 18));
			txtDPport1.setText("0000");
			txtDPport1.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPport1;
	}

	/**
	 * This method initializes txtDPi1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPi1() {
		if (txtDPi1 == null) {
			txtDPi1 = new IntegerTextField();
			txtDPi1.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPi1.setText(Variables.ocupar(Variables.DPi).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPi1.addKeyListener(new TxtDestinationPointLisener(this));
			txtDPi1.addFocusListener(new TxtVariableListener(Variables.DPi));
		}
		return txtDPi1;
	}

	/**
	 * This method initializes jPanel53	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel53() {
		if (jPanel53 == null) {
			jLabel22 = new JLabel();
			jLabel22.setText("Punto");
			jLabel42 = new JLabel();
			jLabel42.setText("Puerto");
			jLabel33 = new JLabel();
			jLabel33.setText("Servidor");
			FlowLayout flowLayout33 = new FlowLayout();
			flowLayout33.setAlignment(FlowLayout.LEFT);
			jPanel53 = new JPanel();
			jPanel53.setLayout(flowLayout33);
			jPanel53.add(jLabel22, null);
			jPanel53.add(getTxtDPi2(), null);
			jPanel53.add(jLabel33, null);
			jPanel53.add(getTxtDPinternet2(), null);
			jPanel53.add(jLabel42, null);
			jPanel53.add(getTxtDPport2(), null);
		}
		return jPanel53;
	}

	/**
	 * This method initializes txtDPinternet2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPinternet2() {
		if (txtDPinternet2 == null) {
			txtDPinternet2 = new JTextField();
			txtDPinternet2.setPreferredSize(new Dimension(200, 18));
			txtDPinternet2.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPinternet2;
	}

	/**
	 * This method initializes txtDPport2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPport2() {
		if (txtDPport2 == null) {
			txtDPport2 = new IntegerTextField();
			txtDPport2.setText("0000");
			txtDPport2.setPreferredSize(new Dimension(100, 18));
			txtDPport2.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPport2;
	}

	/**
	 * This method initializes txtDPi2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPi2() {
		if (txtDPi2 == null) {
			txtDPi2 = new IntegerTextField();
			txtDPi2.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPi2.setText(Variables.ocupar(Variables.DPi).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPi2.addKeyListener(new TxtDestinationPointLisener(this));
			txtDPi2.addFocusListener(new TxtVariableListener(Variables.DPi));
		}
		return txtDPi2;
	}

	/**
	 * This method initializes jPanel54	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel54() {
		if (jPanel54 == null) {
			jLabel23 = new JLabel();
			jLabel23.setText("Punto");
			jLabel43 = new JLabel();
			jLabel43.setText("Puerto");
			jLabel34 = new JLabel();
			jLabel34.setText("Servidor");
			FlowLayout flowLayout34 = new FlowLayout();
			flowLayout34.setAlignment(FlowLayout.LEFT);
			jPanel54 = new JPanel();
			jPanel54.setLayout(flowLayout34);
			jPanel54.add(jLabel23, null);
			jPanel54.add(getTxtDPi3(), null);
			jPanel54.add(jLabel34, null);
			jPanel54.add(getTxtDPinternet3(), null);
			jPanel54.add(jLabel43, null);
			jPanel54.add(getTxtDPport3(), null);
		}
		return jPanel54;
	}

	/**
	 * This method initializes txtDPinternet3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPinternet3() {
		if (txtDPinternet3 == null) {
			txtDPinternet3 = new JTextField();
			txtDPinternet3.setPreferredSize(new Dimension(200, 18));
			txtDPinternet3.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPinternet3;
	}

	/**
	 * This method initializes txtDPport3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPport3() {
		if (txtDPport3 == null) {
			txtDPport3 = new IntegerTextField();
			txtDPport3.setText("0000");
			txtDPport3.setPreferredSize(new Dimension(100, 18));
			txtDPport3.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPport3;
	}

	/**
	 * This method initializes txtDPi3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPi3() {
		if (txtDPi3 == null) {
			txtDPi3 = new IntegerTextField();
			txtDPi3.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPi3.setText(Variables.ocupar(Variables.DPi).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPi3.addKeyListener(new TxtDestinationPointLisener(this));
			txtDPi3.addFocusListener(new TxtVariableListener(Variables.DPi));
		}
		return txtDPi3;
	}

	/**
	 * This method initializes jPanel512	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel512() {
		if (jPanel512 == null) {
			jLabel231 = new JLabel();
			jLabel231.setText("Punto");
			jLabel312 = new JLabel();
			jLabel312.setText("Telefono");
			FlowLayout flowLayout312 = new FlowLayout();
			flowLayout312.setAlignment(FlowLayout.LEFT);
			jPanel512 = new JPanel();
			jPanel512.setLayout(flowLayout312);
			jPanel512.add(jLabel231, null);
			jPanel512.add(getTxtDPp1(), null);
			jPanel512.add(jLabel312, null);
			jPanel512.add(getTxtDPphone1(), null);
		}
		return jPanel512;
	}

	/**
	 * This method initializes txtDPphone1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPphone1() {
		if (txtDPphone1 == null) {
			txtDPphone1 = new JTextField();
			txtDPphone1.setPreferredSize(new Dimension(200, 18));
			txtDPphone1.setText("");
			txtDPphone1.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPphone1;
	}

	/**
	 * This method initializes txtDPp1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPp1() {
		if (txtDPp1 == null) {
			txtDPp1 = new IntegerTextField();
			txtDPp1.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPp1.setText(Variables.ocupar(Variables.DPp).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPp1.addFocusListener(new TxtVariableListener(Variables.DPp));
		}
		return txtDPp1;
	}

	/**
	 * This method initializes jPanel513	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel513() {
		if (jPanel513 == null) {
			jLabel232 = new JLabel();
			jLabel232.setText("Punto");
			jLabel313 = new JLabel();
			jLabel313.setText("Telefono");
			FlowLayout flowLayout313 = new FlowLayout();
			flowLayout313.setAlignment(FlowLayout.LEFT);
			jPanel513 = new JPanel();
			jPanel513.setLayout(flowLayout313);
			jPanel513.add(jLabel232, null);
			jPanel513.add(getTxtDPp2(), null);
			jPanel513.add(jLabel313, null);
			jPanel513.add(getTxtDPphone2(), null);
		}
		return jPanel513;
	}

	/**
	 * This method initializes txtDPphone2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPphone2() {
		if (txtDPphone2 == null) {
			txtDPphone2 = new JTextField();
			txtDPphone2.setPreferredSize(new Dimension(200, 18));
			txtDPphone2.setText("");
			txtDPphone2.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPphone2;
	}

	/**
	 * This method initializes txtDPp2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPp2() {
		if (txtDPp2 == null) {
			txtDPp2 = new IntegerTextField();
			txtDPp2.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPp2.setText(Variables.ocupar(Variables.DPp).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPp2.addFocusListener(new TxtVariableListener(Variables.DPp));
		}
		return txtDPp2;
	}

	/**
	 * This method initializes jPanel514	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel514() {
		if (jPanel514 == null) {
			jLabel233 = new JLabel();
			jLabel233.setText("Punto");
			jLabel314 = new JLabel();
			jLabel314.setText("Telefono");
			FlowLayout flowLayout314 = new FlowLayout();
			flowLayout314.setAlignment(FlowLayout.LEFT);
			jPanel514 = new JPanel();
			jPanel514.setLayout(flowLayout314);
			jPanel514.add(jLabel233, null);
			jPanel514.add(getTxtDPp3(), null);
			jPanel514.add(jLabel314, null);
			jPanel514.add(getTxtDPphone3(), null);
		}
		return jPanel514;
	}

	/**
	 * This method initializes txtDPphone3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPphone3() {
		if (txtDPphone3 == null) {
			txtDPphone3 = new JTextField();
			txtDPphone3.setPreferredSize(new Dimension(200, 18));
			txtDPphone3.setText("");
			txtDPphone3.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPphone3;
	}

	/**
	 * This method initializes txtDPp3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPp3() {
		if (txtDPp3 == null) {
			txtDPp3 = new IntegerTextField();
			txtDPp3.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPp3.setText(Variables.ocupar(Variables.DPp).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPp3.addFocusListener(new TxtVariableListener(Variables.DPp));
		}
		return txtDPp3;
	}

	/**
	 * This method initializes jPanel5141	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5141() {
		if (jPanel5141 == null) {
			jLabel3141 = new JLabel();
			jLabel3141.setText("PUNTOS DE DESTINO (DP)");
			FlowLayout flowLayout3141 = new FlowLayout();
			flowLayout3141.setAlignment(java.awt.FlowLayout.CENTER);
			jPanel5141 = new JPanel();
			jPanel5141.setLayout(flowLayout3141);
			jPanel5141.add(jLabel3141, null);
		}
		return jPanel5141;
	}

	/**
	 * This method initializes jPanel5142	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5142() {
		if (jPanel5142 == null) {
			jLabel3142 = new JLabel();
			jLabel3142.setText("Telefono");
			jLabel2331 = new JLabel();
			jLabel2331.setText("Punto");
			FlowLayout flowLayout3142 = new FlowLayout();
			flowLayout3142.setAlignment(FlowLayout.LEFT);
			jPanel5142 = new JPanel();
			jPanel5142.setLayout(flowLayout3142);
			jPanel5142.add(jLabel2331, null);
			jPanel5142.add(getTxtDPp4(), null);
			jPanel5142.add(jLabel3142, null);
			jPanel5142.add(getTxtDPphone4(), null);
		}
		return jPanel5142;
	}

	/**
	 * This method initializes txtDPp4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPp4() {
		if (txtDPp4 == null) {
			txtDPp4 = new IntegerTextField();
			txtDPp4.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPp4.setText(Variables.ocupar(Variables.DPp).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPp4.addFocusListener(new TxtVariableListener(Variables.DPp));
		}
		return txtDPp4;
	}

	/**
	 * This method initializes txtDPphone4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPphone4() {
		if (txtDPphone4 == null) {
			txtDPphone4 = new JTextField();
			txtDPphone4.setPreferredSize(new Dimension(200, 18));
			txtDPphone4.setText("");
			txtDPphone4.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPphone4;
	}

	/**
	 * This method initializes jPanel5143	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5143() {
		if (jPanel5143 == null) {
			jLabel3143 = new JLabel();
			jLabel3143.setText("Telefono");
			jLabel2332 = new JLabel();
			jLabel2332.setText("Punto");
			FlowLayout flowLayout3143 = new FlowLayout();
			flowLayout3143.setAlignment(FlowLayout.LEFT);
			jPanel5143 = new JPanel();
			jPanel5143.setLayout(flowLayout3143);
			jPanel5143.add(jLabel2332, null);
			jPanel5143.add(getTxtDPp5(), null);
			jPanel5143.add(jLabel3143, null);
			jPanel5143.add(getTxtDPphone5(), null);
		}
		return jPanel5143;
	}

	/**
	 * This method initializes txtDPp5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPp5() {
		if (txtDPp5 == null) {
			txtDPp5 = new IntegerTextField();
			txtDPp5.setPreferredSize(new Dimension(30, 18));
			try {
				txtDPp5.setText(Variables.ocupar(Variables.DPp).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDPp5.addFocusListener(new TxtVariableListener(Variables.DPp));
		}
		return txtDPp5;
	}

	/**
	 * This method initializes txtDPphone5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDPphone5() {
		if (txtDPphone5 == null) {
			txtDPphone5 = new JTextField();
			txtDPphone5.setPreferredSize(new Dimension(200, 18));
			txtDPphone5.setText("");
			txtDPphone5.addKeyListener(new TxtDestinationPointLisener(this));
		}
		return txtDPphone5;
	}

	/**
	 * This method initializes jPanel51431	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel51431() {
		if (jPanel51431 == null) {
			jLabel23321 = new JLabel();
			jLabel23321.setText("DIRECCIONES DE DESTINO (DA)");
			FlowLayout flowLayout31431 = new FlowLayout();
			flowLayout31431.setAlignment(java.awt.FlowLayout.CENTER);
			jPanel51431 = new JPanel();
			jPanel51431.setLayout(flowLayout31431);
			jPanel51431.add(jLabel23321, null);
		}
		return jPanel51431;
	}

	/**
	 * This method initializes jPanelTituloReportes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTituloReportes() {
		if (jPanelTituloReportes == null) {
			jLabel31411 = new JLabel();
			jLabel31411.setText("REPORTES");
			FlowLayout flowLayout31411 = new FlowLayout();
			jPanelTituloReportes = new JPanel();
			jPanelTituloReportes.setLayout(flowLayout31411);
			jPanelTituloReportes.add(jLabel31411, null);
		}
		return jPanelTituloReportes;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
