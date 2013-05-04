package battletanks.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;

import com.sun.opengl.util.awt.TextRenderer;
import com.sun.opengl.util.gl2.GLUT;

public class Logger {
	HashMap<String, String> debugValues;
	LinkedList<String> log;

	public static Logger instance = null;

	public static Logger getInstance() {
		if (Logger.instance == null)
			Logger.instance = new Logger();

		return Logger.instance;
	}

	public void Log(String l) {
		String entry = "<" + Long.toString(System.currentTimeMillis()) + ">";
		entry += l ;
		log.add(entry);
		System.out.println(entry);
	}

	public void debugVal(String k, String v) {
		debugValues.put(k, v);

	}

	public Logger() {
		debugValues = new HashMap<String, String>();
		log = new LinkedList<String>();
	}

	public void display(TextRenderer renderer) {

		Iterator it = debugValues.entrySet().iterator();
		int x = 5;
		int y = 5;
		String key, val;

		while (it.hasNext()) {

			Map.Entry pairs = (Map.Entry) it.next();
			key = pairs.getKey().toString() + ":";
			val = pairs.getValue().toString();

			renderer.setColor(Color.GREEN);
			renderer.draw(key, x, y);
			renderer.draw(val, x + 100, y);

			y += 15;

		}

		Iterator<String> logit = log.descendingIterator();
		x = 500;
		y = 150;
		String entry;

		while (logit.hasNext() && y > 5) {

			entry = (String) logit.next();

			renderer.setColor(Color.GREEN);
			renderer.draw(entry, x, y);

			y -= 15;

		}

	}

}
