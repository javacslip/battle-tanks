package battletanks.game;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

public interface GameObject {
	
	public Vector3f getPos();
	public Vector3f getVel();
	public Vector2d getBoundingBox();
	public Vector3f getDirection();
	
	public void setPos(float x, float y, float z);
	public void setVel(float x, float y, float z);
	public void setBoundingBox(Vector2d v);
	public void setDirection(float x, float y, float z);
	
	public void Update(long dtime);

}
