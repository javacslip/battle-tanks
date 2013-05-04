package battletanks.game.objects;

import java.util.List;

import javax.vecmath.Vector2f;

import battletanks.game.GameInput;
import battletanks.game.Gamestate;
import battletanks.game.INPUT_TYPE;
import battletanks.game.Logger;

public class PlayerTankController implements Controller {
	
	private boolean isForward = false;
	private boolean isBackward = false;
	private boolean isLeft = false;
	private boolean isRight = false;
	
	Tank controlled;
	public PlayerTankController(Tank c){
		controlled = c;
	}

	@Override
	public void update(long dtime) {
		
		List<GameInput> playerInput = Gamestate.getInstance().getPlayerInput();
		int mousex = 0;
		int mousey = 0;
		
		INPUT_TYPE input;
		for (GameInput o : playerInput) {
			if(o.getInputType() != INPUT_TYPE.VIEWX &&  o.getInputType() != INPUT_TYPE.VIEWY){
			Logger.getInstance().debugVal("LastKey" , o.getInputType().name());
			Logger.getInstance().Log("LastKey:" + o.getInputType().name());
			}
			input = o.getInputType();
			switch (input) {
			
			
			case FORWARD_PRESSED:
				isForward = true;
				break;
			case BACKWARD_PRESSED:
				isBackward = true;
				break;
			case LEFT_PRESSED:
				isLeft = true;
				break;
			case RIGHT_PRESSED:
				isRight = true;
				break;
			case FIRE_PRESSED:
				break;
			case FORWARD_RELEASED:
				isForward = false;
				break;
			case BACKWARD_RELEASED:
				isBackward = false;
				break;
			case LEFT_RELEASED:
				isLeft = false;
				break;
			case RIGHT_RELEASED:
				isRight = false;
				break;
			case FIRE_RELEASED:
				//player stop
				break;
			case VIEWX:
				mousex = (int) o.getValue();
				break;
			case VIEWY:
				mousey = (int) o.getValue();
				break;
			}
		}
		playerInput.clear();
		
		
		controlled.setLookImpulse(mousex, mousey);
		
		if(isForward == true && isBackward == false){
			controlled.moveForward();
			
		}
		if(isBackward == true && isForward == false){
			controlled.moveBackward();
			
		}
		if(isLeft == true && isRight == false){
			controlled.turnLeft();
			
		}
		if(isRight == true && isLeft == false){
			controlled.turnRight();
			
		}
		
		Logger.getInstance().debugVal("Pos", controlled.getTurret().getPos().toString());
		Logger.getInstance().debugVal("Vel", controlled.getTurret().getPhys().getVel().toString());
		Logger.getInstance().debugVal("Accel", controlled.getTurret().getPhys().getAccel().toString());
		Logger.getInstance().debugVal("rot", controlled.getTurret().getDir().toString());
		Logger.getInstance().debugVal("rotspeed", controlled.getTurret().getPhys().getDirSpeed().toString());
		Logger.getInstance().debugVal("rotaccel", controlled.getTurret().getPhys().getDirAccel().toString());
		Logger.getInstance().debugVal("LookDir", controlled.getTurret().toString());
		
		

	}

}
