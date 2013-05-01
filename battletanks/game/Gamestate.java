package battletanks.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import battletanks.game.objects.EnemyTank;
import battletanks.game.objects.Obstacle;
import battletanks.game.objects.PlayerTank;


public class Gamestate {
	
	private List<GameObject> obstacles;
	private List<GameObject> enemies;
	private List<GameObject> bullets;
	
	private GameObject player;
	private List<GameInput> playerInput;
	
	private Gamestate(){
		obstacles = new ArrayList<GameObject>();
		enemies = new ArrayList<GameObject>();
		bullets = new ArrayList<GameObject>();
		player = new PlayerTank();
		playerInput = new ArrayList<GameInput>();
	}
	
	public void addObject(GameObject o){
		if(o instanceof Obstacle){
			obstacles.add(o);
		}
		else if(o instanceof EnemyTank){
			enemies.add(o);
		}
		else{
			bullets.add(o);
		}
		
	}
	
	public void removeObject(GameObject o){
		if(o instanceof Obstacle){
			obstacles.remove(obstacles.indexOf(o));
		}
		else if(o instanceof EnemyTank){
			enemies.remove(enemies.indexOf(o));
		}
		else{
			bullets.remove(bullets.indexOf(o));
		}
	}
	
	public GameObject getPlayer(){
		return player;
	}
	
	public List<GameObject> getObstacles(){
		return obstacles;
		
	}
	
	public List<GameObject> getEnemies(){
		return enemies;
		
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
	
	public void UpdateState(long dtime) {
		INPUT_TYPE input;
		for (GameInput o : playerInput) {
			input = o.getInputType();
			switch (input) {
			case FORWARD_PRESSED:
				break;
			case BACKWARD_PRESSED:
				break;
			case LEFT_PRESSED:
				break;
			case RIGHT_PRESSED:
				break;
			case FIRE_PRESSED:
				break;
			case FORWARD_RELEASED:
			case BACKWARD_RELEASED:
			case LEFT_RELEASED:
			case RIGHT_RELEASED:
			case FIRE_RELEASED:
				//player stop
				break;
			}
		}
	}

	public void setUpMap(){
		Obstacle ob;
		EnemyTank et;
		Random rf = new Random(System.nanoTime());
		// obstacles
		for(int i = 0; i < 10; i++){
			ob = new Obstacle();
			ob.setPos(rf.nextFloat() + i, rf.nextFloat() + i, rf.nextFloat() + i);
			ob.setDirection(rf.nextFloat() + i, rf.nextFloat() + i, rf.nextFloat() + i);
			addObject(ob);
		}
		// enemy tanks
		for(int i = 0; i < 2; i++){
			et = new EnemyTank();
			et.setPos(rf.nextFloat() + i, rf.nextFloat() + i, rf.nextFloat() + i);
			et.setDirection(rf.nextFloat() + i, rf.nextFloat() + i, rf.nextFloat() + i);
			addObject(et);
		}
		// player
		player.setPos(0, 0, 0);
		player.setDirection(0, 0, 0);
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

