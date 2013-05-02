package battletanks.game.objects;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import battletanks.game.Logger;

public abstract class PhysObject implements GameObject {

	private Vector3f accel;
	private Vector3f velocity;
	private Vector3f position;
	private Vector2f bbox;
	private Vector2f rot;
	private Vector2f rotspeed;
	private Vector2f rotaccel;


	private float dragconst;
	private float rotdragconst;
	private float maxrotaccel;
	private float maxrotvel;
	private float maxvel;
	private float maxaccel;

	public PhysObject() {
		position = new Vector3f(0, 0, 0);
		velocity = new Vector3f(0, 0, 0);
		accel = new Vector3f(0, 0, 0);
		bbox = new Vector2f(1, 1);

		rot = new Vector2f(0, 0);
		rotspeed = new Vector2f(0, 0);
		rotaccel = new Vector2f(0, 0);

		maxrotaccel = 1f;
		maxrotvel = 2.5f;
		maxvel = 0.2f;
		maxaccel = 0.1f;
		dragconst = 0.01f;
		rotdragconst = 0.01f;
		

	}
	
	public float getDragconst() {
		return dragconst;
	}

	public void setDragconst(float dragconst) {
		this.dragconst = dragconst;
	}

	public float getRotdragconst() {
		return rotdragconst;
	}

	public void setRotdragconst(float rotdragconst) {
		this.rotdragconst = rotdragconst;
	}

	private Vector3f clamp(Vector3f v, float max){
		float len = v.length();
		if(len > max){
			Vector3f r = new Vector3f(v);
			r.normalize();
			r.scale(max);
			return r;
		}
		return v;
		
	}
	
	private Vector2f clamp(Vector2f v, float max){
		float len = v.length();
		if(len > max){
			Vector2f r = new Vector2f(v);
			r.normalize();
			r.scale(max);
			return r;
		}
		return v;
		
	}

	public void update(long dtime) {
		
		Logger.getInstance().debugVal("Pos", position.toString());
		Logger.getInstance().debugVal("Vel", velocity.toString());
		Logger.getInstance().debugVal("Accel", accel.toString());
		Logger.getInstance().debugVal("rot", rot.toString());
		Logger.getInstance().debugVal("rotspeed", rotspeed.toString());
		Logger.getInstance().debugVal("rotaccel", rotaccel.toString());
		
		accel = clamp(accel, maxaccel);
		velocity = clamp(velocity, maxvel);
		rotspeed = clamp(rotspeed, maxrotvel);
		rotaccel = clamp(rotaccel, maxrotaccel);
		
		
		Vector3f tmpa = new Vector3f(accel);
		tmpa.scale(dtime);
		velocity.add(accel);
		
		Vector3f tmpdrag = new Vector3f(velocity);
		tmpdrag.scale(dtime);
		tmpdrag.scale(dragconst);
		velocity.sub(tmpdrag);
		
		
		Vector3f tmpv = new Vector3f(velocity);
		tmpv.scale(dtime);
		position.add(velocity);
		
		
		Vector2f tmprs = new Vector2f(rotaccel);
		tmprs.scale(dtime);
		rotspeed.add(rotaccel);
		
		
		Vector2f tmprotdrag = new Vector2f(rotspeed);
		tmprotdrag.scale(dtime);
		tmprotdrag.scale(rotdragconst);
		rotspeed.sub(tmprotdrag);
		

		
		Vector2f tmpr = new Vector2f(rotspeed);
		tmpr.scale(dtime);
		rot.add(rotspeed);
		

	}
	
	
	public float getMaxrotvel() {
		return maxrotvel;
	}
	
	public float getMaxrotaccel() {
		return maxrotaccel;
	}

	public void setMaxrotvel(float maxrot) {
		this.maxrotvel = maxrot;
	}
	
	public void setMaxrotaccel(float maxrot) {
		this.maxrotaccel = maxrot;
	}


	public float getMaxvel() {
		return maxvel;
	}

	public void setMaxvel(float maxvel) {
		this.maxvel = maxvel;
	}

	public float getMaxaccel() {
		return maxaccel;
	}

	public void setMaxaccel(float maxaccel) {
		this.maxaccel = maxaccel;
	}

	public Vector3f getPos() {
		return this.position;
	}

	public Vector3f getVel() {
		return this.velocity;
	}

	public Vector3f getAccel() {
		return this.accel;
	}

	public Vector2f getBoundingBox() {
		return bbox;
	}

	public void setPos(float x, float y, float z) {
		position = new Vector3f(x, y, z);

	}

	public void setPos(Vector3f p) {
		position = p;
	}

	public void setVel(float x, float y, float z) {
		velocity = new Vector3f(x, y, z);

	}

	public void setVel(Vector3f v) {
		velocity = v;
	}

	public void setAccel(float x, float y, float z) {
		accel = new Vector3f(x, y, z);

	}

	public void setAccel(Vector3f v) {
		accel = v;
	}

	public void setBoundingBox(Vector2f v) {
		this.bbox = v;

	}

	// dir
	public Vector2f getDir() {
		return this.rot;
	}

	public void setDir(float x, float y) {
		rot = new Vector2f(x, y);
	}

	public void setDir(Vector2f rot) {

		this.rot = rot;
	}

	// dirspeed
	public Vector2f getDirSpeed() {
		return this.rotspeed;
	}

	public void setDirSpeed(float x, float y) {
		this.rotspeed = new Vector2f(x, y);
	}

	public void setDirSpeed(Vector2f rot) {
		this.rotspeed = rot;
	}

	// diraccel
	public Vector2f getDirAccel() {
		return this.rotaccel;
	}

	public void setDirAccel(float x, float y) {
		this.rotaccel = new Vector2f(x, y);
	}

	public void setDirAccel(Vector2f rot) {
		this.rotaccel = rot;
	}

}
