package battletanks.game.objects;

import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

import battletanks.graphics.MODELS;



public class Obstacle extends GameObjectImp {

	public Obstacle(){
		super();
		base = new Part(MODELS.SQUARE);

	}
	

	public void update(long dtime) {
		

	}



}
