package battletanks.game.objects;

import javax.vecmath.Vector2f;

import battletanks.game.Logger;

public class EnemyTankController implements Controller {

	Tank controlled;
	long count = 5000;
	long lastaction = 0;
	
	private Vector2f movetarget; 
	private Vector2f firetarget; 
	private Vector2f pos; 
	
	public EnemyTankController(Tank c){
		controlled = c;
		movetarget = null;
		firetarget = null;
	}
	
	
	
	
	public void update(long dtime) {
		//controlled.turnLeft();
		count++;
		controlled.fire();
		//pos = controlled.getBase().getPos();
		double rand = Math.random();
		if(((double)(count - lastaction)/5000)-0.1d > rand ){
			rand = Math.random();
			if(rand > 0.5d){
				movetarget = new Vector2f((float)Math.random() * 100f,  (float)Math.random() * 100f);
				Logger.getInstance().Log("New Move Action:" + movetarget);
				lastaction = count;
			}
			else{
				firetarget = new Vector2f((float)Math.random() * 100f,  (float)Math.random() * 100f);
				Logger.getInstance().Log("New Fire Action:" + firetarget);
				lastaction = count;
			}
			
		}
		
		
		
		
		
		
		//controlled.setLookImpulse((int) (Math.sin(count) * 0f), (int)impy);
			
			//controlled.fire();
		

	}

}
