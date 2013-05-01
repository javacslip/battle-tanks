package battletanks.game;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

public interface GameObject {
	
	public Vector3f getPos();
	public Vector3f getVel();
	public Vector2d getBoundingBox();
	public Vector3f getDirection();
	
	public Vector3f setPos();
	public Vector3f setVel();
	public Vector2d setBoundingBox();
	public Vector3f setDirection();
	
	public void Update(long dtime);

}
