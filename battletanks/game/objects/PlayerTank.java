package battletanks.game.objects;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;


public class PlayerTank extends PhysObject {

	private float accelConst = 0.05f;
	private float rotRate = 0.35f;
	
	public PlayerTank(){
		super();
	
		
	}
	
	
	public void moveForward(){
		
		Vector2f rot = this.getDir();
		double radphi = Math.toRadians(rot.y);
		double radtheta = Math.toRadians(rot.x);
		Vector3f accel = new Vector3f();
		
		accel.x = -(float) Math.sin(radtheta) * accelConst;
		accel.z = (float)(Math.cos(radtheta)) * accelConst;
		accel.y = -(float) (Math.sin(radphi)) * accelConst;
		
		this.setAccel(accel);
		
	}
	
	public void stopAccel(){
		
		this.setAccel(new Vector3f(0,0,0));
		
	}
	
	public void stopRot(){
		
		this.setDirAccel(new Vector2f(0,0));
		
	}
	
	public void moveBackward(){
		Vector2f rot = this.getDir();
		double radphi = Math.toRadians(rot.y);
		double radtheta = Math.toRadians(rot.x);
		Vector3f accel = new Vector3f();
		
		accel.x = (float) Math.sin(radtheta) * accelConst;
		accel.z = -(float)(Math.cos(radtheta)) * accelConst;
		accel.y = +(float) (Math.sin(radphi)) * accelConst;
		
		this.setAccel(accel);
	}
	
	public void turnLeft(){
		Vector2f rot = this.getDirAccel();
		this.setDirAccel(rot.x -rotRate, rot.y);
	}
	
	public void turnRight(){
		Vector2f rot = this.getDirAccel();
		this.setDirAccel(rot.x +rotRate, rot.y);
	}
	


}
