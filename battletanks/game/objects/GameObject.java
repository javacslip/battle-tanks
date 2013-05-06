package battletanks.game.objects;

import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import battletanks.game.CollisionResult;

public interface GameObject {
	
	public void update(long dtime);
	public List<Part> getParts();
	public Controller getController();
	public Part getBase();
	void setController(Controller c);
	public void doCollision(CollisionResult c);
	public int getTeam();
	public void setTeam(int i);
	
	

}
