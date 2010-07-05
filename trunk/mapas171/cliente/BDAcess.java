package org.mapas171.cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDAcess {
	public static void main(String[] args) {
		BDAcess bd = new BDAcess();
		ResultSet res = bd.consultar("SELECT * FROM Localizacion");
		try {
			while (res.next()) {
				System.out.println(res.getString(1) + "|" + res.getString(2)
						+ "|" + res.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Connection conex;
	private final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private final String URL = "jdbc:odbc:";
	private final String DSN = "DatosAmbulancia";
	private final String USUARIO = "";
	private final String CLAVE = "";

	public BDAcess() {
		conectar();
	}

	public void conectar() {
		try {
			Class.forName(DRIVER);
			conex = DriverManager.getConnection(URL + DSN, USUARIO, CLAVE);
			conex.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hacer una consulta a la base de datos
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet consultar(String sql) {
		ResultSet rs = null;
		Statement stm = null;
		try {
			stm = conex.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * Cierra la conexion con la base de datos
	 */
	public void desconectar() {
		try {
			if (conex != null) {
				conex.setAutoCommit(true);
				conex.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the cLAVE
	 */
	public String getCLAVE() {
		return CLAVE;
	}

	/**
	 * @return the conex
	 */
	public Connection getConex() {
		return conex;
	}

	/**
	 * @return the dRIVER
	 */
	public String getDRIVER() {
		return DRIVER;
	}

	/**
	 * @return the dSN
	 */
	public String getDSN() {
		return DSN;
	}

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @return the uSUARIO
	 */
	public String getUSUARIO() {
		return USUARIO;
	}

	/**
	 * Modificar algo en la base de datos
	 * 
	 * @param sql
	 * @return
	 */
	public boolean modificar(String sql) {
		boolean rd = false;
		Statement stm = null;
		try {
			stm = conex.createStatement();
			stm.executeUpdate(sql);
			rd = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rd;
	}
}
