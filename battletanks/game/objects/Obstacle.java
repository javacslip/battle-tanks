package battletanks.game.objects;

import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3f;

import battletanks.game.CollisionResult;
import battletanks.graphics.MODELS;



public class Obstacle extends GameObjectImp {
	public float getSize() {
		return size;
	}


	public void setSize(float size) {
		this.size = size;
	}


	float size;

	public Obstacle(float size){
		super();
		this.size = size;
		base = new Part(MODELS.SQUARE);
		base.getPhys().setRadius(.8f*size);
		
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
