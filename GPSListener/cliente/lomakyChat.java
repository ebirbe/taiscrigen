package cliente;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A Swing-based top level window class.
 * <P>
 * @author lomaky
 */
public class lomakyChat extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6429953512757118155L;
	BorderLayout borderLayout1 = new BorderLayout();
	JMenuBar menuBar1 = new JMenuBar();
	JMenu menuFile = new JMenu();
	JMenuItem menuFileExit = new JMenuItem();
	JMenu menuHelp = new JMenu();
	JMenuItem menuHelpAbout = new JMenuItem();
	JSplitPane jSplitPane1 = new JSplitPane();
	JSplitPane jSplitPane2 = new JSplitPane();
	JScrollPane jScrollPane1 = new JScrollPane();
	JScrollPane jScrollPane2 = new JScrollPane();
	JTextArea jTextArea1 = new JTextArea();
	JTextArea jTextArea2 = new JTextArea();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel1 = new JLabel();
	JSplitPane jSplitPane3 = new JSplitPane();
	JScrollPane jScrollPane3 = new JScrollPane();
	JPanel jPanel2 = new JPanel();
	TextField input = new TextField();
	JTextArea output = new JTextArea();  
	JButton jButton1 = new JButton();
	DataInputStream i;
	DataOutputStream o;
	protected Thread listener;
	/**
	 * Constructs a new instance.
	 */
	public lomakyChat(String title, InputStream i, OutputStream o) {
		super("Lomaky Chat");
		this.i = new DataInputStream (new BufferedInputStream (i));
		this.o = new DataOutputStream (new BufferedOutputStream (o));
		try  {
			jbInit();
		}
		catch (Exception e) {
		}
		input.requestFocusInWindow();
		listener = new Thread (this);
		listener.start ();     
	}

	/**
	 * Initializes the state of this instance.
	 */
	private void jbInit() throws Exception {
		this.getContentPane().setLayout(borderLayout1);
		this.setSize(new Dimension(495, 592));
		menuFile.setText("Acciones");
		menuFileExit.setText("Salir");
		jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jTextArea1.setText("");

		jTextArea2.setEditable(false);
		output.setEditable(false);
		jLabel1.setText("Erickcion Chat");
		jLabel1.setFont(new Font("Dialog", 1, 40));
		jButton1.setText("Enviar!");
		menuFile.add(menuFileExit);
		menuBar1.add(menuFile);
		this.setJMenuBar(menuBar1);
		this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);
		jSplitPane1.add(jSplitPane2, JSplitPane.BOTTOM);
		jSplitPane2.add(jScrollPane1, JSplitPane.RIGHT);
		jScrollPane1.getViewport().add(jTextArea1, null);
		jScrollPane1.getViewport().add(output, null);
		jSplitPane2.add(jScrollPane2, JSplitPane.LEFT);
		jScrollPane2.getViewport().add(jTextArea2, null);
		jSplitPane1.add(jPanel1, JSplitPane.TOP);
		jPanel1.add(jLabel1, null);
		this.getContentPane().add(jSplitPane3, BorderLayout.SOUTH);
		jSplitPane3.add(jScrollPane3, JSplitPane.BOTTOM);
		jScrollPane3.getViewport().add(input, null);
		jSplitPane3.add(jPanel2, JSplitPane.TOP);
		jPanel2.add(jButton1, null);
		//----------------------------------------------------------------------    
		menuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileExit_ActionPerformed(e);
			}
		});  
		//----------------------------------------------------------------------        
		jButton1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i=0; i<input.getText().length(); i++){
						if(input.getText().charAt(i) < 0 || input.getText().charAt(i) > 127) throw new IOException("Se finalizo la conexion");
						o.write((int)input.getText().charAt(i));
					}
					o.write((int)'\n');
					o.flush (); 
					System.out.println("Enviando..");
				} 
				catch (IOException ex) {
					ex.printStackTrace();
					listener.stop ();
					//listener.interrupt();
				}
				input.setText ("");

			}
		});    
		//----------------------------------------------------------------------    
	}

	public void run () {
		try {
			while (true) {
				String line = i.readUTF();

				if (line.substring(0,2).equals("->")){
					jTextArea2.setText("                  \n");
					jTextArea2.append(line.substring(2));
				}
				else{
					if (line.substring(0,2).equals("<-")){
						jTextArea2.setText("                  \n");
						System.out.println("Salio "+line);
						jTextArea2.append(line.substring(2));
					}
					else
						output.append(line + "\n");
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace ();
		} finally {
			listener = null;
			input.setVisible(false);
			validate ();
			try {
				o.close ();
			} catch (IOException ex) {
				ex.printStackTrace ();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public boolean handleEvent (Event e) {
		if ((e.target == input) && (e.id == Event.ACTION_EVENT)) {
			try {
				o.writeUTF ((String) e.arg);
				o.flush (); 
			} catch (IOException ex) {
				ex.printStackTrace();
				listener.stop ();
				//listener.interrupt();
			}
			input.setText ("");
			return true;
		} else if ((e.target == this) && (e.id == Event.WINDOW_DESTROY)) {
			if (listener != null)
				listener.stop ();
				//listener.interrupt();
			setVisible(false);
			return true;
		}
		return super.handleEvent (e);
	}

	//----------------------------------------------------------------------
	void fileExit_ActionPerformed(ActionEvent e) {
		System.exit(0);
	}
}

