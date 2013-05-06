package battletanks.game.objects;

import javax.vecmath.Vector2f;


public class DemoController implements Controller {

	Tank controlled;
	int count = 0;
	

	public DemoController(Tank c) {
		controlled = c;
		
	}
	
	public void update(long dtime) {
		count ++;
		
		controlled.setLookImpulse((int)(Math.sin((double)count/50d) * 10d), 0);
		if(Math.random() > .5d && count % 160 == 0)
			controlled.fire();

	}

}
