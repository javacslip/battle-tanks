package battletanks.graphics;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

import battletanks.game.GameObject;
import battletanks.game.Gamestate;

public class Drawer {
	
	private objModel tankmodel;
	
	
	GL gl;
	GLU glu;
	GLUT glut;
	
	private boolean wireframe = false;
	private boolean cullface = true;
	private boolean flatshade = false;
	
	private float znear, zfar;
	

	/* Here you should give a conservative estimate of the scene's bounding box
	 * so that the initViewParameters function can calculate proper
	 * transformation parameters to display the initial scene.
	 * If these are not set correctly, the objects may disappear on start.
	 */
	private float xmin = -1f, ymin = -1f, zmin = -1f;
	private float xmax = 1f, ymax = 1f, zmax = 1f;	
    

	public Drawer(GL gl, GLU glu, GLUT glut) {
		
		this.gl = gl;
		this.glu = glu;
		this.glut = glut;

		
		gl.glClearColor(.05f, .05f, .05f, 1f);
		gl.glClearDepth(1.0f);
		

	    gl.glEnable( GL.GL_NORMALIZE );
	    gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glCullFace(GL.GL_BACK);
		gl.glEnable(GL.GL_CULL_FACE);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glDisable( GL.GL_LIGHTING );
		
	}

	public void LoadRes(){
		tankmodel = new objModel("./obj/tank.obj");
		
	}
	
	public void Draw(Gamestate g){
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, wireframe ? GL.GL_LINE : GL.GL_FILL);	
		gl.glShadeModel(flatshade ? GL.GL_FLAT : GL.GL_SMOOTH);		
		if (cullface)
			gl.glEnable(GL.GL_CULL_FACE);
		else
			gl.glDisable(GL.GL_CULL_FACE);		
		
		gl.glLoadIdentity();
		
		
		GameObject player = Gamestate.getInstance().getPlayer();
		gl.glPushMatrix();
		gl.glTranslatef(player.getPos().x, player.getPos().y, player.getPos().z);
		gl.glRotatef(player.getPhi(), 0, 0, 1.0f);
		gl.glRotatef(player.getTheta(), 1.0f, 0, 0.0f);
		
		gl.glColor3f(0.5F,0.5F,.5F);
		for(GameObject ob : Gamestate.getInstance().getObstacles(()){
			gl.glPushMatrix();
			gl.glTranslatef(ob.getPos().x,ob.getPos().y,ob.getPos().z);
			gl.glRotatef(ob.getPhi(), 0, 0, 1.0f);
			gl.glRotatef(ob.getTheta(), 0, 0, 1.0f);
			glut.glutSolidSphere(0.4, 40, 40);
			gl.glPopMatrix();
		}
		
		gl.glColor3f(0.9F,0.0F,.0F);
		for(GameObject ob : Gamestate.getInstance().getEnemies(()){
			gl.glPushMatrix();
			gl.glTranslatef(ob.getPos().x,ob.getPos().y,ob.getPos().z);
			gl.glRotatef(ob.getPhi(), 0, 0, 1.0f);
			gl.glRotatef(ob.getTheta(), 0, 0, 1.0f);
			tankmodel.Draw(gl);
			gl.glPopMatrix();
		}
		

		gl.glPopMatrix();
		
	}
	


	public void resize(int width, int height) {

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();
			glu.gluPerspective(45.f, (float)width/(float)height, znear, zfar);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		
	}

}
