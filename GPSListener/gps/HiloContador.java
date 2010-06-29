package gps;

import log.MyLogger;
import servidor.GPSServer;

public class HiloContador extends Thread {
	GPSHandler gpsh;
	public HiloContador(GPSHandler gpsh) {
		super();
		this.gpsh = gpsh;
		start();
	}
	@Override
	public void run() {
		try {
			sleep(15000);
			if(gpsh.id.equals("")){
				MyLogger.escribirLog(gpsh.name  + " no ha reportado ID, Eliminando...");
				GPSServer.wp.lblStatus.setText(gpsh.name  + " no ha reportado ID, Eliminando...");
				GPSServer.removeConectados(gpsh.name, gpsh);
				gpsh.leer = false;
				gpsh.finalize();
			}else{
				MyLogger.escribirLog(gpsh.name  + " ya reporto ID, Continuando...");
				GPSServer.wp.lblStatus.setText(gpsh.name  + " ya reporto ID, Continuando...");
			}
		} catch (InterruptedException e) {
			MyLogger.escribirLog(e.toString());
			e.printStackTrace();
		} catch (Throwable e) {
			MyLogger.escribirLog(e.toString());
			e.printStackTrace();
		}
	}
}
