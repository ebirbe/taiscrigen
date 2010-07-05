package org.mapas171.cliente;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelControl extends Panel {
	private static final long serialVersionUID = 5937764274612217746L;
	JButton zMas = new JButton();
	JButton zMenos = new JButton();
	JPanel pnlZoom = new JPanel();
	JButton arriba = new JButton();
	JButton abajo = new JButton();
	JButton derecha = new JButton();
	JButton izquierda = new JButton();
	ImageIcon icoArriba;
	ImageIcon icoAbajo;
	ImageIcon icoDerecha;
	ImageIcon icoIzquierda;
	ImageIcon icoMas;
	ImageIcon icoMenos;

	public PanelControl() {
		cargarIconos();
		pnlZoom.setLayout(new GridLayout(2, 1));
		pnlZoom.add(zMas);
		pnlZoom.add(zMenos);
		setPreferredSize(new java.awt.Dimension(150, 150));
		setMaximumSize(new java.awt.Dimension(150, 150));
		setLayout(new GridLayout(3, 3));
		add(new JLabel());
		add(arriba);
		add(new JLabel());
		add(izquierda);
		add(pnlZoom);
		add(derecha);
		add(new JLabel());
		add(abajo);
		add(new JLabel());
	}

	private void cargarIconos() {
		icoArriba = new ImageIcon(PanelControl.this.getClass().getClassLoader()
				.getResource("up.png"));
		arriba.setIcon(icoArriba);
		icoAbajo = new ImageIcon(this.getClass().getClassLoader()
				.getResource("down.png"));
		abajo.setIcon(icoAbajo);
		icoIzquierda = new ImageIcon(this.getClass().getClassLoader()
				.getResource("left.png"));
		izquierda.setIcon(icoIzquierda);
		icoDerecha = new ImageIcon(this.getClass().getClassLoader()
				.getResource("right.png"));
		derecha.setIcon(icoDerecha);
		icoMas = new ImageIcon(this.getClass().getClassLoader()
				.getResource("in.png"));
		zMas.setIcon(icoMas);
		icoMenos = new ImageIcon(this.getClass().getClassLoader()
				.getResource("out.png"));
		zMenos.setIcon(icoMenos);
	}
}
