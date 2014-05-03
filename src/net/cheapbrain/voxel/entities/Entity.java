package net.cheapbrain.voxel.entities;

//le entità presenti nel gioco, compreso il giocatore

public class Entity {
	private int id; //univocal id
	public int x,y;
	private Rotation rotation;
	
	public Entity(int id){
		this.id=id;
		rotation=new Rotation();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rotation getRotation() {
		return rotation;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	public void setRotation(float x, float y) {
		this.rotation.x=x;
		this.rotation.y=y;
	}
	
}
