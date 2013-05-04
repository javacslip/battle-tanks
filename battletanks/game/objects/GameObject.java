package battletanks.game.objects;

import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public interface GameObject {
	
	public void update(long dtime);
	public List<Part> getParts();
	public Controller getController();
	public Part getBase();
	void setController(Controller c);
	

}
