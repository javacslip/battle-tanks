package battletanks.game.objects;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import battletanks.game.CollisionResult;
import battletanks.game.Gamestate;
import battletanks.game.Logger;
import battletanks.graphics.MODELS;

public class Tank extends GameObjectImp {

	private float accelConst = 0.02f;
	private float rotRate = 1f;
	private float turretRate = 7;
	private int fireRate = 35;
	private int lastFired = 35;
	private int health = 3;

	private Part turret;

	public Tank() {

		super();
		

		base = new Part(MODELS.TANKBASE);
		turret = new Part(MODELS.TANKTURRET);
		turret.rotJoin(base);
		turret.posJoin(base);
		parts.add(base);
		parts.add(turret);

		base.getPhys().setMaxvel(.5f);
		turret.getPhys().setPos(new Vector3f(0.00f, 0.173f, 0f));
		turret.setCenterrot(new Vector3f(-0.1f, 0, 0f));
		base.getPhys().setDragconst(.005f);
		base.getPhys().setRadius(.65f);
	}

	public void update(long dtime) {
		super.update(dtime);
		lastFired++;
		Vector2f lookdir = turret.getPhys().getDir();
		if (lookdir.y > 25.0f)
			lookdir.y = 25.0f;
		if (lookdir.y < -25.0f)
			lookdir.y = -25.0f;

		if (lookdir.x > 45.0f)
			lookdir.x = 45.0f;
		if (lookdir.x < -45.0f)
			lookdir.x = -45.0f;

		turret.getPhys().setDir(lookdir);

	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int h){
		health = h;
	}
	
	public float getReloadPer(){
		return Math.min((float)lastFired / (float)fireRate, 1);
	}
	
	public boolean fire(){
		
		if(lastFired > fireRate){
			Bullet b = new Bullet();
			Gamestate.getInstance().addObject(b);
			b.fire(new Vector3f(this.turret.getPos()), new Vector2f(turret.getDir()));
			
			
			lastFired = 0;
			return true;
		}
		return false;
	}

	public Vector2f getLookDir() {
		return turret.getDir();
	}

	public Part getTurret() {
		return turret;

	}

	public void moveForward() {

		Vector2f rot = base.getDir();
		double radphi = Math.toRadians(rot.y);
		double radtheta = Math.toRadians(rot.x);
		Vector3f accel = new Vector3f();

		accel.x = +(float) Math.sin(radtheta) * accelConst;
		accel.y = +(float) (Math.sin(radphi)) * accelConst;
		accel.z = -(float) (Math.cos(radtheta)) * accelConst;

		base.getPhys().setAccel(accel);

	}

	public void moveBackward() {

		Vector2f rot = base.getDir();
		double radphi = Math.toRadians(rot.y);
		double radtheta = Math.toRadians(rot.x);
		Vector3f accel = new Vector3f();

		accel.x = -(float) Math.sin(radtheta) * accelConst;
		accel.z = +(float) (Math.cos(radtheta)) * accelConst;
		accel.y = -(float) (Math.sin(radphi)) * accelConst;

		base.getPhys().setAccel(accel);

	}

	public void turnLeft() {

		Vector2f rot = base.getPhys().getDirAccel();
		base.getPhys().setDirAccel(rot.x - rotRate, rot.y);

	}

	public void turnRight() {

		Vector2f rot = base.getPhys().getDirAccel();
		base.getPhys().setDirAccel(rot.x + rotRate, rot.y);

	}

	public void setLookImpulse(int x, int y) {

		turret.getPhys().setDirAccel(-((float) x) / turretRate,
				-((float) y) / turretRate);
	}

	@Override
	public void doCollision(CollisionResult c) {
		Vector3f tank = new Vector3f(this.base.getPos());
		tank.sub(c.overlap);
		tank.y = 0;
		this.base.getPhys().setPos(tank);
		
	}

}
