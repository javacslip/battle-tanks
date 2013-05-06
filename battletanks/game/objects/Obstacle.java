package battletanks.game.objects;

import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

import battletanks.game.CollisionResult;
import battletanks.graphics.MODELS;



public class Obstacle extends GameObjectImp {

	public Obstacle(){
		super();
		base = new Part(MODELS.SQUARE);
		base.getPhys().setRadius(.8f);

	}
	

	public void update(long dtime) {
		

	}


	@Override
	public void doCollision(CollisionResult c) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getTeam() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setTeam(int i) {
		// TODO Auto-generated method stub
		
	}



}
