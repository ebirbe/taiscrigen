package assembler.ui;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Dimension;
import javax.swing.JInternalFrame;

public class Dialog extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * This is the default constructor
	 */
	public Dialog() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("JFrame");
	}

}
