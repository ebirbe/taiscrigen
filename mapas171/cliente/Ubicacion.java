package org.mapas171.cliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

public class Ubicacion extends JFrame {
	private static final long serialVersionUID = 1L;

	JButton btnUbicar = new JButton("Ubicar");
	JLabel imagen = new JLabel();
	JList lstGPS = new JList(new ModeloListaUnidades());
	PanelControl pnControl = new PanelControl();
	JPanel pnlLateral = new JPanel();
	JScrollPane spImagen;
	JScrollPane spLista = new JScrollPane();
	JLabel status = new JLabel("Listo");
	Mapa creador = new Mapa(this);

	public Ubicacion() {
		pnControl.arriba.addActionListener(new ArribaControl(creador));
		pnControl.abajo.addActionListener(new AbajoControl(creador));
		pnControl.derecha.addActionListener(new DerechaControl(creador));
		pnControl.izquierda.addActionListener(new IzquierdaControl(creador));
		pnControl.zMas.addActionListener(new ZoomMasControl(creador));
		pnControl.zMenos.addActionListener(new ZoomMenosControl(creador));
		spLista.getViewport().add(lstGPS);
		pnlLateral.setLayout(new BoxLayout(pnlLateral, BoxLayout.Y_AXIS));
		pnlLateral.add(pnControl);
		pnlLateral.add(spLista);
		btnUbicar.addActionListener(new UbicarListener(creador, lstGPS));
		JPanel pnlbtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlbtn.add(btnUbicar);
		pnlLateral.add(pnlbtn);
		imagen.setPreferredSize(new Dimension(640, 640));
		imagen.setBorder(BorderFactory.createEtchedBorder());
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setOpaque(true);
		imagen.setBackground(Color.WHITE);
		status.setPreferredSize(new Dimension(0, 32));
		status.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Localizacion");
		setIconImage(new ImageIcon(this.getClass().getClassLoader()
				.getResource("world.png")).getImage());
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		getContentPane().add(pnlLateral, BorderLayout.WEST);
		getContentPane().add(imagen, BorderLayout.CENTER);
		getContentPane().add(status, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		creador.start();
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Ubicacion();
	}
}
