package battletanks.game.objects;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObjectImp implements GameObject {
	
	protected Part base;
	protected List<Part> parts;
	protected Controller control;
	
	public GameObjectImp(){
		parts = new ArrayList<Part>();
		control = new NoController();
	}
	
	@Override
	public  void update(long dtime){
		
		control.update(dtime);
		base.update(dtime);
	}

	@Override
	public List<Part> getParts() {
		return parts;
	}

	@Override
	public Controller getController() {

		return control;
	}

	@Override
	public void  setController(Controller c) {

		control = c;
	}

	@Override
	public Part getBase() {

		return base;
	}

}
