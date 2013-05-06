package battletanks.game.objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import battletanks.game.Logger;
import battletanks.graphics.*;

public class Part{
	
	private Model model;
	private Set<Part> subparts;
	private Part rotJoined;
	private Part posJoined;
	private PhysObject part;
	
	private Vector3f centerrot;


	private float scale;
	private Vector3f pos;
	private Vector2f dir;
	
	public Part (Model m){
		model = m;
		subparts = new HashSet<Part>();
		rotJoined = null;
		posJoined = null;
		centerrot = new Vector3f(0,0,0);
		scale = 1.0f;
		part = new PhysObject();
	}
	
	public Vector3f getCenterrot() {
		return centerrot;
	}

	public void setCenterrot(Vector3f centerrot) {
		this.centerrot = centerrot;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public Vector3f getPos(){
		if(pos == null){
			pos = part.getPos();
		}
		return pos;
	}
	
	public Vector2f getDir(){
		if(dir == null){
			dir = part.getDir();
		}
		return dir;
	}
	
	public PhysObject getPhys(){
		return part;
	}
	
	public void update(long dtime){
		part.update(dtime);
		
		pos = part.getPos();
		if(posJoined != null){
			pos = new Vector3f(0,0,0);
			pos.add(part.getPos(), posJoined.getPos());
		}
		
		
		dir = part.getDir();
		if(rotJoined != null){
			dir = new Vector2f(0,0);

			dir.add(part.getDir(), rotJoined.getDir());

		}
		
		for(Part p : subparts){
			p.update(dtime);
		}
	}
	
	public  Model getModel(){
		return model;
	}
	
	public void setModel(Model m){
		model = m;
	}
	
	public  void joined(Part p){
		subparts.add(p);
	}
	
	public void unjoin(Part p){
		subparts.remove(p);
	}
	
	public  void rotJoin(Part p){
		p.joined(this);
		this.rotJoined = p;
	}
	
	public  void posJoin(Part p){
		p.joined(this);
		this.posJoined = p;
	}
	
	public  Iterator<Part> getSubParts(){
		return subparts.iterator();
	}

}
