package battletanks.game.objects;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

import battletanks.game.GameObject;

public class EnemyTank implements GameObject {

	private Vector3f velocity;
	private Vector3f position;
	private Vector3f direction;
	
	public EnemyTank(){
		
	}
	
	@Override
	public Vector3f getPos() {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

	@Override
	public void Update(long dtime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPos(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVel(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBoundingBox(Vector2d v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirection(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

}
