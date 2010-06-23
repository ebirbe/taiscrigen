package assembler.ui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;

public class Start extends JPanel {

	private static final long serialVersionUID = 1L;
	private JToolBar jToolBar = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JScrollPane jScrollPane = null;
	private JTree jTree = null;
	private JScrollPane jScrollPane1 = null;
	private JList jList = null;
	/**
	 * This is the default constructor
	 */
	public Start() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(579, 318);
		this.setLayout(new BorderLayout());
		this.add(getJToolBar(), BorderLayout.NORTH);
		this.add(getJScrollPane(), BorderLayout.EAST);
		this.add(getJScrollPane1(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setPreferredSize(new Dimension(300, 30));
			jToolBar.add(getJButton());
			jToolBar.add(getJButton1());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Generar Script");
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
			jButton1.setText("Limpiar");
		}
		return jButton1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setPreferredSize(new Dimension(200, 300));
			jScrollPane.setViewportView(getJTree());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
		}
		return jTree;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJList());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
		}
		return jList;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
