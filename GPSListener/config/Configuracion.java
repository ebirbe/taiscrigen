package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import log.MyLogger;

public class Configuracion extends Properties{
	private static final long serialVersionUID = -6962968299670902858L;
	private final String nombreArchivo = "gpslistener.conf";
	
	public static String keyGsmDevice = "gsm_modem_dispositivo";
	private String gsmDevice = "/dev/ttyUSB_utps_pcui";
	
	public static String keyHostPort = "puerto_escucha_gps";
	private Integer hostPort = 5001;
	private FileInputStream flujoEntrada;
	private FileOutputStream flujoSalida;

	public FileInputStream getFlujoEntrada() {
		return flujoEntrada;
	}

	public void setFlujoEntrada(FileInputStream flujoEntrada) {
		this.flujoEntrada = flujoEntrada;
	}

	public FileOutputStream getFlujoSalida() {
		return flujoSalida;
	}

	public void setFlujoSalida(FileOutputStream flujoSalida) {
		this.flujoSalida = flujoSalida;
	}

	public String getGsmDevice() {
		return gsmDevice;
	}

	public void setGsmDevice(String gsmDevice) {
		this.gsmDevice = gsmDevice;
	}

	public Integer getHostPort() {
		return hostPort;
	}

	public void setHostPort(Integer hostPort) {
		this.hostPort = hostPort;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public Configuracion() {
		try {
			flujoEntrada = new FileInputStream(nombreArchivo);
			load(flujoEntrada);
			setHostPort(Integer.parseInt((String) get(keyHostPort)));
			setGsmDevice((String) get(keyGsmDevice));
		} catch (FileNotFoundException e) {
			MyLogger.escribirLog(this.getClass().getName(),e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			MyLogger.escribirLog(this.getClass().getName(),e.toString());
			e.printStackTrace();
		}
	}

	public void guardarConfiguracion() {
		try {
			flujoSalida = new FileOutputStream(nombreArchivo);
			store(flujoSalida, "Archivo de Configuracion para gpslistener.");
		} catch (FileNotFoundException e) {
			MyLogger.escribirLog(this.getClass().getName(),e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			MyLogger.escribirLog(this.getClass().getName(),e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Configuracion c = new Configuracion();
		c.put("puerto_escucha_gps", "tristras");
		c.guardarConfiguracion();
		System.out.println(c.get("puerto_escucha_gps"));
	}
}