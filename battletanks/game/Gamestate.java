package battletanks.game;

import java.util.List;


public class Gamestate {
	
	private Gamestate(){
		
	}
	
	public void addObject(GameObject o){
		
	}
	
	public void removeObject(GameObject o){
		
	}
	
	public List<GameObject> getObstacles(GameObject o){
		return null;
		
	}
	
	public List<GameObject> getEnemies(GameObject o){
		return null;
		
	}
	
	public List<GameObject> getBullets(GameObject o){
		return null;
		
	}
	
	public CollisionResult doCollision(GameObject a, GameObject b){
		return null;
		
	}
	
	public void AddInput(GameInput o){
		
	}
	
	public void UpdateState(long dtime){
		
	}
	
	private static Gamestate instance = null;
	
	public static Gamestate getInstance(){
		if (Gamestate.instance == null)
			Gamestate.instance = new Gamestate();
		
		return Gamestate.instance;
	}
	

}
