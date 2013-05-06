package battletanks.game.objects;

import java.util.List;

import javax.vecmath.Vector3f;

import battletanks.game.CollisionResult;
import battletanks.graphics.MODELS;

public class ExplosionCluster extends GameObjectImp {
	
	public ExplosionCluster(){
		Part explosion;
		Vector3f pos, vel;
		for(int i = (int) (Math.random()*10f+5f); i > 0; i--){
			explosion = new Part(MODELS.EXPLOSION);
			pos = new Vector3f((float)Math.random()-.5f, (float)Math.random()-.5f, (float)Math.random()-.5f);
			vel = new Vector3f((float)Math.random()*.5f-.25f, (float)Math.random()*.5f-.25f, (float)Math.random()*.5f-.25f);
			
			explosion.getPhys().setPos(pos);
			explosion.getPhys().setVel(vel);
		}
		
	}
	
	public void update(long dtime){
		
		
		
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
