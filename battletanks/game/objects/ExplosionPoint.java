package battletanks.game.objects;

import java.util.Random;

import javax.vecmath.Vector3f;

import battletanks.graphics.Model;

public class ExplosionPoint extends Part{
	int smalltime;
	float speed;
	Vector3f color;
	int count;

	public ExplosionPoint(Model m) {
		super(m);
		Random r = new Random();
		
		Vector3f pos = new Vector3f((float)r.nextFloat()*.4f-.2f, (float)r.nextFloat()*.4f-.2f, (float)r.nextFloat()*.4f-.2f);
		Vector3f vel = new Vector3f((float)r.nextFloat()*.3f-.15f, (float)r.nextFloat()*.3f-.15f, (float)r.nextFloat()*.3f-.15f);
		
		this.getPhys().setPos(pos);
		this.getPhys().setVel(vel);
		this.getPhys().setMaxrotvel(40f);
		this.getPhys().setRotdragconst(0.0f);
		this.getPhys().setDirSpeed(r.nextFloat()*20f, r.nextFloat()*20f);
		this.setScale((float)r.nextFloat() * 0.2f+0.02f);
		
		smalltime = (int) r.nextFloat()*50;
		speed = r.nextFloat()*0.004f+0.001f;
		color = new Vector3f(0,r.nextFloat()*.9f + .1f,r.nextFloat()*.5f + .5f);
		count = 0;
	}
	
	public void update(long dtime){
		count++;
		super.update(dtime);
		if(this.smalltime > count){
			this.setScale(this.getScale() + speed);
		}
		else{
			this.setScale(this.getScale() - speed);
			if(this.getScale() <= 0){
				this.setScale(0.0f);
			}
		}
		
	}
	
	
	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}



}
