package battletanks.game;

public class GameInput {
	
	private INPUT_TYPE input;
	private long value;
	
	public GameInput(INPUT_TYPE type){
		input = type;
		value = 0;
	}
	
	public GameInput(INPUT_TYPE type, long value){
		input = type;
		this.value = value;
	}
	
	public void setInputType(INPUT_TYPE t){
		this.input = t;
	}

	public INPUT_TYPE getInputType(){
		return this.input;
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}


}

