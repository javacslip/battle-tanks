package battletanks.game.objects;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

import battletanks.game.GameObject;


public class Obstacle implements GameObject {
	
	private Vector3f position;
	private Vector3f direction;

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
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}

	@Override
	public void setVel(float x, float y, float z) {

	}

	@Override
	public void setBoundingBox(Vector2d v) {
		
	}

	@Override
	public void setDirection(float x, float y, float z) {
		this.direction.x = x;
		this.direction.y = y;
		this.direction.z = z;
	}

}
