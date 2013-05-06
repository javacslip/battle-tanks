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
	private float dir;

	private Vector2f tpos;
	private float tdir;

	public EnemyTankController(Tank c) {
		controlled = c;
		movetarget = null;
		firetarget = null;
	}

	public void update(long dtime) {
		// controlled.turnLeft();

		count++;
		pos = new Vector2f(controlled.getBase().getPos().x, controlled
				.getBase().getPos().z);

		tpos = new Vector2f(controlled.getTurret().getPos().x, controlled
				.getTurret().getPos().z);

		playerpos = new Vector2f(Gamestate.getInstance().getPlayer().getBase()
				.getPos().x, Gamestate.getInstance().getPlayer().getBase()
				.getPos().z);
		dir = controlled.getBase().getPhys().getDir().x;

		tdir = dir + controlled.getTurret().getPhys().getDir().x;

		double rand = Math.random();

		if (movetarget == null) {
		
			if (rand > .5d)
				movetarget = new Vector2f((float) Math.random() * 40f - 20f,
						(float) Math.random() * 40f - 20f);

			else {
				movetarget = new Vector2f(playerpos);
			}
			Logger.getInstance().Log("New Move Action:" + movetarget);

		}
		
		if (((double) (count - lastaction) / 3000) > rand) {
			firetarget = new Vector2f(playerpos.x + (float) Math.random() * 1f
					- .5f, playerpos.y + (float) Math.random() * 1f - .5f);
			Logger.getInstance().Log("New Fire Action:" + firetarget);
			lastaction = count;
			
		}

		
		
		if (firetarget != null) {

			firetarget = new Vector2f(playerpos.x + (float) Math.random() * 1f
					- .5f, playerpos.y + (float) Math.random() * 1f - .5f);

			Vector2f tmp = new Vector2f(pos);
			tmp.sub(firetarget);
			float dist = tmp.length();

			if (dist > 25) {
				firetarget = null;
			} else {

				boolean t1 = turretfacepos(firetarget, 2.0f);
				boolean t2 = facepos(firetarget, 35f);

				if (dist > 5) {
					controlled.moveForward();
				}

				if (t1 && t2) {
					controlled.fire();
					firetarget = null;

				}
			}

		}

		if (movetarget != null && firetarget == null) {

			Vector2f tmp = new Vector2f(pos);
			tmp.sub(movetarget);
			float dist = tmp.length();

			facepos(movetarget, 3.5f);

			if (dist > 3f) {
				controlled.moveForward();
			} else {
				movetarget = null;
				Logger.getInstance().Log("Arrived:");
			}

		}



	}

	private boolean facepos(Vector2f target, float tolerance) {
		float angle = (float) Math.toDegrees(Math.atan2(pos.y - target.y, pos.x
				- target.x));
		angle -= 90;

		Logger.getInstance().debugVal("AI_T_ANGLE", Float.toString(angle));
		Logger.getInstance().debugVal("AI_ANGLE", Float.toString(tdir));

		if (angle - dir < -tolerance) {
			controlled.turnLeft();

			return false;
		} else if (angle - dir > tolerance) {
			controlled.turnRight();

			return false;
		}
		return true;

	}

	private boolean turretfacepos(Vector2f target, float tolerance) {
		float angle = (float) Math.toDegrees(Math.atan2(tpos.y - target.y,
				tpos.x - target.x));
		angle -= 90;

		if (angle - tdir < -tolerance) {
			controlled.setLookImpulse((int) 2f, 0);

			return false;
		} else if (angle - tdir > tolerance) {
			controlled.setLookImpulse((int) -2f, 0);

			return false;
		}
		return true;

	}

}
