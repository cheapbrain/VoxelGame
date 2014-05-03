package net.cheapbrain.voxel.entities;

public class Chicken extends Animal{
	boolean canEgg;
	int timeBeforeEgg;
	
	public Chicken() {
		super(0);
	}
	
	public void Egg(){
		drop("egg");
	}
	
}
