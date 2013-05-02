package battletanks.game.objects;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

import battletanks.game.GameObject;


public class Obstacle implements GameObject {
	
	private Vector3f position;
	private Vector3f direction;
	private Vector3f velocity;
	float phi, theta;

	public Obstacle(){
		position = new Vector3f(0, 0, 0);
		direction = new Vector3f(0, 0, 0);
	}
	
	@Override
	public void Update(long dtime) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector3f getPos() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public Vector3f getVel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2d getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3f getDirection() {
		// TODO Auto-generated method stub
		return this.direction;
	}

	@Override
	public void setPos(float x, float y, float z) {
		position = new Vector3f(x,y,z);
		
	}

	@Override
	public void setVel(float x, float y, float z) {
		velocity = new Vector3f(x,y,z);
		
	}

	@Override
	public void setBoundingBox(Vector2d v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public float getPhi() {
		// TODO Auto-generated method stub
		return phi;
	}

	@Override
	public float getTheta() {
		// TODO Auto-generated method stub
		return theta;
	}
	@Override
	public void setDirection(float theta, float phi) {
		direction = new Vector3f((float)(Math.cos(phi) * Math.sin(theta)),(float) (Math.sin(theta) * Math.sin(phi)), (float)Math.cos(theta));
		this.theta = theta;
		this.phi = phi;
		
	}

}
