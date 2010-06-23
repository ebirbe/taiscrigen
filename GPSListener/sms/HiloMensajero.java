package sms;

import servidor.GPSServer;

public class HiloMensajero extends Thread {
	
	String numero, msg;
	SerialToGsm stg;
	
	public HiloMensajero( SerialToGsm stg, String numero, String msg) {
		super();
		this.numero = numero;
		this.msg = msg;
		this.stg = stg;
		this.start();
	}

	public void run(){
		System.out.println("Hilo de Mensaje Iniciado.");
		GPSServer.wp.lblStatus.setText("Hilo de Mensaje Iniciado.");
		stg.sendSms(numero, msg);
		System.out.println("Hilo de Mensaje Terminado.");
		GPSServer.wp.lblStatus.setText("Hilo de Mensaje Terminado.");
	}
}
