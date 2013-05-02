package battletanks.game.objects;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

import battletanks.game.GameObject;

public class PlayerTank implements GameObject {

	private Vector3f velocity;
	private Vector3f position;
	private Vector3f direction;
	float phi, theta;

	
	public PlayerTank(){
		
	}
	
	
	public void moveForward(){
		
		double radphi = Math.toRadians(phi);
		double radtheta = Math.toRadians(theta);
		position.x -= (float) Math.sin(radtheta);
		position.z  += (float)(Math.cos(radtheta));
		position.y  -= (float) (Math.sin(radphi));
		
		
	}
	
	public void moveBackward(){
		double radphi = Math.toRadians(phi);
		double radtheta = Math.toRadians(theta);
		position.x += (float) Math.sin(radtheta);
		position.z  -= (float)(Math.cos(radtheta));		
		position.y  += (float) (Math.sin(radphi));
	}
	
	public void turnLeft(){
		setDirection(theta -2.5f,0);
	}
	
	public void turnRight(){
		setDirection(theta +2.5f,0);
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
		return this.direction;
	}



	public void Update(long dtime) {
		// TODO Auto-generated method stub

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

		

		
		
		this.theta = theta;
		this.phi = phi;
		
	}

}
