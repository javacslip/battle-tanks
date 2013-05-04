package battletanks.game.objects;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import battletanks.game.Logger;


public class PlayerTank extends PhysObject {

	private float accelConst = 0.05f;
	private float rotRate = 0.5f;
	private boolean isMovingForward = false;
	private boolean isMovingBackward = false;
	private boolean isTurningLeft = false;
	private boolean isTurningRight = false;


	float lookdirx = -90f;
	float lookdiry = 0f;
	
	public PlayerTank(){

		super();
		
		lookdirx = -90f;
		lookdiry = 0f;
		
	}
	
	public void update(long dtime){
		
		if(isMovingForward)
			moveForward();
		if(isMovingBackward)
			moveBackward();
		if(isTurningLeft)
			turnLeft();
		if(isTurningRight)
			turnRight();
		
		if(isTurningLeft == false && isTurningRight == false){
			this.setDirAccel(new Vector2f(0,0));
		}
		
		if(isMovingForward == false && isMovingBackward == false){
			this.setAccel(new Vector3f(0,0,0));
		}
		
		Vector2f dir = new Vector2f(this.getDir());
		super.update(dtime);
		Vector2f dir2 = new Vector2f(this.getDir());
		dir2.sub(dir);
		lookdirx += dir2.x;
		lookdiry += dir2.y;
		Logger.getInstance().debugVal("Look","<" + lookdirx + ">," + "<" + lookdiry + ">");
		
		
	}
	
	public float getLookdirx() {
		return lookdirx;
	}

	public void setLookdirx(float lookdirx) {
		
		this.lookdirx = lookdirx;
		this.lookdirx = this.lookdirx % 360.0f;
	}

	public float getLookdiry() {
		return lookdiry;
	}

	public void setLookdiry(float lookdiry) {
		this.lookdiry = lookdiry;
		this.lookdiry = this.lookdiry % 360.0f;
		if(this.lookdiry > 25.0f)
			this.lookdiry = 25.0f;
		if(this.lookdiry < -25.0f)
			this.lookdiry = -25.0f;
	}
	
	
	public void moveForward(){
		isMovingForward = true;
		if(isMovingForward == true && isMovingBackward == true){
			this.setAccel(0,0,0);
		}else{
		Vector2f rot = this.getDir();
		double radphi = Math.toRadians(rot.y);
		double radtheta = Math.toRadians(rot.x);
		Vector3f accel = new Vector3f();
		
		accel.x = -(float) Math.sin(radtheta) * accelConst;
		accel.z = (float)(Math.cos(radtheta)) * accelConst;
		accel.y = -(float) (Math.sin(radphi)) * accelConst;
		
		this.setAccel(accel);
		}
		
	}
	
	public void moveBackward(){
		isMovingBackward = true;
		if(isMovingForward == true && isMovingBackward == true){
			this.setAccel(0,0,0);
		}else{
		
		Vector2f rot = this.getDir();
		double radphi = Math.toRadians(rot.y);
		double radtheta = Math.toRadians(rot.x);
		Vector3f accel = new Vector3f();
		
		accel.x = (float) Math.sin(radtheta) * accelConst;
		accel.z = -(float)(Math.cos(radtheta)) * accelConst;
		accel.y = +(float) (Math.sin(radphi)) * accelConst;
		
		this.setAccel(accel);
		}
	}
	
	public void turnLeft(){
		isTurningLeft = true;
		if(isTurningRight == true && isTurningLeft == true){
			this.setDirAccel(0,0);
		}else{
		
		Vector2f rot = this.getDirAccel();
		this.setDirAccel(rot.x -rotRate, rot.y);
		
		}
	}
	
	public void turnRight(){
		isTurningRight = true;
		if(isTurningRight == true && isTurningLeft == true){
			this.setDirAccel(0,0);
		}else{
		
		Vector2f rot = this.getDirAccel();
		this.setDirAccel(rot.x +rotRate, rot.y);
	
		}
	}
	
	
	public void stopForwards(){
		isMovingForward = false;
		
	}
	
	public void stopBackwards(){
		isMovingBackward = false;
		
	}
	
	public void stopLeft(){
		isTurningLeft = false;
		
	}
	
	public void stopRight(){
		isTurningRight = false;
		
	}

	public void setLookImpulse(int mousex, int mousey) {
		
		
	}
	


}
