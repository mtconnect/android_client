package javapadawan.android;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;




import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

import com.ITAMCO.MTConnect.*;

import javapadawan.android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import static javapadawan.android.MessageList.*;



public class BaseFeedParser {
	
	//static String feedUrlString = "http://www.androidster.com/android_news.rss";
    //String Value = "http://www.androidster.com/android_news.rss";
	//public static final String PREFS_NAME = "MyPrefsFile";
	//SharedPreferences myPreference = getSharedPreferences(PREFS_NAME,0);
    //String result = myPreference.getString("key",""); 
	//String Value1 = Value.replace(" ", "%20");
	//String Value1 = getString(R.id.etUsername);
	
	String Value1;
	//String Value1 = "http://www.androidster.com/android_news.rss";
	
	//names of the XML tags
	//static final String RSS = "rss";
	//static final String CHANNEL = "channel";
	//static final String ITEM = "maintag";
	
	//static final String PUB_DATE = "pubDate";
	//static final String DESCRIPTION = "description";
	//static final String LINK = "link";
	//static final String TITLE = "title";
	
	static final String RSS = "MTConnectStreams";
	static final String CHANNEL = "Streams";
	static final String DEVICESTREAM = "DeviceStream";
	static final String COMPONENTSTREAM = "ComponentStream";
	static final String ITEM = "Samples";
	
	static final String SAMPLES = "Samples";
	static final String EVENTS = "Events";
	static final String PUB_DATE = "Frequency";
	static final String DESCRIPTION = "Watts";
	static final String LINK = "Voltage";
	static final String TITLE = "Amperage";
	static final String POWER = "PowerState";
	static final String ESTOP = "EmergencyStop";
	static final String ALARM = "Message";
	static final String BLOCK = "Block";
	static final String CONTROL = "ControllerMode";
	static final String LINE = "Line";
	static final String PROGRAM = "Program";
	static final String EXECUTION = "Execution";
	static final String PATHF = "PathFeedrate";
	static final String PATHP = "PathPosition";
	static final String SPINDLE = "SpindleSpeed";
	static final String XPOS = "Position";
	static final String YPOS = "Position";
	static final String ZPOS = "Position";
	
	
	private final URL feedUrl;
	
	
	public BaseFeedParser(Context context) {
		
		//Value1 = getIntent.getStringExtra("key");
		//FileInputStream fis;
		//String result = "";		
		//try {
		//	fis = openFileInput(FILENAME);
			//Scanner sc = new Scanner(fis);
				
			//while(sc.hasNextLine()) {
				//result += sc.nextLine();
			//}
		// }catch (FileNotFoundException e){}
		 	//String result = "http://www.androidster.com/android_news.rss";	 
		try {
			SharedPreferences myPreference = context.getSharedPreferences(MessageList.PREFS_NAME,0);
		    String Value1 = myPreference.getString("key","");
		    Value1 = Value1+"/current";
			this.feedUrl = new URL(Value1);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	

	}

	



	protected InputStream getInputStream() {
		try {
			return feedUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Message> parse() {
		//final Message currentMessage = new Message();
		//RootElement root = new RootElement(RSS);
		//final List<Message> messages = new ArrayList<Message>();
		//Element itemlist = root.getChild(CHANNEL);
		//Element itemlist1 = itemlist.getChild(DEVICESTREAM);
		//Element itemlist2 = itemlist1.getChild(COMPONENTSTREAM);
		////Element item = itemlist.getChild(ITEM);
		//Element item = itemlist2.getChild(ITEM);
		//item.setEndElementListener(new EndElementListener(){
			//public void end() {
				//messages.add(currentMessage.copy());
			//}
		//});
		//item.getChild(TITLE).setEndTextElementListener(new EndTextElementListener(){
			//public void end(String body) {
				//currentMessage.setTitle(body);
			//}
		//});
		////item.getChild(LINK).setEndTextElementListener(new EndTextElementListener(){
			////public void end(String body) {
				////currentMessage.setLink(body);
			////}
		////});
		////item.getChild(DESCRIPTION).setEndTextElementListener(new EndTextElementListener(){
			////public void end(String body) {
				////currentMessage.setDescription(body);
			////}
		////});
		////item.getChild(PUB_DATE).setEndTextElementListener(new EndTextElementListener(){
			////public void end(String body) {
				////currentMessage.setDate(body);
			////}
		////});
		//try {
			//Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		//} catch (Exception e) {
			//throw new RuntimeException(e);
		//}
		//return messages;
	//}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		List<Message> messages = new ArrayList<Message>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(this.getInputStream());
			//Element root = dom.getDocumentElement();
			//NodeList items = root.getElementsByTagName(ITEM);
			dom.getDocumentElement().normalize();
			NodeList items = dom.getElementsByTagName(RSS);
			for (int i=0;i<items.getLength();i++){
				Message message = new Message();
				Node item = items.item(i);
				//NodeList properties = item.getChildNodes();
				Element fstElmnt = (Element) item;
				NodeList spindleList = fstElmnt.getElementsByTagName(CHANNEL);
				Element nameElement = (Element) spindleList.item(0);
				spindleList = nameElement.getChildNodes();
				NodeList spindleList2 = nameElement.getElementsByTagName(DEVICESTREAM);
				Element nameElement2 = (Element) spindleList2.item(0);
				spindleList2 = nameElement2.getChildNodes();
				//Spindle Speed
				NodeList spindleList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element nameElement3 = (Element) spindleList3.item(0);
				spindleList3 = nameElement3.getChildNodes();
				NodeList spindleList4 = nameElement3.getElementsByTagName(SAMPLES);
				Element nameElement4 = (Element) spindleList4.item(0);
				spindleList4 = nameElement4.getChildNodes();
				NodeList spindleList5 = nameElement4.getElementsByTagName(SPINDLE);
				Element nameElement5 = (Element) spindleList5.item(0);
				spindleList5 = nameElement5.getChildNodes();		
				message.setTitle(((Node) spindleList5.item(0)).getNodeValue());
				//Emergency Stop
				NodeList estopList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element estopElement3 = (Element) estopList3.item(1);
				estopList3 = estopElement3.getChildNodes();
				NodeList estopList4 = estopElement3.getElementsByTagName(EVENTS);
				Element estopElement4 = (Element) estopList4.item(0);
				estopList4 = estopElement4.getChildNodes();
				NodeList estopList5 = estopElement4.getElementsByTagName(ESTOP);
				Element estopElement5 = (Element) estopList5.item(0);
				estopList5 = estopElement5.getChildNodes();		
				message.setEstop(((Node) estopList5.item(0)).getNodeValue());
				//Alarm and Message
				NodeList alarmList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element alarmElement3 = (Element) alarmList3.item(1);
				alarmList3 = alarmElement3.getChildNodes();
				NodeList alarmList4 = alarmElement3.getElementsByTagName(EVENTS);
				Element alarmElement4 = (Element) alarmList4.item(0);
				alarmList4 = alarmElement4.getChildNodes();
				NodeList alarmList5 = alarmElement4.getElementsByTagName(ALARM);
				Element alarmElement5 = (Element) alarmList5.item(0);
				alarmList5 = alarmElement5.getChildNodes();		
				message.setAlarm(((Node) alarmList5.item(0)).getNodeValue());
				//Power State
				NodeList powerList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element powerElement3 = (Element) powerList3.item(4);
				powerList3 = powerElement3.getChildNodes();
				NodeList powerList4 = powerElement3.getElementsByTagName(EVENTS);
				Element powerElement4 = (Element) powerList4.item(0);
				powerList4 = powerElement4.getChildNodes();
				NodeList powerList5 = powerElement4.getElementsByTagName(POWER);
				Element powerElement5 = (Element) powerList5.item(0);
				powerList5 = powerElement5.getChildNodes();		
				message.setPower(((Node) powerList5.item(0)).getNodeValue());
				//Path Feedrate
				NodeList pathfList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element pathfElement3 = (Element) pathfList3.item(6);
				pathfList3 = pathfElement3.getChildNodes();
				NodeList pathfList4 = pathfElement3.getElementsByTagName(SAMPLES);
				Element pathfElement4 = (Element) pathfList4.item(0);
				pathfList4 = pathfElement4.getChildNodes();
				NodeList pathfList5 = pathfElement4.getElementsByTagName(PATHF);
				Element pathfElement5 = (Element) pathfList5.item(0);
				pathfList5 = pathfElement5.getChildNodes();		
				message.setPathf(((Node) pathfList5.item(0)).getNodeValue());
				//Path Position
				NodeList pathpList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element pathpElement3 = (Element) pathpList3.item(6);
				pathpList3 = pathpElement3.getChildNodes();
				NodeList pathpList4 = pathpElement3.getElementsByTagName(SAMPLES);
				Element pathpElement4 = (Element) pathpList4.item(0);
				pathpList4 = pathpElement4.getChildNodes();
				NodeList pathpList5 = pathpElement4.getElementsByTagName(PATHP);
				Element pathpElement5 = (Element) pathpList5.item(0);
				pathpList5 = pathpElement5.getChildNodes();		
				message.setPathp(((Node) pathpList5.item(0)).getNodeValue());
				//Block
				NodeList blockList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element blockElement3 = (Element) blockList3.item(6);
				blockList3 = blockElement3.getChildNodes();
				NodeList blockList4 = blockElement3.getElementsByTagName(EVENTS);
				Element blockElement4 = (Element) blockList4.item(0);
				blockList4 = blockElement4.getChildNodes();
				NodeList blockList5 = blockElement4.getElementsByTagName(BLOCK);
				Element blockElement5 = (Element) blockList5.item(0);
				blockList5 = blockElement5.getChildNodes();		
				message.setBlock(((Node) blockList5.item(0)).getNodeValue());
				//Controller Mode
				NodeList controlList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element controlElement3 = (Element) controlList3.item(6);
				controlList3 = controlElement3.getChildNodes();
				NodeList controlList4 = controlElement3.getElementsByTagName(EVENTS);
				Element controlElement4 = (Element) controlList4.item(0);
				controlList4 = controlElement4.getChildNodes();
				NodeList controlList5 = controlElement4.getElementsByTagName(CONTROL);
				Element controlElement5 = (Element) controlList5.item(0);
				controlList5 = controlElement5.getChildNodes();		
				message.setControl(((Node) controlList5.item(0)).getNodeValue());
				//Line
				NodeList lineList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element lineElement3 = (Element) lineList3.item(6);
				lineList3 = lineElement3.getChildNodes();
				NodeList lineList4 = lineElement3.getElementsByTagName(EVENTS);
				Element lineElement4 = (Element) lineList4.item(0);
				lineList4 = lineElement4.getChildNodes();
				NodeList lineList5 = lineElement4.getElementsByTagName(LINE);
				Element lineElement5 = (Element) lineList5.item(0);
				lineList5 = lineElement5.getChildNodes();		
				message.setLine(((Node) lineList5.item(0)).getNodeValue());
				//Program
				NodeList programList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element programElement3 = (Element) programList3.item(6);
				programList3 = programElement3.getChildNodes();
				NodeList programList4 = programElement3.getElementsByTagName(EVENTS);
				Element programElement4 = (Element) programList4.item(0);
				programList4 = programElement4.getChildNodes();
				NodeList programList5 = programElement4.getElementsByTagName(PROGRAM);
				Element programElement5 = (Element) programList5.item(0);
				programList5 = programElement5.getChildNodes();		
				message.setProgram(((Node) programList5.item(0)).getNodeValue());
				//Execution
				NodeList executionList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element executionElement3 = (Element) executionList3.item(6);
				executionList3 = executionElement3.getChildNodes();
				NodeList executionList4 = executionElement3.getElementsByTagName(EVENTS);
				Element executionElement4 = (Element) executionList4.item(0);
				executionList4 = executionElement4.getChildNodes();
				NodeList executionList5 = executionElement4.getElementsByTagName(EXECUTION);
				Element executionElement5 = (Element) executionList5.item(0);
				executionList5 = executionElement5.getChildNodes();		
				message.setExecution(((Node) executionList5.item(0)).getNodeValue());
				//X POSITION
				NodeList xposList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element xposElement3 = (Element) xposList3.item(7);
				xposList3 = xposElement3.getChildNodes();
				NodeList xposList4 = xposElement3.getElementsByTagName(SAMPLES);
				Element xposElement4 = (Element) xposList4.item(0);
				xposList4 = xposElement4.getChildNodes();
				NodeList xposList5 = xposElement4.getElementsByTagName(XPOS);
				Element xposElement5 = (Element) xposList5.item(0);
				xposList5 = xposElement5.getChildNodes();		
				message.setXpos(((Node) xposList5.item(0)).getNodeValue());
				//Y POSITION
				NodeList yposList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element yposElement3 = (Element) yposList3.item(8);
				yposList3 = yposElement3.getChildNodes();
				NodeList yposList4 = yposElement3.getElementsByTagName(SAMPLES);
				Element yposElement4 = (Element) yposList4.item(0);
				yposList4 = yposElement4.getChildNodes();
				NodeList yposList5 = yposElement4.getElementsByTagName(YPOS);
				Element yposElement5 = (Element) yposList5.item(0);
				yposList5 = yposElement5.getChildNodes();		
				message.setYpos(((Node) yposList5.item(0)).getNodeValue());
				//Z POSITION
				NodeList zposList3 = nameElement2.getElementsByTagName(COMPONENTSTREAM);
				Element zposElement3 = (Element) zposList3.item(9);
				zposList3 = zposElement3.getChildNodes();
				NodeList zposList4 = zposElement3.getElementsByTagName(SAMPLES);
				Element zposElement4 = (Element) zposList4.item(0);
				zposList4 = zposElement4.getChildNodes();
				NodeList zposList5 = zposElement4.getElementsByTagName(ZPOS);
				Element zposElement5 = (Element) zposList5.item(0);
				zposList5 = zposElement5.getChildNodes();		
				message.setZpos(((Node) zposList5.item(0)).getNodeValue());
				///////START HERE!!!!
				//for (int j=0;j<properties.getLength();j++){
					//Node property = properties.item(j);
					//String name = property.getNodeName();
					//if (name.equals(TITLE)){
						//message.setTitle(property.getFirstChild().getNodeValue());
					//} else if (name.equalsIgnoreCase(LINK)){
						//message.setLink(property.getFirstChild().getNodeValue());
					//} else if (name.equalsIgnoreCase(DESCRIPTION)){
						//StringBuilder text = new StringBuilder();
						//NodeList chars = property.getChildNodes();
						//for (int k=0;k<chars.getLength();k++){
							//text.append(chars.item(k).getNodeValue());
						//}
						//message.setDescription(text.toString());
					//} else if (name.equalsIgnoreCase(PUB_DATE)){
						//message.setDate(property.getFirstChild().getNodeValue());
					//}
				//}
				messages.add(message);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return messages;
	}

}
