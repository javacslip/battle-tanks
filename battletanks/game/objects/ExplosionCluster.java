package battletanks.game.objects;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

import battletanks.game.CollisionResult;
import battletanks.game.Gamestate;
import battletanks.graphics.MODELS;

public class ExplosionCluster extends GameObjectImp {
	int count = 0;
	
	public ExplosionCluster(Vector3f pos, Vector3f vel, int size){
		Part explosion = new Part(MODELS.EXPLOSION);
		explosion.getPhys().setPos(pos);
		explosion.getPhys().setVel(vel);
		base = explosion;
		

		count = 0;
		float scalar = size/10f; 
		for(int i = (int) (Math.random()*size+5f); i > 0; i--){
			explosion = new ExplosionPoint(MODELS.EXPLOSION, .15f * scalar , .4f * scalar,.3f * scalar, scalar);
			count++;
			explosion.posJoin(base);
			this.parts.add(explosion);
			
			
		}
		this.base.update(10);
		
	}
	
	public ExplosionCluster(Vector3f pos){
		this(pos,new Vector3f(0,0,0), 10);
		
		
	}
	
	public void update(long dtime){
		
		base.update(dtime);
		int dcount = 0;
		for(Part p : this.parts){
			if(p.getScale() <= 0.0f){
				dcount++;
			}
		}
		if(count <= dcount){
			Gamestate.getInstance().removeObject(this);
		}
		super.update(dtime);
		
	}
	

	@Override
	public void doCollision(CollisionResult c) {
		
		
	}

	@Override
	public int getTeam() {
		
		return 0;
	}

	@Override
	public void setTeam(int i) {
		
		
	}

	

}
