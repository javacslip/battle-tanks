package battletanks.game.objects;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import battletanks.game.Gamestate;
import battletanks.game.Logger;

public class EnemyTankController implements Controller {

	Tank controlled;
	long count = 5000;
	long lastaction = 0;
	
	private Vector2f movetarget; 
	private Vector2f firetarget; 
	private Vector2f pos; 
	private Vector2f playerpos; 
	private Vector2f dir; 
	
	public EnemyTankController(Tank c){
		controlled = c;
		movetarget = null;
		firetarget = null;
	}
	
	
	
	
	public void update(long dtime) {
		//controlled.turnLeft();
		
		count++;
		pos = new Vector2f(controlled.getBase().getPos().x, controlled.getBase().getPos().z);
		playerpos = new Vector2f(Gamestate.getInstance().getPlayer().getBase().getPos().x, Gamestate.getInstance().getPlayer().getBase().getPos().z);
		float dir = controlled.getBase().getPhys().getDir().x;
		double rand = Math.random();
		if(((double)(count - lastaction)/1000)-0.1d > rand ){
			rand = Math.random();
			if(rand > 0.0d){
				//movetarget = new Vector2f((float)Math.random() * 100f,  (float)Math.random() * 100f);
				movetarget = new Vector2f(20,20);
				//Logger.getInstance().Log("New Move Action:" + movetarget);
				lastaction = count;
			}
			else{
				firetarget = new Vector2f((float)Math.random() * 100f,  (float)Math.random() * 100f);
				Logger.getInstance().Log("New Fire Action:" + firetarget);
				lastaction = count;
				movetarget = null;
			}
			
		}
		
		if(movetarget != null){
			Vector2f tmp = new Vector2f(pos);
			tmp.sub(movetarget);
			float dist = tmp.length();
			
			 
			float angle = (float) Math.toDegrees(Math.atan2(pos.y-movetarget.y, pos.x-movetarget.x));
			
			
			//Logger.getInstance().Log("dir:" + (angle - dir));
			if( angle-dir < -2.5f ){
				controlled.turnLeft();
				//Logger.getInstance().Log("left");
				controlled.moveForward();
			}
			else if( angle-dir > 2.5f){
				controlled.turnRight();
				//Logger.getInstance().Log("right");
				controlled.moveForward();
			}
			else if (dist > 2){
				controlled.moveForward();
			}
			else{
				//movetarget = new Vector2f((float)Math.random() * 100f,  (float)Math.random() * 100f);
				Logger.getInstance().Log("arrived:" + movetarget);
				lastaction = count;
			}
			
			
		}
		
		
		
		
		
		
		//controlled.setLookImpulse((int) (Math.sin(count) * 0f), (int)impy);
			
			//controlled.fire();
		

	}

}
