package org.mapas171.cliente;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

public class ModeloListaUnidades extends DefaultListModel {
	private static final long serialVersionUID = 3067546141267327469L;

	public ModeloListaUnidades() {
		super();
		llenar();
	}

	private void llenar() {
		try {
			BDAcess bd = new BDAcess();
			ResultSet res = bd.consultar("SELECT * FROM Unidades");
			while (res.next()) {
				addElement(res.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
