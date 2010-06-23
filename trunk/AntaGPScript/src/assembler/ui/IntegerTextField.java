package assembler.ui;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class IntegerTextField extends JTextField{

	private static final long serialVersionUID = 1832559371872333276L;
	final static String badchars = "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";
	private boolean acceptNegative;
	
	
	public IntegerTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		// TODO Auto-generated constructor stub
	}
	public IntegerTextField(int columns) {
		super(columns);
		// TODO Auto-generated constructor stub
	}
	public IntegerTextField(String text, int columns) {
		super(text, columns);
		// TODO Auto-generated constructor stub
	}
	public IntegerTextField(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	public IntegerTextField(boolean acceptNegative) {
		this.acceptNegative = acceptNegative;
	}
	public IntegerTextField() {
		this.acceptNegative = false;
	}

	@Override
	public void processKeyEvent(KeyEvent ev) {

		char c = ev.getKeyChar();

		if((Character.isLetter(c) && !ev.isAltDown()) 
				|| badchars.indexOf(c) > -1) {
			ev.consume();
			return;
		}
		if(c == '-' && getDocument().getLength() > 0 && acceptNegative) 
			ev.consume();
		else super.processKeyEvent(ev);

	}
}
