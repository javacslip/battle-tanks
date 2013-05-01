package battletanks.game;

import java.util.List;


public class Gamestate {
	
	private List<GameObject> obstacles;
	private List<GameObject> enemies;
	private List<GameObject> bullets;
	
	private GameObject player;
	private List<GameInput> playerInput;
	
	private Gamestate(){
		obstacles = new List<GameObject>();
		enemies = new List<GameObject>();
		bullets = new List<GameObject>();
		player = new GameObject;
		playerInput = new List<GameInput>;
	}
	
	public void addObject(GameObject o){
		if(typeof(o) == Obstacle){
			obstacles.add(o);
		}
		else if(typeof(o) == EnemyTanks){
			enemies.add(o);
		}
		else{
			bullets.add(o);
		}
		
	}
	
	public void removeObject(GameObject o){
		if(typeof(o) == Obstacle){
			obstacles.remove(obstacles.indexOf(o));
		}
		else if(typeof(o) == EnemyTanks){
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
	
	public void UpdateState(long dtime){
		private enum INPUT;
		for(GameInput o : playerInput){
			INPUT = getInputType(o);
			switch(INPUT){
			case 1: INPUT = FORWARD_PRESSED;
			case 2: INPUT = BACKWARD_PRESSED;
			case 3: INPUT = LEFT_PRESSED;
			case 4: INPUT = RIGHT_PRESSED;
			case 5: INPUT = FIRE_PRESSED;
			case 6: INPUT = FORWARD_RELEASED;
			case 7: INPUT = BACKWARD_RELEASED;
			case 8: INPUT = LEFT_RELEASED;
			case 9: INPUT = RIGHT_RELEASED;
			case 10: INPUT = FIRE_RELEASED;
			}
		}
	}

	public void setUpMap(){
		// obstacles
		for(int i = 0; i < 20; i++){
			
		}
		// enemy tanks
		for(int i = 0; i < 5; i++){
			
		}
		// player
	}
	
	public void reset(){
		this.instance = null;
	}
	
	private static Gamestate instance = null;
	
	public static Gamestate getInstance(){
		if (Gamestate.instance == null)
			Gamestate.instance = new Gamestate();
		
		return Gamestate.instance;
	}
	

}

