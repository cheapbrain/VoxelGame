package net.cheapbrain.voxel.entities;

public class Animal extends Mob{
	private float age;
	private boolean inLove,isSitting;
	private String owner;
	
	public Animal(int id) {
		super(id+100);
		setOwner(null);
	}

	public float getAge() {
		return age;
	}

	public void setAge(float age) {
		this.age = age;
	}

	public boolean isInLove() {
		return inLove;
	}

	public void setInLove(boolean inLove) {
		this.inLove = inLove;
	}

	public boolean isSitting() {
		return isSitting;
	}

	public void setSitting(boolean isSitting) {
		this.isSitting = isSitting;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
