package battletanks.graphics;

public class MODELS {
	public static Model TANKBASE, TANKTURRET, SQUARE, BULLET;

	public static void LoadModels(){
		TANKTURRET = new Model(".\\obj\\tankturret.obj");
		TANKBASE = new Model(".\\obj\\tankbase.obj");
		BULLET = new Model(".\\obj\\bullet.obj");
	}

}
