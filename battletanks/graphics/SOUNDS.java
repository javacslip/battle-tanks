package battletanks.graphics;

import java.io.*;
import javax.sound.sampled.*;

public enum SOUNDS{
	BULLET_HIT("sounds/Explosion74.wav"),
	FIRE_GUN("sounds/Explosion71.wav"),
	TANK_EXPLODE("sounds/Explosion79.wav");
	//MUSIC("sounds/Explosion79.wav");
	
	
	private Clip clip;
	
	private SOUNDS(String filename){
		
		 try {
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
	         clip = AudioSystem.getClip();
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
	
	public void play() {
		if (clip.isRunning())
			clip.stop(); // Stop the player if it is still running
		clip.setFramePosition(0); // rewind to the beginning
		clip.start(); // Start playing
	}
	
	
}