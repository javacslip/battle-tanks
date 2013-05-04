package battletanks.game.objects;

public class EnemyTankController implements Controller {

	Tank controlled;
	double count = Math.random()*5.0d;
	
	public EnemyTankController(Tank c){
		controlled = c;
	}
	
	public void update(long dtime) {
		//controlled.turnLeft();
		count += Math.random() * 0.05d;
		controlled.setLookImpulse((int) (Math.sin(count) * 5.0f), 0);

	}

}
