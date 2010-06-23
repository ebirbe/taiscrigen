package gps;

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
				System.out.println(gpsh.name  + " no ha reportado ID, Eliminando...");
				GPSServer.wp.lblStatus.setText(gpsh.name  + " no ha reportado ID, Eliminando...");
				GPSServer.removeConectados(gpsh.name, gpsh);
				gpsh.leer = false;
				gpsh.finalize();
			}else{
				System.out.println(gpsh.name  + " ya reporto ID, Continuando...");
				GPSServer.wp.lblStatus.setText(gpsh.name  + " ya reporto ID, Continuando...");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
