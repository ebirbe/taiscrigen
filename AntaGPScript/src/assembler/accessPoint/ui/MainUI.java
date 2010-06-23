package assembler.accessPoint.ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class MainUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField11 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel3 = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
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
		jLabel3 = new JLabel();
		jLabel3.setText("Banda");
		jLabel3.setBounds(new Rectangle(15, 44, 45, 14));
		jLabel2 = new JLabel();
		jLabel2.setText("Password");
		jLabel2.setBounds(new Rectangle(15, 105, 70, 14));
		jLabel1 = new JLabel();
		jLabel1.setText("Login");
		jLabel1.setBounds(new Rectangle(15, 75, 39, 14));
		jLabel = new JLabel();
		jLabel.setText("APN");
		jLabel.setBounds(new Rectangle(15, 15, 28, 14));
		this.setSize(392, 239);
		this.setLayout(new BorderLayout());
		this.add(getJPanel1(), BorderLayout.CENTER);
		this.add(getJPanel(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(105, 15, 271, 18));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(106, 74, 180, 18));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField11	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField11() {
		if (jTextField11 == null) {
			jTextField11 = new JTextField();
			jTextField11.setBounds(new Rectangle(105, 103, 182, 18));
		}
		return jTextField11;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Aceptar");
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Cancelar");
		}
		return jButton1;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(105, 43, 271, 23));
		}
		return jComboBox;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new FlowLayout());
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setPreferredSize(new Dimension(300, 200));
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJComboBox(), null);
			jPanel1.add(getJTextField11(), null);
			jPanel1.add(getJTextField1(), null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(jLabel, null);
			jPanel1.add(getJTextField(), null);
		}
		return jPanel1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
