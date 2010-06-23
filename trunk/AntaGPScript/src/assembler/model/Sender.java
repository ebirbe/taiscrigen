package assembler.model;

import java.util.Vector;

import data.LineVector;
import data.TempBuffer;
import assembler.model.constants.BatteryActiveFlag;
import assembler.model.constants.EventHandling;
import assembler.model.constants.EventSense;
import assembler.model.constants.MessageID;

public class Sender {
	
	@SuppressWarnings("unused")
	private String firmware_version = Configuration.firmware_version;
	public LineVector lv = new LineVector();
	
	public void mkID(String id, boolean imeiId){
		lv.addComment("GPS ID");
		if(imeiId)
			//Usar IMEI como ID
			lv.addSentence("SXAID1");
		else{
			//ID de Usuario
			lv.addSentence("SXAID0");
			lv.addSentence("SID"+id);
		}
	}
	public void mkReset(boolean reset){
		if(reset){
			lv.addComment("Clear the previous configuration");
			lv.addSentence("SRT;CONFIG");
			lv.addSentence("SXADP**U");
		}
	}
	public void mkAPN(String apn){
		lv.addComment("Access Point Network");
		lv.addSentence("SRFA"+apn);
	}
	public void mkPIN(String pin){
		lv.addComment("SIM card PIN");
		lv.addSentence("SRFI"+pin);
	}
	public void mkLogin(String login){
		lv.addComment("APN Login");
		lv.addSentence("SRFL"+login);
	}
	public void mkPassword(String pswrd){
		lv.addComment("APN Password");
		lv.addSentence("SRFP"+pswrd);
	}
	public void mkDestinationPointIP(String ip, String port, Integer id, Character cnslAccess, Character protocol ){
		String sId = intFiller(id, 2);
		lv.addComment("Internet Destination Point "+sId);
		lv.addSentence("SXADP"+sId+cnslAccess.toString()+protocol.toString()+ip+";"+port);
	}
	public void mkDestinationPointPhone(String phone, Integer id, Character typeMessage, Character access){
		String sId = intFiller(id, 2);
		lv.addComment("Cellphone Destination Point "+sId);
		lv.addSentence("SXADP"+sId+typeMessage.toString()+access.toString()+phone);
	}
	public void mkDestinationAdress(Integer id, Vector<String> vPoints){
		String strPorts = new String();
		for (String s : vPoints) {
			strPorts += "P"+s+";";
		}
		strPorts = strPorts.substring(0, strPorts.length()-2);//Eliminamos el ultimo ";"
		lv.addComment("Destination Adress #"+id);
		lv.addSentence("SDA"+id+";"+strPorts);
	}
	public void mkEventDefinition(int id, EventHandling eH, MessageID msgID, int dA, Vector<EventCondition> evtCond, EventSense evtSense, Vector<EventAction> evtAction){
		String sId = intFiller(id, 2);
		String sEvtCond = new String();
		String sEvtAction = new String();
		for(EventCondition ec : evtCond){
			sEvtCond += ec.signal + ec.logicOperator;
		}
		for(EventAction ea : evtAction){
			sEvtAction += ";"+ea.actionMessage+"="+ea.taipAction;
		}
		lv.addComment("Event definition #"+sId);
		lv.addSentence("SED"+sId+eH+msgID+dA+";"+sEvtCond+evtSense+sEvtAction);
	}
	public void mkTimeDistance(int id, int tMin, int distance, int tMax){
		String sId = intFiller(id, 1);
		String sTmin = intFiller(tMin, 4);
		String sDistance = intFiller(distance, 4);
		String sTmax = intFiller(tMax, 4);
		lv.addComment("Time and Distance #"+sId);
		lv.addSentence("STD"+sId+sTmin+"XXXX"+sDistance+sTmax);
	}
	public void mkSpeedLimit(int id, boolean active, int speed){
		String sActive;
		if(active) sActive = "1";
		else sActive = "U";
		lv.addComment("Speed Limit #"+id);
		lv.addSentence("SGS"+intFiller(id, 2)+sActive+intFiller(speed, 4));
	}
	public void mkBatteryLevel(int id, BatteryActiveFlag baf, int bateryLevel){
		lv.addComment("Battery Level #"+id);
		lv.addSentence("XAGB"+intFiller(id, 2)+baf+intFiller(bateryLevel, 5));
	}
	public void mkHeadingDelta(int id, String hd, int delta){
		lv.addComment("Heading Delata #"+id);
		lv.addSentence("XAGH"+intFiller(id, 2)+hd+intFiller(delta, 3));
	}
	/**
	 * Realiza la completacion de los enteros
	 * que vienen con formato entero a string
	 * en el formato 00 <-> 99
	 * @param i
	 * @return
	 */
	public static String intFiller(Integer i, int size){
		String s = i.toString();
		int lenght = s.length();
		int rest = size - lenght;
		for(int j = 0; j < rest; j++){
			s = "0"+s;
		}
		return s;
	}
	/**
	 * Envia los datos al buffer temporal
	 */
	public void SendToBuffer(){
		TempBuffer.buffer.add(lv);
	}
}
