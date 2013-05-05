package battletanks.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;


import battletanks.game.objects.EnemyTankController;
import battletanks.game.objects.GameObject;
import battletanks.game.objects.Obstacle;
import battletanks.game.objects.PlayerTankController;
import battletanks.game.objects.Tank;


public class Gamestate {
	
	private List<GameObject> obstacles;
	private List<GameObject> removelist;
	private List<GameObject> tanks;
	private List<GameObject> bullets;
	
	private GameObject player;
	
	private List<GameInput> playerInput;

	
	long lasttime;
	
	private Gamestate(){
		removelist = new ArrayList<GameObject>();
		lasttime = System.currentTimeMillis();
		obstacles = new ArrayList<GameObject>();
		tanks = new ArrayList <GameObject>();
		bullets = new ArrayList <GameObject>();
		player = new Tank();
		player.setController(new PlayerTankController((Tank) player));
		playerInput = new ArrayList<GameInput>();
		setUpMap();
	}
	
	public void addObject(GameObject o){
		if(o instanceof Obstacle){
			obstacles.add(o);
		}
		else if(o instanceof Tank){
			tanks.add(o);
		}
		else{
			bullets.add(o);
		}
		
	}
	
	public void removeObject(GameObject o){
		removelist.add(o);

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
		for(GameObject o : removelist){
		if(o instanceof Obstacle){
			i = obstacles.indexOf(o);
			if(i != -1)
			obstacles.remove(i);
		}
		else if(o instanceof Tank){
			i = tanks.indexOf(o);
			if(i != -1)
			tanks.remove(i);
		}
		else{
			i = bullets.indexOf(o);
			if(i != -1)
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
		
		player.update(deltaTime);
		

	}
	
	

	public void setUpMap(){
		
		Obstacle ob;
		Tank et;
		Random rf = new Random(System.nanoTime());
		// obstacles
		for(int i = 0; i < 30; i++){
			ob = new Obstacle();
			ob.getBase().getPhys().setPos(rf.nextFloat() * 20 - 10, .25f, rf.nextFloat() * 20 - 10);
			ob.getBase().getPhys().setDir(0, rf.nextFloat() * 180);
			addObject(ob);
		}
		// enemy tanks
		for(int i = 0; i < 5; i++){
			et = new Tank();
			et.setController(new EnemyTankController(et));
			et.getBase().getPhys().setPos(rf.nextFloat() * 20 - 10, -.16f, rf.nextFloat() * 20 - 10);
			et.getBase().getPhys().setDir(0,0);
			addObject(et);
		}
		// player
		player.getBase().getPhys().setPos(0, -.16f, 0);
		player.getBase().getPhys().setDir(0, 0);
		
	}
	
	public void reset(){
		Gamestate.instance = null;
	}
	
	private static Gamestate instance = null;
	
	public static Gamestate getInstance(){
		if (Gamestate.instance == null)
			Gamestate.instance = new Gamestate();
		
		return Gamestate.instance;
	}
	

}

