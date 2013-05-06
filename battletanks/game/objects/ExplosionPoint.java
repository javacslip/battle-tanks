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
		
		Vector3f pos = new Vector3f((float)r.nextFloat()-.5f, (float)r.nextFloat()-.5f, (float)r.nextFloat()-.5f);
		Vector3f vel = new Vector3f((float)r.nextFloat()*.5f-.25f, (float)r.nextFloat()*.5f-.25f, (float)r.nextFloat()*.5f-.25f);
		
		this.getPhys().setPos(pos);
		this.getPhys().setVel(vel);
		this.setScale((float)r.nextFloat() * 2f+0.5f);
		
		smalltime = (int) r.nextFloat()*50;
		speed = (int) r.nextFloat()*20;
		color = new Vector3f(r.nextFloat(),r.nextFloat(),r.nextFloat());
		count = 0;
	}
	
	public void update(long dtime){
		count++;
		super.update(dtime);
		if(this.smalltime < count){
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
