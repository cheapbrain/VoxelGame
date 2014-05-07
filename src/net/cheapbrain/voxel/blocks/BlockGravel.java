package net.cheapbrain.voxel.blocks;

public class BlockGravel extends Block {

	public BlockGravel() {
		super(5, true);
		setElasticity(64);
		setResistance(16);
		setRoughness(256);
	}	
	
	@Override
	public void render(int x, int y, int z, int data, boolean[] neighbours) {
		render(x, y, z, new int[]{5}, neighbours);
	}
}
