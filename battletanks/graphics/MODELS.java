package battletanks.graphics;

public class MODELS {
	public static Model TANKBASE, TANKTURRET, SQUARE, BULLET;
	public static Model EXPLOSION;

	public static void LoadModels(){
		TANKTURRET = new Model(".\\obj\\tankturret.obj", 90, 0, 6);
		TANKBASE = new Model(".\\obj\\tankbase.obj", 0, 0, 6);
		BULLET = new Model(".\\obj\\bullet.obj", 0, 0, 6);
	}

}
