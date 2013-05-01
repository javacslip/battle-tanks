package battletanks.game;

public class GameInput {
	
	private INPUT_TYPE input;
	
	public GameInput(INPUT_TYPE t){
		input = t;
	}
	
	public void setInputType(INPUT_TYPE t){
		this.input = t;
	}

	public INPUT_TYPE getInputType(){
		return this.input;
		
	}
}

