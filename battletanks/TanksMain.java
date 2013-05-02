package battletanks;

import java.awt.*;


import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.swing.JFrame;

import battletanks.game.GameInput;
import battletanks.game.Gamestate;
import battletanks.game.INPUT_TYPE;
import battletanks.game.Logger;
import battletanks.graphics.Drawer;

import com.sun.opengl.util.*;
import com.sun.opengl.util.j2d.Overlay;
import com.sun.opengl.util.j2d.TextRenderer;


import java.nio.ByteBuffer;



public class TanksMain extends JFrame implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, ActionListener{

	// mouse control variables
	private final GLCanvas canvas;
	private int winW = 512, winH = 512;
	private int mouseX, mouseY;
	private int mouseButton;
	private boolean mouseClick = false;
	private float tx = 0.0f, ty = 0.0f;
	private float scale = 1.0f;
	private float angle = 0.0f;
	private FPSAnimator animator;
	Drawer draw;
	private long time = System.nanoTime();
	TextRenderer renderer;

	public static void main(String args[]) {
		new TanksMain();
	}

	// constructor
	public TanksMain() {
		super("Battle Tanks");
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		getContentPane().add(canvas);
		setSize(winW, winH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		animator = new FPSAnimator(canvas, 30);	// create a 30 fps animator
		animator.start();
		canvas.requestFocus();
		
		
	}
	

	
	// gl display function
	public void display(GLAutoDrawable drawable) {
		
		
	
		time = System.currentTimeMillis();
		
		Gamestate.getInstance().UpdateState(time);
		
		draw.Draw(Gamestate.getInstance());
		
		
		renderer.beginRendering(drawable.getWidth(), drawable.getHeight());
		Logger.getInstance().display(renderer);
		renderer.endRendering();
		


	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
		// TODO Auto-generated method stub
	}

	// initialization
	public void init(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		gl.setSwapInterval(1);
		GLU glu = new GLU();
		GLUT glut = new GLUT();
		renderer = new TextRenderer(new Font("sansserif", Font.BOLD, 22));
		draw = new Drawer(gl, glu, glut);
		draw.LoadRes();
		
		
		
	}

	// reshape callback function: called when the size of the window changes
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		winW = width;
		winH = height;
		draw.resize(width, height);
	}

	// mouse pressed even callback function
	public void mousePressed(MouseEvent e) {
		mouseClick = true;
		mouseX = e.getX();
		mouseY = e.getY();
		mouseButton = e.getButton();

	}

	public void mouseReleased(MouseEvent e) {
		
	
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (mouseButton == MouseEvent.BUTTON3) {
			// right button scales
			scale += (y - mouseY) * 0.01f;
		}
		else if (mouseButton == MouseEvent.BUTTON2) {
			// middle button translates
			tx += (x - mouseX) * 0.01f;
			ty -= (y - mouseY) * 0.01f;
		}
		else if (mouseButton == MouseEvent.BUTTON1) {
			// left button rotates
			angle += (y - mouseY);
		}
		mouseX = x;
		mouseY = y;
		
	}
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
		case KeyEvent.VK_Q:
			System.exit(0);
			break;
		case KeyEvent.VK_W:		
		case KeyEvent.VK_UP:		
			Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.FORWARD_PRESSED));
			break;
		case KeyEvent.VK_S:		
		case KeyEvent.VK_DOWN:		
			Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.BACKWARD_PRESSED));
			break;
		case KeyEvent.VK_A:		
		case KeyEvent.VK_LEFT:		
			Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.LEFT_PRESSED));
			break;
		case KeyEvent.VK_D:		
		case KeyEvent.VK_RIGHT:		
			Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.RIGHT_PRESSED));
			break;
		case KeyEvent.VK_SPACE:		
			Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.FIRE_PRESSED));
			break;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_Q:
				System.exit(0);
			case KeyEvent.VK_W:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.FORWARD_RELEASED));
			case KeyEvent.VK_UP:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.FORWARD_RELEASED));
			case KeyEvent.VK_S:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.BACKWARD_RELEASED));
			case KeyEvent.VK_DOWN:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.BACKWARD_RELEASED));
			case KeyEvent.VK_A:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.LEFT_RELEASED));
			case KeyEvent.VK_LEFT:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.LEFT_RELEASED));
			case KeyEvent.VK_D:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.RIGHT_RELEASED));
			case KeyEvent.VK_RIGHT:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.RIGHT_RELEASED));
			case KeyEvent.VK_SPACE:		
				Gamestate.getInstance().AddInput(new GameInput(INPUT_TYPE.FIRE_RELEASED));
		}
	}

	// these event functions are not used for this assignment
	// but may be useful in the future
	public void keyTyped(KeyEvent e) { }

	public void mouseMoved(MouseEvent e) { }
	public void actionPerformed(ActionEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) {	}

}
