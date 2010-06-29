package sms;

import java.io.*;

import javax.comm.*;

import log.MyLogger;


public class SerialToGsm{
	
	InputStream in;
	OutputStream out;
	String lastIndexRead;
	String senderNum;
	String smsMsg;

	/**
	 * Constructor
	 */
	static boolean modemReseted = false;
	
	public SerialToGsm(String port) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException {
		
			CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(port);
			SerialPort sp = (SerialPort)portId.open("Sms_GSM", 0);
			sp.setSerialPortParams(4800, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			sp.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

			in = sp.getInputStream();
			out = sp.getOutputStream();
			
			resetModem();

			/*
		try {}
		catch (Exception e) {
			System.err.println(e + ": ALERTA: El Dispositivo de SMS no ha sido detectado.");
			System.exit(0);
		}*/
	}
	
	synchronized public void resetModem(){
		
		if (modemReseted) {
			System.out.println("Reset skipped");
			return;
		}
		
		// modem reset
		sendAndRecv("+++AT", 30);       // delay for 20 sec/10
		sendAndRecv("AT&F", 30);
		sendAndRecv("ATE0", 30);        // echo off
		sendAndRecv("AT +CMEE=1", 30);  // verbose error messages
		sendAndRecv("AT+CMGF=1", 70);   // set to text mode
	}

	private String sendAndRecv(String s, int timeout) {
		try {
			// clean serial port input buffer
			in.skip(in.available());
			MyLogger.escribirLog("=> " + s);
			s = s + "\r";         // add CR
			out.write(s.getBytes());
			out.flush();            

			String strIn = new String();
			for (int i = 0; i < timeout; i++){
				int numChars = in.available();
				if (numChars > 0) {
					byte[] bb = new byte[numChars];
					in.read(bb,0,numChars);
					strIn += new String(bb);
				}
				// start exit conditions
				// ---------------------
				if (strIn.indexOf(">\r\n") != -1) {
					break;
				}                                         

				if (strIn.indexOf("OK\r\n") != -1){
					break;
				}                                         

				if (strIn.indexOf("ERROR") != -1) { // if find 'error' wait for CR+LF
					if (strIn.indexOf("\r\n",strIn.indexOf("ERROR") + 1) != -1) {
						break;                                             
					}
				}                                         
				Thread.sleep(100); // delay 1/10 sec
			}
			MyLogger.escribirLog("<= " + strIn);

			if (strIn.length() == 0) {
				return "ERROR: len 0";
			}
			return strIn;
		}
		catch (Exception e) {                  
			MyLogger.escribirLog("Env/Rec Exception " + e);
			return "ERROR: send e recv Exception";
		}
	}

	synchronized public String sendSms (String numToSend, String whatToSend) {
		String s = new String();
		if(whatToSend.length() > 140){
			MyLogger.escribirLog("Mensaje muy largo: Tiene " + whatToSend.length() + " caracteres.");
			return "ERROR";
		}
		s = sendAndRecv("AT+CMGS=\""+numToSend+"\"\r", 30);
		if (s.indexOf(">") != -1) {
			s = sendAndRecv(whatToSend+(char)26, 150);
			return s;
		}              
		else {
			return "ERROR";
		}
	}

	// used to reset message data
	private void resetGsmObj() {
		lastIndexRead = null;
		senderNum = null;
		smsMsg = null;
	}


	public String checkSms (){
		String str = new String();
		String strGsm = new String();                          
		strGsm = sendAndRecv("AT+CMGL=0", 30);  // list unread msg and sign them as read
		// if answer contain ERROR then ERROR
		if (strGsm.indexOf("ERROR") != -1) {
			resetGsmObj();
			return strGsm; // error
		}

		strGsm = sendAndRecv("AT+CMGL=1", 30);  // list read msg
		// if answer contain ERROR then ERROR
		if (strGsm.indexOf("ERROR") != -1) {
			resetGsmObj();
			return strGsm; // error
		}

		// evaluate message index
		if (strGsm.indexOf(':') <= 0) {
			resetGsmObj();
			return ("ERROR unexpected answer");
		}

		str = strGsm.substring(strGsm.indexOf(':') + 1,strGsm.indexOf(','));
		str = str.trim(); // remove white spaces
		//        System.out.println("Index: " + str);
		lastIndexRead = str;

		// find message string
		// -------------------
		// look for start point (search \r, then skip \n, add and one more for right char
		int startPoint = strGsm.indexOf("\r",(strGsm.indexOf(":") + 1)) + 2;
		int endPoint = strGsm.indexOf("\r",startPoint + 1);
		if (endPoint == -1) {
			// only one message
			endPoint = strGsm.length();
		}

		// extract string
		str = strGsm.substring(startPoint, endPoint);
		MyLogger.escribirLog("String to be decoded :" + str);

		/*ComputeSmsData sms = new ComputeSmsData();
        sms.setRcvdPdu(str);
//        SMSCNum = new String(sms.getRcvdPduSMSC());
        senderNum = new String(sms.getRcvdSenderNumber());
        smsMsg = new String(sms.getRcvdPduTxt());

        System.out.println("SMSC number:   " + sms.getRcvdPduSMSC());
        System.out.println("Sender number: " + sms.getRcvdSenderNumber());
        System.out.println("Message: " + sms.getRcvdPduTxt());*/

		return "OK";
	}

	public String readSmsSender() {
		return senderNum;
	}

	public String readSms() {
		return smsMsg;
	}

	public String delSms() {   
		if (lastIndexRead != "") {                
			return sendAndRecv("AT+CMGD=" + lastIndexRead, 30);
		}
		return ("ERROR");
	}
}

