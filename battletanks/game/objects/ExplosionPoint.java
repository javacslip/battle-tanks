package battletanks.game.objects;

import java.util.Random;

import javax.vecmath.Vector3f;

import battletanks.graphics.Model;

public class ExplosionPoint extends Part{
	int smalltime;
	float speed;
	Vector3f color;
	int count;

	public ExplosionPoint(Model m, float scalescale,float sizescale, float velscale, float dur) {
		super(m);
		Random r = new Random();
		
		Vector3f pos = new Vector3f((float)r.nextFloat() * sizescale - sizescale /2f, (float)r.nextFloat()* sizescale - sizescale /2f, (float)r.nextFloat()* sizescale - sizescale /2f);
		Vector3f vel = new Vector3f((float)r.nextFloat()*velscale-velscale/2f, (float)r.nextFloat()*velscale-velscale/2f, (float)r.nextFloat() * velscale-velscale/2f);
		
		this.getPhys().setPos(pos);
		this.getPhys().setVel(vel);
		this.getPhys().setMaxrotvel(40f);
		this.getPhys().setRotdragconst(0.0f);
		this.getPhys().setDirSpeed(r.nextFloat()*20f, r.nextFloat()*20f);
		
		float size = (float)r.nextFloat() * scalescale+0.2f*sizescale;
		this.getPhys().setDragconst((r.nextFloat() * .01f + .002f) + size*.001f);
		this.setScale(size);
		
		smalltime = (int) (r.nextFloat()*dur * 5);
		speed = (r.nextFloat()*0.005f+0.003f) * (1f/dur);
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
