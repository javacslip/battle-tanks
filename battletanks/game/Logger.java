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
import com.sun.opengl.util.j2d.TextRenderer;

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
	

	public void display(TextRenderer renderer) {


		Iterator it = debugValues.entrySet().iterator();
		int x = 100;
		int y = 100;
		String key,val;
		
		 while (it.hasNext()) {
			
			 Map.Entry pairs = (Map.Entry)it.next();
			 key = pairs.getKey().toString() + ":";
			 val = pairs.getValue().toString();
			 
			 renderer.setColor(Color.GREEN);
			 renderer.draw(key, x,y);
			 renderer.draw(val,x+100, y);
			 
			 y+= 16;

		 }


	
		
	}

}
