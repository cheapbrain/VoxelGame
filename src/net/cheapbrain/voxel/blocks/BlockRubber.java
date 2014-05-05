package net.cheapbrain.voxel.blocks;

public class BlockRubber extends Block{

	public BlockRubber(){
		super(6, true); //passing id to super constructor
		setElasticity(8);
		setResistance(128);
		setRoughness(512);
	}
	
	@Override
	public void render(int x, int y, int z, int data, boolean[] neighbours) {
		render(x, y, z, new int[]{6}, neighbours);
	}
}
