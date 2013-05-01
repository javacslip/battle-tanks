package battletanks.game;

public class GameInput {
	
	private enum INPUT_TYPE;
	
	public void setInputType(INPUT_TYPE t){
		this.INPUT_TYPE = t;
	}

	public INPUT_TYPE getInputType(){
		return this.INPUT_TYPE;
		
	}
}

