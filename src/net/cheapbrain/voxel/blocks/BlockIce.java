package net.cheapbrain.voxel.blocks;


public class BlockIce extends Block{

	public BlockIce(){
		super(9, true); //passing id to super constructor
		setElasticity(2);
		setResistance(128);
		setRoughness(4);
	}
	
	@Override
	public void render(int x, int y, int z, int data, boolean[] neighbours) {
		render(x, y, z, new int[]{10}, neighbours);
	}
}
