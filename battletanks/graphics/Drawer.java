package battletanks.graphics;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.vecmath.Vector3f;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;

import com.sun.opengl.util.awt.TextRenderer;
import com.sun.opengl.util.gl2.GLUT;

import battletanks.game.Gamestate;
import battletanks.game.Logger;
import battletanks.game.objects.GameObject;
import battletanks.game.objects.PlayerTank;

public class Drawer {

	private objModel tankmodel;

	GL2 gl;
	GLU glu;
	GLUT glut;
	private float znear, zfar;

	public Drawer(GL2 gl2, GLU glu, GLUT glut) {

		this.gl = gl2;
		this.glu = glu;
		this.glut = glut;

		znear = 0.01f;
		zfar = 1000.f;

		gl2.glClearColor(.1f, .1f, .1f, 1f);
		gl2.glClearDepth(1.0f);

		gl2.glEnable(GL.GL_DEPTH_TEST);
		gl2.glDepthFunc(GL.GL_LESS);
		gl2.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_NICEST);
		gl2.glCullFace(GL.GL_BACK);
		gl2.glEnable(GL.GL_CULL_FACE);

	}

	public void LoadRes() {
		tankmodel = new objModel(".\\obj\\tankalt.obj");

	}

	private void moveCamera(Vector3f pos, float theta, float phi) {

		gl.glRotatef(phi, 1.0f, 0, 0f);
		gl.glRotatef(theta, 0, 1.0f, 0);
		gl.glTranslatef(pos.x, pos.y, pos.z);

	}

	private void DrawGeometry(Vector3f color) {
		gl.glPushMatrix();
		gl.glLineWidth(3);

		if (color == null)
			gl.glColor3f(0.5F, 0.5F, .5F);
		else
			gl.glColor3f(color.x, color.y, color.z);
		for (GameObject ob : Gamestate.getInstance().getObstacles()) {
			gl.glPushMatrix();
			gl.glTranslatef(ob.getPos().x, ob.getPos().y, ob.getPos().z);
			gl.glRotatef(ob.getDir().x, 1.0f, 0, 0f);
			gl.glRotatef(ob.getDir().y, 0, 1.0f, 0);
			glut.glutSolidCube(1);
			gl.glPopMatrix();
		}
		
		if (color == null)
			gl.glColor3f(0.9F, 0.0F, .0F);
		else
			gl.glColor3f(color.x, color.y, color.z);

		for (GameObject ob : Gamestate.getInstance().getEnemies()) {
			gl.glPushMatrix();
			gl.glTranslatef(ob.getPos().x, ob.getPos().y, ob.getPos().z);
			gl.glRotatef(ob.getDir().x, 1.0f, 0, 0f);
			gl.glRotatef(ob.getDir().y, 0, 1.0f, 0);
			tankmodel.Draw(gl);
			gl.glPopMatrix();
		}

		gl.glPopMatrix();
	}

	private void DrawBackground() {
		gl.glColor3f(0, 1, 0);
		gl.glPushMatrix();
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(20.0f, -0.5f, -20.0f);
		gl.glVertex3f(-20.0f, -0.5f, -20.0f);
		gl.glVertex3f(-20.0f, -0.5f, 20.0f);
		gl.glVertex3f(20.0f, -0.5f, 20.0f);
		gl.glEnd();

	}

	public void Draw(Gamestate g) {
		
		

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glShadeModel(GL2.GL_FLAT);

		gl.glLoadIdentity();
		gl.glPushMatrix();

		PlayerTank player = (PlayerTank) Gamestate.getInstance().getPlayer();

		moveCamera(player.getPos(), player.getDir().x, player.getDir().y);

		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		DrawGeometry(new Vector3f(.1f, .1f, .1f));

		gl.glEnable(GL.GL_POLYGON_OFFSET_FILL);
		 gl.glPolygonOffset( 1, 1 );
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
		DrawGeometry(null);

		gl.glPopMatrix();

		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);

	}

	public void resize(int width, int height) {

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(40.0f, (float) width / (float) height, znear, zfar);
		gl.glMatrixMode(GL2.GL_MODELVIEW);

	}

}
