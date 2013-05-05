package battletanks.game.objects;

public class EnemyTankController implements Controller {

	Tank controlled;
	double count = 0;
	
	public EnemyTankController(Tank c){
		controlled = c;
	}
	
	public void update(long dtime) {
		//controlled.turnLeft();
		count += Math.random() * 0.05d;
		float impy = 1;
		if(count > 0.5f)
			impy = 0;
			
		
		controlled.setLookImpulse((int) (Math.sin(count) * 0f), (int)impy);
			
			controlled.fire();
		

	}

}
