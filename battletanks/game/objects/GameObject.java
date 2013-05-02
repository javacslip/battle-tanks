package battletanks.game.objects;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public interface GameObject {
	
	public void update(long dtime);
	public Vector3f getPos();
	public Vector2f getDir();
	

}
