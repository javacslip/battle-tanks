package battletanks.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.vecmath.Vector3f;


import battletanks.game.objects.EnemyTankController;
import battletanks.game.objects.ExplosionCluster;
import battletanks.game.objects.GameObject;
import battletanks.game.objects.Obstacle;
import battletanks.game.objects.PlayerTankController;
import battletanks.game.objects.Tank;


public class Gamestate {
	
	private List<GameObject> obstacles;
	private List<GameObject> removelist;
	private List<GameObject> tanks;
	private List<GameObject> bullets;
	
	private List<GameObject> explosions;
	
	private GameObject player;
	
	private static int startTankCount = 1;
	private int tankCount, wave;
	
	private List<GameInput> playerInput;

	
	long lasttime;
	
	private Gamestate(int wave){
		this.wave = wave;
		removelist = new ArrayList<GameObject>();
		lasttime = System.currentTimeMillis();
		obstacles = new ArrayList<GameObject>();
		tanks = new ArrayList <GameObject>();
		explosions = new ArrayList <GameObject>();
		bullets = new ArrayList <GameObject>();
		player = new Tank();
		player.setController(new PlayerTankController((Tank) player));
		playerInput = new ArrayList<GameInput>();
		tankCount = 1; 
		for (int i = 1; i < wave; i++)
			tankCount = tankCount * 2;
		
		setUpMap();
	}
	

	
	
	public void addObject(GameObject o){
		if(o instanceof Obstacle){
			obstacles.add(o);
		}
		else if(o instanceof Tank){
			tanks.add(o);
		}
		
		else if(o instanceof ExplosionCluster){
			explosions.add(o);
		}
		else{
			bullets.add(o);
		}
		
	}
	
	public void removeObject(GameObject o){
		removelist.add(o);

	}
	
	public List<GameObject> getExplosions(){
		return explosions;
	}
	
	public GameObject getPlayer(){
		return player;
	}
	
	public List<GameObject> getObstacles(){
		return obstacles;
		
	}
	
	public List<GameObject> getTanks(){
		return tanks;
		
	}
	
	public List<GameObject> getBullets(){
		return bullets;
		
	}
	
	public CollisionResult doCollision(GameObject a, GameObject b){
		return null;
		
	}
	
	public void AddInput(GameInput o){
		playerInput.add(o);
	}

	
	public List<GameInput> getPlayerInput(){
		return playerInput;

	}
	
	public void UpdateState(long dtime) {
		
		
		
		int i;
		for (GameObject o : removelist) {
			if (o instanceof Obstacle) {
				i = obstacles.indexOf(o);
				if (i != -1)
					obstacles.remove(i);
			} else if (o instanceof Tank) {
				i = tanks.indexOf(o);
				if (i != -1)
					tanks.remove(i);
			}
			else if (o instanceof ExplosionCluster) {
				i = explosions.indexOf(o);
				if (i != -1)
					explosions.remove(i);
			}
			
			else {
				i = bullets.indexOf(o);
				if (i != -1)
					bullets.remove(i);
			}
		}
		
		
		long deltaTime = dtime - lasttime;
		Logger.getInstance().debugVal("Dtime" , Float.toString(deltaTime));
		lasttime = dtime;
		if (deltaTime > 500){
			deltaTime = 500;
		}
		

		
		for(GameObject go : tanks){
			go.update(deltaTime);
		}
		
		for(GameObject go : bullets){
			go.update(deltaTime);
		}
		
		for(GameObject go : explosions){
			go.update(deltaTime);
		}
		
		
		player.update(deltaTime);
		
		float x, y, z, d;
		CollisionResult cr;
		Vector3f overlap;
		float scalar;
		
		List<CollisionResult> tankObjCol = new ArrayList<CollisionResult>();
		for (GameObject go : tanks) {
			for (GameObject ob : obstacles) {
				x = ob.getBase().getPhys().getPos().x- go.getBase().getPhys().getPos().x;
				y = ob.getBase().getPhys().getPos().y- go.getBase().getPhys().getPos().y;
				z = ob.getBase().getPhys().getPos().z- go.getBase().getPhys().getPos().z;
				d = (float) Math.sqrt(x * x + y * y + z * z);
				if (d < (ob.getBase().getPhys().getRadius() + go.getBase()
						.getPhys().getRadius())) {
					cr = new CollisionResult();
					cr.setCollided(ob);
					overlap = new Vector3f(ob.getBase().getPhys().getPos());
					overlap.sub(go.getBase().getPhys().getPos());
					overlap.normalize();
					scalar = ob.getBase().getPhys().getRadius()
							+ go.getBase().getPhys().getRadius() - d;
					overlap.scale(scalar);
					cr.setVector(overlap);
					tankObjCol.add(cr);
				}
			}
			for (GameObject ob : tanks) {
				x = ob.getBase().getPhys().getPos().x
						- go.getBase().getPhys().getPos().x;
				y = ob.getBase().getPhys().getPos().y
						- go.getBase().getPhys().getPos().y;
				z = ob.getBase().getPhys().getPos().z
						- go.getBase().getPhys().getPos().z;
				d = (float) Math.sqrt(x * x + y * y + z * z);
				if (d < (ob.getBase().getPhys().getRadius() + go.getBase()
						.getPhys().getRadius())) {
					cr = new CollisionResult();
					cr.setCollided(ob);
					overlap = new Vector3f(ob.getBase().getPhys().getPos());
					overlap.sub(go.getBase().getPhys().getPos());
					overlap.normalize();
					scalar = ob.getBase().getPhys().getRadius()
							+ go.getBase().getPhys().getRadius() - d;
					overlap.scale(scalar);
					cr.setVector(overlap);
					tankObjCol.add(cr);
				}
			}
			CollisionResult c = new CollisionResult();
			Vector3f max = new Vector3f(0, 0, 0);
			for (CollisionResult r : tankObjCol) {
				if (r.getOverlapVector().x > max.x) {
					max.x = r.getOverlapVector().x;
					c.collidedWith = r.getCollided();
				}
				if (r.getOverlapVector().y > max.y) {
					max.y = r.getOverlapVector().y;
				}
			}
			c.setVector(max);
			if (!tankObjCol.isEmpty())
				go.doCollision(c);
			tankObjCol.clear();
		}
		
		
		List<CollisionResult> playerObjCol = new ArrayList<CollisionResult>();
		// player collision detection
		for(GameObject ob : tanks){
			x = ob.getBase().getPhys().getPos().x - player.getBase().getPhys().getPos().x;
			y = ob.getBase().getPhys().getPos().y - player.getBase().getPhys().getPos().y;
			z = ob.getBase().getPhys().getPos().z - player.getBase().getPhys().getPos().z;
			d = (float) Math.sqrt(x*x + y*y + z*z);
			if(d < (ob.getBase().getPhys().getRadius() + player.getBase().getPhys().getRadius())){
				cr = new CollisionResult();
				cr.setCollided(ob);
				overlap = new Vector3f(ob.getBase().getPhys().getPos());
				overlap.sub(player.getBase().getPhys().getPos());
				overlap.normalize();
				scalar = ob.getBase().getPhys().getRadius() + player.getBase().getPhys().getRadius() - d;
				overlap.scale(scalar);
				cr.setVector(overlap);
				playerObjCol.add(cr);
			}
		}
		for(GameObject ob : obstacles){
			x = ob.getBase().getPhys().getPos().x - player.getBase().getPhys().getPos().x;
			y = ob.getBase().getPhys().getPos().y - player.getBase().getPhys().getPos().y;
			z = ob.getBase().getPhys().getPos().z - player.getBase().getPhys().getPos().z;
			d = (float) Math.sqrt(x*x + y*y + z*z);
			if(d < (ob.getBase().getPhys().getRadius() + player.getBase().getPhys().getRadius())){
				cr = new CollisionResult();
				cr.setCollided(ob);
				overlap = new Vector3f(ob.getBase().getPhys().getPos());
				overlap.sub(player.getBase().getPhys().getPos());
				overlap.normalize();
				scalar = ob.getBase().getPhys().getRadius() + player.getBase().getPhys().getRadius() - d;
				overlap.scale(scalar);
				cr.setVector(overlap);
				playerObjCol.add(cr);	
			}
		}
		CollisionResult c = new CollisionResult();
		Vector3f max = new Vector3f(0, 0, 0);
		for(CollisionResult r : playerObjCol){
			if(Math.abs(r.getOverlapVector().x) > Math.abs(max.x)){
				max.x = r.getOverlapVector().x;
				c.collidedWith = r.getCollided();
			}
			if(Math.abs(r.getOverlapVector().y) > Math.abs(max.y)){
				max.y = r.getOverlapVector().y;
			}
		}
		c.setVector(max);
		if(!playerObjCol.isEmpty())
		player.doCollision(c);
		playerObjCol.clear();
	
		cr = new CollisionResult();
		for(GameObject go : bullets){
			x = go.getBase().getPhys().getPos().x - player.getBase().getPhys().getPos().x;
			y = go.getBase().getPhys().getPos().y - player.getBase().getPhys().getPos().y;
			z = go.getBase().getPhys().getPos().z - player.getBase().getPhys().getPos().z;
			d = (float) Math.sqrt(x*x + y*y + z*z);
			if(d < (go.getBase().getPhys().getRadius() + player.getBase().getPhys().getRadius())){
				cr.setCollided(go);
				player.doCollision(cr);
				cr.setCollided(player);
				go.doCollision(cr);
				continue;
			}
			for(GameObject et : tanks){
				x = go.getBase().getPhys().getPos().x - et.getBase().getPhys().getPos().x;
				y = go.getBase().getPhys().getPos().y - et.getBase().getPhys().getPos().y;
				z = go.getBase().getPhys().getPos().z - et.getBase().getPhys().getPos().z;
				d = (float) Math.sqrt(x*x + y*y + z*z);
				if(d < (go.getBase().getPhys().getRadius() + et.getBase().getPhys().getRadius())){
					cr.setCollided(go);
					et.doCollision(cr);
					cr.setCollided(et);
					go.doCollision(cr);
					break;
				}
			}
			for(GameObject ob : obstacles){
				x = go.getBase().getPhys().getPos().x - ob.getBase().getPhys().getPos().x;
				y = go.getBase().getPhys().getPos().y - ob.getBase().getPhys().getPos().y;
				z = go.getBase().getPhys().getPos().z - ob.getBase().getPhys().getPos().z;
				d = (float) Math.sqrt(x*x + y*y + z*z);
				if(d < (go.getBase().getPhys().getRadius() + ob.getBase().getPhys().getRadius())){
					cr.setCollided(go);
					ob.doCollision(cr);
					cr.setCollided(ob);
					go.doCollision(cr);
					break;
				}
			}
		}
		
		if(tanks.size() == 0){
			nextWave();
		}

	}
	
	

	public void setUpMap(){
		
		Obstacle ob;
		Tank et;
		Random rf = new Random(System.nanoTime());
		// obstacles
		Vector3f tmp, pos;
		boolean foundspot;
		obstacles.clear();
		for(int i = 0; i < 52; i++){
			ob = new Obstacle();
			do{
				foundspot = true;
				pos = new Vector3f(rf.nextFloat() * 50f - 25f, .35f, rf.nextFloat() * 50f - 25f);
				for(GameObject o : obstacles){
					tmp = new Vector3f(o.getBase().getPos());
					tmp.sub(pos);
					if(tmp.length() < 5){
						foundspot = false;
					}
				}
			}
			while(!foundspot);
			
			ob.getBase().getPhys().setPos(pos);
			ob.getBase().getPhys().setDir(0, rf.nextFloat() * 180f);
			ob.setTeam(3);
			addObject(ob);
		}
		// enemy tanks
		for(int i = 0; i < tankCount; i++){
			et = new Tank();
			et.setController(new EnemyTankController(et));
			et.getBase().getPhys().setPos(rf.nextFloat() * 20 - 25, 0f, rf.nextFloat() * 20 - 15);
			et.getBase().getPhys().setDir( rf.nextFloat() * 180,0);
			et.setTeam(2);
			addObject(et);
		}
		// player
		player.getBase().getPhys().setPos(0, 0, 0);
		player.getBase().getPhys().setDir(0, 0);
		player.setTeam(1);
		((Tank)player).setHealth(3);
		
	}
	
	public void nextWave(){
		Gamestate.instance = new Gamestate(wave + 1);
			
	
	}
	
	public void reset(){

		Gamestate.instance = new Gamestate(startTankCount);
	}
	
	private static Gamestate instance = null;
	
	public static Gamestate getInstance(){
		if (Gamestate.instance == null)
			Gamestate.instance = new Gamestate(startTankCount);
		
		return Gamestate.instance;
	}
	

}

