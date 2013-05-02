package battletanks.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.j2d.Overlay;

public class Logger {
	HashMap<String,String> debugValues;
	LinkedList<String> log;
	
	public static Logger instance = null;
	
	public static Logger getInstance(){
		if (Logger.instance == null)
			Logger.instance = new Logger();
		
		return Logger.instance;
	}
	
	public void Log(String l){
		log.add(l);
		System.out.println(l);
	}
	
	public void debugVal(String k, String v){
			debugValues.put(k, v);

	}
	
	
	public Logger(){
		debugValues = new HashMap<String,String>();
		log = new LinkedList<String>();
	}
	

	public void display(Overlay overlay) {

		Graphics2D g = overlay.createGraphics();
		Iterator it = debugValues.entrySet().iterator();
		int x = 10;
		int y = 10;
		 while (it.hasNext()) {
			
			 Map.Entry pairs = (Map.Entry)it.next();
			 String key = pairs.getKey().toString() + ":";
			 String val = pairs.getValue().toString();
			 System.out.println(key); 
			 g.setColor(Color.GREEN);
			 g.drawString(key, x,y);
			 g.drawString(val,x+100, y);
			 
			 y+= 16;

		 }
		
		overlay.drawAll();
		g.dispose();

	
		
	}

}
