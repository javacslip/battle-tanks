package battletanks.game;

import javax.vecmath.Vector3f;

import battletanks.game.objects.GameObject;

public class CollisionResult {
	
	public Vector3f overlap;
	public GameObject collidedWith;
	
	public GameObject getCollided(){
		return collidedWith;
		
	}
	
	public Vector3f getOverlapVector(){
		return overlap;
		
	}
	
	public void setCollided(GameObject o ){
		collidedWith = o;
	}
	
	public void setVector(Vector3f v){
		overlap = v;
	}

}
